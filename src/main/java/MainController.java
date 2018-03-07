package main.java;

import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import main.java.Board.Board;
import main.java.Board.BoardReader;
import main.java.Board.ModifiableBoard;
import main.java.Board.Tile;

public class MainController {

    private GraphicsContext gc;
    @FXML private Canvas canvas;
    @FXML private Spinner<Integer> widthSpinner, heightSpinner;

    private double tileWidth, tileHeight;
    private double offset = 20;
    private Board board;
    private ModifiableBoard modifiableBoard;
    private boolean modifiable = true, resettable = true;


    public MainController(){
        modifiableBoard = new ModifiableBoard(5,5);
    }

    public void initialize(){
        gc = canvas.getGraphicsContext2D();
        gc.setTextAlign(TextAlignment.CENTER);

        drawBoard();
    }

    public void drawBoard(){
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        Board board = modifiable ? modifiableBoard : this.board;
        tileWidth = (canvas.getWidth()-2*offset)/(board.WIDTH + 1);
        tileHeight = (canvas.getHeight()-2*offset)/(board.HEIGHT + 1);

        gc.setFont(new Font(((canvas.getHeight()-2*offset)/(board.HEIGHT+1))-5 ));
        gc.setTextBaseline(VPos.CENTER);

        drawBackground(board);
        for(int i = 0; i < board.HEIGHT; i++) {
            drawRowNumber(i, board.getRowNumber(i));
        }
        for(int i = 0; i < board.WIDTH; i++) {
            drawColumnNumber(i, board.getColumnNumber(i));
        }
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

    private void drawRowNumber(int row, int num){
        drawNumber(0, row + 1, num);
    }

    private void drawColumnNumber(int col, int num){
        drawNumber(col + 1,  0, num);
    }

    private void drawNumber(int x, int y, Integer num){
        gc.fillText(num.toString(),(x+0.5)*tileWidth + offset, (y+0.5)*tileHeight + offset);
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
            if(x == -1){
                modifiableBoard.incrementRowNumber(y);
            } else if(y == -1){
                modifiableBoard.incrementColumnNumber(x);
            } else {
                modifiableBoard.setTile(x, y, Tile.TREE);
            }
            drawBoard();
        }else if(event.getButton() == MouseButton.SECONDARY && modifiable) {
            int x = (int) ((xCoord - offset) / tileWidth) - 1;
            int y = (int) ((yCoord - offset) / tileHeight) - 1;
            if (x == -1) {
                modifiableBoard.decrementRowNumber(y);
            } else if (y == -1) {
                modifiableBoard.decrementColumnNumber(x);
            } else {
                modifiableBoard.setTile(x, y, Tile.EMPTY);
            }
            drawBoard();
        }
    }

    @FXML private void resetButton(){
        if(resettable){
            int x = widthSpinner.getValue();
            int y = heightSpinner.getValue();
            modifiableBoard = new ModifiableBoard(x,y);
            modifiable = true;
            drawBoard();
        }
    }

    @FXML private void runButton(){
        if(modifiable){
            modifiable = false;
            resettable = false;
            board = BoardReader.readBoard(modifiableBoard.toString());
            TentsAndTrees tAndT = new TentsAndTrees();
            tAndT.giveBoard(board);
            tAndT.giveController(this);
            tAndT.start();
        }
    }

    public void finishedCalculation(){
        drawBoard();
        resettable = true;
    }

}
