package it.unibo.pps.e2;

public interface Piece {
    Pair<Integer, Integer> getPosition();

    boolean move(Pair<Integer, Integer> newPosition);
}
