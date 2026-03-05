package it.unibo.pps.e2.pieces;

import it.unibo.pps.grid.Position;

public abstract class abstractPiece implements Piece{
    private Position currentPosition;

    public abstractPiece(Position initialPosition){
        this.currentPosition = initialPosition;
    }

    @Override
    public Position getPosition() {
        return currentPosition;
    }

    protected void setPosition(Position position){
        this.currentPosition = position;
    }

    public abstract boolean move(Position newPosition);
}
