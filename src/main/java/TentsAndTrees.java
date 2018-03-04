package main.java;

import main.java.Board.Board;
import main.java.Board.BoardReader;
import main.java.Board.InvalidBoardStateException;
import main.java.Stages.ImpliesGrassStage;
import main.java.Stages.OpenSpace;
import main.java.Stages.RowsAndColumnsStage;

import java.io.File;
import java.io.IOException;

public class TentsAndTrees extends Thread{

    public void run(){
        try {
            Board board = BoardReader.readBoard(new File("res/test.txt"));
            OpenSpace.run(board);
            try {
                while (true) {
                    if (!RowsAndColumnsStage.run(board)) {
                        if (!ImpliesGrassStage.run(board)) {
                            break;
                        }
                    }
                }
            } catch (InvalidBoardStateException e) {
                e.printStackTrace();
            }
            System.out.println(board);
        }catch (IOException e){}
    }
}
