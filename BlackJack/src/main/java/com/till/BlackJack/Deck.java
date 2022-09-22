package com.till.BlackJack;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Karte> spielbareKarten = new ArrayList<>();

    private ArrayList<Karte> gespielteKarten = new ArrayList<>();

    //Erstellt ein neues Deck (oder mehrere)
    public Deck(int anzahlDecks) throws Exception {
        for (int i = 0; i < anzahlDecks; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 13; k++) {
                    spielbareKarten.add(new Karte(j, k));
                }
            }
        }
    }

    public void spielbareKartenMischen() {
        Collections.shuffle(this.spielbareKarten);
    }

    public ArrayList<Karte> kartenGeben(int anzahl) {
        ArrayList<Karte> karten = new ArrayList<>();
        for (int i = 0; i < anzahl; i++) {
            karten.add(spielbareKarten.get(i));
            gespielteKarten.add(spielbareKarten.get(i));
            spielbareKarten.remove(i);
        }
        return karten;
    }

    public ArrayList<Karte> getGespielteKarten() {
        return gespielteKarten;
    }

    public ArrayList<Karte> getSpielbareKarten() {
        return spielbareKarten;
    }
}
