package chessmaster.user;

import chessmaster.game.ChessBoard;
import chessmaster.game.Color;
import chessmaster.game.Coordinate;
import chessmaster.game.Move;
import chessmaster.pieces.ChessPiece;

import java.util.ArrayList;

public abstract class Player {

    protected ArrayList<Move> moves;
    protected ArrayList<ChessPiece> pieces;
    protected Color colour;

    /**
     * A player is a dependency of the Game class. This class stores all move
     * history, all current pieces, and colour of each player. 
     * It also contains functions to request input from the user for the next 
     * move and to execute that move.
     * 
     * @param colour The ChessPiece.Colour desired for this player.
     */
    public Player(Color colour, ChessBoard board) {
        this.moves = new ArrayList<Move>();
        this.pieces = new ArrayList<ChessPiece>();
        this.colour = colour;
        initialisePieces(board);
    }

    /**
     * Adds a given move into the Player's move history.
     * 
     * @param move The given move to be added to history.
     */
    public void addMove(Move move) {
        this.moves.add(move);
    }

    public Color getColour() {
        return this.colour;
    }

    /**
     * Adds all the player's pieces to their ChessPiece array 
     * when Player is initialised.
     * 
     * @param board The new ChessBoard containing all 32 chess pieces.
     */
    private void initialisePieces(ChessBoard board) {
        for (int row = 0; row < ChessBoard.SIZE; row++) {
            for (int col = 0; col < ChessBoard.SIZE; col++) {
                ChessPiece piece = board.getPieceAtCoor(new Coordinate(col, row));
                if (piece.isSameColorAs(this.colour)) {
                    this.pieces.add(piece);
                }
            }
        }
    }

    /**
     * Prints out all the player's pieces including whether it has been captured or
     * not.
     * Used for debugging purposes only.
     */
    public void printAllPieces() {
        for (ChessPiece p : pieces) {
            System.out.println("Piece: " + p);
            System.out.println("Colour: " + p.getColor().toString());
            System.out.println("Is captured: " + p.getIsCaptured());
        }
    }

    // /**
    //  * High-level function which calls necessary APIs to request user input and
    //  * parse it into a Move object.
    //  * 
    //  * @param board The board which to make the move on.
    //  * @return null if the user inputs "abort", an empty Move if an error occurred
    //  *         during parsing, otherwise return the requested Move object.
    //  */
    // public Move getNextMove(ChessBoard board) {
    //     String input = TextUI.getUserInput(); // Get user input
    //     try {
    //         Parser parser = new Parser();
    //         Command command = parser.parseCommand(input, board);
    //         if (command instanceof AbortCommand) {
    //             return null;
    //         }
    //         return command.getMove();
        
    //     } catch (ParseCoordinateException | NullPieceException e) {
    //         TextUI.printErrorMessage(e);
    //     }
    //     return new Move();
    // }

    // /**
    //  * Allows a player to execute a move and add it to their history.
    //  * 
    //  * @param move  The move to be executed.
    //  * @param board The board the move is to be executed on.
    //  * @return true on success, false on InvalidMoveException
    //  */
    // public boolean move(Move move, ChessBoard board) {
    //     try {
    //         board.executeMove(move);
    //         this.addMove(move);
    //         if (board.canPromote(move)) {
    //             promote(board, board.getPieceAtCoor(move.getTo()));
    //         }
    //     } catch (InvalidMoveException e) {
    //         e.printStackTrace();
    //         return false;
    //     } catch (NullPieceException e) {
    //         return false;
    //     }

    //     return true;
    // }
}
