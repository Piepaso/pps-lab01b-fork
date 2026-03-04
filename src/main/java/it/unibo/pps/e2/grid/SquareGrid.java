package it.unibo.pps.e2.grid;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SquareGrid implements Grid {
    private final int size;
    private final Iterator<Position> randomFreePositions;

    public SquareGrid(int size) {
	    this.size = size;
        this.randomFreePositions = getRandomPositionIterator(size);
    }

    @Override
    public boolean belongsToTheGrid(Position p) {
        return p.getX() >= 0 && p.getX() < size && p.getY() >= 0 && p.getY() < size;
    }

    @Override
    public Position getNewRandomPos() {
        if (!randomFreePositions.hasNext()) {
            throw new IllegalStateException("All possible position has been returned");
        }
        return randomFreePositions.next();
    }
    
    private Iterator<Position> getRandomPositionIterator(int size) {
        List<Position> positions = IntStream.range(0, size * size)
                .mapToObj(i -> new Position(i % size, i / size))
                .collect(Collectors.toList());
        Collections.shuffle(positions);
        return positions.iterator();
    }

}
