//@@author TongZhengHong
package chessmaster.exceptions;

import chessmaster.game.Coordinate;
import chessmaster.ui.ExceptionMessages;

public class NullPieceException extends ChessMasterException {

    public NullPieceException(Coordinate coordinate) {
        super(String.format(ExceptionMessages.MESSAGE_NULL_PIECE_COORDINATE_EXCEPTION, 
            coordinate.toString()));
    }

    public NullPieceException(String message) {
        super(message);
    }
}
