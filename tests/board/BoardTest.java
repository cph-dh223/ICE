package board;

import static org.junit.Assert.assertArrayEquals;

import java.util.HashSet;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import game.Letter;

public class BoardTest {

    Board board;
 
    @BeforeEach
    void setup(){
        HashSet<String> dict = new HashSet<String>();
        dict.add("word");
        dict.add("words");
        dict.add("wild");
        board = new Board(15, 15, dict);
    }

    @AfterEach
    void teardown(){
        board = null;
    }

    @Test
    void goToFirstLetterTest(){
        // arrange
        board.placeLetter(7, 7, new Letter('w', 1));
        board.placeLetter(8, 7, new Letter('o', 1));
        board.placeLetter(9, 7, new Letter('r', 1));
        board.placeLetter(10, 7, new Letter('d', 1));
        board.placeLetter(7, 8, new Letter('o', 1));
        board.placeLetter(7, 9, new Letter('r', 1));
        board.placeLetter(7, 10, new Letter('d', 1));
        board.updateBoard();

        int[] expected = new int[]{7,7};

        // actaual
        int[] actaulX = board.goToFirstLetterTest(10,7, Direction.HORISONTAL);
        int[] actaulY = board.goToFirstLetterTest(7,10, Direction.VERTICAL);

        // assert
        assertArrayEquals(expected, actaulX);
        assertArrayEquals(expected, actaulY);
    }
}
