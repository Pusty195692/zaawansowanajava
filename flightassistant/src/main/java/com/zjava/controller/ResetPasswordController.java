package com.zjava.controller;

import com.zjava.controller.model.UserEmail;
import com.zjava.model.User;
import com.zjava.service.EmailService;
import com.zjava.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.net.MalformedURLException;

/**
 * Created by Piotr on 16.06.2017.
 */
@Controller
@Log4j2
public class ResetPasswordController {

    private static final String FORGET_VIEW = "password/forget";
    private static final String CHANGE_VIEW = "password/change";
    private static final String REDIRECT_FORGET_VIEW = "redirect:/account/password/change";

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @GetMapping("account/password/change")
    public String getForgetPasswordView(@ModelAttribute UserEmail userEmail, Model model) {
        model.addAttribute("userEmail", userEmail);
        return FORGET_VIEW;
    }

    @PostMapping("account/password/change/reset/token/send")
    public String sendEmail(@Valid UserEmail userEmail, BindingResult bindingResult, Model model, RedirectAttributes attributes) throws MalformedURLException {

        if (bindingResult.hasErrors()) {
            model.addAttribute("userEmail", userEmail);
            model.addAttribute("errors", bindingResult.getFieldErrors());
            log.info("ResetPassword validation error." + bindingResult.getAllErrors());
            return FORGET_VIEW;
        }

        if (userEmail.getUserMail().isEmpty()) {
            model.addAttribute("error", new ObjectError("mailIsEmpty", "Proszę wpisać adres email"));
            return REDIRECT_FORGET_VIEW;
        }
        User user = userService.findUserByEmail(userEmail.getUserMail());

        if (user == null) {
            model.addAttribute("info", new ObjectError("sendOrNotSend", "Email został wysłany"));
            return REDIRECT_FORGET_VIEW;
        }

        String token = userService.generateToken();
        userService.createPasswordResetTokenForUser(user, token);
        emailService.sendEmail(userService.constructResetTokenEmail(user, token));
        attributes.addFlashAttribute("sendOrNotSend", "Mail do resetowania hasła został wysłany na podany adres e-mail");
        return "redirect:/login";
    }

}
