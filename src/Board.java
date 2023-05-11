import java.util.List;
import java.util.Set;

public class Board {
    private Tile[][] tiles;
    private List<Tile> toBePlaced;
    private Set<String> dict;
    private int width;
    private int height;

    public Board(int width, int height, Set<String> dict){
        Tile[][] tiles = new Tile[15][15];
        width = 20;
        height = 20;
        
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

