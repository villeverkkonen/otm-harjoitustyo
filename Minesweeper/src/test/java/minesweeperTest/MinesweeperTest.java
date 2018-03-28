
package minesweeperTest;

import java.util.Scanner;
import minesweeper.Minesweeper;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class MinesweeperTest {
    
    private Minesweeper minesweeper;
    
    public MinesweeperTest() {
    }
    
    @Before
    public void setUp() {
        Scanner scanner = new Scanner(System.in);
        this.minesweeper = new Minesweeper(scanner, 9);
    }
    
    @Test
    public void minesweeperHasFieldOfRightSize() {
        assertEquals(9, this.minesweeper.getFieldSize());
    }
}
