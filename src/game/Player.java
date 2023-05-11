package game;
import java.util.List;

public class Player {
    private List<Letter> letters;
    private int score;
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public void addScore(int scoreToAdd){
        this.score += scoreToAdd;
    }
  
    public int getScore(){
        return this.score;
    }

    public void removeLetters(List<Letter> lettersToBeRemoved){
        
      for (Letter letter : this.letters) {
          for (Letter letterToBeRemoved : lettersToBeRemoved) {
              if (letter.equals(letterToBeRemoved)) {
                  letters.remove(letter);
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
}
