
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate kassapaate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @Test
    public void konstruktoriLuoOikeanKassapaatteen() {
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullinenLounasKasvattaaRahamaaraaJaSyotyjaLounaitaJaAntaaVaihtorahat() {
        assertEquals(760, kassapaate.syoEdullisesti(1000));
        
        assertEquals(100240, kassapaate.kassassaRahaa());
        
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukasLounasKasvattaaRahamaaraaJaSyotyjaLounaitaJaAntaaVaihtorahat() {
        assertEquals(600, kassapaate.syoMaukkaasti(1000));
        
        assertEquals(100400, kassapaate.kassassaRahaa());
        
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void josRahatEivatRiitaEdulliseenLounaaseenEiTehdaMitaanJaPalautetaanRahat() {
        assertEquals(100, kassapaate.syoEdullisesti(100));
        
        assertEquals(100000, kassapaate.kassassaRahaa());
        
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void josRahatEivatRiitaMaukkaaseenLounaaseenEiTehdaMitaanJaPalautetaanRahat() {
        assertEquals(100, kassapaate.syoMaukkaasti(100));
        
        assertEquals(100000, kassapaate.kassassaRahaa());
        
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortillaVoiOstaaEdullisenLounaan() {
        assertEquals(true, kassapaate.syoEdullisesti(kortti));
        assertEquals(760, kortti.saldo());
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kortillaVoiOstaaMaukkaanLounaan() {
        assertEquals(true, kassapaate.syoMaukkaasti(kortti));
        assertEquals(600, kortti.saldo());
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void josKortillaEiTarpeeksiRahaaEdulliseenLounaaseenEiTehdaMitaan() {
        Maksukortti koyhaKortti = new Maksukortti(10);
        
        assertEquals(false, kassapaate.syoEdullisesti(koyhaKortti));
        assertEquals(10, koyhaKortti.saldo());
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaEiTarpeeksiRahaaMaukkaaseenLounaaseenEiTehdaMitaan() {
        Maksukortti koyhaKortti = new Maksukortti(10);
        
        assertEquals(false, kassapaate.syoMaukkaasti(koyhaKortti));
        assertEquals(10, koyhaKortti.saldo());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortillePositiivisestiRahaaLadattaessaKassanJaKortinSaldotKasvavat() {
        kassapaate.lataaRahaaKortille(kortti, 100);
        assertEquals(1100, kortti.saldo());
        assertEquals(100100, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kortilleNegatiivisestRahaaLadattaessaKassanJaKortinSaldotEivätKasva() {
        kassapaate.lataaRahaaKortille(kortti, -1);
        assertEquals(1000, kortti.saldo());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
}
