package com.asan.cms.web.validator;

import com.asan.cms.configuration.security.PasswordComplexityConfiguration;
import com.asan.cms.configuration.security.UsernameComplexityConfiguration;
import com.asan.cms.api.UserService;
import com.asan.cms.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
@PropertySource("classpath:security.properties")
public class UserValidator implements Validator {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserValidator.class);

    @Autowired
    private UserService userService;

    @Autowired
    PasswordComplexityConfiguration passwordComplexityConfiguration;

    @Autowired
    UsernameComplexityConfiguration usernameComplexityConfiguration;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.could.not.be.empty");

        if (user.getUsername().length() < usernameComplexityConfiguration.getMinLength()
                || user.getUsername().length() > usernameComplexityConfiguration.getMaxLength()) {
            errors.rejectValue("username", "username.must.be.between.min.and.max.characters",
                    new Object[]{usernameComplexityConfiguration.getMinLength(), usernameComplexityConfiguration.getMaxLength()}, null);
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "username.already.exists");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.could.not.be.empty");

        if (user.getPassword().length() < passwordComplexityConfiguration.getMinLength()
                || user.getPassword().length() > passwordComplexityConfiguration.getMaxLength()) {
            errors.rejectValue("password", "password.should.be.at.least.min.characters.long",
                    new Object[]{passwordComplexityConfiguration.getMinLength()}, null);
        }

        if (!Objects.equals(user.getPasswordConfirm(), user.getPassword())) {
            errors.rejectValue("passwordConfirm", "password.does.not.math.confirm.password");
        }
    }
}