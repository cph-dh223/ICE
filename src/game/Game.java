package game;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import board.Board;
import util.IUI;

public class Game{
    private ArrayList<Letter> letters;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Board board;
    private IUI ui;
    private void dataSetup(){throw new UnsupportedOperationException();}
    private void startGame(){throw new UnsupportedOperationException();}
    private void mainMenu(){
        ui.displayMessage("1) Play game");
        ui.displayMessage("2) Load game");
        ui.displayMessage("3) Quit game");
        String option = ui.getInput("Please type number to choose option");
        while(true) {
        switch(option) {
            case "1":
                startGame();
                break;
            case "2":
                loadSavedGame();
                break;
            case "3":
                endGame();
                break;
            default:
                ui.displayMessage("The input did not match any of the options, please try again");
            }
        }
    }
    private void loadSavedGame(){throw new UnsupportedOperationException();}
    private void gameLoop(){throw new UnsupportedOperationException();}
    private void endGame(){throw new UnsupportedOperationException();}
    private void close(){throw new UnsupportedOperationException();}
    private void removeLetters(List<Letter> takenLetters) {
        for(Letter letter : takenLetters) {
            this.letters.remove(letter); //TODO : Test that letters we try to remove exist in letters list
        }
    }

    /**
     * This method can take list of letters from player, requested by the player, add them to this letter list
     * @List
     */
    private List<Letter> addRandomLettersToPlayer(int amountOfLetters) {
        // Find random letters from letters list and use that list as parameter in player.addToLetters
        // remove from games list of letters
        Random random = new Random();
        ArrayList<Letter> randomLetters = new ArrayList<>();
        for(int i = 0; i < amountOfLetters; i++) {
            int intRandom = random.nextInt(this.letters.size());
            Letter currentLetter = this.letters.get(intRandom);
            randomLetters.add(currentLetter);
        }

        currentPlayer.addLetters(randomLetters);
        return randomLetters;
    }
}