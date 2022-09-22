package com.till.BlackJack;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class SpielController {
    @Setter
    @Getter
    @NoArgsConstructor
    class Angaben{
        private int spielerAnzahl;
        private int decksAnzahl;
    }
    Spiel spiel;

    public SpielController() throws Exception {
    }

    @RequestMapping(value = "/spielStarten")
    public String spielStarten(Model model){
        model.addAttribute("angaben", new Angaben());
       return "spielStarten.html";
    }


    @GetMapping(value = "/angaben")
    public String spielerAnzahl(Model model, Angaben angaben) throws Exception {
       model.addAttribute("angaben", angaben);
       spiel = new Spiel(angaben.spielerAnzahl, angaben.decksAnzahl);
       return "redirect:/betting";
    }

    @RequestMapping(value = "/betting")
    public String betting(Model model) throws Exception {
        ArrayList<String> spielerNamen = new ArrayList<>();
        for (int i = 0; i < spiel.getAlleSpieler().size() - 1; i++) {
            String spielerName = String.format("Spieler %d",i + 1);
            spielerNamen.add(spielerName);
        }
        model.addAttribute("spielerNamen",spielerNamen);
        System.out.println(spielerNamen.size());
        for (String spierler: spielerNamen
             ) {
        }
        return "betting.html";
    }






}
