package chessmaster.game;

import chessmaster.game.move.Move;

public class BoardScoreTuple implements Comparable<BoardScoreTuple> {

    private ChessBoard board;
    private int score;
    private Move move;

    public BoardScoreTuple(ChessBoard board, int score, Move move) {
        this.board = board;
        this.score = score;
        this.move = move;
    }

    public ChessBoard getBoard() {
        return board;
    }

    public int getScore() {
        return score;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(BoardScoreTuple tuple) {
        if (this.score > tuple.getScore()) {
            return 1;
        } else if (this.score < tuple.getScore()) {
            return -1;
        } else {
            return 0;
        }
    }
}
