package game;

import game.Letter;
import game.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player name;
    Player addScore;
    Player getScore;
    Letter a;


    @BeforeEach
    void setUp() {
        a = new Letter('A', 1);
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
        Letter expected = a;

        name.addScore(1);
        int actual = name.getScore();
        assertEquals(expected, actual);

    }
    @Test
    void removeLetters() {
    }

    @Test
    void addLetters() {
    }

    @Test
    void getName() {
    }
}