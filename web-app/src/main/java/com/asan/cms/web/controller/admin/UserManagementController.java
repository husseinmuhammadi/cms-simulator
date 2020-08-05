package com.asan.cms.web.controller.admin;

import com.asan.cms.api.UserService;
import com.asan.cms.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequestMapping("/admin/user/")
public class UserManagementController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementController.class);

    @Autowired
    private UserService service;

    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam("page") Optional<Integer> page,
                       @RequestParam("size") Optional<Integer> size) {
        LOGGER.info("Show all products");

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<User> userPage = service.findAll(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("userPage", userPage);
        int totalPages = userPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "admin/user/index";
    }

    @GetMapping("/entry")
    public String entry(Model model) {
        model.addAttribute("user", new User());
        return "admin/user/insert";
    }

    @PostMapping("/save")
    public String save(User user) {
        try {
            LOGGER.info("Saving user");
            User user1 = service.save(user);
            LOGGER.info("User {} saved successfully!", user1.getUsername());
            return "redirect:/admin/user/list";
        } catch (Exception e) {
            LOGGER.error("Error during saving product", e);
            return null;
        }
    }

}
