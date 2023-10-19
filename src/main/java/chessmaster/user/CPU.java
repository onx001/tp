package chessmaster.user;

import chessmaster.game.Move;

public class CPU extends Player {

    public CPU(int colour) {
        super(colour);
    }

    public Move getRandomMove() {
        return new Move();
    }

}
