package com.zjava;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Adrian on 2017-06-02.
 */

@Controller
public class HomeController {
    @GetMapping("/home")
    public String homeMethod() {
        return "home";
    }
}
