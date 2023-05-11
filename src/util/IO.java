package util;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class IO {
    private static IO instance;

    private IO(){
    }

    public static IO getInstance(){
        if(instance == null){
            instance = new IO();
        }
        return instance;
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
