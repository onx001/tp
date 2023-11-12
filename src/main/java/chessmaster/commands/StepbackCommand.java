package chessmaster.commands;

import chessmaster.exceptions.ChessMasterException;
import chessmaster.game.ChessBoard;
import chessmaster.game.Coordinate;
import chessmaster.game.Game;
import chessmaster.game.move.CastleMove;
import chessmaster.game.move.Move;
import chessmaster.game.move.PromoteMove;
import chessmaster.pieces.ChessPiece;
import chessmaster.pieces.Pawn;

import java.util.ArrayList;
import java.util.Collections;

public class StepbackCommand extends Command {

    public static final String STEPBACK_COMMAND_STRING = "stepback";
    private String userInput;

    public StepbackCommand(String inputString) {
        this.userInput = inputString;
    }

    private void reverseMove(Move move, ChessBoard board) {
        // need to reverse the previousMove by moving the piece back from `to` to `from`.
        Coordinate moveTo = move.getTo();
        Coordinate moveFrom = move.getFrom();

        ChessPiece clonedPieceToMove = board.getPieceAtCoor(moveTo);
        clonedPieceToMove.updatePosition(moveFrom);
        board.getTileAtCoor(moveFrom).updateTileChessPiece(clonedPieceToMove);

        // Need to check if the move captured a piece. If it did, need to replace `to` tile with pieceCaptured
        if (move.hasCapturedAPiece()) {
            board.getTileAtCoor(moveTo).updateTileChessPiece(move.getPieceCaptured());
        } else {
            board.getTileAtCoor(moveTo).setTileEmpty(moveTo);
        }
    }

    private void reversePromotion(PromoteMove move, ChessBoard board) {
        // Put the original pawn which was promoted back on the tile
        Coordinate endTile = move.getTo(); // move.getFrom() is the same
        Pawn pawnPromoted = move.getPawnPromoted();
        board.getTileAtCoor(endTile).updateTileChessPiece(pawnPromoted);
    }

    @Override
    public CommandResult execute(Game game) throws ChessMasterException {
        ChessBoard currentBoard = game.getBoard();
        ChessBoard historyBoard = currentBoard.clone();
        int totalNumMoves = game.getNumMoves();

        // INPUT ERROR HANDLING
        int numMovesToStepBack = 0;
        try {
            numMovesToStepBack = Integer.parseInt(this.userInput);
            if (numMovesToStepBack > totalNumMoves) {
                throw new ChessMasterException("You cannot step back more moves than have been played!");
            } else if (numMovesToStepBack <= 0) {
                throw new ChessMasterException("Number of steps has to be greater than 0.");
            }
        } catch (NumberFormatException e) {
            throw new ChessMasterException("Please input an integer number of steps to step back.");
        }

        ArrayList<PlayerMoveTuple> allMoves = HistoryCommand.getAllMovesInChronologicalOrder(game);
        Collections.reverse(allMoves);

        for (int i = 0; i < numMovesToStepBack; i++) {
            Move previousMove = allMoves.get(i).getMove();
            reverseMove(previousMove, historyBoard);

            // If castling, need to reverse the rook move as well
            if (previousMove instanceof CastleMove) {
                Move rookCastleMove = ((CastleMove) previousMove).getRookMove();
                reverseMove(rookCastleMove, historyBoard);
            } else if (previousMove instanceof PromoteMove) {
                reversePromotion((PromoteMove) previousMove, historyBoard);
            }
        }

        game.getUI().printChessBoard(historyBoard.getBoard());

        return new CommandResult(String.format(
                "Stepped back %d steps!\nUse `show` to see the current board.",
                numMovesToStepBack)
        );
    }

}
