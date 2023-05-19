package game;

public class Letter {
    public final char letter;
    public final int letterScore;

    public Letter(char letter, int letterScore){
        this.letter = letter;
        this.letterScore = letterScore;
    }

    public char getLetter() {
        return letter;
    }

    public int getLetterScore() {
        return letterScore;
    }

    @Override
    public String toString(){
        return ""+getLetter();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof Letter)){
            return false;
        }
        Letter other = (Letter)obj;
        if(other.getLetter() == letter){
            return true;
        }
        return false;
    }
}
