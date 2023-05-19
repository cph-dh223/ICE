package board;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Letter;

public class BoardTest {

    Board board;
    
    
 
    @BeforeEach
    public void setup(){
        Set<String>dict = new HashSet<String>();
        dict.add("word");
        dict.add("words");
        dict.add("wild");
        board = new Board(15, 15, dict);
    }

    @AfterEach
    public void teardown(){
        // board = null;
    }

    @Test // this test shuld work because the program woks as expected but it doesnt
    public void goToFirstLetterTest(){

        // arrange
        board.placeLetter(7, 7, new Letter(Character.toUpperCase('w'), 1));
        board.placeLetter(8, 7, new Letter(Character.toUpperCase('o'), 1));
        board.placeLetter(9, 7, new Letter(Character.toUpperCase('r'), 1));
        board.placeLetter(10, 7, new Letter(Character.toUpperCase('d'), 1));
        board.checkSubmittedLetters();
        board.placeLetter(7, 8, new Letter(Character.toUpperCase('o'), 1));
        board.placeLetter(7, 9, new Letter(Character.toUpperCase('r'), 1));
        board.placeLetter(7, 10, new Letter(Character.toUpperCase('d'), 1));
        board.checkSubmittedLetters();
        board.updateBoard();

        List<Integer> expected = new ArrayList<>();
        expected.add(7);
        expected.add(7);
        // act
        List<Integer> actaulX = board.goToFirstLetter(10,7, Direction.HORISONTAL);
        List<Integer> actaulY = board.goToFirstLetter(7,10, Direction.VERTICAL);

        // assert
        assertEquals(expected.get(0), actaulX.get(0));
        assertEquals(expected.get(1), actaulX.get(1));
        assertEquals(expected.get(0), actaulY.get(0));
        assertEquals(expected.get(1), actaulY.get(1));
    }
}
