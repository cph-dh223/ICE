package util;

import processing.core.*;
import board.Board;
import board.Multiplier;
import board.Tile;

import java.util.ArrayList;

public class GUI extends PApplet implements IUI {

    private final int width;
    private final int height;
    private PGraphics pg;
    private static GUI instanse;
    private final int sizeOfText = 50;
    private PGraphics textBox;
    private String inputText;
    private PImage boardImage;

    TextUI tui = new TextUI();

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
        //noLoop();
        pg = createGraphics(width,height);
        textBox = createGraphics(width,height);
    }

    public void draw() {
        background(255);
        fill(0);
        image(pg,0,0);
        image(textBox,0,0);
        if(boardImage != null){
            image(boardImage,width/2-(boardImage.width/2),0);
        }
        if(pg.image != null){
            pg.clear();
        }
    }
    
    @Override
    public void displayMessage(String msg) {
        // Visually show message on screen
        pg.beginDraw();
        pg.fill(0);
        pg.text(msg, pg.width/2 - textWidth(msg),500);
        pg.endDraw();

        redraw();
        // noLoop
    }

    /**
     * This method can display any menu. The options are desplayed in the middle of the screen
     * @param menuFields the list of menu options
     */
    @Override
    public void displayMenu(String[] menuFields) {
        pg.beginDraw();
        pg.background(255);
        pg.fill(0);
        pg.textSize(sizeOfText);
        pg.strokeWeight(15);
        int posX = (width/2) - (int)textWidth(menuFields[0])*2;
        int spasing = sizeOfText + (int)(sizeOfText*0.2);
        for (int i = 0; i < menuFields.length; i++) {
            int posY = ((height*3)/4) - (spasing * menuFields.length/2) + (spasing * i) ;
            pg.text(menuFields[i], posX, posY);
        }
        pg.endDraw();
        redraw();
    }

    @Override
    public String getInput(String msg) {
        // Returns key pressed as a String(1, 2, 3 or other...)

        System.out.println(msg);
        String option = getInputTextBox();
        //String option = String.valueOf(getInputMainMenu());
        return option;

    }

    private String getInputCoordinates() {
        String coordinates = "mouseX: ";
        while(true) {
            System.out.println("Waiting for input"); // TODO: Some line has to be here for some reason??
            if (mousePressed) {
                coordinates += mouseX + " mouseY: " + mouseY; // Generic method for getting exact input
                System.out.println(coordinates);
                return coordinates;
            }
        }
    }

    // Visual TextBox as a replacement for console
    private String getInputTextBox() {
        inputText = "";
        String tmpText;
        while(true) {
            if (keyPressed) {
                if (keyCode == ENTER) {
                    tmpText = inputText;
                    textBox.beginDraw();
                    textBox.clear();
                    textBox.endDraw();
                    return tmpText;
                }
            }
            displayMessage(""); // TODO: Some line has to be here for some reason??
        }
    }


    private void displayTextBox() {
        textBox.beginDraw();
        textBox.fill(0);
        textBox.textSize(30);
        textBox.text(inputText, width/4, height-60);
        textBox.endDraw();
        redraw();
    }

    private char getInputMainMenu() {
        while (true) {
            System.out.println("Waiting for input"); // TODO: Some line has to be here for some reason??
            if(keyPressed){
                return key;
            }
        }
    }

    @Override
    public void displayBoard(Board board) {
        PGraphics boardGraphic = createGraphics(400,400);

        int tileSize = boardGraphic.width/board.getWidth();
        int strokeWeight = 5;
        boardGraphic.beginDraw();
        boardGraphic.stroke(0);
        boardGraphic.strokeWeight(strokeWeight);
        boardGraphic.fill(255);
        boardGraphic.textSize(strokeWeight*4);
        for(int i = 0; i < board.getWidth(); i++){
            for (int j = 0; j < board.getHeight(); j++) {
                Tile currentTile =board.getTile(i, j);
                boardGraphic.fill(255);
                if(currentTile.getMultiplier() == Multiplier.DOUBLE_LETTER){
                    boardGraphic.fill(150,150,255);
                }
                if(currentTile.getMultiplier() == Multiplier.DOUBLE_WORD){
                    boardGraphic.fill(255,150,150);
                }
                if(currentTile.getMultiplier() == Multiplier.TRIPLE_LETTER){
                    boardGraphic.fill(0,0,255);
                }
                if(currentTile.getMultiplier() == Multiplier.TRIPLE_WORD){
                    boardGraphic.fill(255,0,0);
                }
                
                boardGraphic.rect(i * tileSize + strokeWeight/2,j*tileSize + strokeWeight/2,tileSize,tileSize);
                if(board.getTile(i, j).getLetter() != null) {
                    boardGraphic.fill(90);
                    char tileChar = board.getTile(i, j).getLetterChar();
                    boardGraphic.text(tileChar, (i * tileSize) + (int)(tileSize/2.5) ,j*tileSize + tileSize);
                    boardGraphic.stroke(0);
                    boardGraphic.fill(255);
                }
            }
        }
        boardGraphic.endDraw();
        boardImage = boardGraphic;
        redraw();
    }

    @Override
    public void keyPressed() {

        if (key >= 'A' && key <= 'z' || key >= '0' && key <= '9') {
            inputText += key;
        }
        displayTextBox();
    }
}
