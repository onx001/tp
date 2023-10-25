package chessmaster.game;

import chessmaster.commands.AbortCommand;
import chessmaster.commands.Command;
import chessmaster.commands.CommandResult;
import chessmaster.commands.MoveCommand;
import chessmaster.commands.ShowMovesCommand;
import chessmaster.exceptions.ChessMasterException;
import chessmaster.parser.Parser;
import chessmaster.pieces.ChessPiece;
import chessmaster.storage.Storage;
import chessmaster.ui.TextUI;
import chessmaster.user.CPU;
import chessmaster.user.Human;
import chessmaster.user.Player;

public class Game {

    private static Color playerColor;

    private CPU cpu;
    private Human human;
    private Player currentPlayer;

    private ChessBoard board;
    private Storage storage;

    private Command command;
    private boolean hasEnded;

    public Game(Color playerColour, ChessBoard board, Storage storage) {
        this.board = board;
        this.storage = storage;
        Game.playerColor = playerColour;

        this.human = new Human(playerColour, board);
        Color cpuColor = playerColour.getOppositeColour();
        this.cpu = new CPU(cpuColor, board);
        currentPlayer = human; // Human goes first
    }

    public void run() {
        board.showChessBoard();

        while (!hasEnded && !AbortCommand.isAbortCommand(command)) {
            try {
                if (currentPlayer.isHuman()) {
                    command = getUserCommand();
                    if (!command.isMoveCommand()) {
                        continue; // Get next command
                    }
                    handleHumanMove();
                    
                } else if (currentPlayer.isCPU()) {
                    handleCPUMove();
                }
                board.showChessBoard();
                storage.saveBoard(board, playerColor);

                hasEnded = checkEndState();
                currentPlayer = togglePlayerTurn();

            } catch (ChessMasterException e) {
                TextUI.printErrorMessage(e);
            }
        }
    }

    private Command getUserCommand() throws ChessMasterException {
        String userInputString = TextUI.getUserInput();
        command = Parser.parseCommand(userInputString);

        CommandResult result = command.execute(board);
        TextUI.printCommandResult(result);
        return command;
    }

    private void handleHumanMove() throws ChessMasterException {
        Move humanMove = ((MoveCommand) command).getMove();
        board.executeMove(humanMove);
        human.addMove(humanMove);
        
        // Handle human promotion
        if (!board.isEndGame()) {
            if (board.canPromote(humanMove)) {
                human.handlePromote(board, humanMove);
            }
        }
    }

    private void handleCPUMove() throws ChessMasterException {
        Move cpuMove = cpu.getRandomMove(board);
        TextUI.printCPUMove(cpuMove);
        board.executeMove(cpuMove);
        cpu.addMove(cpuMove);
    }

    private boolean checkEndState() throws ChessMasterException {
        boolean end = board.isEndGame();
        if (end) {
            Color winningColor = board.getWinningColor();
            TextUI.printWinnerMessage(winningColor);
            storage.resetBoard();
        }
        return end;
    }

    public void cpuFirstMove() {
        try {
            Move cpuMove = cpu.getRandomMove(board);
            TextUI.printCPUMove(cpuMove);
            board.executeMove(cpuMove);
            cpu.addMove(cpuMove);

        } catch (ChessMasterException e){
            TextUI.printErrorMessage(e);
        }
    }

    private Player togglePlayerTurn() {
        return currentPlayer.isHuman() ? cpu : human;
    }

    public static boolean isPieceFriendly(ChessPiece otherPiece) {
        return Game.playerColor == otherPiece.getColor();
    }

    public static boolean isPieceOpponent(ChessPiece otherPiece) {
        return Game.playerColor != otherPiece.getColor();
    }

}
