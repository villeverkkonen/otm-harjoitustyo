```
2.5.2018 - Suoritettava jar-tiedosto ei toimi tällä hetkellä tietokannan kanssa.
NetBeansissa sovellus toimii moitteettomasti.
```

# Miinaharava

Kurssin harjoitustyönä tehtävä Miinaharava-sovellus.

## Dokumentaatio
[Vaatimusmaarittely](dokumentaatio/vaatimusmaarittely.md)
<br />
[Käyttöohje](dokumentaatio/kayttoohje.md)
<br />
[Arkkitehtuurikuvaus](dokumentaatio/arkkitehtuuri.md)
<br />
[Työaikakirjanpito](dokumentaatio/tyoaikakirjanpito.md)
<br />

## Komentorivitoiminnot

### Ohjelman suorittaminen

Komento
```
mvn compile exec:java -Dexec.mainClass=minesweeper.gui.Minesweeper
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