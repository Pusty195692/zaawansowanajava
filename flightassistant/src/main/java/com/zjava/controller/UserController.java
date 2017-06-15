package com.zjava.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Rafal Lebioda on 15.06.2017.
 */
@Log4j2
@Controller
public class UserController {

    @RequestMapping(value = "/user/home")
    public ModelAndView admin() {
        return new ModelAndView("userHome", "showBackLink", false);
    }
}
