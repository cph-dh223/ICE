package util;

import game.Letter;
import processing.core.*;
import board.Board;
import board.Multiplier;
import board.Tile;

import java.util.List;

public class GUI extends PApplet implements IUI {
    private static GUI instanse;
    private String inputText = "";
    private final int sizeOfText = 35;
    private PGraphics textBox;
    private PGraphics menuGraphic;
    private PGraphics msgGraphic;
    private PGraphics handGraphic;
    private PImage boardImage;
    private int finalMouseX;
    private int finalMouseY;
    private int tileSize = -1;
    private char[] playerLetters;
    boolean enter;
    boolean waitingForKey;


    public GUI(){
        instanse = this;

    }
    public static GUI getInstance(){
        return instanse;
    }

    public void settings(){
        fullScreen();
        //size(1920,1080);
    }

    public void setup() {
        textBox = createGraphics(width,height);
        noLoop();
        menuGraphic = createGraphics(width/2,height/2);
        msgGraphic = createGraphics(width,height/10+sizeOfText*2);
        handGraphic = createGraphics(width/2, height/5);
    }

    public void draw() {
        background(255);
        fill(0);
        image(textBox,0,0);
        image(menuGraphic,20,50);
        image(msgGraphic,20,(3 * height)/4-sizeOfText);
        image(handGraphic,width/2,height - sizeOfText * 2);
        if(boardImage != null){
            image(boardImage,width/2,0);
        }
    }

    @Override
    public void displayMessage(String msg) {
        // Visually show message on screen
        msgGraphic.beginDraw();
        msgGraphic.background(255);
        msgGraphic.fill(0);
        msgGraphic.textSize(sizeOfText);
        msgGraphic.text(msg, 0,50);
        msgGraphic.endDraw();
        redraw();
        delay(msg.length()*50);
        waitingForKey = true;
        // noLoop
    }

    public void displayMessageNoTypeDelay(String msg) {
        // Visually show message on screen
        msgGraphic.beginDraw();
        msgGraphic.background(255);
        msgGraphic.fill(0);
        msgGraphic.textSize(sizeOfText);
        msgGraphic.text(msg, 0,50);
        msgGraphic.endDraw();
        redraw();
        //delay(0);
        waitingForKey = true;
        // noLoop
    }

    /**
     * This method can display any menu. The options are desplayed in the middle of the screen
     * @param menuFields the list of menu options
     */
    @Override
    public void displayMenu(String[] menuFields) {
        menuGraphic.beginDraw();
        menuGraphic.background(255);
        menuGraphic.fill(0);
        menuGraphic.textSize(sizeOfText);
        menuGraphic.strokeWeight(15);
        int spasing = sizeOfText + (int)(sizeOfText*0.2);
        for (int i = 0; i < menuFields.length; i++) {
            int posY = (spasing * menuFields.length/2) + (spasing * i) ;
            menuGraphic.text(menuFields[i], 0, posY);
        }
        menuGraphic.endDraw();
        redraw();
    }

    @Override
    public String getInput(String msg) {
        String outputText;
        waitingForKey = false;
        displayMessageNoTypeDelay(msg);
        enter = false;
        inputText = "";
        waitingForKey = true;
        while(!enter) {
            delay(0);
        }
        waitingForKey = false;
        textBox.beginDraw();
        textBox.background(255);
        textBox.endDraw();
        redraw();
        outputText = inputText;
        inputText = "";
        return outputText;
    }

    public void mousePressed() {
        if(boardImage != null) {
            finalMouseX = mouseX;
            finalMouseY = mouseY;
            int handBorderLeftX = width/2 + (-(6 * sizeOfText) + handGraphic.width - (int)(sizeOfText * 1.5));
            int handBorderRightX = width-sizeOfText/2;
            int handBorderTopY = height - sizeOfText * 2 + (sizeOfText / 5);
            int handBorderBottomY = height - sizeOfText * 2 + (sizeOfText / 5 + sizeOfText);

            if (finalMouseX > width / 2 && finalMouseY < width && finalMouseY > 0 && finalMouseY < width / 2) {
                int boardIndexX = (int) map(finalMouseX, width / 2, width, 0, 15);
                int BoardIndexY = (int) map(finalMouseY, 0, width / 2, 0, 15);
                inputText = inputText.replaceAll("[^a-zA-Z]", "");
                String tmpText = boardIndexX + "," + BoardIndexY + ",";
                inputText = tmpText + inputText;
            }
            else if (finalMouseX > handBorderLeftX && finalMouseX < handBorderRightX && finalMouseY > handBorderTopY && finalMouseY < handBorderBottomY) {
                int handIndex = (int) map(finalMouseX, handBorderLeftX, handBorderRightX, 7, 0);
                inputText = inputText.replaceAll("[a-zA-Z]", "");
                if (playerLetters.length > handIndex) {
                   inputText += playerLetters[handIndex];
                }
            }
            displayTextBox();
        }

    }

    private void displayTextBox() {
        textBox.beginDraw();
        textBox.background(255);
        textBox.fill(0);
        textBox.textSize(30);
        textBox.text(inputText, width/4, height-60);
        textBox.endDraw();
        redraw();
    }

    @Override
    public void displayBoard(Board board) {
        PGraphics boardGraphic = createGraphics(width/2,width/2);

        tileSize = boardGraphic.width/board.getWidth();
        int strokeWeight = 5;
        boardGraphic.beginDraw();
        boardGraphic.stroke(0);
        boardGraphic.strokeWeight(strokeWeight);
        boardGraphic.fill(255);
        boardGraphic.textSize(tileSize);
        boardGraphic.textAlign(3,3);// Center,Center
        for(int i = 0; i < board.getWidth(); i++){
            for (int j = 0; j < board.getHeight(); j++) {
                Tile currentTile = board.getTile(i, j);
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
                    boardGraphic.fill(0);
                    char tileChar = board.getTile(i, j).getLetterChar();
                    // DISPLAY ADDED LETTERS
                    for(Tile tile : board.getToBePlaced()) {
                        if (tile.getPositionX() == i && tile.getPositionY() == j) {
                            boardGraphic.fill(200,0,0);
                            tileChar = tile.getLetterChar();
                        }
                    }
                    boardGraphic.text(tileChar, i * tileSize + tileSize/2 + strokeWeight,j*tileSize + tileSize/2 - strokeWeight);
                    boardGraphic.stroke(0);
                    boardGraphic.fill(255);
                }
            }
        }
        boardGraphic.endDraw();
        boardImage = boardGraphic;
        redraw();
    }

    public void keyPressed() {
        if(waitingForKey) {
            if (key >= 'A' && key <= 'z' || key >= '0' && key <= '9' || key == ',') {
                inputText += key;
                displayTextBox();
            } else if (key == BACKSPACE) {
                if (inputText.length() > 0) {
                    inputText = inputText.substring(0, inputText.length() - 1);
                }
                displayTextBox();
            } else if (key == ENTER && inputText.length() > 0) {
                enter = true;
            }
        }

    }

    @Override
    public void displayHand(String playerName,List<Letter> letters) {
        String playerString = "Current player:" + playerName;
        playerLetters = new char[letters.size()];
        handGraphic.beginDraw();
        handGraphic.background(255);
        handGraphic.textSize(sizeOfText);
        handGraphic.strokeWeight(3);
        handGraphic.fill(0);
        handGraphic.textAlign(37,102); //Left, bottom
        handGraphic.text(playerString,0,50);
        handGraphic.textAlign(3,102);//center,bottom
        for (int i = 0; i < letters.size(); i++) {
            char letter = letters.get(i).getLetter();
            playerLetters[i] = letter;
            handGraphic.fill(255);
            handGraphic.rect(-(i * sizeOfText) + handGraphic.width - (int)(sizeOfText * 1.5), (sizeOfText / 5), sizeOfText , sizeOfText);
            handGraphic.fill(0);
            handGraphic.text(letter, -(i * sizeOfText) + handGraphic.width - sizeOfText,50);
        }
        handGraphic.endDraw();
        redraw();
    }
}
