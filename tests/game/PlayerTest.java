package game;

import game.Letter;
import game.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player name;
    Player addScore;
    Player getScore;
    Letter a;


    @BeforeEach
    void setUp() {
        a = new Letter('a', 1);
        name = new Player("Alex");
    }

    @AfterEach
    void tearDown() {
        addScore = null;
        getScore = null;
    }
    @Test
    void getScore() {
        int expected = 1;
        int actual = name.getScore();
        assertEquals(expected, actual);
    }
    @Test
    void addScore() {
        int expected = 1;

        name.addScore(1);
        int actual = name.getScore();
        assertEquals(expected, actual);

    }
    @Test
    void  removeLetters() {
        Letter expected = null;
        ArrayList<Letter> addLetters = new ArrayList<>();
        addLetters.add(new Letter('b', 2));
        name.addLetters(addLetters);
        name.removeLetters(addLetters);
        Letter actual = name.getLetter('b');
        assertEquals(expected, actual);
    }

    @Test
    void addLetters() {
        ArrayList<Letter> addLetter = new ArrayList<>();
        addLetter.add(new Letter('b', 2));
        name.addLetters(addLetter);
        Letter actual = name.getLetter('b');
        assertNotNull(actual);
    }

    @Test
    void getName() {
        Player expected = name;

        Player actual = name;

        assertEquals(expected, actual);
    }
    @Test
    void testSetup(){
        assertEquals("Alex", name.getName());
    }
}