
package serviceTest;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.List;
import minesweeper.domain.User;
import minesweeper.service.UserService;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 * Luokka testaa UserServiceä
 */
public class UserServiceTest {
    
    private UserService userService;
    
    public UserServiceTest() {
    }
    
    /**
     * Ennen testejä alustetaan userService ja luodaan User tietokantataulu
     */
    @Before
    public void setUp() {
        userService = new UserService();
        try {
            JdbcConnectionSource connectionSource 
                = new JdbcConnectionSource("jdbc:h2:mem:account");
            
            TableUtils.createTableIfNotExists(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Metodi testaa, että User-olio voidaan tallettaa tietokantaan
     */
    @Test
    public void canCreateUser() {        
        try {
            JdbcConnectionSource connectionSource 
                = new JdbcConnectionSource("jdbc:h2:mem:account");
            
            Dao<User, Long> userDao = DaoManager.createDao(connectionSource, User.class);
            
            // Poista käyttäjä nimeltä "Test" jos on, ennen kuin se luodaan tätä metodia varten
            deleteTestUser();
            
            List<User> userList = userDao.queryForAll();            
            int usersCount = userList.size();
            
            assertEquals(usersCount, userList.size());
        
            userService.createUser("Test");
        
            userList = userDao.queryForAll();
            assertEquals(usersCount + 1, userList.size());
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Metodi testaa, että jos nickname on jo varattu, päivitetään vanha eikä luoda uutta
     */
    @Test
    public void canCreateUserWhichAlreadyExists() {
        userService.createUser("Test");
        
        try {
            JdbcConnectionSource connectionSource 
                = new JdbcConnectionSource("jdbc:h2:mem:account");
            
            Dao<User, Long> userDao = DaoManager.createDao(connectionSource, User.class);
            List<User> userList = userDao.queryForAll();
            
            int usersCount = userList.size();
            
            assertEquals(usersCount, userList.size());
        
            userService.createUser("Test");
        
            userList = userDao.queryForAll();
            assertEquals(usersCount, userList.size());
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void canUpdateUser() {
        try {
            JdbcConnectionSource connectionSource 
                = new JdbcConnectionSource("jdbc:h2:mem:account");
            
            Dao<User, Long> userDao = DaoManager.createDao(connectionSource, User.class);
            User user = new User("Test");
            userService.createUser(user.getNickname());
            
            user.setScore(10);
            userService.setScoreToUser(user.getScore());
            userService.updateUser();
            //userDao.update(user);
        
            QueryBuilder<User, Long> queryBuilder = userDao.queryBuilder();
            queryBuilder.where().eq(User.NICKNAME_FIELD_NAME, user.getNickname());
            List<User> userList = queryBuilder.query();

            assertEquals(user.getScore(), userList.get(0).getScore());
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Metodi poistaa "Test" nimisen käyttäjän jos sellainen on.
     * Tämä sitä varten, että testeissä luodaan "Test" nimellä käyttäjiä
     * ja oletetaan, ettei sellaista ole jo valmiiksi.
     */
    public void deleteTestUser() {
        try {
            JdbcConnectionSource connectionSource 
                = new JdbcConnectionSource("jdbc:h2:mem:account");
            
            Dao<User, Long> highscoreDao = DaoManager.createDao(connectionSource, User.class);
            
            // Poista highscore nimeltä "Test" jos on, ennen kuin se luodaan tätä metodia varten
            DeleteBuilder<User, Long> deleteBuilder = highscoreDao.deleteBuilder();
            deleteBuilder.where().eq(User.NICKNAME_FIELD_NAME, "Test");
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Seuraavat testit ovat getterejä ja setterejä varten,
    // joiden puute laskee ikävästi kokonaisprosenttia
    
    @Test
    public void canGetAndSetUser() {
        userService.setUser(new User("Test"));
        User userGet = userService.getUser();
        
        assertNotNull(userGet);
        assertNotNull(userGet.getNickname());
        assertNotNull(userGet.getScore());
        assertEquals("Test", userGet.getNickname());
    }
    
    
}
