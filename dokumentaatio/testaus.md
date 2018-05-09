# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Automatisoidut testit testaavat sekä Service-luokkia että niiden käsittelemiä yksittäisiä User- ja Highscore-olioita.
Testit testaavat myös tietokantaa, joka toimii H2:lla ja ORMLitellä.

### Tastauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 86% ja haarautumakattavuus 67%
![testikattavuus](https://github.com/villeverkkonen/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/testikattavuus.png)

Testaamatta jäi lähinnä käyttöliittymään liittyviä asioita.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja konfigurointi

Sovellusta on testattu Windows-ympäristössä. Mitään vaadittavia etukäteistoimenpiteitä ei tarvita.