package chessmaster.commands;

import chessmaster.game.Move;

public class HelpCommand extends Command{

    public HelpCommand() {
    }

    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public Move getMove() {
        return new Move();
    }
    
}
