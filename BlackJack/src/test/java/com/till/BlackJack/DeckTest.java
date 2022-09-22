package com.till.BlackJack;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.till.BlackJack.Karte.Farbe.HERZ;
import static com.till.BlackJack.Karte.Wert.ZEHN;
import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    @Test
    void deckHatGenugKarten() throws Exception {
        int anzahlDecks = 2;
        Deck deck = new Deck(anzahlDecks);
        assertEquals(52 * anzahlDecks, deck.getSpielbareKarten().size());

    }

    @Test
    void anzahlBestimmterFarbe() throws Exception {
        int anzahlDecks = 2;
        List<Karte> farbe = new ArrayList<>();
        Deck deck = new Deck(anzahlDecks);
        deck.getSpielbareKarten().stream().filter(x -> x.getFarbe() == HERZ).forEach(farbe::add);
        assertEquals(13 * anzahlDecks, farbe.size());
    }

    @Test
    void anzahlBestimmterWert() throws Exception {
        int anzahlDecks = 2;
        List<Karte> wert = new ArrayList<>();
        Deck deck = new Deck(anzahlDecks);
        deck.getSpielbareKarten().stream().filter(x -> x.getWert() == ZEHN).forEach(wert::add);
        assertEquals(4 * anzahlDecks, wert.size());
    }

    @Test
    void decksSindImmerGleich() throws Exception {
        int anzahlDecks = 2;
        Deck deck = new Deck(anzahlDecks);
        Deck deck2 = new Deck(anzahlDecks);
        boolean check = true;
        for (int i = 0; i < 52; i++) {
            if (
                    !(deck.getSpielbareKarten().get(i).getFarbe() == deck2.getSpielbareKarten().get(i).getFarbe() && deck.getSpielbareKarten().get(i).getWert() == deck2.getSpielbareKarten().get(i).getWert())
            ) {
                check = false;
            }
        }
        assertTrue(check);

    }

    @Test
    void decksWerdenGemischt() throws Exception {
        int anzahlDecks = 2;
        Deck deck = new Deck(anzahlDecks);
        Deck deck2 = new Deck(anzahlDecks);
        deck.spielbareKartenMischen();
        boolean check = true;
        for (int i = 0; i < 52; i++) {
            if (
                    !(deck.getSpielbareKarten().get(i).getFarbe() == deck2.getSpielbareKarten().get(i).getFarbe() && deck.getSpielbareKarten().get(i).getWert() == deck2.getSpielbareKarten().get(i).getWert())
            ) {
                check = false;
            }
        }
        assertFalse(check);

    }

    @Test
    void kartenGebenTest() throws Exception {
        Deck deck = new Deck(1);
        ArrayList<Karte> gegebeneKarten = new ArrayList<>();
        int anzahlKarten = 10;
        boolean check = true;
        gegebeneKarten = deck.kartenGeben(anzahlKarten);
        for (int i = 0; i < anzahlKarten; i++) {
            if (
                    !(deck.getGespielteKarten().get(i).getFarbe() == gegebeneKarten.get(i).getFarbe() && deck.getGespielteKarten().get(i).getWert() == gegebeneKarten.get(i).getWert())
            ) {
                check = false;
            }
        }
        assertEquals(anzahlKarten, gegebeneKarten.size());
        assertEquals(52 - anzahlKarten, deck.getSpielbareKarten().size());
        assertTrue(check);
    }

    @Test
    void keinAssKlein() throws Exception {
        Deck testDeck = new Deck(3);
        boolean assKleinVorhanden = false;
        for (Karte karte : testDeck.getGespielteKarten()
        ) {
            if (karte.getKartenWert() == 1) {
                assKleinVorhanden = true;
            }
        }
        assertFalse(assKleinVorhanden);
    }
}
