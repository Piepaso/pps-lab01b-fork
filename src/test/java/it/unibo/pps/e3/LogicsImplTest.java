package it.unibo.pps.e3;

import it.unibo.pps.grid.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class LogicsImplTest {

	private Set<Position> mines;
	private Logics logic;
	private final int size = 8;

	@BeforeEach
	void init() {
		this.mines = Set.of(new Position(3, 2), new Position(1, 1));
		this.logic = new LogicsImpl(size, mines);
	}

	@Test
	void testIsThereMine() {
		for (var mine : mines) {
			assertTrue(logic.isThereMine(mine));
		}
	}

	@Test
	void testInitiallyAllHidden() {
		assertAllCellsMatch(Cell::isHidden);
	}

	@Test
	void testInitiallyAllNotFlag() {
		assertAllCellsMatch(cell -> !cell.hasFlag());
	}

	@Test
	void testReveal() {
		var pos = new Position(0, 0);
		logic.reveal(pos);
		assertFalse(logic.getCellAtPosition(pos).isHidden());
	}

	@Test
	void testRevealedNearbyMinesCount() {
		var pos = new Position(2, 2);
		logic.reveal(pos);
		assertEquals(2, logic.getCellAtPosition(pos).getNumber());
	}

	@Test
	void testMineRevealed() {
		var pos = mines.stream().findFirst().get();
		logic.reveal(pos);
		assertTrue(logic.reveal(pos));
	}


	@Test
	void testRevealAdjacentsIfEmptyCell() {
		var pos = new Position(4, 4);
		logic.reveal(pos);
		assertFalse(logic.getCellAtPosition(new Position(3, 4)).isHidden());
	}

	@Test
	void isThereVictory() {
		logic.reveal(new Position(0, 4));
		logic.reveal(new Position(0, 0));
		logic.reveal(new Position(0, 1));
		assertFalse(logic.isThereVictory());
		logic.reveal(new Position(1, 0));
		assertTrue(logic.isThereVictory());
	}

	@Test
	void testToggleFlag() {
		var pos = new Position(1,1);
		logic.toggleFlag(pos);
		assertTrue(logic.getCellAtPosition(pos).hasFlag());
		logic.toggleFlag(pos);
		assertFalse(logic.getCellAtPosition(pos).hasFlag());
	}

	private void assertAllCellsMatch(Predicate<Cell> condition) {
		for (int col = 0; col < size; col++) {
			for (int row = 0; row < size; row++) {
				Cell cell = logic.getCellAtPosition(new Position(row, col));
				assertTrue(condition.test(cell), "Failed at " + row + "," + col);
			}
		}
	}
}