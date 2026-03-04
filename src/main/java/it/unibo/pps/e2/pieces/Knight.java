package it.unibo.pps.e2.pieces;

import it.unibo.pps.e2.grid.Position;

public class Knight extends abstractPiece {
    public Knight(Position initialPosition) {
        super(initialPosition);
    }

    @Override
    public boolean move(Position newPosition) {
        int x = this.getPosition().getX() - newPosition.getX();
        int y = this.getPosition().getY() - newPosition.getY();
        if (x != 0 && y != 0 && Math.abs(x) + Math.abs(y) == 3) {
            this.setPosition(newPosition);
            return true;
        }
        return false;
    }
}
