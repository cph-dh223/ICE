package game;
import java.util.List;

public class Player {
    private List<Letter> letters;
    private int score;
    private String name;




    public void addScore(int scoreToAdd){

        //throw new UnsupportedOperationException();

        this.score += scoreToAdd;


    }




    public int getScore(){
        //throw new UnsupportedOperationException();

        return this.score;


    }





    public void removeLetters(List<Letter> lettersToBeRemoved){

        //throw new UnsupportedOperationException();

        for (Letter letter : this.letters) {

            for (Letter letterToBeRemoved : lettersToBeRemoved) {

                if (letter.equals(letterToBeRemoved)) {

                    letters.remove(letter);

                }

            }
        }


    }





    public void addLetters(List<Letter> lettersToBeAdded){
        //throw new UnsupportedOperationException();


        for (Letter letter : lettersToBeAdded) {

            this.letters.add(letter);

        }


    }






    public String getName(){
        //throw new UnsupportedOperationException();

        return this.name;

    }
}
