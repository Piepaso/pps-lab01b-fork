package it.unibo.pps.e2.logics;
import it.unibo.pps.grid.Pair;
import org.junit.jupiter.api.*;

import java.util.function.BiPredicate;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

    private final int size = 5;
    private Logics logic;
    private Pair<Integer, Integer> pawn;
    private Pair<Integer, Integer> knight;

    @BeforeEach
    public void beforeEach(){
        this.pawn = new Pair<>(3 , 3);
        this.knight = new Pair<>(4 , 1);
        this.logic = new LogicsImpl(size, pawn, knight);
    }

    @Test
    public void testThereIsInitiallyOnePawn() {
        final Logics randomLogic = new LogicsImpl(size);
        assertEquals(1, countNumberOfPiece(randomLogic::hasPawn));
    }

    @Test
    public void testThereIsInitiallyOneKnight() {
        final Logics randomLogic = new LogicsImpl(size);
        assertEquals(1, countNumberOfPiece(randomLogic::hasKnight));
    }

    @Test
    public void pawnAndKnightAreNotInSamePosition() {
        final Logics randomLogic = new LogicsImpl(size);
        for (int row=0;row<size;row++){
            for(int col=0;col<size;col++){
                assertFalse(randomLogic.hasPawn(row, col) && randomLogic.hasKnight(row, col));
            }
        }
    }

    @Test
    public void testSimpleHit() {
        final int knightX = knight.getX() - 2;
        final int knightY = knight.getY() + 1;

        assertFalse(logic.hit(knightX, knightY));
        assertTrue(logic.hasKnight(knightX, knightY));
    }

    @Test
    public void testIllegalHitNotMove() {
        final int knightX = knight.getX() - 1;
        final int knightY = knight.getY() + 1;

        logic.hit(knightX, knightY);
        assertFalse(logic.hasKnight(knightX, knightY));
    }

    @Test
    public void testIllegalPawnHit() {
        logic.hit(knight.getX() - 2, knight.getY() + 1);
        assertFalse(logic.hit(pawn.getX(), pawn.getY()));
    }

    @Test
    public void testPawnHit() {
        assertTrue(logic.hit(pawn.getX(), pawn.getY()));
    }

    private int countNumberOfPiece(BiPredicate<Integer, Integer> thereIsPieceInPosition) {
        int pieceFoundNumber = 0;
        for (int row=0;row<size;row++){
            for(int col=0;col<size;col++){
                pieceFoundNumber += thereIsPieceInPosition.test(row, col) ? 1 : 0;
            }
        }
        return pieceFoundNumber;
    }
}
