package util;

import processing.core.PApplet;
import board.Board;
import processing.core.PShape;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class GUI extends PApplet implements IUI {

    private int width;
    private int height;


    private static GUI instanse;


    public GUI(){
        instanse = this;
        width = 800;
        height = 800;
    }
    public static GUI getInstance(){
        return instanse;
    }

    public void settings(){
        size(width,height);
    }

    public void setup() {
        noLoop();
    }

    private String tmp = "";
    public void draw() {
        background(255);
        fill(0);
        rect(100,100,100,100);
        textSize(20);
    }
    @Override
    public void displayMessage(String msg) {
        // Visually show message on screen
        tmp = msg;
        PShape(msg,100,100);
        fill(100);
        rect(200,200,100,100);
        redraw();
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
