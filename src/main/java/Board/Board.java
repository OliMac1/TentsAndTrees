package main.java.Board;

import java.util.List;

public class Board {

    private Tile[][] board;
    private int[] topNumbers, sideNumbers;
    public final int WIDTH, HEIGHT;

    public Board(int[] topNumbers, int[] sideNumbers, List<Pair> treePositions){
        this.topNumbers = topNumbers;
        this.sideNumbers = sideNumbers;
        WIDTH = topNumbers.length;
        HEIGHT = sideNumbers.length;
        board = generateEmptyBoard(WIDTH, HEIGHT);
        if(treePositions != null) {
            for (Pair p : treePositions) {
                pSetTile(p.getA(), p.getB(), Tile.TREE);
            }
        }
    }

    private void pSetTile(int x, int y, Tile tile){
        board[x][y] = tile;
    }

    public boolean setTile(int x, int y, Tile tile){
        if(tile == Tile.TREE || x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT){
            return false;
        }
        Tile current = board[x][y];
        if(current.replacable(tile)){
            pSetTile(x, y, tile);
            return true;
        }
        return false;
    }

    public boolean setTent(int x, int y){
        if(setTile(x, y, Tile.TENT)){
            for(int i = -1; i < 2; i++){
                for(int j = -1; j < 2; j++){
                    setTile(x+i, y+j, Tile.GRASS);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int j = 0; j < board[0].length; j++){
            for(int i = 0; i < board.length; i++){
                sb.append(String.format("[%s]", board[i][j].toString()));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static Tile[][] generateEmptyBoard(int width, int height){
        Tile[][] board = new Tile[width][height];
        for(int j = 0; j < board[0].length; j++){
            for(int i = 0; i < board.length; i++){
                board[i][j] = Tile.EMPTY;
            }
        }
        return board;
    }

    public Tile get(int i, int j) {
        if(i < 0 || i >= WIDTH || j < 0 || j >= HEIGHT){
            return Tile.EMPTY;
        }
        return board[i][j];
    }

    public int getRowNumber(int row) {
        return sideNumbers[row];
    }

    public int getColumnNumber(int column) {
        return topNumbers[column];
    }
}
