package game;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Letter> letters = new ArrayList<>();
    private int score;
    private String name;

    public Player(String name) {
        this.name = name;
        score = 0;
        letters = new ArrayList<>();
    }

    public void addScore(int scoreToAdd){
        this.score += scoreToAdd;
    }
  
    public int getScore(){
        return this.score;
    }

    public void removeLetters(List<Letter> lettersToBeRemoved){
        
        for (int i = letters.size()-1; i >= 0; i--) {
            Letter letter = letters.get(i);
            if(lettersToBeRemoved.size() == 0) { return; }
            for (int j = lettersToBeRemoved.size()-1; j >= 0 ; j--) {
                Letter letterToBeRemoved = lettersToBeRemoved.get(j);
                if (letter.equals(letterToBeRemoved)) {
                    letters.remove(letter);
                    lettersToBeRemoved.remove(letterToBeRemoved);
                }
            }
        }
    }

    public void addLetters(List<Letter> lettersToBeAdded){
        for (Letter letter : lettersToBeAdded) {
            this.letters.add(letter);
        }
    }

    public String getName(){
        return this.name;
    }

    public List<Letter> getLetters() {
        return letters;
    }

    public Letter getLetter(char c) {
        for (Letter letter : letters) {
            if(letter.getLetter() == c){
                return letter;
            }
        }
        return null;
    }
}
