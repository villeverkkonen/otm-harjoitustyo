
package minesweeper.service;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import minesweeper.domain.Tile;

public class MinesweeperService {
    
    private static final int TILE_SIZE = 60;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    
    private static final int X_TILES = WIDTH / TILE_SIZE;
    private static final int Y_TILES = HEIGHT / TILE_SIZE;
    
    private Tile[][] grid;
    
    public MinesweeperService() {
        grid = new Tile[X_TILES][Y_TILES];
    }

    public Parent createGameScreen() {
        Pane gameScreen = new Pane();
        gameScreen.setPrefSize(WIDTH, HEIGHT);
        
        for (int y = 0; y < Y_TILES; y++) {
            for (int x = 0; x < X_TILES; x++) {
                Tile tile = new Tile(x, y, Math.random() < 0.2, TILE_SIZE, X_TILES, Y_TILES, this.grid);
                
                grid[x][y] = tile;
                gameScreen.getChildren().add(tile);
            }
        }
        
        for (int y = 0; y < Y_TILES; y++) {
            for (int x = 0; x < X_TILES; x++) {
                Tile tile = grid[x][y];
                
                if (tile.hasBomb()) {
                    continue;
                }
                
                long bombs = tile.getNeighbours(tile, this.grid).stream().filter(t -> t.hasBomb()).count();
                
                if (bombs > 0) {
                    tile.setText(String.valueOf(bombs));
                }
            }
        }
        
        return gameScreen;
    }
    
    public void endGame() {
        
    }
}
