
package minesweeper.service;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import java.sql.SQLException;
import java.util.List;
import minesweeper.domain.User;

public class UserService {
    
    private User user;
    
    public UserService() {
        
    }
    
    /**
     * Metodi luo uuden käyttäjän,
     * tai päivittää samalla nimimerkillä olevan käyttäjän scoren nollaksi.
     * @param nickname Pelaajan syöttämä nimimerkki
     */
    public void createUser(String nickname) {
        this.user = new User(nickname);
        try {
            JdbcConnectionSource connectionSource 
                = new JdbcConnectionSource("jdbc:h2:mem:account");
            
            Dao<User, String> userDao = DaoManager.createDao(connectionSource, User.class);
            QueryBuilder<User, String> queryBuilder = userDao.queryBuilder();
            queryBuilder.where().eq(User.NICKNAME_FIELD_NAME, user.getNickname());
            List<User> userList = queryBuilder.query();
            
            if (userList.size() > 0) {
                User foundUser = userList.get(0);
                UpdateBuilder<User, String> updateBuilder = userDao.updateBuilder();
                updateBuilder.where().eq("nickname", foundUser.getNickname());
                updateBuilder.updateColumnValue("score", 0);
                updateBuilder.update();
            } else {
                userDao.create(this.user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public void setScoreToUser(int score) {
        this.user.setScore(score);
    }
}
