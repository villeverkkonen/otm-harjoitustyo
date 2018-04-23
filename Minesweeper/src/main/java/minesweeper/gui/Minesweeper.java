
package minesweeper.gui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import minesweeper.domain.User;
import minesweeper.service.MinesweeperService;
import minesweeper.service.UserService;

public class Minesweeper extends Application {
    
    private static MinesweeperService minesweeperService;
    private static UserService userService;
    private static Stage window;
    private static Scene startScreen, gameScreen, endScreen;
    
    @Override
    public void init() throws Exception {
        userService = new UserService();
        minesweeperService = new MinesweeperService(userService);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // createStartButton();
        
        window = primaryStage;
        window.setTitle("Miinaharava");
        startScreen = new Scene(createStartScreen());
        
        window.setScene(startScreen);
        window.show();
    }
    
    public static Parent createStartScreen() {
        GridPane startGrid = new GridPane();
        startGrid.setAlignment(Pos.CENTER);
        startGrid.setHgap(10);
        startGrid.setVgap(10);
        startGrid.setPadding(new Insets(25, 25, 25, 25));
        startGrid.setPrefSize(600, 600);
        
        Label infoText = new Label();
        infoText.setText("Nimimerkki:");
        infoText.setFont(Font.font(32));
        
        TextField nicknameField = new TextField ();
        
        Button startButton = new Button();
        startButton.setText("Aloita peli");
        startButton.setFont(Font.font(32));
        
        startButton.setOnAction(e-> {
            userService.createUser(nicknameField.getText());
            nicknameField.setText("");
            gameScreen = new Scene(minesweeperService.createGameScreen());
            window.setScene(gameScreen);
        });
        
        startGrid.add(infoText, 0, 0);
        startGrid.add(nicknameField, 0, 1);
        startGrid.add(startButton, 0, 3);
        
        return startGrid;
    }
    
    public static void endGame() {
        minesweeperService.countOpenTiles();
        
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
        User user = userService.getUser();
        endText2.setText(user.getNickname() + ": " + user.getScore() + " pistettä.");
        endText2.setFont(Font.font(28));
        
        Label endText3 = new Label();
        endText3.setText("Paina nappia aloittaaksesi uuden pelin.");
        endText3.setFont(Font.font(28));
        
        Button startButton = new Button();
        startButton.setText("Aloita peli");
        startButton.setFont(Font.font(32));
        
        startButton.setOnAction(e-> {
            window.setScene(startScreen);
        });
        
        endGrid.add(endText1, 0, 0);
        endGrid.add(endText2, 0, 1);
        endGrid.add(startButton, 0, 3);
        
        endScreen = new Scene(endGrid);
        window.setScene(endScreen);
    }
    
//    public void createStartButton() {
//        this.startButton = new Button();
//        this.startButton.setText("Aloita peli");
//        this.startButton.setFont(Font.font(32));
//        
//        this.startButton.setOnAction(e-> {
//            this.gameScreen = new Scene(this.minesweeperService.createGameScreen());
//            this.window.setScene(this.gameScreen);
//        });
//    }
    
    @Override
    public void stop() {
        Platform.exit();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
