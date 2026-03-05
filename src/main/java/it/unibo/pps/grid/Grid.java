package it.unibo.pps.grid;

import java.util.function.Supplier;
import java.util.stream.Stream;

public interface Grid {
    Position getNewRandomPos();
    boolean belongsToTheGrid(Position newPosition);
    <T> T[][] getFilledGrid(Class<T> clazz, Supplier<T> supplier);
    Stream<Position> getAdjacents(Position centre);
}

