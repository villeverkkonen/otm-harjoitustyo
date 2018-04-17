
package minesweeper;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {
    private final int x, y;
    private final boolean hasBomb;
    private boolean isOpen = false;
    private final int tileSize;
    private final int xTiles;
    private final int yTiles;

    private final Rectangle border;
    private Text text = new Text();
    private Tile[][] grid;

    public Tile(int x, int y, boolean hasBomb, int tileSize, int xTiles, int yTiles, Tile[][] grid) {
        this.x = x;
        this.y = y;
        this.hasBomb = hasBomb;
        this.tileSize = tileSize;
        this.border = new Rectangle(tileSize - 2, tileSize - 2);
        this.xTiles = xTiles;
        this.yTiles = yTiles;
        this.grid = grid;
        
        border.setStroke(Color.RED);
        
        text.setFont(Font.font(28));
        text.setText(this.hasBomb ? "X" : "");
        text.setVisible(false);
        
        this.getChildren().addAll(border, text);
        
        this.setTranslateX(x * this.tileSize);
        this.setTranslateY(y * this.tileSize);
        
        setOnMouseClicked(e -> open());
    }
    
    public void open() {
        if (this.isOpen) {
            return;
        }
        
        this.isOpen = true;
        this.text.setVisible(true);
        this.border.setFill(null);
        
        if (this.text.getText().isEmpty()) {
            getNeighbours(this, this.grid).forEach(Tile::open);
        }
        
        if (this.hasBomb) {
            Minesweeper.endGame();
        }
    }
    
    public List<Tile> getNeighbours(Tile tile, Tile[][] grid) {
        List<Tile> neighbours = new ArrayList<>();
        
        int[] points = new int[] {
            -1, -1,
            -1, 0,
            -1, 1,
            0, -1,
            0, 1,
            1, -1,
            1, 0,
            1, 1
        };
        
        for (int i = 0; i < points.length; i++) {
            int dx = points[i];
            int dy = points[++i];
            
            int newX = tile.getX() + dx;
            int newY = tile.getY() + dy;
            
            if (newX >= 0 && newX < this.xTiles
                    && newY >= 0 && newY < this.yTiles) {
                neighbours.add(grid[newX][newY]);
            }
        }
        return neighbours;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean hasBomb() {
        return hasBomb;
    }

    public Text getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text.setText(text);
    }
}
