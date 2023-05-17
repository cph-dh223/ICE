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
                if(i == 0 && j == 0 || i == 7 && j == 0 || i == 14 && j == 0 || i == 0 && j == 7 || i == 14 && j == 7 || i == 0 && j == 14 || i == 7 && j == 14 || i == 14 && j == 14) {
                    tiles[i][j] = new Tile(i, j, Multiplier.TRIPLE_WORD);
                }
                    else if(i == 3 && j == 0 || i == 11 && j == 0 || i == 6 && j == 2 || i == 8 && j == 2 || i == 0 && j == 3 || i == 7 && j == 3 || i == 14 && j == 3 || i == 2 && j == 6 || i == 6 && j == 6 || i == 8 && j == 6 || i == 12 && j == 6 || i == 3 && j == 7 || i == 11 && j == 7 || i == 2 && j == 8 || i == 6 && j == 8 || i == 8 && j == 8 || i == 12 && j == 8 || i == 0 && j == 11 || i == 7 && j == 11 || i == 14 && j == 11 || i == 6 && j == 12 || i == 8 && j == 12 || i == 3 && j == 14 || i == 11 && j == 14) {
                        tiles[i][j] = new Tile(i, j, Multiplier.DOUBLE_LETTER);
                    }
                    else if(i == 1 && j == 1 || i == 13 && j == 1 || i == 2 && j == 2 || i == 12 && j == 2 || i == 3 && j == 3 || i == 11 && j == 3 || i == 4 && j == 4 || i == 10 && j == 4 || i == 7 && j == 7 || i == 4 && j == 10 || i == 10 && j == 10 || i == 3 && j == 11 || i == 11 && j == 11 || i == 2 && j == 12 || i == 12 && j == 12 || i == 1 && j == 13 || i == 13 && j == 13){
                    tiles[i][j] = new Tile(i, j, Multiplier.DOUBLE_WORD);
                }
                    else if(i == 5 && j == 1 || i == 9 && j == 1 || i == 1 && j == 5 || i == 5 && j == 5 || i == 9 && j == 5 || i == 13 && j == 5 || i == 1 && j == 9 || i == 5 && j == 9 || i == 9 && j == 9 || i == 13 && j == 9 || i == 5 && j == 13 || i == 9 && j == 13){
                    tiles[i][j] = new Tile(i, j, Multiplier.TRIPLE_LETTER);
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
        return width;
    }
    public int getHeight()    {
        return height;
    }
    public char getLetter(int x, int y){
        throw new UnsupportedOperationException();
    }

    public Tile getTile(int x, int y){
        return tiles[x][y];
    }
}

