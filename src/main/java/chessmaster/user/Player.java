package chessmaster.user;

import chessmaster.exceptions.InvalidMoveException;
import chessmaster.exceptions.NullPieceException;
import chessmaster.exceptions.ParseCoordinateException;
import chessmaster.game.ChessBoard;
import chessmaster.game.ChessTile;
import chessmaster.game.Coordinate;
import chessmaster.game.Move;
import chessmaster.parser.Parser;
import chessmaster.pieces.ChessPiece;
import chessmaster.pieces.Pawn;
import chessmaster.ui.TextUI;
import chessmaster.commands.Command;

import java.util.ArrayList;

public abstract class Player {

    protected ArrayList<Move> moves;
    protected ArrayList<ChessPiece> pieces;
    protected int colour;
    protected Parser parser;

    /**
     * A player is a dependency of the Game class. This class stores all move history, all current pieces, and colour
     * of each player. It also contains functions to request input from the user for the next move and to execute
     * that move.
     * @param colour The ChessPiece.Colour desired for this player.
     */
    public Player(int colour) {
        this.moves = new ArrayList<>();
        this.pieces = new ArrayList<>();
        this.colour = colour;
    }

    /**
     * Adds a given move into the Player's move history.
     * @param move The given move to be added to history.
     */
    public void addMove(Move move) {
        this.moves.add(move);
    }

    /**
     * Adds all the player's pieces to their Piece array. Run once during setup of the game.
     * @param board The new ChessBoard containing all 32 chess pieces.
     */
    public void initialisePieces(ChessBoard board) {
        int row, col;
        if (this.colour == ChessPiece.BLACK) {
            row = 6;
        } else {
            row = 0;
        }

        for (int row_temp = row; row < row_temp + 2; row++) {
            for (col = 0; col < ChessBoard.SIZE; col++) {
                try {
                    ChessPiece piece = board.getPieceAtCoor(new Coordinate(col, row));
                    this.pieces.add(piece);
                } catch (NullPieceException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Prints out all the player's pieces including whether it has been captured or not.
     * Used for debugging purposes only.
     */
    public void printAllPieces() {
        for (ChessPiece p : pieces) {
            System.out.println("Piece: " + p);
            System.out.println("Colour: " + p.getColour());
            System.out.println("Is captured: " + p.getCaptured());
        }
    }

    public int getColour() {
        return this.colour;
    }

    /**
     * High-level function which calls necessary APIs to request user input and parse it into a Move object.
     * @param board The board which to make the move on.
     * @return null if the user inputs "abort", an empty Move if an error occurred during parsing, otherwise returns
     * the requested Move object.
     */
    public Move getNextMove(ChessBoard board) {
        // Get user input
        String input = TextUI.getUserInput();
        try {
            Parser parser = new Parser();
            Command command = parser.parseCommand(input, board);
            //if (command.execute()){
                //return null;
            //} else {
                return command.getMove();
            //}
        } catch (ParseCoordinateException | NullPieceException e) {
            TextUI.printErrorMessage(e);
        }


        return new Move();
    }

    /**
     * Allows a player to execute a move and add it to their history.
     * @param move The move to be executed.
     * @param board The board the move is to be executed on.
     * @return true on success, false on InvalidMoveException
     */
    public boolean move(Move move, ChessBoard board) {
        try {
            board.executeMove(move);
            this.addMove(move);
            if (board.canPromote(move)) {
                promote(board, board.getPieceAtCoor(move.getTo()));
            }
        } catch (InvalidMoveException e) {
            e.printStackTrace();
            return false;
        } catch (NullPieceException e) {
            return false;
        }

        return true;
    }

    /**
     * Prompts the user to enter a type of piece to promote a pawn to. If the promotion is not successful,
     * the user is prompted again. If successful, the pawn is replaced with the new piece.
     *
     * @param board Chessboard that the game is being played on.
     * @param promoteFrom The piece being promoted.
     */
    private void promote(ChessBoard board, ChessPiece promoteFrom) {
        // Promote function for humans
        // Add promote for CPU later
        board.showChessBoard();
        Coordinate coord = promoteFrom.getPosition();
        boolean promoteFailure = true;

        do {
            TextUI.printPromotePrompt(coord);
            String in = TextUI.getUserInput();
            ChessPiece promoteTo = Parser.parsePromote(promoteFrom, in);
            ChessTile promoted = new ChessTile(promoteTo);
            board.setTile(coord.getY(), coord.getX(), promoted);

            promoteFailure = promoteTo.toString().equalsIgnoreCase(Pawn.PAWN_WHITE);
            if(promoteFailure){
                TextUI.printPromoteInvalidMessage();
            }
        } while(promoteFailure);
    }
}
