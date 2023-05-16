package board;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.IO;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IOTest {

    @Test
    void getInstance() {
    }

    @Test
    void getDataFromTxt() {
        // Arrange
        String path = "testfile.txt";
        String expectedLine1 = "Line 1";
        String expectedLine2 = "Line 2";

        try {

            List<String> data = IO.getDataFromTxt(path);


            Assertions.assertEquals(2, data.size());
            Assertions.assertEquals(expectedLine1, data.get(0));
            Assertions.assertEquals(expectedLine2, data.get(1));
        } catch (FileNotFoundException e) {
            Assertions.fail("File not found exception");
        }
    }
}