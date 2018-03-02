package main.java.Board;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BoardReader {

    public static Board readBoard(String path){
        int[] topLine = null, sideLine = null;
        ArrayList<Pair> trees = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            topLine = stringToIntArray(reader.readLine());
            sideLine = stringToIntArray(reader.readLine());
            String line = reader.readLine();
            while(line != null){
                String[] pair = line.split(",");
                trees.add(new Pair(Integer.parseInt(pair[0]), Integer.parseInt(pair[1])));
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Board(topLine, sideLine, trees);
    }

    private static int[] stringToIntArray(String in){
        String[] strings = in.split(",");
        int[] out = new int[strings.length];
        for(int i = 0; i < strings.length; i++){
            out[i] = Integer.parseInt(strings[i]);
        }
        return out;
    }

}
