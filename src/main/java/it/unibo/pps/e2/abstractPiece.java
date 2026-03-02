package it.unibo.pps.e2;

public abstract class abstractPiece implements Piece{
    private Pair<Integer, Integer> currentPosition;

    public abstractPiece(Pair<Integer, Integer> initialPosition){
        this.currentPosition = initialPosition;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return currentPosition;
    }

    protected void setPosition(Pair<Integer, Integer> position){
        this.currentPosition = position;
    }

    public abstract boolean move(Pair<Integer, Integer> newPosition);
}
