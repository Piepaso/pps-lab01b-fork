package it.unibo.pps.e3;

import it.unibo.pps.grid.Position;

public interface Logics {

	boolean reveal(Position pos);

	boolean isThereVictory();

	void toggleFlag(Position pos);

	boolean isThereMine(Position value);

	Cell getCellAtPosition(Position position);
}
