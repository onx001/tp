package chessmaster.game;

import chessmaster.exceptions.ParseCoordinateException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CoordinateTest {

    @Test
    public void testIsOffsetWithinBoard() {
        Coordinate coordinate = new Coordinate(3, 4);

        assertTrue(coordinate.isOffsetWithinBoard(-1, 0));
        assertTrue(coordinate.isOffsetWithinBoard(0, -1));
        assertTrue(coordinate.isOffsetWithinBoard(1, 1));

        assertFalse(coordinate.isOffsetWithinBoard(8, 0));
        assertFalse(coordinate.isOffsetWithinBoard(0, 8));
        assertFalse(coordinate.isOffsetWithinBoard(-4, 0));
    }

    @Test
    public void testAddOffsetToCoordinate() {
        Coordinate coordinate = new Coordinate(2, 3);

        Coordinate newCoord1 = coordinate.addOffsetToCoordinate(1, 2);
        Coordinate newCoord2 = coordinate.addOffsetToCoordinate(-2, -3);
        Coordinate newCoord3 = coordinate.addOffsetToCoordinate(0, 0);

        assertEquals(new Coordinate(3, 5), newCoord1);
        assertEquals(new Coordinate(0, 0), newCoord2);
        assertEquals(coordinate, newCoord3); // Should be the same coordinate
    }

    @Test
    public void testParseAlgebraicCoor() throws ParseCoordinateException {
        Coordinate coordinate = Coordinate.parseAlgebraicCoor("d5");

        assertEquals(new Coordinate(3, 3), coordinate);

        // Test invalid notations
        assertThrows(ParseCoordinateException.class, () -> Coordinate.parseAlgebraicCoor("x3"));
        assertThrows(ParseCoordinateException.class, () -> Coordinate.parseAlgebraicCoor("h0"));
        assertThrows(ParseCoordinateException.class, () -> Coordinate.parseAlgebraicCoor("a9"));
    }

    @Test
    public void testCalculateOffsetFrom() {
        Coordinate coordinate1 = new Coordinate(2, 3);
        Coordinate coordinate2 = new Coordinate(4, 1);

        int[] offset = coordinate1.calculateOffsetFrom(coordinate2);

        assertEquals(-2, offset[0]);
        assertEquals(2, offset[1]);
    }

    @Test
    public void testToString() {
        Coordinate coordinate = new Coordinate(1, 6);

        assertEquals("b2", coordinate.toString());
    }

    @Test
    public void testEquals() {
        Coordinate coordinate1 = new Coordinate(2, 3);
        Coordinate coordinate2 = new Coordinate(2, 3);
        Coordinate coordinate3 = new Coordinate(4, 1);

        assertEquals(coordinate1, coordinate2);
        assertNotEquals(coordinate1, coordinate3);
    }
}
