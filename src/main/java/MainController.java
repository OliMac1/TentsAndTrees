package main.java;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.java.Board.Board;
import main.java.Board.BoardReader;

import java.io.File;
import java.io.IOException;

public class MainController {

    private GraphicsContext gc;
    @FXML private Canvas canvas;
    private double tileWidth, tileHeight;
    private double offset = 20;
//    private Board board = new Board(new int[18], new int[18], null);
    private Board board = BoardReader.readBoard(new File("res/test.txt"));

    public MainController() throws IOException {
    }

    public void initialize(){

        TentsAndTrees tAndT = new TentsAndTrees();
        tAndT.giveBoard(board);
        tAndT.run();

        gc = canvas.getGraphicsContext2D();

        tileWidth = (canvas.getWidth()-2*offset)/(board.WIDTH + 1);
        tileHeight = (canvas.getHeight()-2*offset)/(board.HEIGHT + 1);

        startBoard();
    }

    public void startBoard(){
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        drawBackground();
        for(int i = 0; i < board.WIDTH; i++){
            for(int j = 0; j < board.HEIGHT; j++){
                switch(board.get(i,j)){
                    case TENT:
                        drawTent(i,j);
                        break;
                    case TREE:
                    case RESERVED_TREE:
                        drawTree(i,j);
                        break;
                    case GRASS:
                        drawGrass(i,j);
                        break;
                    default:
                        drawEmpty(i,j);
                }
            }
        }
    }

    public void drawEmpty(int x, int y){
        gc.setFill(Color.WHITE);
        replaceSpace(x, y);
    }

    public void drawTent(int x, int y){
        gc.setFill(Color.DARKRED);
        replaceSpace(x, y);
    }

    public void drawGrass(int x, int y){
        gc.setFill(Color.PALEGREEN);
        replaceSpace(x, y);
    }

    public void drawTree(int x, int y){
        gc.setFill(Color.FORESTGREEN);
        replaceSpace(x, y);
    }

    private void drawBackground(){
        gc.setFill(Color.BLACK);
        gc.fillRect(offset+tileWidth, offset+tileHeight, tileWidth*board.WIDTH, tileHeight*board.HEIGHT);
    }

    private void replaceSpace(int x, int y){
        gc.fillRect((x+1)*tileWidth + offset + 1.5, (y+1)*tileHeight + offset + 1.5,tileWidth - 3,tileHeight - 3);
    }

}
