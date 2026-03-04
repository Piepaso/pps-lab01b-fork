package it.unibo.pps.e2.logics;

import it.unibo.pps.e2.grid.Grid;
import it.unibo.pps.e2.grid.Pair;
import it.unibo.pps.e2.grid.Position;
import it.unibo.pps.e2.grid.SquareGrid;
import it.unibo.pps.e2.pieces.Knight;
import it.unibo.pps.e2.pieces.Pawn;
import it.unibo.pps.e2.pieces.Piece;

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
        this.pawn = new Pawn(new Position(pawnPosition.getX(), pawnPosition.getY()));
        this.knight = new Knight(new Position(knightPosition.getX(), knightPosition.getY()));
    }

    
	@Override
	public boolean hit(int row, int col) {
        var newPosition = new Position(row,col);
		if (!grid.belongsToTheGrid(newPosition)) {
			throw new IndexOutOfBoundsException();
		}
        return knight.move(newPosition) && this.pawn.getPosition().equals(this.knight.getPosition());
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.knight.getPosition().equals(new Position(row,col));
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.pawn.getPosition().equals(new Position(row,col));
	}
}
