package com.till.BlackJack;

import java.util.ArrayList;

public class Spieler {

    //Kontostand
    private int kontostand = 1000;
    //Runden Einsatz
    private int rundenEinsatz;
    //Hand
    private ArrayList<Karte> hand = new ArrayList<>();
    //Stand(0) or hit(1)

    private int handWert = 0;
    private boolean stand;
    //Spielernummer
    private int spielernummer;

    //Spielt noch
    private boolean spieltNoch = true;

    public Spieler(int spielernummer) {
        this.spielernummer = spielernummer;
    }

    public void updateHandWert() {
        this.handWert = 0;
        for (Karte karte : hand
        ) {
            this.handWert += karte.getKartenWert();
        }
    }

    public int getKontostand() {
        return kontostand;
    }

    public void setKontostand(int kontostand) {
        this.kontostand = kontostand;
    }

    public int getRundenEinsatz() {
        return rundenEinsatz;
    }

    public void setRundenEinsatz(int rundenEinsatz) {
        this.rundenEinsatz = rundenEinsatz;
    }

    public ArrayList<Karte> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Karte> hand) {
        this.hand = hand;
    }

    public boolean isStand() {
        return stand;
    }

    public void setStand(boolean stand) {
        this.stand = stand;
    }

    public int getSpielernummer() {
        return spielernummer;
    }

    public void setSpielernummer(int spielernummer) {
        this.spielernummer = spielernummer;
    }

    public int getHandWert() {
        this.updateHandWert();
        return handWert;
    }

    public boolean isSpieltNoch() {
        return spieltNoch;
    }

    public void setSpieltNoch(boolean spieltNoch) {
        this.spieltNoch = spieltNoch;
    }

    //nur zum Testen

}
