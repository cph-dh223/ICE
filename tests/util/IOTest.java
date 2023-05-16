package util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.IO;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class IOTest {

    @Test
    public void testGetInstance() {

        IO expectedInstance = IO.getInstance();


        IO actualInstance = IO.getInstance();


        assertEquals(expectedInstance, actualInstance);
    }


    @Test
    public void testGetDataFromTxt() throws FileNotFoundException {

        String path = "./data/testfile.txt";


        List<String> expectedData = List.of("Line 1", "Line 2");



            List<String> actualData = IO.getDataFromTxt(path);


            Assertions.assertEquals(expectedData, actualData);

    }
}
