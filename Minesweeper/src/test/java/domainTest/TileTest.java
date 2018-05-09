
package domainTest;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import minesweeper.domain.Tile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

/**
 * Luokka vastaa Tile-olioiden testaamisesta
 */
public class TileTest extends ApplicationTest {
    
    private Pane pane;
    
    public TileTest() {
        
    }
    
    /**
     * Ennen testejä tulee käynnistää graafinen käyttöliittymä, jossa Tile-oliot sijaitsevat
     * @param stage 
     */
    @Override
    public void start(Stage stage) {
        pane = new Pane();
        Scene scene = new Scene(pane, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metodi testaa Tile-olion luomista
     */
    @Test
    public void canCreateTile() {
        Tile[][] grid = new Tile[10][10];
        // Tile-olion parametrit:
        // int x, int y, boolean hasBomb, int tileSize, int xTiles, int yTiles, Tile[][] grid
        Tile tile = new Tile(1, 2, false, 60, 10, 10, grid);
        
        assertNotNull(tile);
        assertEquals(1, tile.getX());
        assertEquals(2, tile.getY());
        assertEquals(false, tile.hasBomb());
        assertEquals(60, tile.getTileSize());
        assertEquals(10, tile.getXTiles());
        assertEquals(10, tile.getYTiles());
    }
}
