package chessmaster.exceptions;

public class LoadBoardException extends Exception {

    public LoadBoardException() {
        super(ExceptionMessages.MESSAGE_LOAD_BOARD_EXCEPTION);
    }

    public LoadBoardException(String message) {
        super(message);
    }

}

