package chessmaster.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MiniMaxTest {
    @Test
    public void testMiniMax() {
        ChessBoard board = new ChessBoard(Color.BLACK);
        MiniMax miniMax = new MiniMax(board, Color.BLACK, 3, 0);
        Move move = miniMax.getBestMove();
        assertEquals(move.getFrom(), new Coordinate(1, 0));

    }
    //ChessBoard board, Color color, int maxDepth, int score
}
