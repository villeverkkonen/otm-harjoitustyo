
package serviceTest;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import minesweeper.domain.Highscore;
import minesweeper.domain.User;
import minesweeper.service.HighscoreService;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Luokka vastaa HighscoreServicen testaamisesta
 */
public class HighscoreServiceTest {
    
    private HighscoreService highscoreService;
    
    public HighscoreServiceTest() {
    }
    
    /**
     * Ennen testejä alustetaan highscoreService ja luodaan Highscore tietokantataulu
     */
    @Before
    public void setUp() {
        highscoreService = new HighscoreService();
        try {
            JdbcConnectionSource connectionSource 
                = new JdbcConnectionSource("jdbc:h2:mem:account");
            
            TableUtils.createTableIfNotExists(connectionSource, Highscore.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Metodi tarkistaa, että annettu listä pystytään järjestämään TOP-5 järjestykseen
     */
    @Test
    public void canGetTopFiveSorted() {
        List<Highscore> sortedList = new ArrayList<>();
        List<Highscore> unsortedList = new ArrayList<>();
        
        Highscore highscore1 = new Highscore("highscore1", 6);
        Highscore highscore2 = new Highscore("highscore2", 5);
        Highscore highscore3 = new Highscore("highscore3", 4);
        Highscore highscore4 = new Highscore("highscore4", 3);
        Highscore highscore5 = new Highscore("highscore5", 2);
        Highscore highscore6 = new Highscore("highscore6", 1);
        
        sortedList.add(highscore1);
        sortedList.add(highscore2);
        sortedList.add(highscore3);
        sortedList.add(highscore4);
        sortedList.add(highscore5);
        sortedList.add(highscore6);
        
        unsortedList.add(highscore2);
        unsortedList.add(highscore1);
        unsortedList.add(highscore4);
        unsortedList.add(highscore3);
        unsortedList.add(highscore6);
        unsortedList.add(highscore5);
        
        unsortedList = highscoreService.getTopFiveSorted(unsortedList);
        
        for (int i = 0; i < 5; i++) {
            assertEquals(sortedList.get(i).getScore(), unsortedList.get(i).getScore());
        }
    }
    
    /**
     * Metodi testaa, että tietokantaan voi tallettaa highscoren
     */
    @Test
    public void canCreateHighscore() {        
        try {
            JdbcConnectionSource connectionSource 
                = new JdbcConnectionSource("jdbc:h2:mem:account");
            
            Dao<Highscore, Long> highscoreDao = DaoManager.createDao(connectionSource, Highscore.class);
            
            // Poista highscore nimeltä "Test" jos on, ennen kuin se luodaan tätä metodia varten
            deleteTestHighscore();
            
            List<Highscore> highscoreList = highscoreDao.queryForAll();
            
            int highscoresCount = highscoreList.size();
            
            assertEquals(highscoresCount, highscoreList.size());
        
            highscoreService.createHighscore(new User("Test"));
        
            highscoreList = highscoreDao.queryForAll();
            assertEquals(highscoresCount + 1, highscoreList.size());
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Metodi tarkistaa, että jos luodaan jo olemassaolevan niminen highscore,
     * niin päivitetään uusi score vanhan tilalle,
     * eikä luoda uutta highscorea tietokantaan.
     */
    @Test
    public void canCreateHighscoreWhichAlreadyExists() {
        User user = new User("Test");
        highscoreService.createHighscore(user);
        
        try {
            JdbcConnectionSource connectionSource 
                = new JdbcConnectionSource("jdbc:h2:mem:account");
            
            Dao<Highscore, Long> highscoreDao = DaoManager.createDao(connectionSource, Highscore.class);
            List<Highscore> highscoresList = highscoreDao.queryForAll();
            
            int highscoresCount = highscoresList.size();
            
            assertEquals(highscoresCount, highscoresList.size());
        
            // Lisää vanhaan scoreen yksi, että highscoreServicen metodi
            // createHighscore päivittää uuden scoren vanhaan
            user.setScore(user.getScore() + 1);
            highscoreService.createHighscore(user);
        
            highscoresList = highscoreDao.queryForAll();
            assertEquals(highscoresCount, highscoresList.size());
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Metodi tarkistaa, että tietokannasta saadaan haettua kaikki highscoret
     * Kun luodaan uusi highscore, pitäisi palautua yksi enemmän kuin ennen luomista
     */
    @Test
    public void canGetAllHighscores() {
        // Poista highscore nimeltä "Test" jos on, ennen kuin se luodaan tätä metodia varten
        deleteTestHighscore();
        
        List<Highscore> highscoreList = this.highscoreService.getAllHighscores();
        
        int highscoresCount = highscoreList.size();
        
        assertEquals(highscoresCount, highscoreList.size());
        
        User user = new User("Test");
        this.highscoreService.createHighscore(user);
        highscoreList = this.highscoreService.getAllHighscores();
        
        assertEquals(highscoresCount + 1, highscoreList.size());
    }
    
    /**
     * Metodi poistaa "Test" nimisen highscoren jos sellainen on.
     * Tämä sitä varten, että testeissä luodaan "Test" nimellä highscoreja
     * ja oletetaan, ettei sellaista ole jo valmiiksi.
     */
    public void deleteTestHighscore() {
        try {
            JdbcConnectionSource connectionSource 
                = new JdbcConnectionSource("jdbc:h2:mem:account");
            
            Dao<Highscore, Long> highscoreDao = DaoManager.createDao(connectionSource, Highscore.class);
            
            // Poista highscore nimeltä "Test" jos on, ennen kuin se luodaan tätä metodia varten
            DeleteBuilder<Highscore, Long> deleteBuilder = highscoreDao.deleteBuilder();
            deleteBuilder.where().eq(Highscore.NICKNAME_FIELD_NAME, "Test");
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
