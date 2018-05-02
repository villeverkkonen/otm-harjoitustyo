
package minesweeper.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "User")
public class User {
    
    public static final String NICKNAME_FIELD_NAME = "nickname";
    
    @DatabaseField(id = true, columnName = "nickname")
    private String nickname;
    @DatabaseField(columnName = "score")
    private int score;
    
    public User() {
        
    }
    
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
