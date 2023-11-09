package chessmaster.commands;

import chessmaster.exceptions.ChessMasterException;
import chessmaster.game.Game;
import chessmaster.game.Move;
import chessmaster.user.CPU;
import chessmaster.user.Human;
import chessmaster.user.Player;

import java.util.ArrayList;

public class HistoryCommand extends Command {

    public static final String HISTORY_COMMAND_STRING = "history";

    @Override
    public CommandResult execute(Game game) throws ChessMasterException {
        CPU cpu = game.getCPU();
        Human human = game.getHuman();
        int numMoves = game.getNumMoves();

        if (numMoves == 0) {
            return new CommandResult("No moves have been played yet!");
        }

        // currentPlayer has yet to make a move when `history` is called
        // i.e. opponent has just played
        Player currentPlayer = game.getCurrentPlayer();
        ArrayList<Move> currentPlayerMoves = currentPlayer.getMoves();
        Player opponent = currentPlayer.isHuman() ? cpu : human;
        ArrayList<Move> opponentMoves = opponent.getMoves();

        StringBuilder returnStringBuilder = new StringBuilder();

        Move lastMove = opponentMoves.get(opponentMoves.size() - 1);
        String lastMoveStr = String.format(
                "Move %d (currently highlighted) was %s moving their %s from %s to %s.\n",
                numMoves,
                opponent.getColour(),
                lastMove.getPiece().getPieceName(),
                lastMove.getFrom(),
                lastMove.getTo()
        );
        returnStringBuilder.append(lastMoveStr);

        int numMovesCopy = numMoves - 1;
        for (int i = currentPlayerMoves.size() - 1; i >= 0; i--) {
            Move currentPlayerMove = currentPlayerMoves.get(i);
            String currentPlayerString = String.format(
                    "Move %d: %s moves %s from %s to %s\n",
                    numMovesCopy,
                    currentPlayer.getColour(),
                    currentPlayerMove.getPiece().getPieceName(),
                    currentPlayerMove.getFrom(),
                    currentPlayerMove.getTo()
            );
            returnStringBuilder.append(currentPlayerString);

            if (numMoves > 2) {
                numMovesCopy--;

                Move opponentMove = opponentMoves.get(i);
                String opponentString = String.format(
                        "Move %d: %s moves %s from %s to %s\n",
                        numMovesCopy,
                        opponent.getColour(),
                        opponentMove.getPiece().getPieceName(),
                        opponentMove.getFrom(),
                        opponentMove.getTo()
                );
                returnStringBuilder.append(opponentString);
                numMovesCopy--;
            }
        }

        return new CommandResult(returnStringBuilder.toString());
    }


}
