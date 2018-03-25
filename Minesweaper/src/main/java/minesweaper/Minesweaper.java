package minesweaper;

import java.util.Scanner;

public class Minesweaper {
    
    public Scanner scanner;
    
    public Minesweaper(Scanner scanner) {
        this.scanner = scanner;
        
        String scannerText = this.scanner.nextLine();
        
        System.out.println(scannerText);
    }
}
