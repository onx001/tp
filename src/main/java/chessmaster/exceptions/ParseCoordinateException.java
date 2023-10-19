package chessmaster.exceptions;

public class ParseCoordinateException extends ChessMasterException {

    public ParseCoordinateException() {
        super(ExceptionMessages.MESSAGE_PARSE_COORDINATE_EXCEPTION);
    }

    public ParseCoordinateException(String message) {
        super(message);
    }

}
