package util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class IO {
    private static IO instanse;

    private IO(){
    }

    public static IO getInastasne(){
        if(instanse == null){
            instanse = new IO();
        }
        return instanse;
    }


    public static List<String> getDataFromTxt(String path) throws FileNotFoundException{
        List<String> data = new LinkedList<>();

        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()){
            data.add(scanner.nextLine());
        }
        return data;
    }
}
