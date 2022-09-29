package com.till.BlackJack;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String home(Model home) {
        String title = "Black Jack Web";
        home.addAttribute("title", title);
        return "home.html";
    }


}
