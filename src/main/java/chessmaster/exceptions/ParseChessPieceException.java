package chessmaster.exceptions;

public class ParseChessPieceException extends Exception {

    public ParseChessPieceException() {
        super(ExceptionMessages.MESSAGE_PARSE_CHESS_PIECE_EXCEPTION);
    }

    public ParseChessPieceException(String message) {
        super(message);
    }

}
