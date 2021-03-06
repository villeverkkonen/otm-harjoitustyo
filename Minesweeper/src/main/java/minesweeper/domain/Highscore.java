
package minesweeper.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Comparator;

/**
 * Luokka on tietokantaan tallennettava käyttäjän pistemäärä
 */
@DatabaseTable(tableName = "Highscore")
public class Highscore {
    
    public static final String NICKNAME_FIELD_NAME = "nickname";
    
    @DatabaseField(id = true, columnName = "nickname")
    private String nickname;
    @DatabaseField(columnName = "score")
    private int score;
    
    public Highscore() {
        
    }
    
    public Highscore(String nickname, int score) {
        this.nickname = nickname;
        this.score = score;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public String getNickname() {
        return this.nickname;
    }
    
    /**
     * Metodi järjestää highscoret suuruusjärjestykseen, eniten pisteitä ensin
     */
    public static Comparator<Highscore> compareByScore = new Comparator<Highscore>() {
        @Override
        public int compare(Highscore one, Highscore other) {
            return other.getScore() - one.getScore();
        }
    };
}
