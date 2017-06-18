package com.zjava.controller;

import com.zjava.exception.EmailNotUniqueException;
import com.zjava.model.User;
import com.zjava.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Created by Rafal Lebioda on 18.06.2017.
 */
@Log4j2
@Controller
public class AccountController {

    private static final String ACCOUNT_VIEW = "account";

    @Autowired
    private UserService userService;

    @GetMapping("/user/account")
    public String showLoggedUserAccount(Model model, @AuthenticationPrincipal User user) {
        if (user != null) {
            model.addAttribute("user", user);
            return ACCOUNT_VIEW;
        }
        log.info(" There is no user logged in");
        return "redirect:/";
    }


    @PostMapping("/user/account/update")
    public String updateAccount(@Valid User user, BindingResult bindingResult, Model model, @AuthenticationPrincipal User currentUser) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("errors", bindingResult.getFieldErrors());
            log.info("Validation user account error !!!");
            return ACCOUNT_VIEW;
        }

        try {
            userService.updateUser(user);
        } catch (EmailNotUniqueException  e) {
            log.warn("Error during user update", e);

            model.addAttribute("user", user);
            model.addAttribute("error", new ObjectError("emailError", "Użytkownik z takim adresem email już istnieje."));
            return ACCOUNT_VIEW;
        }

        if (currentUser != null) {
            return "redirect:/user/account";
        }

        log.info(" There is no user logged in");
        return "redirect:/";
    }

}
