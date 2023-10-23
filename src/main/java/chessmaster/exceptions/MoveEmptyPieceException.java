package chessmaster.exceptions;

public class MoveEmptyPieceException extends ChessMasterException {
    public MoveEmptyPieceException() {
        super(ExceptionMessages.MESSAGE_MOVE_EMPTY_EXCEPTION);
    }

    public MoveEmptyPieceException(String message) {
        super(message);
    }
}
