
package domainTest;

import minesweeper.domain.User;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UserTest {
    
    public UserTest() {
    }
    
    @Test
    public void canCreateUser() {
        String nickname = "test";
        
        User user = new User(nickname);
        
        assertEquals(nickname, user.getNickname());
        assertEquals(0, user.getScore());
    }
}
