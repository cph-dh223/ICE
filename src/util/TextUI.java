package util;

import java.util.Scanner;

import board.Board;

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
                    System.out.print('*');
                    continue;
                }
                if(i == -1 || i == board.getWidth()){
                    System.out.print('-');
                    continue;
                }
                if (j == -1 || j == board.getHeight()) {
                    System.out.print('|');
                    continue;
                }
                char letter = board.getLetter(i, j);
                
                System.out.print(' ' + letter + ' ');
            }
            System.out.println();
        }
    }
}
