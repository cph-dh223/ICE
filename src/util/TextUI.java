package util;

import java.util.Scanner;

import board.Board;

public class TextUI implements IUI{

    Scanner scanner;

    public TextUI(){
        scanner = new Scanner(System.in);
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

}
