
package util;
import board.Board;

import java.util.ArrayList;

public interface IUI {
    public void displayMessage(String msg);
    public void displayMenu(String[] menuFields);
    public String getInput(String msg);
    public void displayBoard(Board board);
}
