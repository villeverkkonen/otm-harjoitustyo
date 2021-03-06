# Käyttöohje

Lataa tiedosto [minesweeper.jar](https://github.com/villeverkkonen/otm-harjoitustyo/releases/tag/loppupalautus)

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla
```
java -jar minesweeper.jar
```

## Pelin aloittaminen

Sovellus käynnistyy aloitusikkunaan:
<br /><br />
![aloitusikkuna](https://github.com/villeverkkonen/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/kuva-01.png)

## Pelin pelaaminen

Nimimerkin syöttämisen jälkeen aukeaa peli-ikkuna.
Ruudut aukeavat klikkaamalla. Jos avatussa ruudussa on pommi, peli loppuu.
Jos ruutu on tyhjä, se avaa kaikki muut tyhjät ruudut vierestä samalla.
Jos ruudun vieressä on pommi tai pommeja, se näyttää naapuriruutujen pommien määrän numerona.
<br /><br />
![peli-ikkuna](https://github.com/villeverkkonen/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/kuva-02.png)

## Pelin loppu

Peli loppuu kun avaat pommiruudun.
Loppuikkanassa näkyy sinun pisteesi, jotka lasketaan kaikista avatuista ruuduista, paitsi pommiruudusta. Pisteidesi alla näytetään TOP-5 highscores.
Uusi peli -nappia klikkaamalla pääset taas aloitusikkunaan.
<br /><br />
![loppuikkuna](https://github.com/villeverkkonen/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/kuva-03.png)