import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Game{
    private ArrayList<Letter> letters;
    private ArrayList<Player> players;
    private Board board;
    private IUI ui;

    private final int defaultWidth = 15;
    private final int defaultHeight = 15;
    private void dataSetup(){
        List<String> dict = new LinkedList<String>();
        try {
            dict = IO.getDataFromTxt("../data/Dictionary.txt");
        } catch (FileNotFoundException e) {
            ui.displayMessige("The dictionary file was not found please look in the data folder and make shure there is a \"Dictionary.txt\" file");
        }
        board = new Board(defaultWidth, defaultHeight, new HashSet(dict));
    }
    private void startGame(){throw new UnsupportedOperationException();}
    private void mainMenu(){throw new UnsupportedOperationException();}
    private void loadSavedGame(){throw new UnsupportedOperationException();}
    private void gameLoop(){throw new UnsupportedOperationException();}
    private void endGame(){throw new UnsupportedOperationException();}
    private void close(){throw new UnsupportedOperationException();}
}