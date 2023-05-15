package board;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Letter;


class TileTest {

    Tile tileWithOutLetter;
    Tile tileWithLetter;
    Letter a;

    @BeforeEach
    void setup(){
        a = new Letter('A', 1);
        tileWithOutLetter = new Tile(0,0,Multiplier.NORMAL);
        tileWithLetter = new Tile(1,2,Multiplier.DOUBLE_LETTER, a);
    }

    @AfterEach
    void tairDown(){
        tileWithOutLetter = null;
        tileWithLetter = null;
    }


    @Test
    void getLetterScore() {
        // arrange
        int expected = 1;

        // act
        int actual = tileWithLetter.getLetterScore();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void getMultiplier() {
        Multiplier expected1 = Multiplier.NORMAL;
        Multiplier expected2 = Multiplier.DOUBLE_LETTER;
        
        Multiplier actual1 = tileWithOutLetter.getMultiplier();
        Multiplier actual2 = tileWithLetter.getMultiplier();

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }
    
    @Test
    void getLetter() {
        Letter expected = a;
        
        Letter actual1 = tileWithOutLetter.getLetter();
        Letter actual2 = tileWithLetter.getLetter();
    
        assertEquals(null, actual1);
        assertEquals(expected, actual2);
    }

    @Test
    void getLetterChar(){
        char expected = 'A';

        char actual = tileWithLetter.getLetterChar();

        assertEquals(expected, actual);
    }

    @Test
    void setLetter() {
        Letter expected = a;

        tileWithOutLetter.setLetter(a);
        Letter actual = tileWithOutLetter.getLetter();

        assertEquals(expected, actual);
    }

    @Test
    void getPosition() {
        int[] expected1 = {0, 0};
        int[] expected2 = {1, 2};

        int[] actual1 = {tileWithOutLetter.getPositionX(),tileWithOutLetter.getPositionY()};
        int[] actual2 = {tileWithLetter.getPositionX(),tileWithLetter.getPositionY()};

        assertArrayEquals(expected1, actual1);
        assertArrayEquals(expected2, actual2);
    }

}