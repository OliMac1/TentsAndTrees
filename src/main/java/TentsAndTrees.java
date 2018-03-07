package main.java;

import main.java.Board.Board;
import main.java.Board.InvalidBoardStateException;
import main.java.Stages.*;

public class TentsAndTrees extends Thread{

    private MainController controller;
    private Board board;

    public void giveBoard(Board board){
        this.board = board;
    }

    public void giveController(MainController controller){
        this.controller = controller;
    }

    public void run(){
        try {
            OpenSpace.run(board);
            try {
                while (true) {
                    if (!RowsAndColumnsStage.run(board)) {
                        if (!ImpliesGrassStage.run(board)) {
                            if (!ReserveTreeStage.run(board)) {
                                if (!OpenSpace.run(board)) {
                                    if(!TreeLastSpaceStage.run(board)){
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (InvalidBoardStateException e) {}
        }finally {
            controller.finishedCalculation();
        }
    }
}
