package chessmaster.game;

public class BoardScoreTuple {

    private ChessBoard board;
    private int score;

    public BoardScoreTuple(ChessBoard board, int score) {
        this.board = board;
        this.score = score;
    }

    public ChessBoard getBoard() {
        return board;
    }

    public int getScore() {
        return score;
    }
}
