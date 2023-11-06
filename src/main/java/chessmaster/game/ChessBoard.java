package chessmaster.game;

import java.util.ArrayList;

import chessmaster.exceptions.InvalidMoveException;
import chessmaster.parser.Parser;
import chessmaster.pieces.ChessPiece;
import chessmaster.pieces.King;

public class ChessBoard {

    public static final int SIZE = 8;
    public static final int TOP_ROW_INDEX = 0;
    public static final int BOTTOM_ROW_INDEX = 7;
    public static final int MAX_PIECES = 16;

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

    private Color playerColor;

    private boolean isWhiteKingAlive = true;
    private boolean isBlackKingAlive = true;

    private int difficulty = 4;

    private final ChessTile[][] board = new ChessTile[SIZE][SIZE];

    public ChessBoard(Color playerColor) {
        this.playerColor = playerColor;
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
        this.playerColor = playerColor;
    }

    public ChessBoard(Color playerColor, ChessTile[][] boardTiles) {
        this.playerColor = playerColor;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = boardTiles[row][col];
            }
        }
    }

    /**
     * Gets a copy of the current chessboard as a 2D array of ChessTile objects.
     *
     * This method creates a deep copy of the chessboard, allowing for the independent
     * examination of the board's state without modifying the original chessboard.
     *
     * @return A 2D array copy of ChessTile objects representing the current state of the chessboard.
     */
    public ChessTile[][] getBoard() {
        ChessTile[][] copy = new ChessTile[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            copy[i] = board[i].clone();
        }
        return copy;
    }

    public Color getPlayerColor() {
        return this.playerColor;
    }

    //@@author onx001
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public boolean isChecked(Color color) {
        Move[] moves = getAllMoves(color.getOppositeColour());
        for (Move move : moves) {
            Coordinate to = move.getTo();
            if (this.getPieceAtCoor(to) instanceof King) {
                return true;
            }
        }
        return false;
    }

    public boolean hasEnPassant() {
        //Checks all chess pieces for en passant
        for (int row = 0; row < ChessBoard.SIZE; row++) {
            for (int col = 0; col < ChessBoard.SIZE; col++) {
                Coordinate coor = new Coordinate(col, row);
                ChessPiece piece = getPieceAtCoor(coor);
                if (piece.isEnPassant()) {
                    return true;
                }
            }
        }
        return false;
    }

    public Coordinate getEnPassantCoor() {
        //Checks all chess pieces for en passant
        for (int row = 0; row < ChessBoard.SIZE; row++) {
            for (int col = 0; col < ChessBoard.SIZE; col++) {
                Coordinate coor = new Coordinate(col, row);
                ChessPiece piece = getPieceAtCoor(coor);
                if (piece.isEnPassant()) {
                    if (piece.isWhite()) {
                        coor = coor.addOffsetToCoordinate(0, -1);
                    } else {
                        coor = coor.addOffsetToCoordinate(0, 1);
                    }
                    return coor;
                }
            }
        }
        return null;
    }

    public boolean isCheckmated(Color color) {
        Move[] moves = getAllMoves(color);
        for (Move move : moves) {
            ChessBoard newBoard = this.clone();
            try {
                newBoard.executeMove(move);
            } catch (InvalidMoveException e) {
                continue;
            }
            if (!newBoard.isChecked(color)) {
                return false;
            }
        }
        return true;
    }


    public Move[] getAllMoves(Color color) {
        //Declare arraylist of moves as allMoves
        ArrayList<Move> allMoves = new ArrayList<>();

        for (int row = 0; row < ChessBoard.SIZE; row++) {
            for (int col = 0; col < ChessBoard.SIZE; col++) {
                Coordinate coor = new Coordinate(col, row);
                ChessPiece piece = getPieceAtCoor(coor);

                if (piece.isSameColorAs(color)) {
                    Coordinate[] possibleCoordinates = piece.getFlattenedCoordinates(this);
                    for (Coordinate possible: possibleCoordinates) {
                        Move move = new Move(coor, possible, piece);
                        allMoves.add(move);
                    }
                }
            }
        }
        return allMoves.toArray(new Move[0]);
    }

    public Move[] getAllUncheckedMoves(Color color) {
        Move[] moves = getAllMoves(color);
        ArrayList<Move> uncheckedMoves = new ArrayList<>();
        for (Move move : moves) {
            ChessBoard newBoard = this.clone();
            try {
                newBoard.executeMove(move);
            } catch (InvalidMoveException e) {
                continue;
            }
            if (!newBoard.isChecked(color)) {
                uncheckedMoves.add(move);
            }
        }
        return uncheckedMoves.toArray(new Move[0]);
    }

    //@@author TongZhengHong
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


        chessPiece.setHasMoved();
        chessPiece.updatePosition(destCoor);
        getTileAtCoor(startCoor).setTileEmpty(startCoor);
        getTileAtCoor(destCoor).updateTileChessPiece(chessPiece);

        //@@author onx001
        if (move.isLeftCastling() && startCoor.isOffsetWithinBoard(-4, 0)) {
            Coordinate rookStartCoor = startCoor.addOffsetToCoordinate(-4, 0);
            Coordinate rookDestCoor = startCoor.addOffsetToCoordinate(-1, 0);
            ChessPiece rook = getTileAtCoor(rookStartCoor).getChessPiece();

            rook.setHasMoved();
            rook.updatePosition(rookDestCoor);

            getTileAtCoor(rookStartCoor).setTileEmpty(rookStartCoor);
            getTileAtCoor(rookDestCoor).updateTileChessPiece(rook);

        } else if (move.isRightCastling() && startCoor.isOffsetWithinBoard(3, 0)) {
            Coordinate rookStartCoor = startCoor.addOffsetToCoordinate(3, 0);
            Coordinate rookDestCoor = startCoor.addOffsetToCoordinate(1, 0);
            ChessPiece rook = getTileAtCoor(rookStartCoor).getChessPiece();

            rook.setHasMoved();
            rook.updatePosition(rookDestCoor);

            getTileAtCoor(rookStartCoor).setTileEmpty(rookStartCoor);
            getTileAtCoor(rookDestCoor).updateTileChessPiece(rook);
        }

        //clear all en passants
        for (int row = 0; row < ChessBoard.SIZE; row++) {
            for (int col = 0; col < ChessBoard.SIZE; col++) {
                Coordinate coor = new Coordinate(col, row);
                ChessPiece piece = getPieceAtCoor(coor);
                if (piece.isEnPassant()) {
                    piece.clearEnPassant();
                }
            }
        }
    }

    public void executeMoveWithCheck(Move move) throws InvalidMoveException {
        
        if (move.isValidWithCheck(this)) {
            executeMove(move);
        } else {
            throw new InvalidMoveException("Move Causes a check");
        }
    }

    //@@author ken-ruster
    public boolean canPromote(Move move) {
        ChessPiece piece = move.getPiece();
        Coordinate endCoord = move.getTo();

        if (!piece.isPawn()) {
            return false;
        }

        if (isPieceFriendly(piece)) {
            return endCoord.getY() == TOP_ROW_INDEX;
        }

        if (isPieceOpponent(piece)) {
            return endCoord.getY() == BOTTOM_ROW_INDEX;
        }

        return false;
    }

    //@@author TriciaBK
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
    
    //@@author onx001
    public int getPoints(Color color) {
        int points = 0;
        int enemyPoints = 0;
        boolean isUpright;

        if (this.playerColor == color) {
            isUpright = true;
        } else {
            isUpright = false;
        }

        for (int row = 0; row < ChessBoard.SIZE; row++) {
            for (int col = 0; col < ChessBoard.SIZE; col++) {
                Coordinate coor = new Coordinate(col, row);
                ChessPiece piece = this.getPieceAtCoor(coor);

                if (piece.isSameColorAs(color)) {
                    points += piece.getPoints(isUpright);
                } else {
                    enemyPoints += piece.getPoints(isUpright);
                }
            }
        }

        return points - enemyPoints;
    }


    public ChessBoard clone() {
        String stringRep = this.toString();
        return toBoard(stringRep);
    }



    public ChessBoard toBoard(String board) {
        ChessTile[][] boardTiles = new ChessTile[SIZE][SIZE];
        int row = 0;
        int col = 0;
        for (int i = 0; i < board.length(); i++) {
            String pieceString = board.substring(i, i + 1);
            ChessPiece piece = Parser.parseChessPiece(pieceString, row, col);
            assert (row < SIZE);
            assert (col < SIZE);
            boardTiles[row][col] = new ChessTile(piece);
            col++;
            if (col == SIZE) {
                col = 0;
                row++;
            }
            if (row == SIZE) {
                break;
            }
        }
        return new ChessBoard(this.playerColor, boardTiles);
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        for (ChessTile[] row : board) {
            for (ChessTile tile : row) {
                boardString.append(tile.toFileString());
            }
        }
        return boardString.toString();
    }

    //@@author TongZhengHong
    public boolean isPieceFriendly(ChessPiece otherPiece) {
        return this.playerColor == otherPiece.getColor();
    }

    public boolean isPieceOpponent(ChessPiece otherPiece) {
        return this.playerColor != otherPiece.getColor();
    }
}
