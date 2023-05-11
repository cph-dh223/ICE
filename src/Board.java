import java.util.List;
import java.util.Set;

public class Board {
    private Tile[][] tiles;
    private List<Tile> toBePlaced;
    private Set<String> dict;
    private int width;
    private int height;

    public Board(int width, int height, Set<String> dict){
        throw new UnsupportedOperationException();
    }
    public void placeLetter(int posX, int posY, Letter letter){
        Tile = tiles;
        // Find tiles in the tile list with the X and Y value.
        // Save temp tile.
        // Take new tile that you have saved and add the Letter that you get in the parameters.
        // Add tobeplaced in the temp list.
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

