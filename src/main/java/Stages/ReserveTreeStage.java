package main.java.Stages;

import main.java.Board.Board;
import main.java.Board.Pair;
import main.java.Board.Tile;

public class ReserveTreeStage {

    public static boolean run(Board board){
        boolean modified = false;
        for(int i = 0; i < board.WIDTH; i++){
            for(int j = 0; j< board.HEIGHT; j++){
                if(board.get(i,j) == Tile.TENT){
                    int count = 0;
                    Pair location = null;
                    if(board.get(i+1, j) == Tile.TREE){
                        count++;
                        location = new Pair(i+1, j);
                    }
                    if(board.get(i, j+1) == Tile.TREE){
                        count++;
                        location = new Pair(i, j+1);
                    }
                    if(board.get(i-1, j) == Tile.TREE){
                        count++;
                        location = new Pair(i-1, j);
                    }
                    if(board.get(i, j-1) == Tile.TREE){
                        count++;
                        location = new Pair(i, j-1);
                    }
                    if(count == 1){
                        modified |= board.setTile(location.getA(), location.getB(), Tile.RESERVED_TREE);
                    }
                }
            }
        }
        return modified;
    }
}
