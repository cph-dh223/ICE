package util;
import java.util.List;

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
        throw new UnsupportedOperationException();
    }
}
