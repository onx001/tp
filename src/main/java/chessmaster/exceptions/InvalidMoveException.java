package chessmaster.exceptions;

public class InvalidMoveException extends Exception{
    public InvalidMoveException() {
        super(ExceptionMessages.MESSAGE_PARSE_CHESS_PIECE_EXCEPTION);
    }

    public InvalidMoveException(String message) {
        super(message);
    }
}
