package chessmaster.parser;

import chessmaster.exceptions.ParseColorException;
import chessmaster.game.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParsePlayerColorTest {

    @Test
    public void testParsePlayerColor_inputValidColor() throws ParseColorException {
        String inputWhiteString = "WHITE";
        Color whiteColor = Parser.parsePlayerColor(inputWhiteString);
        assertEquals(Color.WHITE, whiteColor);

        String inputBlackString = "BLACK";
        Color blackColor = Parser.parsePlayerColor(inputBlackString);
        assertEquals(Color.BLACK, blackColor);

        String inputEmptyString = "EMPTY";
        Color emptyColor = Parser.parsePlayerColor(inputEmptyString);
        assertEquals(Color.EMPTY, emptyColor);
    }

    @Test
    public void testParsePlayerColor_inputInvalid_expectParseException() {
        String inputColorString = "dfljasdka";
        assertThrows(ParseColorException.class, () -> {
            Parser.parsePlayerColor(inputColorString);
        });
    }

    @Test
    public void testParsePlayerColor_inputLowerCase_expectParseException() {
        String inputColorString = "white";
        assertThrows(ParseColorException.class, () -> {
            Parser.parsePlayerColor(inputColorString);
        });
    }

    @Test
    public void testParsePlayerColor_inputEmptyString_expectParseException() {
        String inputColorString = "";
        assertThrows(ParseColorException.class, () -> {
            Parser.parsePlayerColor(inputColorString);
        });
    }
}
