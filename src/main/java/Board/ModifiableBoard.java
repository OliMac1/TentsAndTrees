package main.java.Board;

import java.util.ArrayList;
import java.util.Arrays;

public class ModifiableBoard extends Board{

    public ModifiableBoard(int width, int height) {
        super(new int[width], new int[height], null);
    }

    @Override
    public boolean setTile(int x, int y, Tile tile){
        if(x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT){
            return false;
        }
        board[x][y] = tile;
        return true;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        ArrayList<Pair> trees = new ArrayList<>();
        for(int i = 0; i < WIDTH; i++){
            for(int j = 0; j < HEIGHT; j++){
                if(board[i][j] == Tile.TREE){
                    trees.add(new Pair(i,j));
                }
            }
        }
        sb.append(Arrays.toString(topNumbers).replaceAll("\\s|\\[|\\]", ""));
        sb.append("\n");
        sb.append(Arrays.toString(sideNumbers).replaceAll("\\s|\\[|\\]", ""));
        for(Pair p : trees) {
            sb.append("\n");
            sb.append(String.format("%d,%d", p.getA(), p.getB()));
        }
        return sb.toString();
    }

    public void incrementRowNumber(int row){
        if(row < 0 || row >= HEIGHT){
            return;
        }
        sideNumbers[row] = (sideNumbers[row]+1)%(1 + (WIDTH + 1)/2);
    }

    public void decrementRowNumber(int row){
        if(row < 0 || row >= HEIGHT){
            return;
        }
        sideNumbers[row] -= 1;
        if(sideNumbers[row] < 0){
            sideNumbers[row] += 1 + (WIDTH + 1)/2;
        }
    }

    public void incrementColumnNumber(int col){
        if(col < 0 || col >= WIDTH){
            return;
        }
        topNumbers[col] = (topNumbers[col]+1)%(1 + (HEIGHT + 1)/2);
    }

    public void decrementColumnNumber(int col){
        if(col < 0 || col >= WIDTH){
            return;
        }
        topNumbers[col] -= 1;
        if(topNumbers[col] < 0){
            topNumbers[col] += 1 + (HEIGHT + 1)/2;
        }
    }

}
