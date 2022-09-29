package com.till.BlackJack;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class SpielController {
    @Setter
    @Getter
    @NoArgsConstructor
    public class Angaben {
        private int spielerAnzahl;
        private int decksAnzahl;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    public class Einsatz {
        private ArrayList<Integer> einsaetze = new ArrayList<>();
    }

    Spiel spiel;

    public SpielController() throws Exception {
    }

    @RequestMapping(value = "/spielStarten")
    public String spielStarten(Model model) {
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
        ArrayList<Spieler> spieler = spiel.getMitSpieler();
        model.addAttribute("spieler", spieler);
        Einsatz einsatz = new Einsatz();
        model.addAttribute("einsatz", einsatz);
        return "betting.html";
    }

    @GetMapping(value = "/einsaetze")
    public String einsaetze(Model model, Einsatz einsatz) throws Exception {
        model.addAttribute("einsaetze", einsatz);
        spiel.betting(einsatz.einsaetze);
        return "redirect:/deal";
    }

    @RequestMapping(value = "/deal")
    public String deal(Model model) {
        spiel.deal();
        boolean natural = spiel.natural();
        ArrayList<Spieler> mitSpieler = spiel.getMitSpieler();
        Spieler dealer = spiel.getAlleSpieler().get(spiel.getAlleSpieler().size() - 1);
        model.addAttribute("dealer", dealer);
        model.addAttribute("mitSpieler", mitSpieler);
        model.addAttribute("spiel", spiel);
        model.addAttribute("natural", natural);
        System.out.println(dealer.getHand().get(0).getKartenWert());
        return "deal";
    }

    @RequestMapping(value = "/thePlay")
    public String thePlay(Model model, @RequestParam(required = false) Boolean standOrHit) {
        Spieler spieler = spiel.getErstenMitspieler();
        if (spieler.getSpielernummer() == spiel.getAlleSpieler().size() - 1) {
            return "redirect:/dealersPlay";
        }
        model.addAttribute("spieler", spieler);
        model.addAttribute("spiel", spiel);

        if (!(standOrHit == null)) {
            spiel.thePlay(standOrHit, spieler);
        }
        return "thePlay";
    }

    @RequestMapping(value = "/dealersPlay")
    public String dealersPlay(Model model) {
        spiel.dealersPlay();
        model.addAttribute("mitspieler", spiel.getMitSpieler());
        model.addAttribute("dealer", spiel.getAlleSpieler().get(spiel.getAlleSpieler().size() - 1));
        return "dealersPlay";
    }


}
