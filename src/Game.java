import java.util.ArrayList;
import java.util.Scanner;

public class Game{
    private ArrayList<Letter> letters;
    private ArrayList<Player> players;
    private Board board;
    private IUI ui;

    public Game() {
    }

    private void dataSetup(){throw new UnsupportedOperationException();}
    private void startGame(){
        String name1 = ui.getInput("player 1. name?");
        ui.displayMessige("player one is "+ name1);
        String name2 = ui.getInput("player 2. name?");
        ui.displayMessige("player two is "+ name2);
        Player player1 = new Player(name1);
        Player player2 = new Player(name2);
        players.add(player1);
        players.add(player2);

        throw new UnsupportedOperationException();}

    private void mainMenu(){throw new UnsupportedOperationException();}
    private void loadSavedGame(){throw new UnsupportedOperationException();}
    private void gameLoop(){



        throw new UnsupportedOperationException();}
    private void endGame(){


        throw new UnsupportedOperationException();}
    private void close(){



        throw new UnsupportedOperationException();}

}