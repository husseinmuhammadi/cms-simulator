package com.asan.cms.configuration;

import com.asan.cms.api.RoleService;
import com.asan.cms.api.UserService;
import com.asan.cms.dto.Role;
import com.asan.cms.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:administrative.properties")
public class SecurityInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger(SecurityInitializer.class);

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Value("${admin.password}")
    private String adminPassword;

    @EventListener
    public void addAdminUser(ApplicationReadyEvent event) {
        User admin = new User("admin");
        if (userService.exists(admin)) {
            LOGGER.info("User {} is already exists, it will keep untouched", admin.getUsername());
            return;
        }

        LOGGER.info("Instantiate admin user with password {}", adminPassword);
        Role role;

        role = getExistingRoleOtherwiseNewRole("ADMIN");
        admin.getRoles().add(role);

        role = getExistingRoleOtherwiseNewRole("USER");
        admin.getRoles().add(role);

        admin.setPassword(adminPassword);
        userService.save(admin);
    }

    /**
     * pickup role by given name
     * @param name
     * @return an existing role otherwise pickup a new one
     */
    private Role getExistingRoleOtherwiseNewRole(final String name) {
        Role role = roleService.findByName(name);
        if (role != null) {
            LOGGER.info("Role {} is exists, it will pickup by user", role.getName());
        } else {
            role = new Role(name);
            LOGGER.info("Role {} is not exists, it will be prepared", name);
        }
        return role;
    }
}
