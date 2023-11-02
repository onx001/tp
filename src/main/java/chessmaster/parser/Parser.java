package chessmaster.parser;

import chessmaster.commands.AbortCommand;
import chessmaster.commands.Command;
import chessmaster.commands.HelpCommand;
import chessmaster.commands.MoveCommand;
import chessmaster.commands.RulesCommand;
import chessmaster.commands.ShowCommand;
import chessmaster.commands.ShowMovesCommand;
import chessmaster.commands.LegendCommand;
import chessmaster.exceptions.MoveOpponentPieceException;
import chessmaster.exceptions.NullPieceException;
import chessmaster.exceptions.ParseColorException;
import chessmaster.exceptions.ParseCoordinateException;
import chessmaster.game.ChessBoard;
import chessmaster.game.Color;
import chessmaster.game.Coordinate;
import chessmaster.game.Move;
import chessmaster.pieces.Bishop;
import chessmaster.pieces.ChessPiece;
import chessmaster.pieces.EmptyPiece;
import chessmaster.pieces.King;
import chessmaster.pieces.Knight;
import chessmaster.pieces.Pawn;
import chessmaster.pieces.Queen;
import chessmaster.pieces.Rook;

public class Parser {

    //@@author ken-ruster
    /**
     * Parses a string telling which chess piece the user wants to promote his piece
     * to, and promotes the relevant piece
     *
     * @param promoteFrom Chess piece to be promoted.
     * @param promoteTo   String representing the type of piece to be promoted to.
     * @return Promoted chess piece.
     */
    public static ChessPiece parsePromote(ChessPiece promoteFrom, String promoteTo) {
        Color color = promoteFrom.getColor();
        Coordinate position = promoteFrom.getPosition();

        switch (promoteTo) {
        case Bishop.BISHOP_WHITE:
            return new Bishop(position.getY(), position.getX(), color);
        case Queen.QUEEN_WHITE:
            return new Queen(position.getY(), position.getX(), color);
        case Knight.KNIGHT_WHITE:
            return new Knight(position.getY(), position.getX(), color);
        case Rook.ROOK_WHITE:
            return new Rook(position.getY(), position.getX(), color);
        default:
            return promoteFrom;
        }
    }

    //@@author TongZhengHong
    /**
     * Parses a chess move from user input and creates a Move object. Used to read
     * user inputs during the chess game.
     *
     * @param in    The user input string with 2 algebraic coordinate notations
     *              (e.g., "e2 e4").
     * @param board The ChessBoard where the move is taking place.
     * @return Move object containing information about the move to be made.
     * 
     * @throws ParseCoordinateException   If the string entered is not in the
     *                                    algebraic coordinate notation.
     * @throws NullPieceException
     * @throws MoveOpponentPieceException
     */
    public static Move parseMove(String in, ChessBoard board) throws ParseCoordinateException,
            NullPieceException, MoveOpponentPieceException {

        String[] parseArray = in.split("\\s+", 2);
        if (parseArray.length < 2) {
            throw new ParseCoordinateException();
        }

        Coordinate from = Coordinate.parseAlgebraicCoor(parseArray[0]);
        Coordinate to = Coordinate.parseAlgebraicCoor(parseArray[1]);

        ChessPiece relevantPiece = board.getPieceAtCoor(from);
        if (relevantPiece.isEmptyPiece()) {
            throw new NullPieceException();
        } else if (board.isPieceOpponent(relevantPiece)) {
            throw new MoveOpponentPieceException();
        }

        return new Move(from, to, relevantPiece);
    }

    /**
     * Parses an input string and creates a ChessPiece object at the specified row
     * and column. Used for loading ChessPiece(s) from storage file or loading
     * starting ChessBoard. Returns null for recognised input string to signify that
     * piece is empty (for ChessTile)
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
            return new Bishop(row, col, Color.BLACK);
        case Bishop.BISHOP_WHITE:
            return new Bishop(row, col, Color.WHITE);
        case King.KING_BLACK:
            return new King(row, col, Color.BLACK);
        case King.KING_WHITE:
            return new King(row, col, Color.WHITE);
        case Queen.QUEEN_BLACK:
            return new Queen(row, col, Color.BLACK);
        case Queen.QUEEN_WHITE:
            return new Queen(row, col, Color.WHITE);
        case Knight.KNIGHT_BLACK:
            return new Knight(row, col, Color.BLACK);
        case Knight.KNIGHT_WHITE:
            return new Knight(row, col, Color.WHITE);
        case Pawn.PAWN_BLACK:
            return new Pawn(row, col, Color.BLACK);
        case Pawn.PAWN_WHITE:
            return new Pawn(row, col, Color.WHITE);
        case Rook.ROOK_BLACK:
            return new Rook(row, col, Color.BLACK);
        case Rook.ROOK_WHITE:
            return new Rook(row, col, Color.WHITE);
        default:
            return new EmptyPiece(row, col);
        }
    }
    //@@author

    public static Command parseCommand(String in) {
        String[] splitInputStrings = in.split("\\s+", 2);
        String commandString = splitInputStrings[0];

        switch (commandString) {
        case ShowMovesCommand.SHOW_MOVE_COMMAND_STRING:
            return new ShowMovesCommand(splitInputStrings[1]);
        case ShowCommand.SHOW_COMAMND_STRING:
            return new ShowCommand();
        case RulesCommand.RULES_COMAMND_STRING:
            return new RulesCommand();
        case HelpCommand.HELP_COMAMND_STRING:
            return new HelpCommand();
        case LegendCommand.LEGEND_COMMAND_STRING:
            return new LegendCommand();
        case AbortCommand.ABORT_COMAMND_STRING:
            return new AbortCommand();

        default:
            return new MoveCommand(in);
        }
    }

    //@@author TongZhengHong
    public static Color parsePlayerColor(String inputColorString) throws ParseColorException {
        try {
            return Color.valueOf(inputColorString);
        } catch (IllegalArgumentException e) {
            throw new ParseColorException();
        }
    }

    //@@author onx001
    public static int parseDifficulty(String inputDifficultyString) throws NumberFormatException {
        try {
            return Integer.parseInt(inputDifficultyString);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }
}
