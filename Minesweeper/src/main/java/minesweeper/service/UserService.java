
package minesweeper.service;

import minesweeper.domain.User;


public class UserService {
    
    private User user;
    
    public UserService() {
        
    }
    
    public void createUser(String nickname) {
        this.user = new User(nickname);
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void addScore() {
        this.user.setScore(this.user.getScore() + 1);
    }
}
