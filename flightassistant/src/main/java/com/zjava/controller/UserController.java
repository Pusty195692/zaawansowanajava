package com.zjava.controller;

import com.zjava.controller.model.AccountActivation;
import com.zjava.controller.model.UserDTO;
import com.zjava.exception.EmailNotUniqueException;
import com.zjava.model.PasswordResetToken;
import com.zjava.model.User;
import com.zjava.service.EmailService;
import com.zjava.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by Rafal Lebioda on 15.06.2017.
 */
@Log4j2
@Controller
public class UserController {

    protected static final String REGISTER_VIEW = "register";
    protected static final String ACTIVATE_VIEW = "activateAccount";

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/user/home")
    public ModelAndView admin() {
        return new ModelAndView("userHome", "showBackLink", false);
    }

    @GetMapping("account/register")
    public String getRegisterView(@ModelAttribute UserDTO newUser, Model model) {
        model.addAttribute("newUser", newUser);
        return REGISTER_VIEW;
    }

    @PostMapping("account/register/mail/send")
    public String sendRegisterMail(@Valid UserDTO newUser, BindingResult bindingResult, Model model, RedirectAttributes attributes) throws EmailNotUniqueException {

        if (bindingResult.hasErrors()) {
            model.addAttribute("newUser", newUser);
            model.addAttribute("errors", bindingResult.getFieldErrors());
            log.info("New user vallidation error." + bindingResult.getAllErrors());
            return REGISTER_VIEW;
        }

        if (userService.findUserByEmail(newUser.getEmail()) != null) {
            model.addAttribute("newUser", newUser);
            model.addAttribute("error", new ObjectError("userExists", "Nazwa użytkowniak zajęta. Wybierz inną."));
            return REGISTER_VIEW;
        }

        String token = userService.generateToken();
        userService.addUser(newUser);
        userService.createPasswordResetTokenForUser(userService.findUserByEmail(newUser.getEmail()), token);
        emailService.sendEmail(userService.constructConfirmationEmail(newUser, token));
        attributes.addFlashAttribute("accountCreated", "Wysłany został mail z potwierdzeniem.");
        return "redirect:/login";
    }

    @GetMapping("account/register/activate/account/token/{token}")
    public String getActivateAccountView(@PathVariable("token") final String token, @ModelAttribute AccountActivation accountActivation, Model model) {
        accountActivation.setToken(token);
        model.addAttribute("accountActivation", accountActivation);
        return ACTIVATE_VIEW;
    }

    @PostMapping("account/register/activate")
    public String activateAccount(@Valid AccountActivation accountActivation, BindingResult bindingResult, Model model, RedirectAttributes attributes) throws EmailNotUniqueException {

        if (bindingResult.hasErrors()) {
            model.addAttribute("accountActivation", accountActivation);
            model.addAttribute("errors", bindingResult.getFieldErrors());
            log.info("token validation error." + bindingResult.getAllErrors());
            return ACTIVATE_VIEW;
        }

        User user = userService.findUserByToken(accountActivation.getToken());
        if (user == null) {
            log.info("user is null");
            attributes.addFlashAttribute("accountActivatedOrNotActivated", "Jeśli konto istnieje, zostało aktywowane.");
            return "redirect:/login";
        }
        user.setIsActive(true);
        userService.updateUser(user);
        log.info("user " + user.getEmail() + " account activated");
        attributes.addFlashAttribute("accountActivatedOrNotActivated", "Jeśli konto istnieje, zostało aktywowane.");
        return "redirect:/login";
    }

}
