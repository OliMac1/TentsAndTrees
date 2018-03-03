package main.java.Stages;

import main.java.Board.Board;
import main.java.Board.Tile;

import java.util.Arrays;

public class ImpliesGrassStage {

    public static boolean run(Board board) {
        boolean modified = false;
        for(int i = 0; i < board.HEIGHT; i++){
            modified |= runRow(board, i);
        }
        for(int i = 0; i < board.WIDTH; i++){
            modified |= runColumn(board, i);
        }
        return modified;
    }

    private static boolean runRow(Board board, int row){
        boolean[] mask = new boolean[board.WIDTH];
        int count = board.getRowNumber(row);
        for(int i = 0; i < board.WIDTH; i++){
            switch(board.get(i, row)){
                case EMPTY:
                    break;
                case TENT:
                    count--;
                default:
                    mask[i] = true;
            }
        }

        boolean[] result = implyGrass(mask, count, 0);

        boolean modified = false;
        for(int i = 0; i < result.length; i++){
            if(result[i]) {
                modified |= board.setTile(i, row - 1, Tile.GRASS);
                modified |= board.setTile(i, row + 1, Tile.GRASS);
            }
        }

        return modified;
    }

    private static boolean runColumn(Board board, int col){
        boolean[] mask = new boolean[board.HEIGHT];
        int count = board.getColumnNumber(col);
        for(int i = 0; i < board.HEIGHT; i++){
            switch(board.get(col, i)){
                case EMPTY:
                    break;
                case TENT:
                    count--;
                default:
                    mask[i] = true;
            }
        }

        boolean[] result = implyGrass(mask, count, 0);

        boolean modified = false;
        for(int i = 0; i < result.length; i++){
            if(result[i]) {
                modified |= board.setTile(col - 1, i, Tile.GRASS);
                modified |= board.setTile(col + 1, i, Tile.GRASS);
            }
        }

        return modified;
    }

    private static boolean[] implyGrass(boolean[] mask, int count, int start){
        if(count == 0) return new boolean[mask.length];
        boolean[] internal = null;
        for(int i = start; i < mask.length; i++){
            if(!mask[i]){
                boolean[] temp = implyGrass(mask, count - 1, i + 2);
                if(temp == null){
                    break;
                }
                placeHouse(temp, i);
                if(internal == null){
                    internal = temp;
                }else{
                    for(int j = 0; j < internal.length; j++){
                        internal[j] &= temp[j];
                    }
                }
            }
        }
        return internal;
    }

    private static void placeHouse(boolean[] temp, int i){
        if(i != 0){
            temp[i-1] = true;
        }
        temp[i] = true;
        if(i != temp.length-1){
            temp[i+1] = true;
        }
    }
}
