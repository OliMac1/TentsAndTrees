package main.java.Board;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class BoardReader {

    public static Board readBoard(File file) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
        return readBoard(content);
    }

    public static Board readBoard(String file){
        int[] topLine = null, sideLine = null;
        ArrayList<Pair> trees = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())))){
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
