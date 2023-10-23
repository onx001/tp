package chessmaster.user;

import chessmaster.game.ChessBoard;
import chessmaster.game.Color;
import chessmaster.game.Coordinate;
import chessmaster.game.Move;
import chessmaster.parser.Parser;
import chessmaster.pieces.ChessPiece;
import chessmaster.pieces.Pawn;
import chessmaster.ui.TextUI;

public class Human extends Player {

    public Human(Color colour, ChessBoard board) {
        super(colour, board);
    }

    /**
     * Prompts the user to enter a type of piece to promote a pawn to. If the
     * promotion is not successful,
     * the user is prompted again. If successful, the pawn is replaced with the new
     * piece.
     *
     * @param board       Chessboard that the game is being played on.
     * @param promoteMove The piece being promoted.
     */
    public void handlePromote(ChessBoard board, Move promoteMove) {
        ChessPiece pawnPiece = promoteMove.getPiece();
        if (!(pawnPiece instanceof Pawn)) {
            return;
        }

        board.showChessBoard();
        Coordinate coord = pawnPiece.getPosition();
        boolean promoteFailure = true;

        TextUI.printPromotePrompt(coord);
        String in = TextUI.getUserInput();
        do {
            ChessPiece promotedPiece = Parser.parsePromote(pawnPiece, in);
            promoteFailure = promotedPiece instanceof Pawn;

            if (promoteFailure) {
                TextUI.printPromoteInvalidMessage();
                in = TextUI.getUserInput();
            } else {
                promotedPiece.setHasMoved();
                this.pieces.add(promotedPiece);
                this.pieces.remove(pawnPiece);
                board.setPromotionPiece(coord, promotedPiece);
            }
        } while (promoteFailure);
    }

}
