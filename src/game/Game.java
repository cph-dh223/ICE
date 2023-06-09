package game;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
    private Set<String> dict;

    public Game() {
        
        //ui = new TextUI();
        ui = GUI.getInstance();
        letters = new ArrayList<Letter>();
        players = new ArrayList<Player>();
        dataSetup();
        mainMenu();
    }

    private final int defaultWidth = 15;
    private final int defaultHeight = 15;
    private void dataSetup(){
        List<String> loadedDict = new ArrayList<String>();
        List<String> lettersFromFile = new ArrayList<String>();
        try {
            loadedDict = IO.getDataFromTxt("./data/Dictionary.txt");
        } catch (FileNotFoundException e) {
            ui.displayMessage("The dictionary file was not found please look in the data folder and make shure there is a \"Dictionary.txt\" file");
        }
        try {
            lettersFromFile = IO.getDataFromTxt("./data/LettersWOblank.csv");
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
        dict = new HashSet(loadedDict);
    }
    
    private void startGame(){
        String name1 = ui.getInput("player 1. name?");
        ui.displayMessage("player one is: "+ name1);
        String name2 = ui.getInput("player 2. name?");
        ui.displayMessage("player two is: "+ name2);
        Player player1 = new Player(name1);
        addRandomLettersToPlayer(7, player1);
        Player player2 = new Player(name2);
        addRandomLettersToPlayer(7, player2);
        players.add(player1);
        players.add(player2);
        
        board = new Board(defaultWidth, defaultHeight, dict);
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



    private void saveGame(){
        IO.saveData(players,board);
    }


    private void loadSavedGame(){throw new UnsupportedOperationException();}
    
    private void gameLoop(){
        currentPlayer = players.get(0);
        while (true) {
            ui.displayMessage("Current player is: " + currentPlayer.getName());
            ui.displayMenu(new String[]{"1) Place letter(s)","2) Extange letter(s)","3) End the game","4) Save game"});
            displayPlayerLetters(currentPlayer);
            ui.displayBoard(board);
            String option = ui.getInput("Please type number to choose option");
            switch (option) {
                case "1":
                    placeLetters();
                    break;
                case "2":
                    extangeLetters();
                    break;
                case "3":
                    return;
                case "4":
                    saveGame();
                    break;
                default:
                    ui.displayMessage("You did not choose one of the given options please choose");
                    continue;
            }
            
            currentPlayer = players.get((players.indexOf(currentPlayer) + 1) % players.size());
        }
    }
    
    
    private void placeLetters() {
        ui.displayMessage("Choose where to place what letter in this format: \nx,y,letter.");
        List<Letter> toBePlacedLetters = new ArrayList<>(1);
        String input;
        while(true){
            ui.displayBoard(board);
                input = ui.getInput("Next letter or type 'y' to confirm selection");
            if (input.equalsIgnoreCase("y")) {
                int playerScore = board.checkSubmittedLetters();

                if (playerScore == -1) {
                    ui.displayMessage("You did not place a valid word. Please try again");
                    placeLetters();
                    return;
                }

                board.updateBoard();
                int toBePlacedLettersSize = toBePlacedLetters.size();
                currentPlayer.removeLetters(toBePlacedLetters);
                currentPlayer.addScore(playerScore);
                ui.displayMessage("You got " + playerScore + " points");
                addRandomLettersToPlayer(toBePlacedLettersSize, currentPlayer);
                displayPlayerLetters(currentPlayer);
                return;
            }

            String[] letter = input.replaceAll(" *", "").split(",");
            try {
                board.placeLetter(Integer.parseInt(letter[0]), Integer.parseInt(letter[1]), currentPlayer.getLetter(Character.toUpperCase(letter[2].charAt(0))));
            } catch(ArrayIndexOutOfBoundsException | NumberFormatException e) {
                continue;
            }
            // Tilføjet af mig
            Letter toBePlacedLetter = currentPlayer.getLetter(letter[2].charAt(0));
            toBePlacedLetters.add(toBePlacedLetter);
            System.out.println("Size of the list of letters used: " + toBePlacedLetters.size());

        }
    }
    
    
    private void extangeLetters() {

        if(letters.size() == 0){
            ui.displayMessage("There are no more letters in the bag of letters this forfits your turn");//TODO: bedere way of handeling this
        }
        displayPlayerLetters(currentPlayer);
        String input = ui.getInput("Choose what letters to replace");
        List<Letter> lettersToReplace = new ArrayList<>();
        char[] charsToReplase = input.toUpperCase().replaceAll(" *,*", "").toCharArray();
        for(char c : charsToReplase){
            lettersToReplace.add(currentPlayer.getLetter(c));
        }
        int lettersToReplaceSize = lettersToReplace.size();
        currentPlayer.removeLetters(lettersToReplace);
        letters.addAll(lettersToReplace);
        addRandomLettersToPlayer(lettersToReplaceSize, currentPlayer);
    }
    
    private void displayPlayerLetters(Player player) {
        ui.displayHand(player.getName(), player.getLetters());
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
    }


    private void removeLetters(List<Letter> takenLetters) {
        for(Letter letter : takenLetters) {
            letters.remove(letter); //TODO : Test that letters we try to remove exist in letters list
        }
    }

    /**
     * This method can take list of letters from player, requested by the player, add them to this letter list
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