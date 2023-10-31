//@@author TongZhengHong
package chessmaster.exceptions;

import chessmaster.ui.ExceptionMessages;

public class MoveOpponentPieceException extends ChessMasterException {
    public MoveOpponentPieceException() {
        super(ExceptionMessages.MESSAGE_MOVE_OPPONENT_EXCEPTION);
    }

    public MoveOpponentPieceException(String message) {
        super(message);
    }
}
