package main.java.Board;

public enum Tile {

    EMPTY(0),
    GRASS(1),
    TENT(1),
    TREE(2),
    RESERVED_TREE(3);

    public final int PRIORITY;

    Tile(int priority){
            PRIORITY = priority;
    }

    @Override
    public String toString(){
        switch(this) {
            case TENT:
                return "^";
            case RESERVED_TREE:
            case TREE:
                return "T";
            case GRASS:
                return "G";
            default:
                return " ";
        }
    }

    public boolean replacable(Tile other) {
        return other.PRIORITY > PRIORITY;
    }
}
