package chessmaster.exceptions;

public class LoadBoardException extends ChessMasterException {

    public LoadBoardException() {
        super(ExceptionMessages.MESSAGE_LOAD_BOARD_EXCEPTION);
    }

    public LoadBoardException(String message) {
        super(message);
    }

}

