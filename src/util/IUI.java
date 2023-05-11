import board.Board;

package util;
public interface IUI {
    public void displayMessage(String msg);
    public String getInput(String msg);
    public void displayBoard(Board board);
}
