# Käyttöohje

Lataa tiedosto minesweeper.jar

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla
```
java -jar minesweeper.jar
```

## Pelin aloittaminen

Sovellus käynnistyy aloitusikkunaan:
[!aloitusikkuna](dokumentaatio/kuvat/kuva-01.png)

## Pelin pelaaminen

Nimimerkin syöttämisen jälkeen aukeaa peli-ikkuna.
Ruudut aukeavat klikkaamalla. Jos avatussa ruudussa on pommi, peli loppuu.
Jos ruutu on tyhjä, se avaa kaikki muut tyhjät ruudut vierestä samalla.
Jos ruudun vieressä on pommi tai pommeja, se näyttää naapuriruutuje pommien määrän numerona.
[!peli-ikkuna](dokumentaatio/kuvat/kuva-02.png)

## Pelin loppu

Peli loppuu kun avaat pommiruudun.
Loppuikkanassa näkyy sinun pisteesi, jotka lasketaan kaikista avatuista ruuduista, paitsi pommiruudusta. Pisteidesi alla näytetään TOP-5 highscores.
Uusi peli -nappia klikkaamalla pääset taas aloitusikkunaan.
[!loppuikkuna](dokumentaatio/kuvat/kuva-03.png)