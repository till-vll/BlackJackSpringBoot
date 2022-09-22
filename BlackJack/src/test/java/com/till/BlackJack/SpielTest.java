package com.till.BlackJack;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.till.BlackJack.Karte.Wert.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpielTest {

    @Test
    void anzahlSpielerTest() throws Exception {
        Spiel testSpiel = new Spiel(2, 6);
        assertEquals(3, testSpiel.getAlleSpieler().size());
    }

    @Test
    void bettingTest() throws Exception {
        Spiel testSpiel = new Spiel(2, 6);
        ArrayList<Integer> einsaetze = new ArrayList<>();
        einsaetze.add(400);
        einsaetze.add(200);
        testSpiel.betting(einsaetze);
        Spieler spieler1 = testSpiel.getAlleSpieler().get(0);
        Spieler spieler2 = testSpiel.getAlleSpieler().get(1);
        assertEquals(600, spieler1.getKontostand());
        assertEquals(200, spieler2.getRundenEinsatz());
    }

    @Test
    void dealTest() throws Exception {
        Spiel testSpiel = new Spiel(3, 1);
        testSpiel.deal();
        Spieler spieler1 = testSpiel.getAlleSpieler().get(0);
        assertEquals(2, spieler1.getHand().size());
        assertEquals(44, testSpiel.getDeck().getSpielbareKarten().size());
        assertEquals(8, testSpiel.getDeck().getGespielteKarten().size());
    }

    @Test
    void checkNaturalDealerT() throws Exception {
        Spiel testSpiel = new Spiel(2, 1);
        ArrayList<Integer> einsaetze = new ArrayList<>();
        einsaetze.add(200);
        einsaetze.add(300);
        testSpiel.betting(einsaetze);
        Spieler dealer = testSpiel.getAlleSpieler().get(2);
        Spieler spieler1 = testSpiel.getAlleSpieler().get(0);
        Spieler spieler2 = testSpiel.getAlleSpieler().get(1);
        testSpiel.deal();
        spieler1.getHand().get(0).setWert(KOENIG);
        spieler1.getHand().get(1).setWert(ASS);
        dealer.getHand().get(0).setWert(KOENIG);
        dealer.getHand().get(1).setWert(ASS);
        spieler2.getHand().get(0).setWert(ZWEI);
        boolean dealerT = testSpiel.natural();
        assertTrue(dealerT);
        assertEquals(1000, spieler1.getKontostand());
        assertEquals(700, spieler2.getKontostand());
        assertEquals(0, spieler1.getRundenEinsatz());
    }

    @Test
    void checkNaturalDealerF() throws Exception {
        Spiel testSpiel = new Spiel(2, 1);
        ArrayList<Integer> einsaetze = new ArrayList<>();
        einsaetze.add(200);
        einsaetze.add(300);
        testSpiel.betting(einsaetze);
        Spieler dealer = testSpiel.getAlleSpieler().get(2);
        Spieler spieler1 = testSpiel.getAlleSpieler().get(0);
        Spieler spieler2 = testSpiel.getAlleSpieler().get(1);
        testSpiel.deal();
        dealer.getHand().get(0).setWert(ZWEI);
        spieler1.getHand().get(0).setWert(KOENIG);
        spieler1.getHand().get(1).setWert(ASS);
        spieler2.getHand().get(0).setWert(ZWEI);
        boolean dealerT = testSpiel.natural();
        assertFalse(dealerT);
        assertEquals(1200, spieler1.getKontostand());
        assertEquals(700, spieler2.getKontostand());
        assertEquals(0, spieler1.getRundenEinsatz());
    }

    @Test
    void checkThePlay() throws Exception {
        Spiel testspiel = new Spiel(1, 1);
        Spieler spieler1 = testspiel.getAlleSpieler().get(0);
        testspiel.getDeck().spielbareKartenMischen();
        while (spieler1.isSpieltNoch()) {
            testspiel.thePlay(false, spieler1);
        }
        assertFalse(spieler1.isSpieltNoch());
        assertTrue(spieler1.getHand().size() > 2);
    }

    @Test
    void checkThePlayAss() throws Exception {
        Spiel testspiel = new Spiel(1, 1);
        Spieler spieler1 = testspiel.getAlleSpieler().get(0);
        testspiel.getDeck().spielbareKartenMischen();
        testspiel.deal();
        spieler1.setStand(false);
        spieler1.getHand().get(0).setWert(ASS);
        while (spieler1.isSpieltNoch()) {
            testspiel.thePlay(spieler1.isStand(), spieler1);
        }
        assertFalse(spieler1.isSpieltNoch());
        assertTrue(spieler1.getHand().size() > 2);
        assertEquals(1, spieler1.getHand().get(0).getKartenWert());
    }

    @Test
    void checkDealersPlay() throws Exception {
        Spiel testspiel = new Spiel(3, 6);
        testspiel.getDeck().spielbareKartenMischen();
        ArrayList<Integer> einsaetze = new ArrayList<>();
        einsaetze.add(400);
        einsaetze.add(200);
        einsaetze.add(800);
        testspiel.betting(einsaetze);
        testspiel.deal();
        testspiel.getAlleSpieler().get(0).getHand().get(0).setWert(ASS);
        testspiel.getAlleSpieler().get(0).getHand().get(1).setWert(KOENIG);
        testspiel.getAlleSpieler().get(1).getHand().get(0).setWert(ZWEI);
        testspiel.getAlleSpieler().get(3).getHand().get(0).setWert(ASS);
        testspiel.getAlleSpieler().get(3).getHand().get(1).setWert(NEUN);
        testspiel.dealersPlay();
        boolean alleFalse = true;
        for (Spieler spieler : testspiel.getAlleSpieler()
        ) {
            if (spieler.isSpieltNoch()) {
                alleFalse = false;
                break;
            }
        }
        testspiel.deal();
        assertTrue(alleFalse);
        assertEquals(1400,testspiel.getAlleSpieler().get(0).getKontostand());
        assertEquals(800,testspiel.getAlleSpieler().get(1).getKontostand());
        assertEquals(0,testspiel.getAlleSpieler().get(0).getRundenEinsatz());
        assertEquals(0,testspiel.getAlleSpieler().get(1).getRundenEinsatz());
        assertEquals(0,testspiel.getAlleSpieler().get(2).getRundenEinsatz());
    }
}
