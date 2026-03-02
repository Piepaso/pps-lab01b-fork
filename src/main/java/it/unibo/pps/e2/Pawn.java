package it.unibo.pps.e2;

public class Pawn extends abstractPiece {
    public Pawn(Pair<Integer, Integer> initialPosition) {
        super(initialPosition);
    }

    @Override
    public boolean move(Pair<Integer, Integer> newPosition) {
        return false;
    }
}
