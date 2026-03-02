package it.unibo.pps.e2;

public interface Grid {
    Pair<Integer, Integer> getNewRandomPos();
    boolean belongsToTheGrid(Pair<Integer, Integer> newPosition);
}
