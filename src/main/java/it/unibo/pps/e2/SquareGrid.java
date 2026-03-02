package it.unibo.pps.e2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SquareGrid implements Grid {
    private final int size;
    private final Random random = new Random();
    private final Set<Pair<Integer, Integer>> usedPositions;

    public SquareGrid(int size) {
        this.size = size;
        this.usedPositions = new HashSet<>();
    }

    @Override
    public boolean belongsToTheGrid(Pair<Integer, Integer> p) {
        return p.getX() >= 0 && p.getX() < size && p.getY() >= 0 && p.getY() < size;
    }

    @Override
    public Pair<Integer, Integer> getNewRandomPos() {
        var pos = new Pair<>(this.random.nextInt(size),this.random.nextInt(size));
        return usedPositions.add(pos) ? pos : getNewRandomPos();
    }

}
