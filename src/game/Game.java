package game;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import board.Board;
import util.GUI;
import util.IO;
import util.IUI;
import util.TextUI;

public class Game{
    /**
     * To save the BLANK letter in the letter list it needs to be repricented as a char and '#' was chosen
     */
    private ArrayList<Letter> letters;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Board board;
    private IUI ui;

    public Game() {
        ui = GUI.getInstance();
        letters = new ArrayList<Letter>();
        players = new ArrayList<Player>();
        dataSetup();
        mainMenu();
        close();
    }

    private final int defaultWidth = 15;
    private final int defaultHeight = 15;
    private void dataSetup(){
        List<String> dict = new ArrayList<String>();
        List<String> lettersFromFile = new ArrayList<String>();
        try {
            dict = IO.getDataFromTxt("./data/Dictionary.txt");
        } catch (FileNotFoundException e) {
            ui.displayMessage("The dictionary file was not found please look in the data folder and make shure there is a \"Dictionary.txt\" file");
        }
        try {
            lettersFromFile = IO.getDataFromTxt("./data/Letters.csv");
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
        addRandomLettersToPlayer(7, player1);
        Player player2 = new Player(name2);
        addRandomLettersToPlayer(7, player2);
        players.add(player1);
        players.add(player2);
        gameLoop();
    }

    private void mainMenu(){
        while(true) {

            ui.displayMenu(new String[]{"1) Play game", "2) Load game", "3) Quit game"});
            String option = ui.getInput("Please type number to choose option");
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
    
    private void gameLoop(){
        currentPlayer = players.get(0);
        while (true) {
            ui.displayMessage("Current player is: " + currentPlayer.getName());
            ui.displayMenu(new String[]{"1) Place letter(s)","2) Extange letter(s)","3) End the game","4) Save game"});
            String option = ui.getInput("Please type number to choose option");
            switch (option) {
                case "1":
                    plaseLetters();
                    break;
                case "2":
                    extangeLetters();
                    break;
                case "3":
                    return;
                case "4":
                    // save game
                    break;
                default:
                    ui.displayMessage("You did not choose one of the given options please choose");
                    break;
            }
            
            currentPlayer = players.get((players.indexOf(currentPlayer) + 1) % players.size());
        }
    }
    
    
    private void plaseLetters() {
        displayPlayerLetters(currentPlayer);
        ui.displayMessage("Choose where to plase what letter in this format: x,y,letter. Or press enter to signal you are done with your selection");
        List<Letter> toBePlasedLetters = new ArrayList<Letter>();
        while(true){
            String input = ui.getInput("Next letter or confirm selection");
            if (input.equals("")) {
                int playerScore = board.checkWord();
                if (playerScore == -1) {
                    ui.displayMessage("you did not plase a valid word please try again");
                    plaseLetters();
                }
                currentPlayer.removeLetters(toBePlasedLetters);
                currentPlayer.addScore(playerScore);
                addRandomLettersToPlayer(toBePlasedLetters.size(), currentPlayer);
                displayPlayerLetters(currentPlayer);
                return;
            }
            String[] letter = input.replaceAll(" *", "").split(",");
            board.placeLetter(Integer.parseInt(letter[0]), Integer.parseInt(letter[1]), currentPlayer.getLetter(letter[2].charAt(0)));
        }
    }
    
    
    private void extangeLetters() {
        displayPlayerLetters(currentPlayer);
        String input = ui.getInput("Choose what letters to replace");
        List<Letter> lettersToReplace = new ArrayList<>();
        for(char c : input.replaceAll("\\W*,*", "replacement").toCharArray()){
            lettersToReplace.add(currentPlayer.getLetter(c));
        }
        //TODO: order of operations, skal dem man bytter først tilføges til letters listen eller skal man først trejke nye og så tilføge dem man vil a med til listen
        currentPlayer.removeLetters(lettersToReplace);
        letters.addAll(lettersToReplace);
        addRandomLettersToPlayer(lettersToReplace.size(), currentPlayer);
    }
    
    private void displayPlayerLetters(Player player) {
        String letters = "";
        for (Letter letter : player.getLetters()) {
            //letters += letter.toString() + ", ";
            letters += letter.getLetter();
        }
        ui.displayMessage("This is your letters:");
        ui.displayMessage(letters);
    }
    
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
            letters.remove(letter); //TODO : Test that letters we try to remove exist in letters list
        }
    }

    /**
     * This method can take list of letters from player, requested by the player, add them to this letter list
     * @List
     */
    private void addRandomLettersToPlayer(int amountOfLetters, Player player) {
        // Find random letters from letters list and use that list as parameter in player.addToLetters
        // remove from games list of letters
        Random random = new Random();
        ArrayList<Letter> randomLetters = new ArrayList<>();
        for(int i = 0; i < amountOfLetters; i++) {
            int intRandom = random.nextInt(letters.size());
            Letter currentLetter = letters.get(intRandom);
            randomLetters.add(currentLetter);
        }

        player.addLetters(randomLetters);
        removeLetters(randomLetters);
    }

}