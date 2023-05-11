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

    public static List<String> getDataFromTxt(String path){
        Scanner scanner = new Scanner(path);
        List<String> data = new LinkedList<>();
        while (scanner.hasNext()){
            data.add(scanner.nextLine());
        }
        return data;
    }
}
