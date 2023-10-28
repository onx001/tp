package chessmaster.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ColorTest {

    @Test
    public void testGetOppositeColour() {
        assertEquals(Color.BLACK, Color.WHITE.getOppositeColour());
        assertEquals(Color.WHITE, Color.BLACK.getOppositeColour());
        assertEquals(Color.EMPTY, Color.EMPTY.getOppositeColour());
    }

    @Test
    public void testIsWhite() {
        assertTrue(Color.WHITE.isWhite());
        assertFalse(Color.BLACK.isWhite());
        assertFalse(Color.EMPTY.isWhite());
    }

    @Test
    public void testIsBlack() {
        assertTrue(Color.BLACK.isBlack());
        assertFalse(Color.WHITE.isBlack());
        assertFalse(Color.EMPTY.isBlack());
    }
}
