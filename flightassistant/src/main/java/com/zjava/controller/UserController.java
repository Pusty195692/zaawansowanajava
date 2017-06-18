package com.zjava.controller;

import com.zjava.controller.model.UserDTO;
import com.zjava.exception.EmailNotUniqueException;
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
import org.springframework.web.bind.annotation.RequestMapping;
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

        userService.addUser(newUser);
        emailService.sendEmail(userService.constructConfirmationEmail(newUser, userService.generateToken()));
        attributes.addFlashAttribute("accountCreated", "Wysłany został mail z potwierdzeniem.");
        return "redirect:/login";
    }

}
