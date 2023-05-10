import java.util.List;
import java.util.Set;

public class Board {
    private Tile[][] tiles;
    private List<Tile> toBePlased;
    private Set<String> dict;
    private int width;
    private int height;

    public Board(int width, int height, Set<String> dict){
        throw new UnsupportedOperationException();
    }
    public void plaseLetter(int posX, int posY, Letter letter){
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

