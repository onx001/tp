package chessmaster.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {

    // @@author onx001
    @Test
    public void pointTest(){
        ChessBoard board = new ChessBoard(Color.WHITE);
        board.showChessBoard();
        int points = board.getPoints(Color.WHITE);
        assertEquals(0, points);
    }
    // @@author
        
}
