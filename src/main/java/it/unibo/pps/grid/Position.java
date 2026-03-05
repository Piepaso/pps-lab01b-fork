package it.unibo.pps.grid;

public class Position extends Pair<Integer, Integer> {

	public Position(int x, int y) {
		super(x, y);
	}

	public Position(Pair<Integer, Integer> pair) {
		super(pair.getX(), pair.getY());
	}

	public static Position sum(Position p1, Pair<Integer, Integer> p2) {
		return new Position(p1.getX() + p2.getX(), p1.getY() + p2.getY());
	}
}
