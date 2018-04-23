
package minesweeper.domain;

public class User {
    
    private String nickname;
    private int score;
    
    public User(String nickname) {
        this.nickname = nickname;
        this.score = 0;
    }

    public String getNickname() {
        return this.nickname;
    }

    public int getScore() {
        return this.score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
}
