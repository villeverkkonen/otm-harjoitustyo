
package minesweeper;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Minesweeper extends Application {
    
    private MinesweeperService minesweeperService;
    private static Stage WINDOW;
    private Scene startScreen, gameScreen, endScreen;
    private static Button startButton;
    
    @Override
    public void init() throws Exception {
        this.minesweeperService = new MinesweeperService();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        createStartButton();
        
        this.WINDOW = primaryStage;
        this.WINDOW.setTitle("Miinaharava");
        this.startScreen = new Scene(createStartScreen());
        
        this.WINDOW.setScene(this.startScreen);
        this.WINDOW.show();
    }
    
    public Parent createStartScreen() {
        GridPane startGrid = new GridPane();
        startGrid.setAlignment(Pos.CENTER);
        startGrid.setHgap(10);
        startGrid.setVgap(10);
        startGrid.setPadding(new Insets(25, 25, 25, 25));
        startGrid.setPrefSize(600, 600);
        
        Label infoText = new Label();
        infoText.setText("Paina nappia aloittaaksesi pelin.");
        infoText.setFont(Font.font(32));
        
        startGrid.add(infoText, 0, 0);
        startGrid.add(this.startButton, 0, 3);
        
        return startGrid;
    }
    
    public static void endGame() {
        GridPane endGrid = new GridPane();
        endGrid.setAlignment(Pos.CENTER);
        endGrid.setHgap(10);
        endGrid.setVgap(10);
        endGrid.setPadding(new Insets(25, 25, 25, 25));
        endGrid.setPrefSize(600, 600);
        
        Label endText1 = new Label();
        endText1.setText("Peli loppui.");
        endText1.setFont(Font.font(28));
        
        Label endText2 = new Label();
        endText2.setText("Paina nappia aloittaaksesi uuden pelin.");
        endText2.setFont(Font.font(28));
        
        endGrid.add(endText1, 0, 0);
        endGrid.add(endText2, 0, 1);
        endGrid.add(startButton, 0, 3);
        
        Scene endScreen = new Scene(endGrid);
        WINDOW.setScene(endScreen);
    }
    
    public void createStartButton() {
        this.startButton = new Button();
        this.startButton.setText("Aloita peli");
        this.startButton.setFont(Font.font(32));
        
        this.startButton.setOnAction(e->{
            this.gameScreen = new Scene(this.minesweeperService.createGameScreen());
            this.WINDOW.setScene(this.gameScreen);
        });
    }
    
    @Override
    public void stop() {
        Platform.exit();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
