package chessmaster.game;

import chessmaster.exceptions.InvalidMoveException;
import chessmaster.parser.Parser;
import chessmaster.pieces.ChessPiece;
import chessmaster.pieces.King;
import chessmaster.pieces.Pawn;
import chessmaster.ui.TextUI;

public class ChessBoard {

    public static final int SIZE = 8;
    public static final int TOP_ROW_INDEX = 0;
    public static final int BOTTOM_ROW_INDEX = 7;

    private static final String[][] STARTING_CHESSBOARD_BLACK = { 
        { "r", "n", "b", "q", "k", "b", "n", "r" }, 
        { "p", "p", "p", "p", "p", "p", "p", "p" }, 
        { ".", ".", ".", ".", ".", ".", ".", "." }, 
        { ".", ".", ".", ".", ".", ".", ".", "." }, 
        { ".", ".", ".", ".", ".", ".", ".", "." }, 
        { ".", ".", ".", ".", ".", ".", ".", "." }, 
        { "P", "P", "P", "P", "P", "P", "P", "P" }, 
        { "R", "N", "B", "Q", "K", "B", "N", "R" }, 
    };

    private static final String[][] STARTING_CHESSBOARD_WHITE = { 
        { "R", "N", "B", "Q", "K", "B", "N", "R" }, 
        { "P", "P", "P", "P", "P", "P", "P", "P" },
        { ".", ".", ".", ".", ".", ".", ".", "." }, 
        { ".", ".", ".", ".", ".", ".", ".", "." }, 
        { ".", ".", ".", ".", ".", ".", ".", "." }, 
        { ".", ".", ".", ".", ".", ".", ".", "." }, 
        { "p", "p", "p", "p", "p", "p", "p", "p" },
        { "r", "n", "b", "q", "k", "b", "n", "r" }, 
    };

    private boolean isWhiteKingAlive = true;
    private boolean isBlackKingAlive = true;

    private final ChessTile[][] board = new ChessTile[SIZE][SIZE];

    public ChessBoard(Color playerColor) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                String chessPieceString = playerColor.isBlack()
                    ? STARTING_CHESSBOARD_BLACK[row][col]
                    : STARTING_CHESSBOARD_WHITE[row][col];
                ChessPiece initialPiece = Parser.parseChessPiece(chessPieceString, row, col);
                board[row][col] = new ChessTile(initialPiece);
                assert (board[row][col] != null);
            }
        }
    }

    public ChessBoard(ChessTile[][] boardTiles) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = boardTiles[row][col];
            }
        }
    }

    public void displayAvailableMoves() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                ChessPiece piece = board[i][j].getChessPiece();
                if (piece.isEmptyPiece()) {
                    piece.displayAvailableCoordinates(this);
                }
            }
        }
    }

    public void showChessBoard() {
        TextUI.printChessBoardHeader();
        TextUI.printChessBoardDivider();
        for (int i = 0; i < board.length; i++) {
            ChessTile[] row = board[i];
            StringBuilder rowString = new StringBuilder();

            for (ChessTile tile : row) {
                rowString.append(tile.toString());
            }

            int rowNum = (i - 8) * -1;
            TextUI.printChessBoardRow(rowNum, rowString.toString());
        }
        TextUI.printChessBoardHeader();
        System.out.println("");
    }

    public void setPromotionPiece(Coordinate coord, ChessPiece promotedPiece) {
        getTileAtCoor(coord).updateTileChessPiece(promotedPiece);
    }

    public void setTile(int row, int col, ChessTile tile) {
        board[row][col] = tile;
    }

    /**
     * Gets the ChessTile object located at the specified coordinate on the
     * chessboard.
     *
     * @param coor The coordinate of the position to retrieve the tile for.
     * @return The ChessTile object at the specified coordinate.
     */
    private ChessTile getTileAtCoor(Coordinate coor) {
        return board[coor.getY()][coor.getX()];
    }

    /**
     * Gets the chess piece located at the specified coordinate on the chessboard.
     *
     * @param coor The coordinate of the position to check.
     * @return The ChessPiece object at the specified coordinate. If empty piece at
     *         coordinate, returns EmptyPiece object
     */
    public ChessPiece getPieceAtCoor(Coordinate coor) {
        ChessTile tile = getTileAtCoor(coor);
        return tile.getChessPiece();
    }

    /**
     * Executes a chess move on the chessboard.
     *
     * @param move The Move object representing the move to be executed.
     * @throws InvalidMoveException If the move is not valid according to the game
     *                              rules.
     */
    public void executeMove(Move move) throws InvalidMoveException {
        Coordinate startCoor = move.getFrom();
        Coordinate destCoor = move.getTo();
        ChessPiece chessPiece = move.getPiece();

        Coordinate[][] possibleCoordinates = chessPiece.getAvailableCoordinates(this);
        if (!move.isValid(possibleCoordinates)) {
            throw new InvalidMoveException();
        }

        chessPiece.setHasMoved();
        chessPiece.updatePosition(destCoor);
        getTileAtCoor(startCoor).setTileEmpty(startCoor);
        getTileAtCoor(destCoor).updateTileChessPiece(chessPiece);

        if (move.isLeftCastling()) {
            Coordinate rookStartCoor = new Coordinate(startCoor.getX() - 4, startCoor.getY());
            Coordinate rookDestCoor = new Coordinate(startCoor.getX() - 1, startCoor.getY());
            ChessPiece rook = getTileAtCoor(rookStartCoor).getChessPiece();

            rook.setHasMoved();
            rook.updatePosition(rookDestCoor);

            getTileAtCoor(rookStartCoor).setTileEmpty(rookStartCoor);
            getTileAtCoor(rookDestCoor).updateTileChessPiece(rook);

        } else if (move.isRightCastling()) {
            Coordinate rookStartCoor = new Coordinate(startCoor.getX() + 3, startCoor.getY());
            Coordinate rookDestCoor = new Coordinate(startCoor.getX() + 1, startCoor.getY());
            ChessPiece rook = getTileAtCoor(rookStartCoor).getChessPiece();

            rook.setHasMoved();
            rook.updatePosition(rookDestCoor);

            getTileAtCoor(rookStartCoor).setTileEmpty(rookStartCoor);
            getTileAtCoor(rookDestCoor).updateTileChessPiece(rook);
        }
    }

    public boolean canPromote(Move move) {
        ChessPiece piece = move.getPiece();
        Coordinate endCoord = move.getTo();

        if (!(piece instanceof Pawn)) {
            return false;
        }

        if (Game.isPieceFriendly(piece)) {
            return endCoord.getY() == TOP_ROW_INDEX;
        }

        if (Game.isPieceOpponent(piece)) {
            return endCoord.getY() == BOTTOM_ROW_INDEX;
        }

        return false;
    }

    public ChessTile[][] getBoard() {
        return this.board;
    }

    public boolean isEndGame() {
        isWhiteKingAlive = false; 
        isBlackKingAlive = false;

        for (int row = 0; row < ChessBoard.SIZE; row++) {
            for (int col = 0; col < ChessBoard.SIZE; col++) {
                Coordinate coor = new Coordinate(col, row);
                ChessPiece piece = getPieceAtCoor(coor);

                if (piece instanceof King) {
                    if (piece.isWhite()) {
                        isWhiteKingAlive = true;
                    } else if (piece.isBlack()) {
                        isBlackKingAlive = true;
                    }
                }
            }
        }

        return !isBlackKingAlive || !isWhiteKingAlive;
    }

    public Color getWinningColor() {
        boolean whiteWin = isWhiteKingAlive && !isBlackKingAlive;
        boolean blackWin = isBlackKingAlive && !isWhiteKingAlive;

        if (whiteWin) {
            return Color.WHITE;
        } else if (blackWin) {
            return Color.BLACK;
        } else {
            return Color.EMPTY;
        }
    }
}
