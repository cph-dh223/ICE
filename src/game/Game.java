package game;
import java.util.ArrayList;

import board.Board;
import util.IUI;

public class Game{
    private ArrayList<Letter> letters;
    private ArrayList<Player> players;
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
}