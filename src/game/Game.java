package game;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import board.Board;
import util.IO;
import util.IUI;

public class Game{
    /**
     * To save the BLANK letter in the letter list it needs to be repricented as a char and '#' was chosen
     */
    private ArrayList<Letter> letters;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Board board;
    private IUI ui;

    public Game(ArrayList<Letter> letters, ArrayList<Player> players, Player currentPlayer, Board board) {
        
    }

    private final int defaultWidth = 15;
    private final int defaultHeight = 15;
    private void dataSetup(){
        List<String> dict = new ArrayList<String>();
        List<String> lettersFromFile = new ArrayList<String>();
        try {
            dict = IO.getDataFromTxt("../data/Dictionary.txt");
        } catch (FileNotFoundException e) {
            ui.displayMessage("The dictionary file was not found please look in the data folder and make shure there is a \"Dictionary.txt\" file");
        }
        try {
            lettersFromFile = IO.getDataFromTxt("../data/Letters.csv");
        } catch (FileNotFoundException e) {
            ui.displayMessage("The file with letters and their ammount and score was not found please look in the data folder and make shure there is a \"Letters.csv\" file");
        }
        for (String letterLine : lettersFromFile) {
            String[] splitLetterLine = letterLine.split(",");

            char letterChar = splitLetterLine[0].equals("BLANK") ? '#' : splitLetterLine[0].charAt(0);
            int letterScore = Integer.parseInt(splitLetterLine[2]);
            for (int i = 0; i < Integer.parseInt(splitLetterLine[1]); i++) {
                letters.add(new Letter(letterChar, letterScore));
            }
        }
        board = new Board(defaultWidth, defaultHeight, new HashSet(dict));
    }
    
    private void startGame(){
        String name1 = ui.getInput("player 1. name?");
        ui.displayMessage("player one is "+ name1);
        String name2 = ui.getInput("player 2. name?");
        ui.displayMessage("player two is "+ name2);
        Player player1 = new Player(name1);
        Player player2 = new Player(name2);
        players.add(player1);
        players.add(player2);
    }

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
    private void endGame() {
        ui.displayMessage(  players.get(0).getName()+ ", you have " + players.get(0).getScore() + " points");
        ui.displayMessage(  players.get(1).getName()+ ", you have " + players.get(1).getScore() + " points");

        if (players.get(0).getScore() > players.get(1).getScore()) {
            ui.displayMessage("Congratulations " + players.get(0).getName() + " you win!");
        }
       else if (players.get(0).getScore() < players.get(1).getScore()) {
            ui.displayMessage("Congratulations " + players.get(1).getName() + " you win!");
       }
       else {
            ui.displayMessage("It is a draw!");
        }
       close();
    }
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