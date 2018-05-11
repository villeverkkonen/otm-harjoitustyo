# Miinaharava

Kurssin harjoitustyönä tehtävä Miinaharava-sovellus.

## Dokumentaatio
[Käyttöohje](dokumentaatio/kayttoohje.md)
<br />
[Vaatimusmaarittely](dokumentaatio/vaatimusmaarittely.md)
<br />
[Arkkitehtuurikuvaus](dokumentaatio/arkkitehtuuri.md)
<br />
[Testausdokumentti](dokumentaatio/testaus.md)
<br />
[Työaikakirjanpito](dokumentaatio/tyoaikakirjanpito.md)
<br />

## Komentorivitoiminnot

### Suoritettavan jarin generointi

Komento
```
mvn package
```
generoi hakemistoon *target* suoritettavan jar-tiedoston *Minesweeper-1.0-SNAPSHOT.jar*

### Ohjelman suorittaminen

Komento
```
mvn compile exec:java -Dexec.mainClass=minesweeper.gui.Minesweeper
```
avaa ohjelman.
<br /><br />
Jos olet luonut jar-tiedoston komennolla *mvn package*, voit suorittaa ohjelman *target*-kansiossa:
```
java -jar Minesweeper-1.0-SNAPSHOT.jar
```
Tai voit suoraan ladata suoritettevan [minesweeper.jar](https://github.com/villeverkkonen/otm-harjoitustyo/releases/tag/loppupalautus)-tiedoston.

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

### JavaDoc

JavaDoc generoidaan komennolla
```
mvn javadoc:javadoc
```
JavaDocia voi tarkastella avaamalla selaimella tiedosto target/site/apidocs/index.html

### Checkstyle

Komennolla
```
mvn jxr:jxr checkstyle:checkstyle
```
luodaan selaimella avattava tiedosto *target/site/checkstyle.html*