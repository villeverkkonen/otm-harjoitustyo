
package minesweeper.service;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import minesweeper.domain.Highscore;
import minesweeper.domain.User;

/**
 * Luokka vastaa Highscore-olioihin liittyvistä toimenpiteistä
 */
public class HighscoreService {
    
    public HighscoreService() {
    }
    
    /**
     * Metodi palauttaa tietokannasta 5 parasta highscorea
     * @param highscores Kaikki highscoret
     * @return TOP-5 suurimmasta pienempään
     */
    public List<Highscore> getTopFiveSorted(List<Highscore> highscores) {
        List<Highscore> topFive = new ArrayList<>();
        
        Collections.sort(highscores, Highscore.compareByScore);
        
        for (int i = 0; i < highscores.size(); i++) {
            topFive.add(highscores.get(i));
            if (i >= 4) {
                break;
            }
        }
        
        return topFive;
    }
    
    /**
     * Metodi tallentaa annetun käyttäjän perusteella luodun highscoren tietokantaan
     * @param user Käyttäjä jonka highscore halutaan tallettaa
     */
    public void createHighscore(User user) {
        try {
            JdbcConnectionSource connectionSource = new JdbcConnectionSource("jdbc:h2:mem:account");
            
            Dao<Highscore, String> highscoreDao = DaoManager.createDao(connectionSource, Highscore.class);
            QueryBuilder<Highscore, String> queryBuilder = highscoreDao.queryBuilder();
            queryBuilder.where().eq(Highscore.NICKNAME_FIELD_NAME, user.getNickname());
            List<Highscore> highscoreList = queryBuilder.query();
            
            if (highscoreList.size() > 0) {
                if (highscoreList.get(0).getScore() < user.getScore()) {
                    UpdateBuilder<Highscore, String> updateBuilder = highscoreDao.updateBuilder();
                    updateBuilder.where().eq("nickname", highscoreList.get(0).getNickname());
                    updateBuilder.updateColumnValue("score", user.getScore());
                    updateBuilder.update();
                }
            } else {
                highscoreDao.create(new Highscore(user.getNickname(), user.getScore()));
            }
        } catch (SQLException e) {
        }
    }
    
    /**
     * Metodi hakee kaikki highscoret tietokannasta
     * @return Kaikki highscoret tietokannasta
     */
    public List<Highscore> getAllHighscores() {
        List<Highscore> highscores = new ArrayList<>();
        try {
            JdbcConnectionSource connectionSource 
                = new JdbcConnectionSource("jdbc:h2:mem:account");
            
            Dao<Highscore, String> highscoreDao = DaoManager.createDao(connectionSource, Highscore.class);
            QueryBuilder<Highscore, String> queryBuilder = highscoreDao.queryBuilder();
            highscores = highscoreDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return highscores;
    }
    
}
