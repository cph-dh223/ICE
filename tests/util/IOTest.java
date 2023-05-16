package util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class IOTest {

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
        String path = "testfile.txt";
        String expectedLine1 = "Line 1";
        String expectedLine2 = "Line 2";


        Scanner scan = new Scanner(path);


            A
            Assertions.assertEquals(expectedLine1, data.get(0));
            Assertions.assertEquals(expectedLine2, data.get(1));

    }
}