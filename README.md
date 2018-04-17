# Miinaharava

Kurssin harjoitustyönä tehtävä Miinaharava-sovellus.

## Dokumentaatio
[Vaatimusmaarittely](dokumentaatio/vaatimusmaarittely.md)
<br />
[Työaikakirjanpito](dokumentaatio/tyoaikakirjanpito.md)
<br />

## Komentorivitoiminnot

### Ohjelman suorittaminen

Komento
```
mvn compile exec:java -Dexec.mainClass=minesweeper.Minesweeper
```
avaa ohjelman

### Testaus

Testit suoritetaan komennolla
```
mvn test
```
Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```
Kattavuusraporttia voi tarkastella avaamalla selaimella tiedoston *target/site/jacoco/index.html*

### Suoritettavan jarin generointi

Komento
```
mvn package
```
generoi hakemistoon *target* suoritettavan jar-tiedoston *Minesweeper-1.0-SNAPSHOT.jar*

### Checkstyle

Komennolla
```
mvn jxr:jxr checkstyle:checkstyle
```
luodaan selaimella avattava tiedosto *target/site/checkstyle.html*