package it.unibo.pps.e3;

public class CellImpl implements Cell {
	private final boolean flag;
	private final int number;

	public CellImpl(boolean flag) {
		this.flag = flag;
		this.number = -1;
	}

	public CellImpl(int number) {
		this.flag = false;
		if (number < 0) {
			throw new IllegalArgumentException("Number must be positive");
		}
		this.number = number;
	}

	public CellImpl() {
		this(false);
	}

	@Override
	public boolean isHidden() {
		return number < 0;
	}

	@Override
	public boolean hasFlag() {
		return flag && isHidden();
	}

	@Override
	public int getNumber() {
		if (isHidden()) {
			throw new IllegalStateException("Cell is hidden");
		}
		return number;
	}
}
