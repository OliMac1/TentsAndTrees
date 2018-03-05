package main.java;

import main.java.Board.Board;
import main.java.Board.BoardReader;
import main.java.Board.InvalidBoardStateException;
import main.java.Stages.ImpliesGrassStage;
import main.java.Stages.OpenSpace;
import main.java.Stages.ReserveTreeStage;
import main.java.Stages.RowsAndColumnsStage;

import java.io.File;
import java.io.IOException;

public class TentsAndTrees extends Thread{

    private Board board;

    public void giveBoard(Board board){
        this.board = board;
    }

    public void run(){
        OpenSpace.run(board);
        try {
            while (true) {
                if (!RowsAndColumnsStage.run(board)) {
                    if (!ImpliesGrassStage.run(board)) {
                        if(!ReserveTreeStage.run(board)) {
                            if(!OpenSpace.run(board)) {
                                break;
                            }
                        }
                    }
                }
            }
        } catch (InvalidBoardStateException e) {
            e.printStackTrace();
        }
        System.out.println(board);
    }
}
