
package minesweeper.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import minesweeper.domain.Highscore;

public class HighscoreService {
    
    public HighscoreService() {
    
    }
    
    public void addToHighscores(Highscore highscore) {
        
    }
    
    /**
     * 
     * @param highscores Kaikki highscoret
     * @return TOP-5 suurimmasta pienempään
     */
    public List<Highscore> getTopFiveSorted(List<Highscore> highscores) {
        List<Highscore> topFive = new ArrayList<>();
        
        Collections.sort(highscores, Highscore.COMPARE_BY_SCORE);
        
        for (int i = 0; i < highscores.size(); i++) {
            topFive.add(highscores.get(i));
            if (i >= 4) {
                break;
            }
        }
        
        return topFive;
    }
    
}
