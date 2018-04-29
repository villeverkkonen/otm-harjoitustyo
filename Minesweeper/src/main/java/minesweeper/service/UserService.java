
package minesweeper.service;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import java.sql.SQLException;
import minesweeper.domain.User;

public class UserService {
    
    private User user;
    
    public UserService() {
        
    }
    
    public void createUser(String nickname) {
        this.user = new User(nickname);
        try {
            JdbcPooledConnectionSource connectionSource 
                = new JdbcPooledConnectionSource("jdbc:h2:mem:minesweeper;DB_CLOSE_DELAY=-1");
            
            Dao<User, Long> userDao = DaoManager.createDao(connectionSource, User.class);
            userDao.create(this.user);
            
//            try {
//            connectionSource.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
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
