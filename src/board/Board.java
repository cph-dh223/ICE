package board;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import game.Letter;

public class Board {
    private Tile[][] tiles;
    private List<Tile> toBePlaced;
    private Set<String> dict;
    private int width;
    private int height;

    public Board(int width, int height, Set<String> dict){
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        initTiles();
        this.dict = dict;
        toBePlaced = new ArrayList<>();
    }


    private void initTiles() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = new Tile(i, j, Multiplier.NORMAL);
                if(tiles[posX][posY] = new Tile(1,1, Multiplier.NORMAL)){

                }
            }
        }
    }
    // If statements: if this tile is on certain x,y coordinate then the multiplier is set to ____
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
    /**
     * This method assumes that there is more than letter that needs to be plased
     * @return the direction of the word that is beeing plased
     */
    private Direction getWordDirection() {
        if (toBePlaced.get(0).getPositionX() == toBePlaced.get(1).getPositionX()) {
            return Direction.VERTICAL;
        } else {
            return Direction.HORISONTAL;
        }

    }
    public int getWidth()    {
        throw new UnsupportedOperationException();
    }
    public int getHeight()    {
        throw new UnsupportedOperationException();
    }
    public char getLetter(int x, int y){
        throw new UnsupportedOperationException();
    }
}

