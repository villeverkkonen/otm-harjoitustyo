package main;

import java.util.Scanner;
import minesweeper.Minesweeper;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Minesweeper minesweeper = new Minesweeper(scanner, 9);
        minesweeper.start();
    }
}
