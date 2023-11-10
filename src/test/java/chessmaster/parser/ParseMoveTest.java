package chessmaster.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import chessmaster.exceptions.ChessMasterException;
import chessmaster.exceptions.MoveOpponentPieceException;
import chessmaster.exceptions.NullPieceException;
import chessmaster.exceptions.ParseCoordinateException;
import chessmaster.game.ChessBoard;
import chessmaster.game.ChessTile;
import chessmaster.game.Color;
import chessmaster.game.Coordinate;
import chessmaster.game.move.Move;
import chessmaster.pieces.ChessPiece;

public class ParseMoveTest {
    private static final String[][] STARTING_CHESSBOARD_BLACK = { 
        { "r", "n", "b", "q", "k", "b", "n", "r" }, 
        { "p", "p", "p", "p", "p", "p", "p", "p" }, 
        { ".", ".", ".", ".", ".", ".", ".", "." }, 
        { ".", ".", ".", ".", ".", ".", ".", "." }, 
        { ".", ".", ".", ".", ".", ".", ".", "." }, 
        { ".", ".", ".", ".", ".", ".", ".", "." }, 
        { "P", "P", "P", "P", "P", "P", "P", "P" }, 
        { "R", "N", "B", "Q", "K", "B", "N", "R" }, 
    };

    private ChessBoard loadChessBoard() {
        ChessTile[][] chessTiles = new ChessTile[ChessBoard.SIZE][ChessBoard.SIZE];
        for (int row = 0; row < ChessBoard.SIZE; row++) {
            for (int col = 0; col < ChessBoard.SIZE; col++) {
                String chessPieceString = STARTING_CHESSBOARD_BLACK[row][col];
                ChessPiece initialPiece = Parser.parseChessPiece(chessPieceString, row, col);
                chessTiles[row][col] = new ChessTile(initialPiece);
                assert (chessTiles[row][col] != null);
            }
        }
        return new ChessBoard(Color.BLACK, chessTiles);
    }

    @Test
    public void testParseMove_inputValidMove() throws ChessMasterException {
        String inputString = "h2 h4";
        ChessBoard emptyBoard = loadChessBoard();
        Move move = Parser.parseMove(inputString, emptyBoard, true);

        Coordinate from = new Coordinate(7, 6); // h2
        Coordinate to = new Coordinate(7, 4);   // h4
        Move expectedMove = new Move(from, to, emptyBoard.getPieceAtCoor(from));

        assertEquals(move, expectedMove);
    }

    @Test
    public void testParseMove_inputCannotParseStart_expectParseException() throws ChessMasterException {
        String inputString = "h2dasd h4";
        ChessBoard emptyBoard = loadChessBoard();
        assertThrows(ParseCoordinateException.class, () -> {
            Parser.parseMove(inputString, emptyBoard, true);
        });
    }

    @Test
    public void testParseMove_inputCannotParseDest_expectParseException() throws ChessMasterException {
        String inputString = "h2 h4dafsd";
        ChessBoard emptyBoard = loadChessBoard();
        assertThrows(ParseCoordinateException.class, () -> {
            Parser.parseMove(inputString, emptyBoard, true);
        });
    }

    @Test
    public void testParseMove_inputMoreThan2Inputs_expectParseException() throws ChessMasterException {
        String inputString = "h2 h4 a3";
        ChessBoard emptyBoard = loadChessBoard();
        assertThrows(ParseCoordinateException.class, () -> {
            Parser.parseMove(inputString, emptyBoard, true);
        });
    }

    @Test
    public void testParseMove_inputOneInput_expectParseException() throws ChessMasterException {
        String inputString = "h2";
        ChessBoard emptyBoard = loadChessBoard();
        assertThrows(ParseCoordinateException.class, () -> {
            Parser.parseMove(inputString, emptyBoard, true);
        });
    }

    @Test
    public void testParseMove_inputEmptyPiece_expectNullPieceException() throws ChessMasterException {
        String inputString = "d4 d5";
        ChessBoard emptyBoard = loadChessBoard();
        assertThrows(NullPieceException.class, () -> {
            Parser.parseMove(inputString, emptyBoard, true);
        });
    }

    @Test
    public void testParseMove_inputNotFriendly_expectMoveOpponentException() throws ChessMasterException {
        String inputString = "a7 a6";
        ChessBoard emptyBoard = loadChessBoard();
        assertThrows(MoveOpponentPieceException.class, () -> {
            Parser.parseMove(inputString, emptyBoard, true);
        });
    }
}
