package chessmaster.commands;

import chessmaster.game.Move;

public abstract class Command {
    public abstract boolean execute();
    public abstract Move getMove();
}