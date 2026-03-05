package it.unibo.pps.grid;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SquareGrid implements Grid {

    private static final List<Pair<Integer, Integer>> OFFSETS = List.of(
            new Position(-1,-1), new Position(-1, 0), new Position(-1, 1),
            new Position( 0,-1),                      new Position( 0, 1),
            new Position( 1,-1), new Position( 1, 0), new Position( 1, 1)
    );
    
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
    public <T> T[][] getFilledGrid(Class<T> clazz, Supplier<T> supplier) {
        @SuppressWarnings("unchecked")
        T[][] array = (T[][]) Array.newInstance(clazz, size, size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = supplier.get();
            }
        }
        return array;
    }

    @Override
    public Stream<Position> getAdjacents(Position centre) {
        return OFFSETS.stream().map(dp -> Position.sum(centre, dp)).filter(this::belongsToTheGrid);
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
