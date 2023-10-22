package chessmaster.game;

import chessmaster.commands.AbortCommand;
import chessmaster.commands.Command;
import chessmaster.commands.CommandResult;
import chessmaster.commands.MoveCommand;
import chessmaster.exceptions.ChessMasterException;
import chessmaster.parser.Parser;
import chessmaster.pieces.ChessPiece;
import chessmaster.ui.TextUI;
import chessmaster.user.CPU;
import chessmaster.user.Human;
import chessmaster.user.Player;

public class Game {

    private static Color playerColor; 

    private Human human;
    private CPU cpu;
    private ChessBoard board;

    private Command command;
    private boolean hasEnded;

    public Game(Color playerColour, String filePath) {
        Game.playerColor = playerColour;
        this.board = new ChessBoard(playerColour);

        this.human = new Human(playerColour, board);
        Color cpuColor = playerColour.getOppositeColour();
        this.cpu = new CPU(cpuColor, board);
    }

    public void run() {
        board.showChessBoard();

        while (!hasEnded && !AbortCommand.isAbortCommand(command)) {

            try {
                String userInputString = TextUI.getUserInput();
                command = Parser.parseCommand(userInputString);
                CommandResult result = command.execute();
                TextUI.printCommandResult(result);

                if (!(command instanceof MoveCommand)) {
                    continue; // Take new user command
                }

                // Input string contains 2 valid coordinates
                Move humanMove = Parser.parseMove(userInputString, board);
                hasEnded = processMove(humanMove, human);

                if (!hasEnded) {
                    if (board.canPromote(humanMove)) {
                        human.handlePromote(board, humanMove);
                    }

                    Move cpuMove = cpu.getRandomMove(board);
                    processMove(cpuMove, cpu);
                }

                board.showChessBoard();
            } catch (ChessMasterException e) {
                TextUI.printErrorMessage(e);
            }
            
        }
    }

    private boolean processMove(Move move, Player player) throws ChessMasterException {
        board.executeMove(move);
        player.addMove(move);

        boolean end = board.isEndGame();
        if (end) {
            Color winningColor = board.getWinningColor();
            TextUI.printWinnerMessage(winningColor);
        }

        return end;
    }

    public static boolean isPieceFriendly(ChessPiece otherPiece) {
        return Game.playerColor == otherPiece.getColor();
    }

    public static boolean isPieceOpponent(ChessPiece otherPiece) {
        return Game.playerColor != otherPiece.getColor();
    }

}
