package com.asan.cms.web.controller;

import com.asan.cms.api.ProductService;
import com.asan.cms.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequestMapping("/product")
@Controller
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService service;

    @GetMapping("/list")
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {
        LOGGER.info("Show all products");

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Product> productPage = service.findAll(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("productPage", productPage);
        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream
                    .rangeClosed(1, totalPages)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "product/index";
    }

    @GetMapping("/entry")
    public String entry(Model model) {
        model.addAttribute("product", new Product());
        return "product/insert";
    }

    @PostMapping("/save")
    public String save(Product product) {
        try {
            LOGGER.info("Saving product");
            Product product1 = service.save(product);
            LOGGER.info("Product {} saved successfully!", product1.getId());
            return "redirect:/product/list";
        } catch (Exception e) {
            LOGGER.error("Error during saving product", e);
            return null;
        }
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id) {
        LOGGER.info("Remove entity id: {}", id);
        service.delete(id);
        return "redirect:/product/list";
    }

    @PostMapping("/remove")
    public String remove(@ModelAttribute(value = "selectedItem") Product product) {
        LOGGER.info("Remove entity entity-id: {}", product.getId());
        service.delete(product);
        return "redirect:/product/list";
    }
}
