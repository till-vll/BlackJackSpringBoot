package com.till.BlackJack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KarteTest {
    @Test
    void testWert() throws Exception {
        Karte test = new Karte(1, 1);
        int wert = test.getKartenWert();
        assertEquals(3, wert);
    }
}
