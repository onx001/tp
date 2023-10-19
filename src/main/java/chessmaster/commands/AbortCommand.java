package chessmaster.commands;

import chessmaster.game.Move;

public class AbortCommand extends Command{

    public AbortCommand() {
    }

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public Move getMove() {
        return new Move();
    }
    
}
