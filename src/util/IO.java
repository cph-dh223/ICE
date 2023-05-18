package util;
import board.Board;
import board.Tile;
import game.Game;
import game.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class IO {
    private Board board;
    private ArrayList<Player> players;
    private static IO instance;

    private IO(){
    }

    public static IO getInstance(){
        if(instance == null){
            instance = new IO();
        }
        return instance;
    }

    public static List<String> getDataFromTxt(String path) throws FileNotFoundException{
        List<String> data = new LinkedList<>();

        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()){
            data.add(scanner.nextLine());
        }
        return data;
    }

    public List<String> saveBoard(){

        String dataOnTile = "";
        Tile[][] tiles = board.getTiles();
        int width = board.getWidth();
        int height = board.getHeight();
        List<String> DataForTile = new ArrayList<>();
        for (int x = 1; x < width ; x++){
            for (int y = 1; y < height; y++){
                dataOnTile += tiles[x][y].getPositionX() + ",";
                dataOnTile += tiles[x][y].getPositionY() + ",";
                //  dataOnTile += tiles[x][y].getMultiplier() + ",";
                dataOnTile += tiles[x][y].getLetterChar() + ",";
                DataForTile.add(dataOnTile);
                dataOnTile = "";

            }
        }

        return DataForTile;
    }

    public List<String> savePlayer(){
                String savePlayer = "";
        List<String> DataForPlayers = new ArrayList<>();

        for (Player p:players) {
            savePlayer += p.getName();
            savePlayer += ";";
            savePlayer += p.getScore();
            savePlayer += ";";
            savePlayer += p.getLetters();
            DataForPlayers.add(savePlayer);
            savePlayer += "";

        }
        return DataForPlayers;


    }

    public void saveData(ArrayList<Player> players, Board board){
        FileWriter writer = null;
        //Tile[][] boardTiles = board.getTiles();
        List<String> DataPlayers = savePlayer();
        List<String> boardTiles = saveBoard();
        System.out.println();
        try {
            writer = new FileWriter("./data/Data.csv");

            writer.write("Name, Score, Letters, \n");
            for (String p:DataPlayers) {
                writer.write(p+"/n");
            }

            writer.write("posX, posY, char,   \n");

            for (String tileData : boardTiles) {
                writer.write(tileData+"\n");

            }

            writer.close();


        } catch (IOException e) {


        }
    }


}
