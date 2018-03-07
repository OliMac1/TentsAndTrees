package main.java.Stages;

import main.java.Board.Board;
import main.java.Board.Pair;
import main.java.Board.Tile;

public class TreeLastSpaceStage {

    public static boolean run(Board board) {
        boolean modified = false;
        for(int i = 0; i < board.WIDTH; i++){
            for(int j = 0; j < board.HEIGHT; j++){
                if(board.get(i,j) == Tile.TREE){
                    modified |= runTree(board, i, j);
                }
            }
        }
        return modified;
    }

    private static boolean runTree(Board board, int i, int j){
        int tents = 0;
        int empty = 0;
        Pair locationEmpty = null;

        if(board.get(i+1,j) == Tile.EMPTY){
            empty++;
            locationEmpty = new Pair(i+1, j);
        }else if(board.get(i+1,j) == Tile.TENT){
            tents++;
        }
        if(board.get(i-1,j) == Tile.EMPTY){
            empty++;
            locationEmpty = new Pair(i-1, j);
        }else if(board.get(i-1,j) == Tile.TENT){
            tents++;
        }
        if(board.get(i,j+1) == Tile.EMPTY){
            empty++;
            locationEmpty = new Pair(i, j+1);
        }else if(board.get(i,j+1) == Tile.TENT){
            tents++;
        }
        if(board.get(i,j-1) == Tile.EMPTY){
            empty++;
            locationEmpty = new Pair(i, j-1);
        }else if(board.get(i,j-1) == Tile.TENT){
            tents++;
        }

        if(tents == 0 && empty == 1){
            return board.setTent(locationEmpty.getA(), locationEmpty.getB());
        }

        return false;
    }

}
