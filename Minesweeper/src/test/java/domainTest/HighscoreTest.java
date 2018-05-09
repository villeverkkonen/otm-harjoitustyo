
package domainTest;

import minesweeper.domain.Highscore;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 * Luokka vastaa Highscore-olioiden testaamisesta
 */
public class HighscoreTest {
    
    public HighscoreTest() {
    }
    
    /**
     * Metodi testaa, että Highscore-olion luominen onnistuu
     */
    @Test
    public void canCreateHighscore() {
        Highscore highscore = new Highscore("Test", 10);
        
        assertNotNull(highscore);
        assertEquals("Test", highscore.getNickname());
        assertEquals(10, highscore.getScore());
    }
}
