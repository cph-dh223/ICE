package util;

import processing.core.PApplet;
import board.Board;

import java.util.ArrayList;

public class GUI extends PApplet implements IUI {

    private int width;
    private int height;

    public GUI (int width, int height) {
        this.width = width;
        this.height = height;
        PApplet.main("Main"); // Calls settings, then setup, then draw
    }

    public void settings(){
        size(width,height);
    }

    public void setup() {
        noLoop();
    }

    public void draw() {
        background(100);
        rect(100,100,100,100);
    }
    @Override
    public void displayMessage(String msg) {
        // Visually show message on screen

        // noLoop
    }

    @Override
    public void displayMainMenu(ArrayList<String> menuFields) {

    }

    @Override
    public String getInput(String msg) {
        return null;
    }

    @Override
    public void displayBoard(Board board) {

    }
}
