package it.unibo.pps.e3;

import it.unibo.pps.grid.Grid;
import it.unibo.pps.grid.Position;
import it.unibo.pps.grid.SquareGrid;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicsImpl implements Logics {

	private final int size;
	private final Set<Position> mines;
	private final Grid grid;
	private final Cell[][] cells;
	private int totalRevealed = 0;

	public LogicsImpl(int size, int minesNumber) {
		this.size = size;
		this.grid = new SquareGrid(size);
		this.mines = Stream.generate(grid::getNewRandomPos).limit(minesNumber).collect(Collectors.toSet());
		this.cells = grid.getFilledGrid(Cell.class, CellImpl::new);
	}

	public LogicsImpl(int size, Set<Position> mines) {
		this.size = size;
		this.grid = new SquareGrid(size);
		this.mines = mines;
		this.cells = grid.getFilledGrid(Cell.class, CellImpl::new);
	}

	@Override
	public boolean reveal(Position pos) {
		if (mines.contains(pos)) {
			return true;
		}
		if (getCellAtPosition(pos).isHidden()) {
			totalRevealed++;
			System.out.println(totalRevealed);

			int adjacentMines = countsAdjacentMines(pos);
			setCell(pos, new CellImpl(adjacentMines));
			if (adjacentMines == 0) {
				grid.getAdjacents(pos).forEach(this::reveal);
			}
		}
		return false;
	}

	@Override
	public boolean isThereVictory() {
		return totalRevealed == size*size - mines.size();
	}

	@Override
	public void toggleFlag(Position pos) {
		var cell = getCellAtPosition(pos);
		if (cell.isHidden()) {
			setCell(pos, new CellImpl(!cell.hasFlag()));
		}
	}

	@Override
	public boolean isThereMine(Position value) {
		return mines.contains(new Position(value));
	}

	@Override
	public Cell getCellAtPosition(Position position) {
		return this.cells[position.getX()][position.getY()];
	}

	private int countsAdjacentMines(Position pos) {
		return grid.getAdjacents(pos).mapToInt(p -> mines.contains(p) ? 1 : 0).sum();
	}

	private void setCell(Position pos, Cell newCell) {
		this.cells[pos.getX()][pos.getY()] = newCell;
	}
}
