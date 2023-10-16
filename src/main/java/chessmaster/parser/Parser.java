package chessmaster.parser;

import chessmaster.exceptions.ParseCoordinateException;
import chessmaster.game.ChessBoard;
import chessmaster.game.Coordinate;
import chessmaster.pieces.King;
import chessmaster.pieces.Queen;
import chessmaster.pieces.Bishop;
import chessmaster.pieces.Rook;
import chessmaster.pieces.Knight;
import chessmaster.pieces.Pawn;
import chessmaster.pieces.ChessPiece;
import chessmaster.game.Move;

public class Parser {
    /**
     * Parses the user's typed input into the UI and executes actions accordingly.
     *
     * @param in    Command entered by the user.
     * @param board Chessboard the user is currently playing on.
     */
    public void parseCommand(String in, ChessBoard board) {
        String commandWord = in.split(" ")[0].toLowerCase();

        switch(commandWord){
            case "abort":
            default:
                try {
                    Move move = parseMove(in, board);
                    board.movePiece(move);
                } catch (Exception E) {
                    //TODO add function to display error message
                }
        }
    }
    /**
     * Parses an input string and returns the move indicated by the string.
     * Used to read user inputs during the chess game.
     *
     * @param in    String containing the user's intended move.
     * @param board The chessboard the user is currently playing on.
     * @return Move class containing information about the move to be made.
     *
     * @throws ParseCoordinateException If the string entered does not match a coordinate.
     */
    public Move parseMove(String in, ChessBoard board) throws ParseCoordinateException {
        Coordinate[] moveArray = new Coordinate[2];
        String[] parseArray = in.split(" ");

        for (int i = 0; i < 2; i += 1) {
            moveArray[i] = Coordinate.parseAlgebraicCoor(parseArray[i]);
        }

        return new Move(moveArray[0], moveArray[1], board);
    }
    /**
     * Parses an input string and creates a ChessPiece object at the specified row
     * and column.
     * Used for loading ChessPiece(s) from storage file or loading starting
     * ChessBoard.
     * Returns null for recognised input string to signify that piece is empty (for
     * ChessTile)
     *
     * @param pieceString The string representation of the chess piece, e.g., "bB"
     *                    for black bishop.
     * @param row         The row where the piece is located.
     * @param col         The column where the piece is located.
     * @return A ChessPiece object representing the parsed chess piece, or null if
     *         the pieceString is not recognized.
     */
    public static ChessPiece parseChessPiece(String pieceString, int row, int col) {
        switch (pieceString) {
        case Bishop.BISHOP_BLACK:
            return new Bishop(row, col, ChessPiece.BLACK);
        case Bishop.BISHOP_WHITE:
            return new Bishop(row, col, ChessPiece.WHITE);
        case King.KING_BLACK:
            return new King(row, col, ChessPiece.BLACK);
        case King.KING_WHITE:
            return new King(row, col, ChessPiece.WHITE);
        case Queen.QUEEN_BLACK:
            return new Queen(row, col, ChessPiece.BLACK);
        case Queen.QUEEN_WHITE:
            return new Queen(row, col, ChessPiece.WHITE);
        case Knight.KNIGHT_BLACK:
            return new Knight(row, col, ChessPiece.BLACK);
        case Knight.KNIGHT_WHITE:
            return new Knight(row, col, ChessPiece.WHITE);
        case Pawn.PAWN_BLACK:
            return new Pawn(row, col, ChessPiece.BLACK);
        case Pawn.PAWN_WHITE:
            return new Pawn(row, col, ChessPiece.WHITE);
        case Rook.ROOK_BLACK:
            return new Rook(row, col, ChessPiece.BLACK);
        case Rook.ROOK_WHITE:
            return new Rook(row, col, ChessPiece.WHITE);
        default:
            return null;
        }
    }
}
