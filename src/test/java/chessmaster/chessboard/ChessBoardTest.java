package chessmaster.chessboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import chessmaster.game.ChessBoard;
import chessmaster.game.Color;

public class ChessBoardTest {

    @Test
    public void pointTest(){
        ChessBoard board = new ChessBoard(Color.WHITE);
        board.showChessBoard();
        int points = board.getPoints(Color.WHITE);
        assertEquals(39, points);
    }
        
}
