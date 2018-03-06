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
        sb.append(Arrays.toString(topNumbers).replaceAll("\\s|\\[|\\]", ""));
        for(Pair p : trees) {
            sb.append("\n");
            sb.append(String.format("%d,%d", p.getA(), p.getB()));
        }
        return sb.toString();
    }
}
