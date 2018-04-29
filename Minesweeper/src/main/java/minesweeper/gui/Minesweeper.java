
package minesweeper.gui;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import minesweeper.domain.Highscore;
import minesweeper.domain.User;
import minesweeper.service.HighscoreService;
import minesweeper.service.MinesweeperService;
import minesweeper.service.UserService;

public class Minesweeper extends Application {

    private static MinesweeperService minesweeperService;
    private static UserService userService;
    private static HighscoreService highscoreService;
    private static Stage window;
    private static Scene startScreen, gameScreen, endScreen;
    
    @Override
    public void init() throws Exception {
        userService = new UserService();
        minesweeperService = new MinesweeperService(userService);
        highscoreService = new HighscoreService();
        
        try {
            JdbcPooledConnectionSource connectionSource 
                = new JdbcPooledConnectionSource("jdbc:h2:mem:minesweeper;DB_CLOSE_DELAY=-1");
            
            TableUtils.createTableIfNotExists(connectionSource, User.class);
            TableUtils.createTableIfNotExists(connectionSource, Highscore.class);
            
//            try {
//            connectionSource.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {        
        window = primaryStage;
        window.setTitle("Miinaharava");
        startScreen = new Scene(createStartScreen());
        
        window.setScene(startScreen);
        window.show();
    }
    
    public Parent createStartScreen() {
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
        
        Highscore highscore = new Highscore(user.getNickname(), user.getScore());
        List<Highscore> highscores = new ArrayList<>();
        
        try {
            JdbcPooledConnectionSource connectionSource 
                = new JdbcPooledConnectionSource("jdbc:h2:mem:minesweeper;DB_CLOSE_DELAY=-1");
            
            Dao<Highscore, Long> highscoreDao = DaoManager.createDao(connectionSource, Highscore.class);
            highscoreDao.create(highscore);
            highscores = highscoreDao.queryForAll();
            
//            try {
//            connectionSource.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        Collections.sort(highscores, Highscore.COMPARE_BY_SCORE);
        
        Label endText3 = new Label();
        StringBuilder highscoreText = new StringBuilder();
        highscoreText.append("Highscores:" + "\n");
        
        for (int i = 0; i < highscores.size(); i++) {
            String nickname = highscores.get(i).getNickname();
            int score = highscores.get(i).getScore();
            highscoreText.append(nickname);
            highscoreText.append(" = ");
            highscoreText.append(score);
            highscoreText.append("\n");
            if (i >= 4) {
                break;
            }
        }
        endText3.setText(highscoreText.toString());
        endText3.setFont(Font.font(22));
        
        Label endText4 = new Label();
        endText4.setText("Paina nappia aloittaaksesi uuden pelin.");
        endText4.setFont(Font.font(28));
        
        Button startButton = new Button();
        startButton.setText("Aloita peli");
        startButton.setFont(Font.font(32));
        
        startButton.setOnAction(e-> {
            window.setScene(startScreen);
        });
        
        endGrid.add(endText1, 0, 0);
        endGrid.add(endText2, 0, 1);
        endGrid.add(endText3, 0, 2);
        endGrid.add(startButton, 0, 4);
        
        endScreen = new Scene(endGrid);
        window.setScene(endScreen);
    }
    
    @Override
    public void stop() {
        Platform.exit();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
