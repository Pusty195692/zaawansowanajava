package com.zjava.config;

import com.zjava.exception.EmailNotUniqueException;
import com.zjava.model.Role;
import com.zjava.model.User;
import com.zjava.service.RoleService;
import com.zjava.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

/**
 * Created by Rafal Lebioda on 15.06.2017.
 */
@Component
@Slf4j
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${auth.login:admin@example.com}")
    private String login;

    @Value("${auth.password:admin}")
    private String password;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        log.info("onApplicationEvent method invoked");

        initializeRoleIfNotExists(Role.Name.ADMIN);
        initializeRoleIfNotExists(Role.Name.USER);

        if (userService.findUserByEmail(login) == null) {
            initializeAdminUser();
        }
    }

    private void initializeAdminUser() {
        log.info("initializeAdminUser method invoked");
        Optional<Role> role = roleService.findRoleByName(Role.Name.ADMIN);
        if (!role.isPresent()) {
            log.warn("ApplicationStartup.initializeAdminUser() - Initializing admin failed!!");
            return;
        }

        User user = new User("admin", "admin", login, passwordEncoder.encode(password), "+48923456783");
        user.setRoles(Collections.singleton(role.get()));
        try {
            userService.addUser(user);
        } catch (EmailNotUniqueException e) {
            log.error("Email {} is not unique", user.getEmail());
        }
    }

    private void initializeRoleIfNotExists(final String roleName) {
        log.info("initializeRole method invoked for role " + roleName);

        Optional<Role> role = roleService.findRoleByName(roleName);
        if (!role.isPresent()) {
            roleService.addRole(new Role(roleName));
        }
    }
}
