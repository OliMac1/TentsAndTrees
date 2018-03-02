package main.java;

import main.java.Board.Board;
import main.java.Board.BoardReader;
import main.java.Board.InvalidBoardStateException;
import main.java.Stages.OpenSpace;
import main.java.Stages.RowsAndColumnsStage;

public class TentsAndTrees {

    public static void main(String[] args) {
        Board board = BoardReader.readBoard("res/test.txt");
        OpenSpace.run(board);
        try {
            while (true) {
                if (!RowsAndColumnsStage.run(board)) {
                    break;
                }
            }
        } catch (InvalidBoardStateException e) {
            e.printStackTrace();
        }
        System.out.println(board);
    }
}
