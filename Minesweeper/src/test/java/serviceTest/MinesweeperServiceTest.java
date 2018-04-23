
package serviceTest;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import minesweeper.service.MinesweeperService;
import minesweeper.service.UserService;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class MinesweeperServiceTest extends ApplicationTest {
    
    Pane pane;
    MinesweeperService minesweeperService;
     
    @Before
    public void setUp() throws Exception {
        UserService userService = new UserService();
        minesweeperService = new MinesweeperService(userService);
    }
    
    @Override
    public void start(Stage stage) {
        pane = new Pane();
        Scene scene = new Scene(pane, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void canCreateGameScreen() {
        Scene scene = new Scene(minesweeperService.createGameScreen());
        
        assertNotNull(scene);
    }
}
