//@@author TongZhengHong
package chessmaster.exceptions;

import chessmaster.ui.ExceptionMessages;

public class ParseCoordinateException extends ChessMasterException {

    public ParseCoordinateException() {
        super(ExceptionMessages.MESSAGE_PARSE_COORDINATE_EXCEPTION);
    }

    public ParseCoordinateException(String message) {
        super(message);
    }

}
