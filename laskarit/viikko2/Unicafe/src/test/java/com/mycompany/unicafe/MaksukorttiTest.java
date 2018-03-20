package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void kortilleVoiLataaRahaa() {
        kortti.lataaRahaa(15);
        assertEquals(25, kortti.saldo());
    }
    
    @Test
    public void kortiltaVoiOttaaRahaa() {
        kortti.otaRahaa(5);
        assertEquals(5, kortti.saldo());
    }
    
    @Test
    public void kortinSaldoEiVoiMennaNegatiiviseksi() {
        kortti.otaRahaa(15);
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void korttiPalauttaaTrueJosSaldoRiittaa() {
        assertTrue(kortti.otaRahaa(5));
        assertFalse(kortti.otaRahaa(15));
    }
    
    @Test
    public void kortinToStringMetodiPalauttaaOikean() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
}
