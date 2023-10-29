package chessmaster.game;

import chessmaster.commands.AbortCommand;
import chessmaster.commands.Command;
import chessmaster.commands.CommandResult;
import chessmaster.commands.MoveCommand;
import chessmaster.exceptions.ChessMasterException;
import chessmaster.parser.Parser;
import chessmaster.storage.Storage;
import chessmaster.ui.TextUI;
import chessmaster.user.CPU;
import chessmaster.user.Human;
import chessmaster.user.Player;

public class Game {

    private static final String[] START_HELP_STRINGS = {
        "Thank you for choosing ChessMaster! Here are the commands that you can use:",
        "Move piece - Input coordinate of piece, followed by coordinate to move to",
        "   Format: [column][row] [column][row]",
        "   E.g. a2 a3",
        "Show board - Shows the current state of the chess board",
        "   Format: show",
        "Abort game - Exit programme",
        "   Format: abort",
        "Obtain rules - Obtain a quick refresher on the rules of chess",
        "   Format: rules",
        "Obtain help - Show a list of commands and what they do",
        "   Format: help"
    };

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

        this.human = new Human(playerColour, board);
        Color cpuColor = playerColour.getOppositeColour();
        this.cpu = new CPU(cpuColor, board);
        currentPlayer = human; // Human goes first

        assert playerColour != Color.EMPTY : "Human player color should not be EMPTY!";
        assert cpuColor != Color.EMPTY : "CPU player color should not be EMPTY!";
        assert currentPlayer != null : "A player should always exist in a game!";
    }

    public void run() {
        TextUI.printText(START_HELP_STRINGS);
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
                storage.saveBoard(board);

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
        Move cpuMove = cpu.getBestMove(board);
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

        } catch (ChessMasterException e) {
            TextUI.printErrorMessage(e);
        }
    }

    private Player togglePlayerTurn() {
        return currentPlayer.isHuman() ? cpu : human;
    }

}
