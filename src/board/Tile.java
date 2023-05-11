package board;
import game.Letter;

public class Tile {
    private Letter letter;
    private int positionX ;
    private int positionY;
    private Multiplier multiplier;

    public Tile(int positionX, int positionY, Multiplier multiplier){
        throw new UnsupportedOperationException();
    }
  
    public int getLetterScore(){
        return letter.getLetterScore();
    }

    public Multiplier getMultiplier(){
        return multiplier;
    }

    public char getLetter(){
        return letter.getLetter();
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
}
