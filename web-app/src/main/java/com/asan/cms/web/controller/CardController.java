package com.asan.cms.web.controller;

import com.asan.cms.dto.Card;
import com.asan.cms.dto.CardStatusInquiryResponse;
import com.asan.cms.rpc.CardGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/card")
public class CardController {
    public static final Logger LOGGER = LoggerFactory.getLogger(CardController.class);

    @Autowired
    CardGrpc cardGrpc;

    @GetMapping("/issue")
    public String entry(Model model) {
        model.addAttribute("card", new Card());
        return "card/card-issue";
    }

    @PostMapping("/issue")
    public String issue(Model model, Card card) {
        int status = cardGrpc.registerCard(card.getMobileNo(), card.getGroup());
        LOGGER.info("status: {}", status);
        card.setStatus(status);
        model.addAttribute("card", card);
        return "card/card-issue";
    }

    @GetMapping("/info")
    public String entry1(Model model) {
        model.addAttribute("card", new Card());
        return "card/card-status-inquiry";
    }

    @PostMapping("/info")
    public String info(Model model, Card card) {
        CardStatusInquiryResponse response = cardGrpc.inquiryStatus(card.getMobileNo(), card.getGroup());
        model.addAttribute("response", response);
        return "fragments/card-status-inquiry :: card-status-inquiry";
    }
}
