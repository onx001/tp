package chessmaster.exceptions;

public class SaveBoardException extends ChessMasterException {

    public SaveBoardException() {
        super(ExceptionMessages.MESSAGE_SAVE_BOARD_EXCEPTION);
    }

    public SaveBoardException(String message) {
        super(message);
    }

}

