package main.java;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import main.java.Board.Board;
import main.java.Board.BoardReader;
import main.java.Board.ModifiableBoard;
import main.java.Board.Tile;

import java.io.File;
import java.io.IOException;

public class MainController {

    private GraphicsContext gc;
    @FXML private Canvas canvas;
    private double tileWidth, tileHeight;
    private double offset = 20;
    private Board board;
    private ModifiableBoard modifiableBoard;
    private boolean modifiable = true;

    public MainController() throws IOException {
        modifiableBoard = new ModifiableBoard(5,5);
        board = BoardReader.readBoard(new File("res/test.txt"));
    }

    public void initialize(){
        gc = canvas.getGraphicsContext2D();

        drawBoard();
    }

    public void drawBoard(){
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        Board board = modifiable ? modifiableBoard : this.board;
        tileWidth = (canvas.getWidth()-2*offset)/(board.WIDTH + 1);
        tileHeight = (canvas.getHeight()-2*offset)/(board.HEIGHT + 1);
        drawBackground(board);
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

    private void drawBackground(Board board){
        gc.setFill(Color.BLACK);
        gc.fillRect(offset+tileWidth, offset+tileHeight, tileWidth*board.WIDTH, tileHeight*board.HEIGHT);
    }

    private void replaceSpace(int x, int y){
        gc.fillRect((x+1)*tileWidth + offset + 1.5, (y+1)*tileHeight + offset + 1.5,tileWidth - 3,tileHeight - 3);
    }

    @FXML private void clicked(MouseEvent event){
        double xCoord = event.getSceneX();
        double yCoord = event.getSceneY();
        if(event.getButton() == MouseButton.PRIMARY && modifiable){
            int x = (int)((xCoord - offset)/tileWidth)-1;
            int y = (int)((yCoord - offset)/tileHeight)-1;
            modifiableBoard.setTile(x,y, Tile.TREE);
            drawBoard();
        }else if(event.getButton() == MouseButton.SECONDARY && modifiable){
            int x = (int)((xCoord - offset)/tileWidth)-1;
            int y = (int)((yCoord - offset)/tileHeight)-1;
            modifiableBoard.setTile(x,y, Tile.EMPTY);
            drawBoard();
        }
    }

    @FXML private void runButton(){
        if(modifiable){
            modifiable = false;
            board = BoardReader.readBoard(modifiableBoard.toString());
            TentsAndTrees tAndT = new TentsAndTrees();
            tAndT.giveBoard(board);
            tAndT.giveController(this);
            tAndT.start();
        }
    }

    public void finishedCalculation(){
        drawBoard();
    }

}
