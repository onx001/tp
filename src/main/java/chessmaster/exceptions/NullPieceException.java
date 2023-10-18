package chessmaster.exceptions;

public class NullPieceException extends ChessMasterException {
    public NullPieceException() {
        super(ExceptionMessages.MESSAGE_NULL_PIECE_EXCEPTION);
    }

    public NullPieceException(String message) {
        super(message);
    }
}
