package main.states;

import javafx.util.Pair;

public enum GameMapSize{
    SMALL,
    MEDIUM,
    LARGE,
    None;
    public Pair<Integer, Integer> size;
    static {
        SMALL.size=new Pair<>(20,40);
        MEDIUM.size=new Pair<>(30,60);
        LARGE.size=new Pair<>(40,80);
        None.size=new Pair<>(0,0);
    }

}
