package util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.IO;

import java.io.FileNotFoundException;
import java.time.Instant;
import java.util.List;

//import static org.junit.Assert.assertEquals;

public class IOTest {

    @Test
    public void testGetDataFromTxt() throws FileNotFoundException {

        String path = "./data/testfile.txt";


        List<String> expectedData = List.of("Line 1", "Line 2");



            List<String> actualData = IO.getDataFromTxt(path);


            Assertions.assertEquals(expectedData, actualData);

    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getInstance() {
    }

    @Test
    void getDataFromTxt() {
    }

    @Test
    void saveBoard() {
    }

    @Test
    void savePlayer() {
    }

    @Test
    void saveData() {
    }
}
