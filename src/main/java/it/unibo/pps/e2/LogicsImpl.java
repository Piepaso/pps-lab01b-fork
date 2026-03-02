package it.unibo.pps.e2;

import java.util.*;

public class LogicsImpl implements Logics {

	private final Piece pawn;
	private final Piece knight;
    private final Grid grid;
	 
    public LogicsImpl(int size){
    	grid = new SquareGrid(size);
        this.pawn = new Pawn(grid.getNewRandomPos());
        this.knight = new Knight(grid.getNewRandomPos());
    }

    public LogicsImpl(int size, Pair<Integer, Integer> pawnPosition, Pair<Integer, Integer> knightPosition) {
        grid = new SquareGrid(size);
        this.pawn = new Pawn(pawnPosition);
        this.knight = new Knight(knightPosition);
    }

    
	@Override
	public boolean hit(int row, int col) {
        var newPosition = new Pair<>(row,col);
		if (!grid.belongsToTheGrid(newPosition)) {
			throw new IndexOutOfBoundsException();
		}
        return knight.move(newPosition) && this.pawn.getPosition().equals(this.knight.getPosition());
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.knight.getPosition().equals(new Pair<>(row,col));
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.pawn.getPosition().equals(new Pair<>(row,col));
	}
}
