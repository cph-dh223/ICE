package board;
import java.util.List;
import java.util.Set;

import game.Letter;

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
    /**
     * This method assumes that there is more than letter that needs to be plased
     * @return the direction of the word that is beeing plased
     */
    private Direction getWordDirection() {
        if (toBePlased.get(0).getPositionX() == toBePlased.get(1).getPositionX()) {
            return Direction.VERTICAL;
        } else {
            return Direction.HORISONTAL;
        }

    }
}

