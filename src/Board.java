import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Board {
    private Tile[][] tiles;
    private List<Tile> toBePlaced;
    private Set<String> dict;
    private int width;
    private int height;

    public Board(int width, int height, Set<String> dict) throws FileNotFoundException {
        Tile[][] tiles = new Tile[15][15];
        width = 20;
        height = 20;
        File file = new File("Dictionary.txt");
        Scanner sc = new Scanner(file);
        dict = new HashSet<String>();
    }
    public void placeLetter(int posX, int posY, Letter letter){
        throw new UnsupportedOperationException();
    }
    public boolean checkSuroundingWords(){
        throw new UnsupportedOperationException();
    }
    public int checkWord(){
        throw new UnsupportedOperationException();
    }
    public void updateBoard(){
        throw new UnsupportedOperationException();
    }
    private Direction getWordDirection()    {
        throw new UnsupportedOperationException();
    }
}

