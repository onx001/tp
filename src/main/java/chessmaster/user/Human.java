package chessmaster.user;

import chessmaster.game.ChessBoard;
import chessmaster.game.Color;
import chessmaster.game.Coordinate;
import chessmaster.game.move.Move;
import chessmaster.game.move.MoveFactory;
import chessmaster.game.move.PromoteMove;
import chessmaster.parser.Parser;
import chessmaster.pieces.ChessPiece;
import chessmaster.pieces.Pawn;
import chessmaster.ui.TextUI;

public class Human extends Player {

    public Human(Color colour, ChessBoard board) {
        super(colour, board);
    }

    //@@author ken-ruster
    /**
     * Prompts the user to enter a type of piece to promote a pawn to. If the
     * promotion is not successful,
     * the user is prompted again. If successful, the pawn is replaced with the new
     * piece.
     *
     * @param board       Chessboard that the game is being played on.
     * @param move The piece being promoted.
     */
    public void handlePromote(ChessBoard board, TextUI ui, Move move) {
        ChessPiece pawnPiece = move.getPieceMoved();
        if (!pawnPiece.isPawn()) {
            return;
        }
        Pawn pawnPromoted = (Pawn) pawnPiece;

        ui.printChessBoard(board.getBoard());
        Coordinate coord = pawnPiece.getPosition();
        boolean promoteFailure = true;

        ui.printPromotePrompt(coord);
        String in = ui.getUserInput(false);
        do {
            ChessPiece promotedPiece = Parser.parsePromote(pawnPiece, in);
            promoteFailure = promotedPiece.isPawn();

            if (promoteFailure) {
                ui.printPromoteInvalidMessage();
                in = ui.getUserInput(false);
            } else {
                promotedPiece.setHasMoved();
                this.pieces.add(promotedPiece);
                this.pieces.remove(pawnPiece);
                board.setPromotionPiece(coord, promotedPiece);
                PromoteMove promoteMove = MoveFactory.createPromoteMove(coord, pawnPromoted, promotedPiece);
                this.addMove(promoteMove);
            }
        } while (promoteFailure);
    }

}
