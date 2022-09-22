package com.till.BlackJack;

import java.util.ArrayList;

import static com.till.BlackJack.Karte.Wert.ASSKLEIN;

public class Spiel {

    //Spieler
    private ArrayList<Spieler> alleSpieler = new ArrayList<>();
    private Deck deck;

    //Spieler & Deck hinzufügen
    public Spiel(int anzahlSpieler, int anzahlDecks) throws Exception {
        for (int i = 0; i < anzahlSpieler + 1; i++) {
            this.alleSpieler.add(new Spieler(i));
            deck = new Deck(anzahlDecks);
        }
    }

    //Betting
    public void betting(ArrayList<Integer> einsaetze) {
        for (int i = 0; i < this.getAlleSpieler().size() - 1; i++) {
            Spieler spieler = alleSpieler.get(i);
            spieler.setRundenEinsatz(einsaetze.get(i));
            spieler.setKontostand(spieler.getKontostand() - einsaetze.get(i));
        }
    }

    public ArrayList<Spieler> getAlleSpieler() {
        return alleSpieler;
    }

    //Deal
    public void deal() {
        for (Spieler spieler : alleSpieler) {
            ArrayList<Karte> hand = this.deck.kartenGeben(2);
            spieler.setHand(hand);
            spieler.setSpieltNoch(true);
        }
    }

    public ArrayList<Integer> checkNatural() {
        ArrayList<Integer> spielerNummern = new ArrayList<>();
        for (Spieler spieler : alleSpieler
        ) {
            if (spieler.getHandWert() == 21) {
                spielerNummern.add(spieler.getSpielernummer());
            }
        }
        return spielerNummern;
    }

    public boolean natural() {
        boolean dealerNatural = false;
        Spieler dealer = this.alleSpieler.get(this.alleSpieler.size() - 1);
        ArrayList<Integer> naturals = this.checkNatural();
        if (naturals.size() == 0) {
            return dealerNatural;
        }
        if (naturals.contains(dealer.getSpielernummer())) {
            dealerNatural = true;
            for (Spieler spieler : this.alleSpieler
            ) {
                spieler.setSpieltNoch(false);
            }
        }
        for (Spieler spieler : this.alleSpieler
        ) {
            if (naturals.contains(spieler.getSpielernummer())) {
                if (dealerNatural) {
                    spieler.setKontostand(spieler.getKontostand() + spieler.getRundenEinsatz());
                } else {
                    spieler.setKontostand(spieler.getKontostand() + 2 * spieler.getRundenEinsatz());
                }
            }
            spieler.setRundenEinsatz(0);
            spieler.setSpieltNoch(false);

        }
        return dealerNatural;
    }

    //ThePlay
    public boolean thePlay(boolean standOrHit, Spieler spieler) {
        if (spieler.isSpieltNoch()) {
            if (standOrHit) {
                return false;
            } else {
                Karte gezogeneKarte = this.deck.kartenGeben(1).get(0);
                spieler.getHand().add(gezogeneKarte);
            }
            if (spieler.getHandWert() > 21) {
                for (Karte karte : spieler.getHand()
                ) {
                    if (karte.getKartenWert() == 11 && spieler.getHandWert() - 10 < 22) {
                        karte.setWert(ASSKLEIN);
                        return true;
                    }
                }
                spieler.setSpieltNoch(false);
                return false;
            }
            return true;
        }
        return false;
    }

    public void dealersPlay() {
        Spieler dealer = this.getAlleSpieler().get(this.alleSpieler.size() -1);
        while (dealer.getHandWert() < 18) {
            Karte gezogeneKarte = this.deck.kartenGeben(1).get(0);
            dealer.getHand().add(gezogeneKarte);
            if (dealer.getHandWert() > 21) {
                for (Karte karte : dealer.getHand()
                ) {
                    if (karte.getKartenWert() == 11 && dealer.getHandWert() - 10 < 22) {
                        karte.setWert(ASSKLEIN);
                    }
                }
                dealer.setSpieltNoch(false);
            }
        }
        if (!(dealer.isSpieltNoch())) {
            for (Spieler spieler : this.alleSpieler
            ) {
                if (spieler.isSpieltNoch()) {
                    spieler.setKontostand(spieler.getKontostand() + spieler.getRundenEinsatz() * 2);
                    spieler.setSpieltNoch(false);
                    spieler.setRundenEinsatz(0);
                }
            }
        } else {
            for (Spieler spieler : this.alleSpieler
            ) {
                if (spieler.getHandWert() > dealer.getHandWert() && spieler.isSpieltNoch()) {
                    spieler.setKontostand(spieler.getKontostand() + spieler.getRundenEinsatz() * 2);
                    spieler.setSpieltNoch(false);
                    spieler.setRundenEinsatz(0);
                } else if (spieler.getHandWert() == dealer.getHandWert() && spieler.isSpieltNoch()) {
                    spieler.setKontostand(spieler.getKontostand() + spieler.getRundenEinsatz());
                    spieler.setSpieltNoch(false);
                    spieler.setRundenEinsatz(0);
                } else {
                    spieler.setSpieltNoch(false);
                    spieler.setRundenEinsatz(0);
                }

            }
        }
    }

    public Deck getDeck() {
        return deck;
    }

}
