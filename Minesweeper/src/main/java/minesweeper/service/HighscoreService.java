
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
    
    public List<Highscore> getTopFiveSorted(List<Highscore> highscores) {
        List<Highscore> topFive = new ArrayList<>();
        
        Collections.sort(highscores, Highscore.COMPARE_BY_SCORE);
        
        if (highscores.size() > 5) {
            for (int i = 0; i < 5; i++) {
                topFive.add(highscores.get(i));
            }
        }
        
        return topFive;
    }
    
}
