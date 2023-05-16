package board;
import game.Letter;

public class Tile {
    private Letter letter;
    private int positionX ;
    private int positionY;
    private Multiplier multiplier;

    public Tile(int positionX, int positionY, Multiplier multiplier) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.multiplier = multiplier;
    }
    public Tile(int positionX, int positionY, Multiplier multiplier, Letter letter) {
        this(positionX,positionY,multiplier);
        this.letter = letter;
    }
    public int getLetterScore(){
        return letter.getLetterScore();
    }

    public Multiplier getMultiplier(){
        return multiplier;
    }

    public char getLetterChar(){
        return letter.getLetter();
    }

    public void setLetter(Letter letter){
        this.letter = letter;
    }

    public Letter getLetter() {
        return letter;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
}
