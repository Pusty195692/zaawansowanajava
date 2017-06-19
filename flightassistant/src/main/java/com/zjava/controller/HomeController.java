package com.zjava.controller;

import com.zjava.model.Role;
import com.zjava.model.User;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * Created by Adrian on 2017-06-02.
 */

@Controller
@Log4j2
public class HomeController {

    @RequestMapping("/")
    public String home(@AuthenticationPrincipal User user) {
        String role;
        if(user==null || !user.getIsActive()){
            return "redirect:/login-error";
        }
        else{
            if(user.getRoles().stream().filter(r -> StringUtils.equals(Role.Name.ADMIN, r.getAuthority())).findFirst().isPresent()) {
                log.info("Admin user logged in");
                return "redirect:/admin/home";
            }
            else if(user.getRoles().stream().filter(r -> StringUtils.equals(Role.Name.USER, r.getAuthority())).findFirst().isPresent()){
                    log.info("Common user logged in");
                    return "redirect:/user/home";
            }
            else{
                log.info("Current user hasn't got any roles");
                return "redirect:/login";
            }
        }
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
