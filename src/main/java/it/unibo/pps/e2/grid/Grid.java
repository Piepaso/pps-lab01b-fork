package it.unibo.pps.e2.grid;

public interface Grid {
    Position getNewRandomPos();
    boolean belongsToTheGrid(Position newPosition);
}
