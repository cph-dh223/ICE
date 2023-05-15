package util;

import processing.core.PApplet;
import board.Board;
import processing.core.PGraphics;
import processing.core.PShape;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class GUI extends PApplet implements IUI {

    private int width;
    private int height;
    private PGraphics pg;
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
        pg = createGraphics(width,height);
    }

    public void draw() {
        background(255);
        fill(0);
        rect(100,100,100,100);
        textSize(20);
        if(pg != null){
            image(pg,0,0);
        }
    }
    @Override
    public void displayMessage(String msg) {
        // Visually show message on screen
        pg.beginDraw();
        fill(0);
        text(msg,100,100);
        pg.endDraw();
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
