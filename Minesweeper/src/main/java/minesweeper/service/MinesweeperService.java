
package minesweeper.service;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import java.sql.SQLException;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import minesweeper.domain.Tile;
import minesweeper.domain.User;
import minesweeper.gui.Minesweeper;

public class MinesweeperService {
    
    private static final int TILE_SIZE = 60;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    
    private static final int X_TILES = WIDTH / TILE_SIZE;
    private static final int Y_TILES = HEIGHT / TILE_SIZE;
    
    private Tile[][] grid;
    private UserService userService;
    
    public MinesweeperService(UserService userService) {
        grid = new Tile[X_TILES][Y_TILES];
        this.userService = userService;
    }

    public Parent createGameScreen() {
        Pane gameScreen = new Pane();
        gameScreen.setPrefSize(WIDTH, HEIGHT);
        
        for (int y = 0; y < Y_TILES; y++) {
            for (int x = 0; x < X_TILES; x++) {
                Tile tile = new Tile(x, y, Math.random() < 0.2, TILE_SIZE, X_TILES, Y_TILES, this.grid);
                
                tile.setOnMouseClicked(e -> {
                    if (tile.isOpen()) {
                        return;
                    }
                    tile.open();
                    if (tile.hasBomb()) {
                        Minesweeper.endGame();
                    }
                });
                this.grid[x][y] = tile;
                gameScreen.getChildren().add(tile);
            }
        }
        
        for (int y = 0; y < Y_TILES; y++) {
            for (int x = 0; x < X_TILES; x++) {
                Tile tile = this.grid[x][y];
                
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
    
    // This method gets called when game ends, add score to user
    // Every opened tile is one score except for the bomb tile
    public void countOpenTiles() {
        int openTiles = 0;
        
        for (int y = 0; y < Y_TILES; y++) {
            for (int x = 0; x < X_TILES; x++) {
                Tile tile = this.grid[x][y];
                if (tile.isOpen()) {
                    openTiles++;
                }
            }
        }
        
        // If the first and only tile wasn't a bomb, decrease points by one
        // Bomb doesn't give a score
        if (openTiles > 0) {
            this.userService.setScoreToUser(openTiles - 1);
        } else {
            this.userService.setScoreToUser(openTiles);
        }
        
        try {
            JdbcConnectionSource connectionSource 
                = new JdbcConnectionSource("jdbc:h2:mem:account");
            Dao<User, Long> userDao = DaoManager.createDao(connectionSource, User.class);
            userDao.update(this.userService.getUser());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
