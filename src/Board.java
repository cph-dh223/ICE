import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Board {
    private Tile[][] tiles;
    private List<Tile> toBePlaced;
    private Set<String> dict;
    private int width;
    private int height;

    public Board(int width, int height, Set<String> dict){
        List<Tile> toBePlaced = new ArrayList<>();
    }
    public void placeLetter(int posX, int posY, Letter letter){
        Tile tile = new Tile(posX,posY,tiles[posX][posY].getMultiplier(),letter);
        toBePlaced.add(tile);
    }
    public boolean checkSurroundingWords(){
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

