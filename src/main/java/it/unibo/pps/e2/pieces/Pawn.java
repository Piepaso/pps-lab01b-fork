package it.unibo.pps.e2.pieces;

import it.unibo.pps.e2.grid.Position;

public class Pawn extends abstractPiece {
    public Pawn(Position initialPosition) {
        super(initialPosition);
    }

    @Override
    public boolean move(Position newPosition) {
        return false;
    }
}
