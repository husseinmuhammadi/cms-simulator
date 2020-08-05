package com.asan.cms.web.controller;

import com.asan.cms.api.UserService;
import com.asan.cms.dto.User;
import com.asan.cms.util.JsonUtil;
import com.asan.cms.web.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Locale;

@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    MessageSource messageSource;

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        LOGGER.info("Redirecting to login page ...");
        if (error != null) {
            LOGGER.warn("Your username and password is invalid.");
            model.addAttribute("error", "Your username and password is invalid.");
        }
        if (logout != null) {
            LOGGER.info("You have been logged out successfully.");
            model.addAttribute("message", "You have been logged out successfully.");
        }
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model, Principal principal) {
        if (principal != null) {
            LOGGER.info("User {} already logged in, redirecting to profile", principal.getName());
        }
        LOGGER.info("Redirecting to registration page ...");
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, BindingResult bindingResult) {
        LOGGER.info("Save user information");
        LOGGER.info("{}", JsonUtil.jsonObject(user));

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            LOGGER.warn("Binding result has errors!");
            bindingResult
                    .getFieldErrors()
                    .forEach(f -> LOGGER.info("{}: {}", f.getField(), messageSource.getMessage(f.getCode(), f.getArguments(), Locale.US)));
            return "registration";
        }

        userService.save(user);

        // securityService.autoLogin(user.getUsername(), user.getPasswordConfirm());

        LOGGER.info("Redirecting to index");
        return "profile";
    }

    @GetMapping("/profile")
    public String profile(Principal principal) {
        if (principal != null)
            LOGGER.info("Show the profile of {}", principal.getName());
        return "profile";
    }
}
