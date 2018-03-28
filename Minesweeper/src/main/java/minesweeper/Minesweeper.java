package minesweeper;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Minesweeper {
    
    private final Scanner scanner;
    private String[][] gameField;
    private String[][] displayField;
    private int fieldSize;
    private int gamePoints;
    private boolean gameIsOn;
    
    public Minesweeper(Scanner scanner, int fieldSize) {
        this.scanner = scanner;
        this.fieldSize = fieldSize;
        this.gameField = new String[fieldSize][fieldSize];
        this.displayField = new String[fieldSize][fieldSize];
        this.gamePoints = 0;
        this.gameIsOn = true;
    }
    
    public void start() {
        createDisplayField();
        createGameField();
        
        System.out.print("Nimimerkki: ");
        String nickname = this.scanner.nextLine();
        System.out.println();
        System.out.println("Hauskaa peliä " + nickname + "!");

        while (this.gameIsOn) {
            System.out.println();
            printField(this.displayField);
            doSelection();
        }
        
        gameOver();
    }
    
    public void doSelection() {
        int selectionY = 0;
        int selectionX = 0;
        System.out.print("Anna Y-akselin koordinaatti 0-" + (this.fieldSize - 1) + ": ");
        try {
            selectionY = this.scanner.nextInt();
            while (selectionY >= this.fieldSize) {
                System.out.println("Numeron pitää olla väliltä 0-" + (this.fieldSize - 1));
                System.out.print("Anna Y-akselin koordinaatti 0-" + (this.fieldSize - 1) + ": ");
                selectionY = this.scanner.nextInt();
            }
        } catch (Exception e) {
            System.out.println("Error:");
            System.out.println(e);
        }
        System.out.print("Anna X-akselin koordinaatti 0-" + (this.fieldSize - 1) + ": ");
        try {
            selectionX = this.scanner.nextInt();
            while (selectionX >= this.fieldSize) {
                System.out.println("Numeron pitää olla väliltä 0-" + (this.fieldSize - 1));
                System.out.print("Anna X-akselin koordinaatti 0-" + (this.fieldSize - 1) + ": ");
                selectionX = this.scanner.nextInt();
            }
        } catch (Exception e) {
            System.out.println("Error:");
            System.out.println(e);
        }
        
        String selectedFieldPlace = this.gameField[selectionY][selectionX];
        this.displayField[selectionY][selectionX] = selectedFieldPlace;
        
        if (selectedFieldPlace.equals(" X ")) {
            this.gameIsOn = false;
        } else if (selectedFieldPlace.equals("   ")) {
            this.gamePoints++;
        }
    }
    
    public void gameOver() {
        System.out.println();
        System.out.println("Peli loppui!");
        System.out.println();
        printField(this.displayField);
        System.out.println();
        System.out.println("Pisteesi: " + this.gamePoints);
    }
    
    public void createDisplayField() {
        String[][] createdDisplayField = new String[this.fieldSize][this.fieldSize];
        
        // Create display field on start
        for (int y = 0; y < this.fieldSize; y++) {
            for (int x = 0; x < this.fieldSize; x++) {
                createdDisplayField[y][x] = " # ";
            }
        }
        this.displayField = createdDisplayField;
    }
    
    public void createGameField() {
        String[][] createdGameField = new String[this.fieldSize][this.fieldSize];
        
        // Create game field, first all empty spaces
        for (int y = 0; y < this.fieldSize; y++) {
            for (int x = 0; x < this.fieldSize; x++) {
                createdGameField[y][x] = "   ";
            }
        }
        
        // Then add bombs amount of fieldSize * 1.5
        int bombCount = 0;
        while (bombCount < this.fieldSize * 1.5) {
            int bombY = ThreadLocalRandom.current().nextInt(0, this.fieldSize);
            int bombX = ThreadLocalRandom.current().nextInt(0, this.fieldSize);
            if (createdGameField[bombY][bombX].equals("   ")) {
                createdGameField[bombY][bombX] = " X ";
                bombCount++;
            }
        }
        this.gameField = createdGameField;
    }
    
    public void printField(String[][] field) {
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field.length; x++) {
                System.out.print(field[y][x]);
            }
            System.out.println();
        }
    }

    public int getFieldSize() {
        return this.fieldSize;
    }
    
    
}
