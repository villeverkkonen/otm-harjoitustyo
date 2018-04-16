package main;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import minesweeper.Tile;

public class Main extends Application {
    
    private Stage window;
    private Scene startScreen, gameScreen;
    
    private static final int TILE_SIZE = 40;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    
    private static final int X_TILES = WIDTH / TILE_SIZE;
    private static final int Y_TILES = HEIGHT / TILE_SIZE;
    
    private Tile[][] grid = new Tile[X_TILES][Y_TILES];
    
    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH, HEIGHT);
        
        for (int y = 0; y < Y_TILES; y++) {
            for (int x = 0; x < X_TILES; x++) {
                Tile tile = new Tile(x, y, Math.random() < 0.2, TILE_SIZE, X_TILES, Y_TILES, this.grid);
                
                grid[x][y] = tile;
                root.getChildren().add(tile);
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
        
        return root;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Miinaharava");
        gameScreen = new Scene(createContent());
        
        window.setScene(gameScreen);
        window.show();
        
//        window = primaryStage;
//        window.setTitle("Miinaharava");
//        
//        Label instructions = new Label("Aloita peli klikkaamalla nappia.");
//        Button buttonStart = new Button();
//        buttonStart.setText("Aloita");
//        
//        FlowPane startLayout = new FlowPane();
//        startLayout.getChildren().add(instructions);
//        startLayout.getChildren().add(buttonStart);
//        startScreen = new Scene(startLayout, 400, 400);
//        
//        Group gameLayout = new Group();
//        
//        for (int y = 0; y < 10; y++) {
//            for (int x = 0; x < 10; x++) {
//                Rectangle tile = new Rectangle();
//                tile.setX(x * 100);
//                tile.setY(y * 100);
//                tile.setWidth(100);
//                tile.setHeight(100);
//                tile.setFill(Color.GREY);
//                gameLayout.getChildren().add(tile);
//            }
//        }
//        
//        gameScreen = new Scene(gameLayout, 400, 400);
//        
//        window.setScene(startScreen);
//        window.show();
//        
//        buttonStart.setOnAction(e->{
//            window.setScene(gameScreen);
//        });
    }
}
