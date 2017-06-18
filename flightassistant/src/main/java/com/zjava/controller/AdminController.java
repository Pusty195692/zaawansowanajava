package com.zjava.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Rafal Lebioda on 13.06.2017.
 */
@Log4j2
@Controller
public class AdminController {

    @RequestMapping(value = "/admin/home")
    public ModelAndView admin() {
        return new ModelAndView("adminHome", "showBackLink", false);
    }

    @RequestMapping(value = "/admin/updateDatabase")
    public ModelAndView updateDb() {
        return new ModelAndView("updateDatabase", "showBackLink", false);
    }
}
