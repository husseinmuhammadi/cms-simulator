package com.tasnim.trade.eshop.web.service;

import com.tasnim.trade.eshop.api.UserService;
import com.tasnim.trade.eshop.dto.Response;
import com.tasnim.trade.eshop.dto.SuccessfulResponse;
import com.tasnim.trade.eshop.dto.User;
import com.tasnim.trade.eshop.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
public class UserRestfulService {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserRestfulService.class);

    @Autowired
    UserService service;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody User userDto) {
        LOGGER.info("Registering user ...");
        LOGGER.info(JsonUtil.jsonObject(userDto));
        User user = service.save(userDto);
        return ResponseEntity.ok(new SuccessfulResponse<>(user));
    }
}
