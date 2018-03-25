package main;

import java.util.Scanner;
import minesweaper.Minesweaper;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Minesweaper minesweaper = new Minesweaper(scanner);
        minesweaper.start();
    }
}
