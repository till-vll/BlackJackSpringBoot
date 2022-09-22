package com.till.BlackJack;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KarteZiehenController {

    @RequestMapping(value = "/karten")
    public String ziehKarte(Model model) {
        System.out.println("Halloooo");

        String string = "String";
        model.addAttribute("nichtString", string);

        return "index.html";
    }
}
