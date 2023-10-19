package chessmaster.game;

import chessmaster.user.CPU;
import chessmaster.user.Human;


public class Game {

    // In v1.0 we will only have player1 = human (white)
    // and player2 = CPU (black).
    // However in the future these will be modifiable.
    // private Player player1;
    // private Player player2;
    private Human human;
    private CPU cpu;

    private ChessBoard board;

    private final String logo =
        "░█████╗░██╗░░██╗███████╗░██████╗░██████╗███╗░░░███╗░█████╗░░██████╗████████╗███████╗██████╗░"
        + System.lineSeparator() +
        "██╔══██╗██║░░██║██╔════╝██╔════╝██╔════╝████╗░████║██╔══██╗██╔════╝╚══██╔══╝██╔════╝██╔══██╗"
        + System.lineSeparator() +
        "██║░░╚═╝███████║█████╗░░╚█████╗░╚█████╗░██╔████╔██║███████║╚█████╗░░░░██║░░░█████╗░░██████╔╝"
        + System.lineSeparator() +
        "██║░░██╗██╔══██║██╔══╝░░░╚═══██╗░╚═══██╗██║╚██╔╝██║██╔══██║░╚═══██╗░░░██║░░░██╔══╝░░██╔══██╗"
        + System.lineSeparator() +
        "╚█████╔╝██║░░██║███████╗██████╔╝██████╔╝██║░╚═╝░██║██║░░██║██████╔╝░░░██║░░░███████╗██║░░██║"
        + System.lineSeparator() +
        "░╚════╝░╚═╝░░╚═╝╚══════╝╚═════╝░╚═════╝░╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═════╝░░░░╚═╝░░░╚══════╝╚═╝░░╚═╝"
        + System.lineSeparator();

    public Game(String mode, int player1Colour) {
        board = new ChessBoard();

        switch (mode) {
        case "multi":
            // here for future expansion
        case "single":
        default:
            this.human = new Human(player1Colour);
            this.cpu = new CPU(1 - player1Colour);
        }

        this.human.initialisePieces(board);
        this.cpu.initialisePieces(board);
    }

    public void run() {
        System.out.println(logo);

        while (true) {

            // 1. Show the chessboard at every move.
            board.showChessBoard();

            // 2. Get the next move.
            // In v1.0 the human is always white, so they will always go first
            // But this needs to be changed in future versions
            Move move = human.getNextMove(board);
            if (move == null) {
                // user has entered "abort"
                break;
            } else if (move.isEmpty()) {
                // if the move was not correctly parsed, move to the next iteration of the game
                continue;
            } else if (move.getPiece().getColour() != this.human.getColour()) {
                System.out.println("You're moving for the wrong side! Try moving one of your pieces instead." );
                continue;
            }

            // 3. Execute the next move.
            boolean success = human.move(move, board);
            if (!success) {
                // if the move was Invalid, go to next iteration
                continue;
            }

            // 4. CPU plays
            Move randomMove = cpu.getRandomMove(board);
            cpu.move(randomMove, board);

            // Check game state
            boolean gameOver = board.isEndGame();
            if (gameOver) {
                // if the game is over
                // determine the winning colour
                board.announceWinningColour();
                break;
            } else {
                //game is not over
                continue;
            }
        }
    }

}
