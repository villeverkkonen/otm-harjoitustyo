package minesweaper;

import java.util.Scanner;

public class Minesweaper {
    
    private final Scanner scanner;
    private String[][] grid;
    
    public Minesweaper(Scanner scanner) {
        this.scanner = scanner;
        this.grid = createGrid(6);
    }
    
    public void start() {
        System.out.print("Nimimerkki: ");
        String nickname = this.scanner.nextLine();
        System.out.println("Hauskaa peliä " + nickname + "!");
        System.out.println("");
        printGrid();
    }
    
    public String[][] createGrid(int size) {
        String[][] createdGrid = new String[size][size];
        
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                createdGrid[y][x] = "#";
            }
        }
        
        return createdGrid;
    }
    
    public void printGrid() {
        for (int y = 0; y < this.grid.length; y++) {
            for (int x = 0; x < this.grid.length; x++) {
                System.out.print(this.grid[y][x]);
            }
            System.out.println();
        }
    }
}
