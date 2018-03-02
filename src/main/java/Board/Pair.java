package main.java.Board;

public class Pair {

    private int a, b;

    public Pair(int a, int b){
        this.a = a;
        this.b = b;
    }

    public int getA(){
        return a;
    }

    public int getB(){
        return b;
    }

    public String toString(){
        return "(" + a + ", " + b + ")";
    }
}
