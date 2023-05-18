package board;
import java.util.*;

import game.Letter;

public class Board {
    private Tile[][] tiles;

    private Set<String> dict;
    private int width;
    private int height;

    private List<Tile> toBePlaced;
    private Tile[][] tempTiles;
    private int wordSubmittedCounter = 0;
    private int tempFlaggedLettersCounter = 1;
    private int tempDoubleWordCounter = 0;
    private int tempTripleWordCounter = 0;
    private int tempTotalWordPoints = 0;

    // HashMap for storing flagged letters (flagged letters = justPlaced letters that, when you
    // go through the letters in one direction (either horizontally or vertically), has letters connected to
    // them in the board)
    HashMap<Integer, List<Integer>> tempLettersWithWordsToGoThrough = new HashMap<>();

    private List<Character> tempWordToCompare = new ArrayList<>();

    public Board(int width, int height, Set<String> dict){
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        initTiles();
        this.dict = dict;
        toBePlaced = new ArrayList<>();
    }


    private void initTiles() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = new Tile(i, j, Multiplier.NORMAL);
                if(i == 0 && j == 0 || i == 7 && j == 0 || i == 14 && j == 0 || i == 0 && j == 7 || i == 14 && j == 7 || i == 0 && j == 14 || i == 7 && j == 14 || i == 14 && j == 14) {
                    tiles[i][j] = new Tile(i, j, Multiplier.TRIPLE_WORD);
                }
                    else if(i == 3 && j == 0 || i == 11 && j == 0 || i == 6 && j == 2 || i == 8 && j == 2 || i == 0 && j == 3 || i == 7 && j == 3 || i == 14 && j == 3 || i == 2 && j == 6 || i == 6 && j == 6 || i == 8 && j == 6 || i == 12 && j == 6 || i == 3 && j == 7 || i == 11 && j == 7 || i == 2 && j == 8 || i == 6 && j == 8 || i == 8 && j == 8 || i == 12 && j == 8 || i == 0 && j == 11 || i == 7 && j == 11 || i == 14 && j == 11 || i == 6 && j == 12 || i == 8 && j == 12 || i == 3 && j == 14 || i == 11 && j == 14) {
                        tiles[i][j] = new Tile(i, j, Multiplier.DOUBLE_LETTER);
                    }
                    else if(i == 1 && j == 1 || i == 13 && j == 1 || i == 2 && j == 2 || i == 12 && j == 2 || i == 3 && j == 3 || i == 11 && j == 3 || i == 4 && j == 4 || i == 10 && j == 4 || i == 7 && j == 7 || i == 4 && j == 10 || i == 10 && j == 10 || i == 3 && j == 11 || i == 11 && j == 11 || i == 2 && j == 12 || i == 12 && j == 12 || i == 1 && j == 13 || i == 13 && j == 13){
                    tiles[i][j] = new Tile(i, j, Multiplier.DOUBLE_WORD);
                }
                    else if(i == 5 && j == 1 || i == 9 && j == 1 || i == 1 && j == 5 || i == 5 && j == 5 || i == 9 && j == 5 || i == 13 && j == 5 || i == 1 && j == 9 || i == 5 && j == 9 || i == 9 && j == 9 || i == 13 && j == 9 || i == 5 && j == 13 || i == 9 && j == 13){
                    tiles[i][j] = new Tile(i, j, Multiplier.TRIPLE_LETTER);
                }
            }
        }

    }
    // If statements: if this tile is on certain x,y coordinate then the multiplier is set to ____
    public void placeLetter(int posX, int posY, Letter letter){
        Tile tile = new Tile(posX,posY,tiles[posX][posY].getMultiplier(),letter);
        toBePlaced.add(tile);
    }

    public void boardWithToBePlacedLetters() {
        System.out.println("boardWithToBePlacedLetters() ...");

        tempTiles = new Tile[this.width][this.height];

        // Creating a copy of the tiles on the board
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.width; y++) {
                tempTiles[x][y] = tiles[x][y];
            }
        }

        // Replacing tiles in the copy with the toBePlaced tiles (using there posX and posY)
        for (Tile aPlacedTile : toBePlaced) {
            int x = aPlacedTile.getPositionX();
            int y = aPlacedTile.getPositionY();
            this.tempTiles[x][y] = aPlacedTile;

        }
    }

    public int checkSubmittedLetters() {

        System.out.println("checkSubmittedLetters()...");

        Direction direction = getWordDirection();
        System.out.println("Direction: " + direction);
        System.out.println("getWordDirection() FINISHED");

        if (direction.equals(Direction.WRONG)) {
            System.out.println("WRONG DIRECTION");

            tempFlaggedLettersCounter = 1;
            tempDoubleWordCounter = 0;
            tempWordToCompare.clear();
            tempLettersWithWordsToGoThrough.clear();
            toBePlaced.clear();
            tempTripleWordCounter = 0;
            return -1;

        }

        // Creating copy of board with toBePlaced letters in board
        boardWithToBePlacedLetters();
        System.out.println("boardWithToBePlacedLetters() FINISHED");

        //
        boolean firstWordInCenter = false;
        // Counting the number of times the checkSubmittedLetters() has been called
        wordSubmittedCounter ++;


        // If it's the first time it's called, word placement is checked to see if it's in center of board
        if (wordSubmittedCounter == 1) {
            System.out.println("-- The word IS the FIRST submitted word");

            // Is first word placed in center:
            firstWordInCenter = checkFirstWordPlacement();
            System.out.println("checkFirstWordPlacement() FINISHED");

            if (firstWordInCenter == false){
                System.out.println("-- Is first word in center: " + firstWordInCenter);
                return -1;
            }
            System.out.println("-- Is first word in center: " + firstWordInCenter);

            boolean areFirstWordConnected = areLettersConnectedToAnyLetter();
            System.out.println("-- areLettersConnectedToAnyLetter() FINISHED");

            if(areFirstWordConnected == false) {
                return -1;
            }
        }
        else {
            System.out.println("-- Word is NOT the FIRST submitted word");
            // Checking if all the letters are directly or indirectly connected to a previously placed letter
            boolean areTheLettersConnected = areLettersProperlyConnected();
            System.out.println("areLettersProperlyConnected() FINISHED");
            System.out.println("-- Are the letters connected: " + areTheLettersConnected);

            if (areTheLettersConnected == false) {
                tempFlaggedLettersCounter = 1;
                tempDoubleWordCounter = 0;
                tempWordToCompare.clear();
                tempLettersWithWordsToGoThrough.clear();
                toBePlaced.clear();
                tempTripleWordCounter = 0;

                // We can maybe have different values for different problems
                return -1;
            }
        }
        // Variable for the total amount of points the toBePlaced letters amount to
        int totalPointsOfPlacedLetters = 0;

        // List with x and y position of first letter in toBePlaced. This is where the checkords() starts and
        // call the goToFirstLetter() method
        int x = toBePlaced.get(0).getPositionX();
        int y = toBePlaced.get(0).getPositionY();
        List<Integer> firstPosXAndPosY = new ArrayList<>();
        firstPosXAndPosY.add(x);
        firstPosXAndPosY.add(y);


        // Filling the HashMap with the x and y position of a toBePlaced letter which either is the first letter
        // in the toBePlaced List of letters or is a toBePlaced letter which is connected to previously placed
        // letters
        tempLettersWithWordsToGoThrough.put(tempFlaggedLettersCounter, firstPosXAndPosY);


        System.out.println("-- Size of hashMap " + tempLettersWithWordsToGoThrough.size());

        // Counter keeping track of how many words has been checked
        int wordCounter = 1;

        System.out.println("-- Entering while loop for first letter in toBePlaved and flagged letters...");

        // While loop going through the HashMap called tempLettersWithWordsToGoThrough. To begin with
        // this HashMap contains the first letter of the toBePlaced list. After first loop it contains
        // all the flagged letters if there is any. If there is no flagged letters, the while loop stops
        while (tempLettersWithWordsToGoThrough.size() >= 1){
            System.out.println("--- Size of hashMap: " + tempLettersWithWordsToGoThrough.size());
            System.out.println("--- Value of wordCounter: "+ wordCounter);


            // Making sure direction of word being checked is correct.
            // In first loop (that is when wordCounter is 1) the direction is the direction given
            // by getWordDirection(). From second loop and so forth the direction is the OPPOSITE of
            // the direction given by getWordDirection()
            if (wordCounter == 2) {
                if (direction.equals(Direction.HORISONTAL)){
                    direction = Direction.VERTICAL;
                }
                else {
                    direction = Direction.HORISONTAL;
                }
            }
            // Getting x and y coordinates for a letter where there is a
            // word to go through. These x and y coordinates is retracted from the HashMap
            // contain small lists with [x, y] coordinates.
            List<Integer> posXAndPosY = tempLettersWithWordsToGoThrough.get(wordCounter);

            // Going to first letter of word to go through
            System.out.println("--- Size of posXAndPosY: " + posXAndPosY.size());
            List<Integer> posXAndYOfFirstLetter = goToFirstLetter(posXAndPosY.get(0), posXAndPosY.get(1), direction);
            System.out.println("goToFirstLetter() FINISHED");

            // Going through word and counting points. goThroughWord either
            // 1) returns -1 (that is if the toBePlaced letters at some point aren't forming a valid word
            // with the tiles they are connected to) or
            // 2) returns the total amount of points that the word amounts to (including DL, TL, DW and TW)
            int pointsOfWord = goThroughWord(direction, posXAndYOfFirstLetter, wordCounter);
            System.out.println("goThroughWord() FINISHED");

            // Removing first element from HashMap this while loop goes through (thereby reducing
            // the size of the HashMap and by that moving towards the end of the while loop)
            tempLettersWithWordsToGoThrough.remove(wordCounter);

            System.out.println("--- tempLettersWithWordsToGoThrough: " +
                    tempLettersWithWordsToGoThrough);

            // If goThroughWord() returns -1 checkSubmittedLetters() (this method) returns -1
            // Clearing all temp-fields and toBePlaced field if this method returns something
            if (pointsOfWord == -1) {
                tempFlaggedLettersCounter = 1;
                tempDoubleWordCounter = 0;
                tempWordToCompare.clear();
                tempLettersWithWordsToGoThrough.clear();
                toBePlaced.clear();
                tempTripleWordCounter = 0;
                return -1;
            }
            // If goThroughWord() returns anything else than -1, what the method returns is added
            // to totalPointsOfPlacedLetters
            else{
                totalPointsOfPlacedLetters += pointsOfWord;
            }
            // Adding 1 to counter counting how many words has been checked
            wordCounter ++;
        }
        System.out.println("-- While loop DONE");

        // Clearing all temp-fields and toBePlaced field if this method returns something
        tempFlaggedLettersCounter = 1;
        tempDoubleWordCounter = 0;
        tempWordToCompare.clear();
        tempLettersWithWordsToGoThrough.clear();
        toBePlaced.clear();
        tempTripleWordCounter = 0;

        System.out.println("-- Returning: " + totalPointsOfPlacedLetters);

        return totalPointsOfPlacedLetters;
    }




    private Direction getWordDirection() {

        System.out.println("getWordDirection()...");

        //################################################################################################

        //################################################################################################
        // ONLY ONE LETTER PLACED BY PLAYER
        //################################################################################################

        // Position of first tile in toBePlaced list
        int x = toBePlaced.get(0).getPositionX();
        int y = toBePlaced.get(0).getPositionY();

        // 1) If the player has placed and submitted only 1 letter
        if (toBePlaced.size() == 1) {
            System.out.println("-- 1 letter placed by player");

            //################################################################################################
            // LETTER NOT PLACED AT EDGE OF BOARD
            //################################################################################################
            // 1.1) If the toBePlaced tile IS NOT at the edge of board...
            if (x > 0
                    && x < this.width-1
                    && y > 0
                    && y < this.height-1){

                System.out.println("-- Tile is not at edge of board");


                //1.11) if there IS a tile WITH a LETTER ABOVE or BENEATH the toBePlaced tile...
                if (tiles[x][y-1].getLetterChar() != '#'
                        || tiles[x][y+1].getLetterChar() != '#') {
                    // ...then the direction is HORIZONTAL.
                    return Direction.VERTICAL;
                }

                //1.12) if there IS a tile WITH A LETTER on the RIGHT OR LEFT side of the toBePlaced tile...
                else if (tiles[x-1][y].getLetterChar() != '#'
                        || tiles[x+1][y].getLetterChar() != '#') {
                    // ...then the direction is HORIZONTAL.
                    return Direction.HORISONTAL;

                    // 1.13 if there is NO letter EITHER above, beneath, left or right
                } else {
                    return Direction.WRONG;
                }

                //################################################################################################
                // LETTER PLACED AT LEFT EDGE OF BOARD BUT NOT IN CORNER
                //################################################################################################

                // 1.2 If the toBePlaced tile is at the LEFT edge of board but NOT IN THE CORNER...
            } else if (x == 0
                    && y > 0
                    && y < this.height-1){

                //1.21) if there IS a tile WITH a LETTER ABOVE or BENEATH the toBePlaced tile
                if (tiles[x][y-1].getLetterChar() != '#'
                        || tiles[x][y+1].getLetterChar() != '#')
                {
                    // ...then the direction is VERTICAL.
                    return Direction.VERTICAL;
                }

                //1.22) if there IS a tile WITH A LETTER on the RIGHT side of the toBePlaced tile...
                else if (tiles[x+1][y].getLetterChar() != '#') {
                    // ...then the direction is HORIZONTAL.
                    return Direction.HORISONTAL;

                }
                //1.23) if there IS NO TILES WITH A LETTER connected to the toBePlaced tile...
                else {
                    // ...then the direction is WRONG.
                    return Direction.WRONG;
                }

            }
            //################################################################################################
            // LETTER PLACED AT RIGHT EDGE OF BOARD BUT NOT IN THE CORNER
            //################################################################################################

            // 1.3 If the toBePlaced tile is at the RIGHT edge of board but NOT IN THE CORNER...
            else if (x == this.width-1
                    && y < this.height
                    && y >= 0 ) {

                //1.31) if there IS a tile WITH a LETTER ABOVE or BENEATH the toBePlaced tile
                //OBS!!! This if statement is a copy of one above
                if (tiles[x][y-1].getLetterChar() != '#'
                        || tiles[x][y+1].getLetterChar() != '#') {
                    // ...then the direction is VERTICAL.
                    return Direction.VERTICAL;
                }

                //1.32) if there IS a tile WITH A LETTER on the LEFT side of the toBePlaced tile...
                else if (tiles[x-1][y].getLetterChar() != '#') {
                    // ...then the direction is HORIZONTAL.
                    return Direction.HORISONTAL;

                }
                //1.33) if there IS NO TILES WITH A LETTER connected to the toBePlaced tile...
                else {
                    // ...then the direction is WRONG.
                    return Direction.WRONG;
                }
            }
            //################################################################################################
            // LETTER PLACED AT BOTTOM EDGE OF BOARD BUT NOT IN THE CORNER
            //################################################################################################

            // 1.4 If the toBePlaced tile is at the BOTTOM edge of board but NOT IN THE CORNER...
            else if (x > 0
                    && x < this.width-1
                    && y== this.height-1){

                //1.41) if there IS a tile WITH a LETTER on the RIGHT or LEFT side of the toBePlaced tile
                if ((tiles[x-1][y].getLetterChar() != '#'
                        || tiles[x+1][y].getLetterChar() != '#')) {
                    // ...then the direction is HORIZONTAL.
                    return Direction.HORISONTAL;
                }

                //1.42) if there IS a tile WITH A LETTER ABOVE the toBePlaced tile...
                else if (tiles[x][y-1].getLetterChar() != '#') {
                    // ...then the direction is VERTICAL.
                    return Direction.VERTICAL;

                }
                //1.43) if there IS NO TILES WITH A LETTER connected to the toBePlaced tile...
                else {
                    // ...then the direction is WRONG.
                    return Direction.WRONG;
                }
            }


            //################################################################################################
            // LETTER PLACED AT TOP EDGE OF BOARD BUT NOT IN THE CORNER
            //################################################################################################

            // 1.5 If the toBePlaced tile is at the TOP edge of board but NOT IN THE CORNER...
            else if (y == 0
                    && x > 0
                    && x < this.width-1){

                // 1.51) if there IS a tile WITH a LETTER on the RIGHT or LEFT side of the toBePlaced tile
                // OBS!!! This is a copy of an if statement above
                if (tiles[x-1][y].getLetterChar() != '#'
                        || tiles[x+1][y].getLetterChar() != '#') {
                    // ...then the direction is HORIZONTAL.
                    return Direction.HORISONTAL;
                }

                //1.52) if there IS a tile WITH A LETTER BENEATH the toBePlaced tile...
                else if (tiles[x][y+1].getLetterChar() != '#') {
                    // ...then the direction is VERTICAL.
                    return Direction.VERTICAL;

                }
                //1.53) if there IS NO TILES WITH A LETTER connected to the toBePlaced tile...
                else {
                    // ...then the direction is WRONG.
                    return Direction.WRONG;
                }
            }


            //################################################################################################
            // LETTER PLACED AT TOP LEFT CORNER OF BOARD
            //################################################################################################

            // 1.6 If the toBePlaced tile is at the TOP LEFT CORNER of the board...
            else if (tiles[x][y] == tiles[0][0]){

                // 1.61) if there IS a tile WITH a LETTER on the RIGHT side of the toBePlaced tile
                if ((tiles[x+1][y].getLetterChar() != '#')) {
                    // ...then the direction is HORIZONTAL.
                    return Direction.HORISONTAL;
                }

                //1.62 If there IS a tile WITH A LETTER BENEATH the toBePlaced tile...
                else if (tiles[x][y+1].getLetterChar() != '#') {
                    // ...then the direction is VERTICAL.
                    return Direction.VERTICAL;

                }
                //1.63) if there IS NO TILES WITH A LETTER connected to the toBePlaced tile...
                else {
                    // ...then the direction is WRONG.
                    return Direction.WRONG;
                }
            }


            //################################################################################################
            // LETTER PLACED AT TOP RIGHT CORNER OF BOARD
            //################################################################################################

            // 1.7 If the toBePlaced tile is at the TOP RIGHT CORNER of the board...
            else if (tiles[x][y] == tiles[this.width-1][0]){

                // 1.71) if there IS a tile WITH a LETTER on the LEFT side of the toBePlaced tile
                if (tiles[x-1][y].getLetterChar() != '#') {
                    // ...then the direction is HORIZONTAL.
                    return Direction.HORISONTAL;
                }

                // 1.72 If there IS a tile WITH A LETTER BENEATH the toBePlaced tile...
                // OBS!!! This is a copy of an if statement above
                else if (tiles[x][y+1].getLetterChar() != '#') {
                    // ...then the direction is VERTICAL.
                    return Direction.VERTICAL;

                }
                //1.73) if there IS NO TILES WITH A LETTER connected to the toBePlaced tile...
                else {
                    // ...then the direction is WRONG.
                    return Direction.WRONG;
                }
            }


            //################################################################################################
            // LETTER PLACED AT BOTTOM LEFT CORNER OF BOARD
            //################################################################################################

            // 1.8 If the toBePlaced tile is at the BOTTOM LEFT CORNER of the board...
            else if (tiles[x][y] == tiles[0][this.height-1]){

                // 1.81) if there IS a tile WITH a LETTER on the RIGHT side of the toBePlaced tile
                if (tiles[x+1][y].getLetterChar() != '#')
                {
                    // ...then the direction is HORIZONTAL.
                    return Direction.HORISONTAL;
                }

                // 1.82 If there IS a tile WITH A LETTER ABOVE the toBePlaced tile...
                else if (tiles[x][y-1].getLetterChar() != '#') {
                    // ...then the direction is VERTICAL.
                    return Direction.VERTICAL;

                }
                //1.83) if there IS NO TILES WITH A LETTER connected to the toBePlaced tile...
                else {
                    // ...then the direction is WRONG.
                    return Direction.WRONG;
                }
            }


            //################################################################################################
            // LETTER PLACED AT BOTTOM RIGHT CORNER OF BOARD
            //################################################################################################

            // 1.9 If the toBePlaced tile is at the BOTTOM RIGHT CORNER of the board...
            else if (tiles[x][y] == tiles[this.width-1][this.height-1]){

                // 1.91) if there IS a tile WITH a LETTER on the LEFT side of the toBePlaced tile
                if (tiles[x-1][y].getLetterChar() != '#')
                {
                    // ...then the direction is HORIZONTAL.
                    return Direction.HORISONTAL;
                }

                // 1.92 If there IS a tile WITH A LETTER ABOVE the toBePlaced tile...
                // OBS!!! This is a copy of an if statement above
                else if (tiles[x][y-1].getLetterChar()
                        != '#') {
                    // ...then the direction is VERTICAL.
                    return Direction.VERTICAL;

                }
                //1.83) if there IS NO TILES WITH A LETTER connected to the toBePlaced tile...
                else {
                    // ...then the direction is WRONG.
                    return Direction.WRONG;
                }
            }
        }

        //################################################################################################

        //################################################################################################
        // MORE THAN ONE LETTER PLACED BY PLAYER
        //################################################################################################


        else {
            System.out.println("-- more than 1 letter placed by player");

            int toBePlacedOnSame_X_Counter = 0;
            int toBePlacedOnSame_Y_Counter = 0;

            for (Tile tile : toBePlaced) {

                if (tile.getPositionX() == x) {
                    toBePlacedOnSame_X_Counter++;}

                if (tile.getPositionY() == y) {
                    toBePlacedOnSame_Y_Counter++;}
            }

            System.out.println("-- Size of toBePlaced list: " + toBePlaced.size());
            System.out.println("-- Number of letters placed on same X-axis: " + toBePlacedOnSame_X_Counter);
            System.out.println("-- Number of letters placed on same Y-axis: " + toBePlacedOnSame_Y_Counter);

            // If all tiles are placed on the same posX...
            if (toBePlacedOnSame_X_Counter == toBePlaced.size()) {
                System.out.println("-- Direction: VERTICAL");
                //...then direction is VERTICAL.
                return Direction.VERTICAL;

                // If all tiles are placed on the same posY...
            } else if (toBePlacedOnSame_Y_Counter == toBePlaced.size()) {
                System.out.println("-- Direction: HORIZONTAL");
                //...then direction is HORISONTAL
                return Direction.HORISONTAL;

                // If it's not all tiles that aren't placed on either same posX or same posY...
            } else {
                System.out.println("-- Direction: WRONG");
                //...then direction is WRONG
                return Direction.WRONG;
            }
        }

        // Returns WRONG in all other cases (it shouldn't be possible but IntelliJ demands it)
        return Direction.WRONG;
    }


    public boolean checkFirstWordPlacement(){
        System.out.println("checkFirstWordPlacement()...");

        boolean isFirstWordPlacedInCenter = false;

        for (Tile tile : toBePlaced) {
            // Checking if the tile is placed in the center of a 15x15 board. (This could maybe be more
            // dynamic. It's hardcoded in the center of a board of 15x15)
            if (tile.getPositionX() == 7 && tile.getPositionY() == 7) {
                isFirstWordPlacedInCenter = true;
            }
        }
        return isFirstWordPlacedInCenter;
    }



    public boolean areLettersConnectedToAnyLetter() {

        System.out.println("areLettersConnectedToAnyLetter()...");

        int x;
        int y;

        for (Tile tile : toBePlaced) {

            System.out.println("-- For loop going through toBePlaced tiles checking if they are connected to any" +
                    " letter...");
            x = tile.getPositionX();
            y = tile.getPositionY();
            System.out.println("-- letter to check: " + tile.getLetterChar());


            // Checking if there are any tiles beside or above or beneath the letter AND making sure
            // only to check in the direction towards center of board if the letter is placed
            // at the edge of the board (hence the  x>0  in the first line, the  x < this.width-1
            // in the second line and so forth)
            if ( (x > 0 && tempTiles[x-1][y].getLetterChar() != '#')
                    ||
                    (x < this.width-1 && tempTiles[x+1][y].getLetterChar() != '#')
                    ||
                    (y > 0 && tempTiles[x][y-1].getLetterChar() != '#')
                    ||
                    (y < this.height-1 && tempTiles[x][y+1].getLetterChar() != '#')
            ) {

                System.out.println("-- This letter is connected to some tile");

            } else {
                System.out.println("-- Are letters connected to any letter: false");
                return false;
            }

        }
        System.out.println("-- Are letters connected to any letter: true");
        return true;
    }

    public boolean areLettersProperlyConnected(){
        System.out.println("areLettersProperlyConnected()...");

        int lettersConnectedToOldLetters = 0;
        int x;
        int y;

        boolean toBePlacedConnected = areLettersConnectedToAnyLetter();
        System.out.println("--areLettersConnectedToAnyLetter() FINISHED");

        if (toBePlacedConnected == false) {
            return false;
        }

        for (Tile tile : toBePlaced) {
            System.out.println("-- For loop going through toBePlaced tiles checking if they are properly " +
                    "connected...");

            x = tile.getPositionX();
            y = tile.getPositionY();
            System.out.println("-- letter to check: " + tile.getLetterChar());


            // Checking if the letter is connected to any of the previously connected placed letters
            if (
                    (x > 0 && tiles[x-1][y].getLetterChar() != '#')
                            ||
                            (x < this.width-1 && tiles[x+1][y].getLetterChar() != '#')
                            ||
                            (y > 0 && tiles[x][y-1].getLetterChar() != '#')
                            ||
                            (y < this.height-1 && tiles[x][y+1].getLetterChar() != '#')
            ) {

                System.out.println("-- The letter IS connected to a previously placed tile");
                lettersConnectedToOldLetters += 1;

            }
            else {

                System.out.println("-- The letter IS NOT connected to a previously placed tile");
            }
        }


        if (lettersConnectedToOldLetters == 0) {
            System.out.println("-- No letter is connected to any previously placed tile");
            return false;
        }
        else {
            System.out.println("-- One or more letters are connected any previously placed tile");
            return true;
        }
    }



    public List<Integer> goToFirstLetter(int posX, int posY, Direction direction) {

        System.out.println("goToFirstLetter()...");
        System.out.println("-- Parameters: \n--- posX: " + posX+"\n--- posY: " + posY);

        // posX and posY of the letter from where the method should go back
        int x = posX;
        int y = posY;

        // List for storing the posX and posY of the first letter of the word (this is the list
        // that this method returns)
        List<Integer> posXAndPosY = new ArrayList<>();

        // If statement checking if direction is VERTICAL or HORIZONTAL
        if (direction == Direction.VERTICAL) {

            System.out.println("-- VERTICAL");
            System.out.println("-- Char to go back from " + tempTiles[x][y].getLetterChar());

            // While loop making the y-value going back to the posY right before the first letter of word
            while (y>=0 && this.tempTiles[x][y].getLetterChar() != '#') {
                y -= 1;
            }
            // Going forward to the first letter of the word
            y ++;
            // Adding the x-value and new y-value to the list to return
            posXAndPosY.add(x);
            posXAndPosY.add(y);


        } else {
            System.out.println("-- HORIZONTAL");
            System.out.println("-- Char to go back from " + tempTiles[x][y].getLetterChar());

            // While loop making the x-value going back to the posX right before the first letter of word
            while (x>=0 && this.tempTiles[x][y].getLetterChar() != '#') {
                x -= 1;
            }
            // Going forward to the first letter of the word
            x ++;
            // Adding the new x-value and the y-value to the list to return
            posXAndPosY.add(x);
            posXAndPosY.add(y);
        }
        System.out.println("-- First letter of word: " + tempTiles[x][y].getLetterChar());
        System.out.println("-- Position of first letter of word: " + posXAndPosY);

        // Returns list with posX and posY of the first letter of the word
        return posXAndPosY;
    }

    public int goThroughWord(Direction direction, List<Integer> posXPosY, int numberOfWordsChecked){

        System.out.println("goThroughWord()...");

        int x = posXPosY.get(0);
        int y = posXPosY.get(1);

        if(direction == Direction.VERTICAL){

            System.out.println("-- Direction in if statement is: VERTICAL");
            System.out.println("-- First letter in the word the method is going through: " + tempTiles[x][y].getLetterChar());

            int letterPoints = 0;

            // While loop going through the word letter by letter (by counting up the y-value -
            // cf. direction is VERTICAL)
            while (y>=0 && y<=this.height-1 && tempTiles[x][y].getLetterChar() != '#') {
                System.out.println("-- Entering goThroughWord while loop");

                // THIS SHOULD BE A METHOD BY ITSELF################################################
                System.out.println("-- Checking if letter has just been placed...");

                boolean justPlaced = false;

                for (Tile tile : toBePlaced) {

                    if (tile.getPositionX() == tempTiles[x][y].getPositionX() &&
                            tile.getPositionY() == tempTiles[x][y].getPositionY()) {

                        justPlaced = true;
                    }
                }
                System.out.println("-- Is letter just placed: " + justPlaced);


                // If statement making sure it is only the first word that is checked and flagged for
                // connected letters
                if (numberOfWordsChecked == 1) {
                    System.out.println("-- Letter to check: " +tempTiles[x][y].getLetterChar() );
                    // Checking for connected letters and flagging (putting them in
                    // the HashMap tempLettersWithWordsToGoThrough)
                    // OBS!!! This method could be incorporated in areLettersProperlyConnected()
                    // and so cleaning up som repetitions
                    checkForConnectedLetters(x, y, justPlaced, direction);
                    System.out.println("checkForConnectedLetters() FINISHED");
                }

                // Every letter point is calculated through countPoints()
                // and accumulated in the variable letterPoints
                letterPoints += countPoints(x, y, justPlaced);

                // Letter is added to a list: tempWordToCompare. In the end this list will form the word
                // of the letters and compareWord() will check if the word is in this program's
                // library
                tempWordToCompare.add(tempTiles[x][y].getLetterChar());
                System.out.println("-- Character for tempWordToCompare: " + tempTiles[x][y].getLetterChar());
                // #########################################################################################

                // Going to next letter
                y += 1;
            }

            System.out.println("-- goThroughWord while loop DONE." +
                    "\nDirection: VERTICAL");
            // Checking if the word is in this program's library
            boolean wordValid = compareWord();
            System.out.println("compareWord() FINISHED");
            System.out.println("-- Is the word valid: " + wordValid);


            // Counting and storing the total points of the word (that is: multiplying it by 2 or 3
            // as many times a toBePlaced letter was placed on a DW or TW)
            int intToReturn = countingPointsIfWordValid(wordValid, letterPoints);
            System.out.println("countingPointsIfWordValid FINISHED");
            System.out.println("-- Points of VERTICAL word: " + intToReturn);

            // Returning the total amount of points the word has generated
            return intToReturn;


        }

        else {
            System.out.println("-- Direction in if statement is: HORIZONTAL");

            int letterPoints = 0;

            // While loop going through the word letter by letter (by counting up the x-value -
            // cf. direction is HORIZONTAL)
            while (x>=0 && x<=this.width-1 && tempTiles[x][y].getLetterChar() != '#') {
                System.out.println("-- Entering goThroughWord while loop...");
                System.out.println("-- Checking if letter has just been placed...");

                boolean justPlaced = false;

                // Checking if letter has just been placed
                for (Tile tile : toBePlaced) {
                    if (tile.getPositionX() == tempTiles[x][y].getPositionX() &&
                            tile.getPositionY() == tempTiles[x][y].getPositionY()) {
                        justPlaced = true;
                    }
                }
                System.out.println("-- Is letter just placed: " + justPlaced);

                // If statement making sure it is only the first word that is checked and flagged for
                // connected letters
                if (numberOfWordsChecked == 1) {
                    System.out.println("-- Letter to check: " +tempTiles[x][y].getLetterChar() );
                    // Checking for connected letters and flagging (putting them in
                    // the HashMap tempLettersWithWordsToGoThrough)
                    // OBS!!! This method could be incorporated in areLettersProperlyConnected()
                    // and so cleaning up som repetitions
                    checkForConnectedLetters(x, y, justPlaced, direction);
                    System.out.println("checkForConnectedLetters() FINISHED");
                }

                // Every letter point is calculated through countPoints()
                // and accumulated in the variable letterPoints
                letterPoints += countPoints(x, y, justPlaced);
                System.out.println("countPoints() FINISHED");

                // Letter is added to a list: tempWordToCompare. In the end this list will form the word
                // of the letters and compareWord() will check if the word is in this program's
                // library
                tempWordToCompare.add(tempTiles[x][y].getLetterChar());

                System.out.println("-- Character for tempWordToCompare: " + tempTiles[x][y].getLetterChar());
                // Going to next letter
                x += 1;
            }


            System.out.println("-- After while loop in goThroughWord: HORIZONTAL");
            // Checking if the word is in this program's library
            boolean wordValid = compareWord();
            System.out.println("compareWord() FINISHED");
            System.out.println("-- Is word valid: " + wordValid);


            // Counting and storing the total points of the word (that is: multiplying it by 2 or 3
            // as many times a toBePlaced letter was placed on a DW or TW)
            int intToReturn = countingPointsIfWordValid(wordValid, letterPoints);
            System.out.println("countingPointsIfWordValid FINISHED");

            System.out.println("-- Points of HORIZONTAL word: " + intToReturn);
            // Returning the total amount of points the word has generated
            return intToReturn;
        }
    }



    public void checkForConnectedLetters(int x, int y, boolean justPlaced, Direction direction){
        System.out.println("checkForConnectedLetters()...");
        System.out.println("-- posX and posY in parameter: " + x +", " + y);
        System.out.println("-- DIRECTION in parameter: " + direction);

        if (direction == Direction.VERTICAL) {
            System.out.println("-- Direction: VERTICAL");

// ######## ###### METODEN KAN PLACEREs >I< DETTE IF STATEMENT I STEDET FOR ##########################

            if (justPlaced == true){
                System.out.println("-- Letter is just placed");

                // (x-1 == 0 && tempTiles[x+1][y].getLetterChar() !='#') || (x+1 ==0 && tempTiles[x-1][y].getLetterChar() !='#')

                // If letter getting checked is at the LEFT OR RIGHT edge of the board
                if ((x == 0 && tempTiles[x+1][y].getLetterChar() !='#') || (x == this.width-1 && tempTiles[x-1][y].getLetterChar() !='#')){

                    System.out.println("-- Letter is at the left or right edge of the board");
                    System.out.println("-- Letter is connected to a letter");
                    tempFlaggedLettersCounter +=1;
                    List<Integer> posXAndposY = new ArrayList<>();
                    posXAndposY.add(x);
                    posXAndposY.add(y);

                    System.out.println("-- Position of flagged letter/tile: "+ posXAndposY);
                    tempLettersWithWordsToGoThrough.put(tempFlaggedLettersCounter, posXAndposY);

                }


                // If the letter getting checked is NOT at the edge of the board AND there is either a letter on the LEFT
                // or on the RIGHT
                else if ((x > 0 && (tempTiles[x-1][y].getLetterChar() != '#')) || (x < this.width-1 && tempTiles[x+1][y].getLetterChar() !='#')) {

                    System.out.println("-- Letter is NOT at the edge of the board");
                    System.out.println("-- Letter is connected to a letter");
                    tempFlaggedLettersCounter +=1;
                    List<Integer> posXAndposY = new ArrayList<>();
                    posXAndposY.add(x);
                    posXAndposY.add(y);

                    System.out.println("--Position of flagged letter/tile: " + posXAndposY);
                    tempLettersWithWordsToGoThrough.put(tempFlaggedLettersCounter, posXAndposY);

                }
                else {
                    System.out.println("-- This toBePlaced letter is NOT connected to any letters");
                }
            }
        }

        else {
            System.out.println("-- Direction: HORIZONTAL");
            if (justPlaced == true){

                // If letter getting checked is at the TOP or BOTTOM edge of the board
                if ( (y == 0 && tempTiles[x][y+1].getLetterChar() !='#') || (y == this.height-1 && tempTiles[x][y-1].getLetterChar() !='#')) {
                    System.out.println("-- Letter is  at the TOP or BOTTOM edge of the board");
                    System.out.println("-- Letter is connected to a letter");

                    tempFlaggedLettersCounter +=1;

                    List<Integer> posXAndposY = new ArrayList<>();
                    posXAndposY.add(x);
                    posXAndposY.add(y);
                    System.out.println("--Position of flagged letter/tile: " + posXAndposY);

                    tempLettersWithWordsToGoThrough.put(tempFlaggedLettersCounter, posXAndposY);
                }
                // If letter getting checked is NOT at the edge of the board
                else if (y > 0 && (tempTiles[x][y-1].getLetterChar() != '#' || y < this.height-1 && tempTiles[x][y+1].getLetterChar() !='#')) {

                    System.out.println("-- Letter is NOT at the edge of the board");
                    System.out.println("-- Letter is connected to a letter");
                    tempFlaggedLettersCounter +=1;

                    List<Integer> posXAndposY = new ArrayList<>();
                    posXAndposY.add(x);
                    posXAndposY.add(y);
                    System.out.println("--Position of flagged letter/tile: " + posXAndposY);

                    tempLettersWithWordsToGoThrough.put(tempFlaggedLettersCounter, posXAndposY);
                }
                else {
                    System.out.println("-- This toBePlaced letter is NOT connected to any letters");
                }

            }
            else {
                System.out.println("-- This toBePlaced letter has NOT just been placed");
            }
        }
    }


    public int countPoints(int posX, int posY, boolean justPlaced) {

        System.out.println("countPoints()...");

        int x = posX;
        int y = posY;
        System.out.println("-- Parameters:\nx: " + posX +"\ny: "+ posY +"\n justPlaced: " + justPlaced);

        //System.out.println("-- Letter being checked: " + );
        // Variable for storing the points that this letter will give the player at a specifik tile
        // At first the variable is filled with the points of the letter itself
        int letterPoint = tempTiles[x][y].getLetterScore();
        System.out.println("-- Letterpoint of letter:" + letterPoint);


        //Then it is checked if the letter is a just placed letter to see if the letter or the word should
        // be multiplied by 2 or 3.
        if (justPlaced == true){

            switch (tempTiles[x][y].getMultiplier()){

                /* When the checkWord method has been through the whole word and if the word has been
                declared valid by the compareWord(), the tempWordPoints is multiplied by 2 and 3 as many times
                as the value of tempDoubleWordCounter and the tempTripleWordCounter
                 */

                //checking if the letter that the (outside) while loop is at is a DW
                case DOUBLE_WORD:
                    System.out.println("-- DOUBLE_WORD registered");
                    tempDoubleWordCounter += 1;
                    break;

                //checking if the letter that the (outside) while loop is at is a TW
                case TRIPLE_WORD:
                    System.out.println("-- TRIPLE_WORD registered");
                    tempTripleWordCounter += 1;
                    break;

                //if the letter that the (outside) while loop is at is a DL

                case DOUBLE_LETTER:
                    System.out.println("-- DOUBLE_LETTER registered");
                    letterPoint = letterPoint*2;
                    break;

                //checking if the letter that the (outside) while loop is at is a TL
                case TRIPLE_LETTER:
                    System.out.println("-- TRIPLE_LETTER registered");
                    letterPoint = letterPoint*3;
                    break;

                default:
                    System.out.println("-- No MULTIPLIER registered");
                    break;
            }
        }
        return letterPoint;
    }


    public boolean compareWord() {

        System.out.println("compareWord()...");

        // Variable for word to compare
        String theWordToCompare = "";


        // Creating the word to compare from list of characters
        for (Character character : tempWordToCompare) {
            theWordToCompare += character;
        }

        this.tempWordToCompare.clear();
        System.out.println("-- The word to compare: " + theWordToCompare);

        // Comparing the word with our dictionary (Probably there is a better method. The dict could be stored
        // in a binary Tree or something like that I guess)
        for (String dictWord : this.dict){

            if (theWordToCompare.equalsIgnoreCase(dictWord)) {
                return true;
            }
        }

        return false;
    }


    public int countingPointsIfWordValid(boolean validWord, int letterPointsOfWord) {

        System.out.println("countingPointsIfWordIsValid()...");

        int totalPointsOfWord = letterPointsOfWord;

//##### ######## METHOD COULD BE PLACED IN THIS IF STATEMENT INSTEAD OF HAVING IT INSIDE IT#############
        if (validWord == true) {
            System.out.println("-- Word is VALID");

            if (tempDoubleWordCounter != 0){

                while (tempDoubleWordCounter >0) {
                    System.out.println("The tempDoubleWordCounter: " + tempDoubleWordCounter);
                    totalPointsOfWord  = totalPointsOfWord  * 2;
                    tempDoubleWordCounter --;
                }
            }

            if (tempTripleWordCounter != 0){
                System.out.println("-- tempTripleWordCounter: " + tempTripleWordCounter);

                while (tempTripleWordCounter >0) {
                    totalPointsOfWord = totalPointsOfWord * 3;
                    tempTripleWordCounter --;
                }
            }

            System.out.println("-- Total points of word: " + totalPointsOfWord);
            return totalPointsOfWord;
        }

        System.out.println("-- Word is NOT VALID");

        //tempTotalWordPoints = -1;
        return -1;
    }



    public void updateBoard(){
        System.out.println("updateBoard()...");
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {

                tiles[x][y] = tempTiles[x][y];
            }
        }
    }


    public int getWidth()    {
        throw new UnsupportedOperationException();
    }
    public int getHeight()    {
        throw new UnsupportedOperationException();
    }
    public char getLetter(int x, int y){
        throw new UnsupportedOperationException();
    }
}

