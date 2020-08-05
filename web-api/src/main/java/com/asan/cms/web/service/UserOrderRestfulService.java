package com.tasnim.trade.eshop.web.service;


import com.tasnim.trade.eshop.api.ProductService;
import com.tasnim.trade.eshop.dto.Product;
import com.tasnim.trade.eshop.dto.Response;
import com.tasnim.trade.eshop.dto.SuccessfulResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/api/v1/user/orders")
@RestController
public class UserOrderRestfulService {


    public static final Logger LOGGER = LoggerFactory.getLogger(UserOrderRestfulService.class);


    @Autowired
    ProductService productService;

    @GetMapping("/{userId}")
    public ResponseEntity<Response> getUserOrders(@PathVariable String userId) {
        LOGGER.info("Getting orders for user {}", userId);
        // Get user from security context
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(new SuccessfulResponse<>(products), HttpStatus.OK);
    }
}
