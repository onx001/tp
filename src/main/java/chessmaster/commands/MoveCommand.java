package chessmaster.commands;

import chessmaster.exceptions.InvalidMoveException;
import chessmaster.exceptions.NullPieceException;
import chessmaster.game.ChessBoard;
import chessmaster.game.Coordinate;
import chessmaster.pieces.ChessPiece;
import chessmaster.game.Move;

public class MoveCommand extends Command {

    private ChessBoard board;
    private Coordinate from;
    private Coordinate to;

    public MoveCommand(ChessBoard board, Coordinate from, Coordinate to) {
        this.board = board;
        this.from = from;
        this.to = to;
    }
    @Override
    public boolean execute() throws InvalidMoveException, NullPieceException{
        try{
            ChessPiece relevantPiece = board.getPieceAtCoor(from);
            Move move = new Move(from, to, relevantPiece);
            board.executeMove(move);
        } catch (InvalidMoveException e){
            throw new InvalidMoveException(e.getMessage());
        } catch (NullPieceException e){
            throw new NullPieceException(e.getMessage());
        }

        return false;
    }

    @Override
    public Move getMove() {
        try{
            ChessPiece relevantPiece = board.getPieceAtCoor(from);
            Move move = new Move(from, to, relevantPiece);
            return move;
        } catch (NullPieceException e){
            return new Move();
        }
    }

}
