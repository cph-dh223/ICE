
package util;
import board.Board;
import game.Letter;

import java.util.ArrayList;
import java.util.List;

public interface IUI {
    public void displayMessage(String msg);
    public void displayMenu(String[] menuFields);
    public String getInput(String msg);
    public void displayBoard(Board board);
    public void displayHand(List<Letter> letters);
}
