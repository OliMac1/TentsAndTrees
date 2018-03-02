package main.java.Stages;

import main.java.Board.Board;
import main.java.Board.InvalidBoardStateException;
import main.java.Board.Pair;
import main.java.Board.Tile;

import java.util.ArrayList;

public class RowsAndColumnsStage {

    public static boolean run(Board board) throws InvalidBoardStateException {
        boolean modified = false;
        for(int i = 0; i < board.HEIGHT; i++){
            modified |= runRow(board, i);
        }
        for(int i = 0; i < board.WIDTH; i++){
            modified |= runColumn(board, i);
        }
        return modified;
    }

    private static boolean runColumn( Board board, int col) throws InvalidBoardStateException {
        ArrayList<Pair> pairs = new ArrayList<>();
        int suggested = board.getColumnNumber(col);
        boolean last = false;
        int start = 0;
        int length = 0;
        for(int i = 0; i < board.HEIGHT; i++){
            Tile t = board.get(col, i);
            switch(t){
                case EMPTY:
                    if(!last){
                        start = i;
                        length = 1;
                    }else{
                        length++;
                    }
                    last = true;
                    break;
                case TENT:
                    suggested--;
                default:
                    if(last) {
                        pairs.add(new Pair(start, length));
                        last = false;
                    }
            }
        }
        if(last){
            pairs.add(new Pair(start, length));
        }
        int total = 0;
        for(Pair p : pairs){
            total += (p.getB()+1)/2;
        }
        if(suggested == 0){
            boolean modified = false;
            for(int i = 0; i < board.WIDTH; i++){
                modified |= board.setTile(col, i, Tile.GRASS);
            }
            return modified;
        }
        if(suggested > total) {
            throw new InvalidBoardStateException("Col: " + col);
        }else if(total == suggested){
            boolean modified = false;
            for(Pair p : pairs){
                if(p.getB()%2 == 1){
                    for(int i = 0; i < p.getB(); i++){
                        modified |= board.setTent(col, p.getA() + i);
                    }
                }
            }
            return modified;
        }
        return false;
    }

    private static boolean runRow(Board board, int row) throws InvalidBoardStateException {
        ArrayList<Pair> pairs = new ArrayList<>();
        int suggested = board.getRowNumber(row);
        boolean last = false;
        int start = 0;
        int length = 0;
        for(int i = 0; i < board.WIDTH; i++){
            Tile t = board.get(i, row);
            switch(t){
                case EMPTY:
                    if(!last){
                        start = i;
                        length = 1;
                    }else{
                        length++;
                    }
                    last = true;
                    break;
                case TENT:
                    suggested--;
                default:
                    if(last) {
                        pairs.add(new Pair(start, length));
                        last = false;
                    }
            }
        }
        if(last){
            pairs.add(new Pair(start, length));
        }
        int total = 0;
        for(Pair p : pairs){
            total += (p.getB()+1)/2;
        }
        if(suggested == 0){
            boolean modified = false;
            for(int i = 0; i < board.WIDTH; i++){
                modified |= board.setTile(i, row, Tile.GRASS);
            }
            return modified;
        }
        if(suggested > total) {
            throw new InvalidBoardStateException("Row: " + row);
        }else if(total == suggested){
            boolean modified = false;
            for(Pair p : pairs){
                if(p.getB()%2 == 1){
                    for(int i = 0; i < p.getB(); i++){
                        modified |= board.setTent(p.getA() + i,row);
                    }
                }
            }
            return modified;
        }
        return false;
    }
}
