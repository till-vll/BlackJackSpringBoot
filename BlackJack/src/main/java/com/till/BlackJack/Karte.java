package com.till.BlackJack;

public class Karte {

    //Die möglichen Farben einer Karte
    public enum Farbe {
        KREUZ, PIK, HERZ, KARO
    }

    //Die möglichen Werte einer Karte
    public enum Wert {
        ZWEI, DREI, VIER, FUENF, SECHS, SIEBEN, ACHT, NEUN, ZEHN, BUBE, DAME, KOENIG, ASS, ASSKLEIN
    }

    private Wert wert;

    public Wert getWert() {
        return wert;
    }

    private Farbe farbe;

    public Farbe getFarbe() {
        return farbe;
    }

    //Constructor fuer eine Karte. Farbe und Wert wird jeweils durch einen int zugewiesen (0-3 und 0-13)
    public Karte(int farbe, int wert) throws Exception {

        if (!(farbe > -1 && farbe < 4) || !(wert > -1 && wert < 14)) {
            throw new Exception("Ungültige Kartenfarbe, oder Wert");
        }

        this.farbe = Farbe.values()[farbe];
        this.wert = Wert.values()[wert];
    }

    public int getKartenWert() {
        int wert = 0;
        switch (this.wert) {
            case ZEHN:
            case DAME:
            case BUBE:
            case KOENIG:
                wert = 10;
                break;
            case ASS:
                wert = 11;
                break;
            case ACHT:
                wert = 8;
                break;
            case DREI:
                wert = 3;
                break;
            case NEUN:
                wert = 9;
                break;
            case VIER:
                wert = 4;
                break;
            case ZWEI:
                wert = 2;
                break;
            case FUENF:
                wert = 5;
                break;
            case SECHS:
                wert = 6;
                break;
            case SIEBEN:
                wert = 7;
                break;
            case ASSKLEIN:
                wert = 1;
                break;
        }
        return wert;
    }

    public void setWert(Wert wert) {
        this.wert = wert;
    }
}
