package com.zjava.controller;

import org.springframework.stereotype.Controller;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Adrian on 2017-06-02.
 */

@Controller
@Log4j2
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "redirect:/admin";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("error", true);
        return "login";
    }
}
