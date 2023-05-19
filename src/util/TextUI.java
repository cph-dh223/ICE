package util;

import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import board.Board;
import board.Tile;
import game.Letter;

public class TextUI implements IUI{

    Scanner scanner;

    public TextUI(){
        scanner = new Scanner(System.in);
    }

    @Override
    public void displayBoard(Board board) {

        for (int i = -1; i < board.getWidth()+1; i++) {
            for (int j = -1; j < board.getHeight()+1; j++) {

                if (i == -1 && j == -1 || 
                    i == board.getWidth() && j == -1 ||
                    i == -1 && j == board.getHeight() ||
                    i == board.getWidth() && j == board.getHeight()) {
                    //System.out.print('*');
                    continue;
                }
                if(i == -1 || i == board.getWidth()){
                    //System.out.print(" "+j+" ");
                    continue;
                }
                if (j == -1 || j == board.getHeight()) {
                    //System.out.print(" "+i+" ");
                    continue;
                }

                char letter = board.getLetter(i, j);
                // DISPLAY ADDED LETTERS
                for(Tile tile : board.getToBePlaced()) {
                    if (tile.getPositionX() == i && tile.getPositionY() == j) {
                        letter = tile.getLetterChar();
                    }
                }
                
                System.out.print("|" + letter + "");
            }
            System.out.println();
        }
    }
  
    @Override
    public String getInput(String msg) {
        System.out.println(msg);
        return scanner.nextLine();
    }

    @Override
    public void displayMessage(String msg) {
        System.out.println(msg);
    }

    @Override
    public void displayMenu(String[] menuFields) {
        for (String field : menuFields) {
            System.out.println(field);
        }
    }

    @Override
    public void displayHand(String playerName, List<Letter> letters){

        String lettersString = "";

        for (Letter letter : letters) {
            lettersString += letter.toString() + "  ";
        }

        String newLetterString = "    ";
        for (Letter letter : letters) {

            newLetterString += letter.getLetter() + " = " + letter.getLetterScore()+"    ";
        }

        lettersString += newLetterString;
        System.out.println(lettersString);
    }
}
