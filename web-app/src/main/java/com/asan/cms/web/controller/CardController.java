package com.asan.cms.web.controller;

import com.asan.cms.dto.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/card")
public class CardController {
    @GetMapping("/issue")
    public String register(Model model) {
        model.addAttribute("product", new Product());
        return "card/card-issue";
    }
}
