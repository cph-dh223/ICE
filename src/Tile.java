
public class Tile {
    private Letter letter;
    private int positionX ;
    private int positionY;
    private Multiplier multiplier;

    public Tile(int positionX, int positionY, Multiplier multiplyer){
        throw new UnsupportedOperationException();
    }

    public Tile(int positionX, int positionY, Multiplier multiplier, Letter letter) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.multiplier = multiplier;
        this.letter = letter;
    }

    public int getLetterScore(){
        throw new UnsupportedOperationException();
    }
    public Multiplier getMultiplier(){
        throw new UnsupportedOperationException();
    }
    public char getLetter(){
        throw new UnsupportedOperationException();
    }
    public char setLetter(){throw new UnsupportedOperationException();}


}
