
package domainTest;

import minesweeper.domain.User;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 * Luokka vastaa User-olioiden testaamisesta
 */
public class UserTest {
    
    public UserTest() {
    }
    
    /**
     * Metodi testaa User-olioiden luomista
     */
    @Test
    public void canCreateUser() {
        String nickname = "test";
        
        User user = new User(nickname);
        
        assertNotNull(user);
        assertEquals(nickname, user.getNickname());
        assertEquals(0, user.getScore());
    }
}
