package main.java.Stages;

import main.java.Board.Board;
import main.java.Board.Tile;

public class OpenSpace {

    public static void run(Board board){
        for(int j = 0; j < board.HEIGHT; j++){
            for(int i = 0; i < board.WIDTH; i++){
                if(board.get(i-1,j) != Tile.TREE && board.get(i+1,j) != Tile.TREE && board.get(i,j-1) != Tile.TREE && board.get(i,j+1) != Tile.TREE){
                    board.setTile(i,j,Tile.GRASS);
                }
            }
        }
    }

}
