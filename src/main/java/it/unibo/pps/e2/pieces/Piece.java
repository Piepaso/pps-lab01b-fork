package it.unibo.pps.e2.pieces;

import it.unibo.pps.e2.grid.Position;

public interface Piece {
    Position getPosition();

    boolean move(Position newPosition);
}
