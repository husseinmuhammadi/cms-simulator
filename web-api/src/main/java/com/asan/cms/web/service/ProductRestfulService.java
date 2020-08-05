package com.tasnim.trade.eshop.web.service;

import com.tasnim.trade.eshop.api.ProductCategoryService;
import com.tasnim.trade.eshop.api.ProductService;
import com.tasnim.trade.eshop.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequestMapping("/api/v1/products")
@RestController
public class ProductRestfulService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestfulService.class);

    private final ProductService service;

    @Autowired
    private ProductCategoryService productCategoryService;

    public ProductRestfulService(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Response> index(@RequestParam("productCategoryName") Optional<String> productCategoryName) {
        List<Product> products = Collections.emptyList();
        if (productCategoryName.isPresent()) {
            Optional<ProductCategory> productCategory = productCategoryService.findByName(productCategoryName.get());
            if (productCategory.isPresent()) {
                products = service.findAllByCategory(productCategory.get());
            }
        }
        ProductPackage productPackage = new ProductPackage(products);
        return new ResponseEntity<>(new SuccessfulResponse<>(productPackage), HttpStatus.OK);
    }

    @GetMapping("/top")
    public ResponseEntity<Response> getTopProducts() {
        List<Product> products = service.getTopProducts();
        return new ResponseEntity<>(new SuccessfulResponse<>(products), HttpStatus.OK);
    }

    @GetMapping("/festival")
    public ResponseEntity<Response> getFestivalProducts() {
        List<Product> products = service.getTopProducts();
        return new ResponseEntity<>(new SuccessfulResponse<>(products), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> save(@RequestBody Product product) {
        Product product1 = service.save(product);
        return ResponseEntity.ok(new SuccessfulResponse<>(product1));
    }

    @GetMapping("/{id}")
    public Optional<Product> find(@PathVariable() Long id) {
        LOGGER.info("Get product, Id: {}", id);
        return service.findById(id);
    }
}
