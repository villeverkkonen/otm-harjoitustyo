
package domainTest;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import minesweeper.domain.Tile;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class TileTest extends ApplicationTest {
    
    Pane pane;
    
    @Override
    public void start(Stage stage) {
        pane = new Pane();
        Scene scene = new Scene(pane, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void canCreateTile() {
        // int x, int y, boolean hasBomb, int tileSize, int xTiles, int yTiles, Tile[][] grid
        Tile[][] grid = new Tile[10][10];
        Tile tile = new Tile(1, 2, false, 60, 10, 10, grid);
        
        assertNotNull(tile);
    }
}
