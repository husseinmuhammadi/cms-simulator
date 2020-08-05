package com.tasnim.trade.eshop.web.service;

import com.tasnim.trade.eshop.api.ProductCategoryService;
import com.tasnim.trade.eshop.dto.ProductCategory;
import com.tasnim.trade.eshop.dto.Response;
import com.tasnim.trade.eshop.dto.SuccessfulResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/api/v1/productCategories")
@RestController
public class ProductCategoryRestfulService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCategoryRestfulService.class);

    private final ProductCategoryService service;

    public ProductCategoryRestfulService(ProductCategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Response> index() {
        return new ResponseEntity<>(new SuccessfulResponse<>(service.findRoot()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductCategory> save(@RequestBody ProductCategory productCategory) {
        LOGGER.info("Add new product category");

        return ResponseEntity.ok(productCategory);
        // return ResponseEntity.ok(new SuccessfulResponse<>(service.save(productCategory)));
    }
}
