package main.java.Stages;

import main.java.Board.Board;
import main.java.Board.Tile;

public class OpenSpace {

    public static boolean run(Board board){
        boolean modified = false;
        for(int j = 0; j < board.HEIGHT; j++){
            for(int i = 0; i < board.WIDTH; i++){
                if(board.get(i-1,j) != Tile.TREE && board.get(i+1,j) != Tile.TREE && board.get(i,j-1) != Tile.TREE && board.get(i,j+1) != Tile.TREE){
                    modified |= board.setTile(i,j,Tile.GRASS);
                }
            }
        }
        return modified;
    }

}
