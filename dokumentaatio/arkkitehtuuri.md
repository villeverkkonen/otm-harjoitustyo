# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin pakkausrakenne kuvataan seuraavassa luokkakaaviossa:
<br />
![luokkakaavio1](https://github.com/villeverkkonen/otm-harjoitustyo/dokumentaatio/kuvat/luokkakaavio-01.png)

## Käyttöliittymä

Käyttöliittymä sisältää korme erillistä näkymää
* Aloituruutu
* Peliruutu
* Lopetusruutu

jokainen näistä on toteutettu omana Scene-oliona. Näkymistä yksi kerrallaan on näkyvänä eli sihoitettuna sovelluksen stageen.
Käyttöliittymä on rakennettu ohjelmallisesti luokassa [minesweeper.gui.Minesweeper](https://github.com/villeverkkonen/otm-harjoitustyo/blob/master/Minesweeper/src/main/java/minesweeper/gui/Minesweeper.java).
<br />
Käyttöliittymä on pyritty eristämään sovelluslogiikasta mahdollisimman hyvin, jolloin Service-luokat huolehtivat sovelluslogiikasta.
<br />

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat luokat User ja Highscore, joiden lisäksi on Tile, joista ruudukko koostuu.

Toiminnallisista kokonaisuuksista vastaavat Service-luokat, eli UserService, HighscoreService sekä MinesweeperService.

Seuraava luokkakaavio osoittaa, kuinka UserService- ja User-luokat liittyvät toisiinsa:
<br />
![luokkakaavio2](https://github.com/villeverkkonen/otm-harjoitustyo/dokumentaatio/kuvat/luokkakaavio-02.png)

## Tietojen pysyväistallennus

Service-luokat hoitavat User- ja Highscore-olioiden tallennuksen tietokantaan ja sieltä hakemisen ja päivittämisen.

Tietokantana toimii H2 ja ORMLite. Tietokanta tyhjenee aina kun sovelluksen sammuttaa.

## Päätoiminnallisuudet

Kuvataan seuraavaksi sovelluksen toimintalogiikka muutaman päätoiminnallisuuden osalta sekvenssikaaviona.

### Pelin aloittaminen

Kun käyttäjä syöttää haluamansa nimimerkin ja painaa *Aloita peli* -nappia etenee sovelluksen kontrolliseuraavasti:

Painikkeen painamiseen reagoiva tapahtumankäsittelijä kutsuu [UserServicen](https://github.com/villeverkkonen/otm-harjoitustyo/blob/master/Minesweeper/src/main/java/minesweeper/service/UserService.java) metodia *createUser* antaen parametriksi kenttään syötetyn nimimerkin. Uudella käyttäjällä on oletuksena 0 pistettä. Jos tietokannasta löytyy käyttäjä jo kyseisellä nimimerkillä, päivitetään hänen pisteensä nollaksi.
<br />
Tämän jälkeen tekstikenttä tyhjennetään, ettei annettu nimimerkki ole valmiina siinä kun seuraava peli aloitetaan. Sitten aloitusruutu vaihdetaan peliruuduksi ja peli alkaa.

### Pelin loppuminen

Kun peli loppuu, eli pelaaja avaa pommiruudun, kutsutaan [MinesweeperServicen](https://github.com/villeverkkonen/otm-harjoitustyo/blob/master/Minesweeper/src/main/java/minesweeper/service/MinesweeperService.java) metodia *countOpenTiles*, joka laskee avatut ruudut eli pisteet ja asettaa [UserServicen](https://github.com/villeverkkonen/otm-harjoitustyo/blob/master/Minesweeper/src/main/java/minesweeper/service/UserService.java) metodilla *setScoreToUser* uudet pisteet Userille.
<br />
Sitten haetaan [UserServicen](https://github.com/villeverkkonen/otm-harjoitustyo/blob/master/Minesweeper/src/main/java/minesweeper/service/UserService.java) metodilla *getUser* User-olio talteen.
<br />

Tämän jälkeen Userille luodaan Highscore [HighscoreServicen](https://github.com/villeverkkonen/otm-harjoitustyo/blob/master/Minesweeper/src/main/java/minesweeper/service/HighscoreService.java) metodilla *createHighscore* joka saa parametrikseen Userin.
<br />

Kun Highscore on luotu, haetaan kaikki Highscoret talteen [HighscoreServicen](https://github.com/villeverkkonen/otm-harjoitustyo/blob/master/Minesweeper/src/main/java/minesweeper/service/HighscoreService.java) metodilla *getAllHighscores*, jonka jälkeen ne karsitaan ja järjestetään vielä TOP-5 järjestykseen metodilla *getTopFiveSorted*, joka saa parametrikseen listan kaikista Highscoreista.

Lopuksi luodaan lopetusruutu teksteineen ja TOP-5 Highscore listoineen ja nappi, josta voi siirtyä takaisin aloitusruutuun ja aloittaa uuden pelin.