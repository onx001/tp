package chessmaster.game;
import chessmaster.pieces.ChessPiece;

import chessmaster.exceptions.ChessMasterException;

public class MiniMax {
    protected int depth;
    protected int maxDepth;
    protected int score;
    protected Move bestMove;
    protected ChessBoard board;
    protected Color color;
    protected Color opponentColor;
    protected BoardScoreTuple tuple;

    //declares all variables needed for the minimax algorithm
    public MiniMax(ChessBoard board, Color color, int maxDepth, int score) {
        this.board = board;
        this.color = color;
        this.opponentColor = color == Color.WHITE ? Color.BLACK : Color.WHITE;
        this.maxDepth = maxDepth;
        this.score = score;
        this.tuple = new BoardScoreTuple(board, score, null);
    }
    

    //returns the best move tuple for the current player
    public static BoardScoreTuple mostPoints(
            BoardScoreTuple tuple, Color color, int depth, int score, boolean isMax, int maxDepth){

        
        //gets all the moves for the current player
        ChessBoard board = tuple.getBoard();
        Move[] moves = board.getAllMoves(color);
        assert moves.length > 0 : "No moves available for " + color + " at depth " + depth;
        BoardScoreTuple[] boards = new BoardScoreTuple[moves.length];
        int bestScore = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        BoardScoreTuple bestTuple = null;

        //if the depth is the max depth, return the score of the board as base case
        if(depth == maxDepth){
            int newscore = board.getPoints(color);
            return new BoardScoreTuple(board,newscore, null);
        }
        
        //for each move, clone the board and execute the move as a possibility
        for(int i = 0; i < moves.length; i++){
            ChessBoard newBoard = board.clone();
            Move move = moves[i];
            Coordinate from = move.getFrom();
            ChessPiece piece = newBoard.getPieceAt(from);
            move.setPiece(piece);
            try {
                newBoard.executeMove(move);
                //get the score of the board after the move
                int newScore = newBoard.getPoints(color);
                boards[i] = new BoardScoreTuple(newBoard, newScore, move);
            } catch(ChessMasterException e){
                continue;
            }
        }
        
        

        //go through boards and find one with best points
        for(int i = 0; i < boards.length; i++){
            BoardScoreTuple iterTuple = boards[i];
            assert iterTuple != null : "iterTuple is null";
            assert iterTuple.getMove() != null : "iterTuple move is null";

            //recursively call mostPoints to find the best move for current board
            BoardScoreTuple tuple1 = mostPoints(
                    iterTuple, color.getOppositeColour(), depth + 1, score, !isMax, maxDepth);
          
            int newScore = tuple1.getScore();

            if(isMax){
                //maximises score if CPU turn
                if (newScore > bestScore) {
                    bestScore = newScore;
                }
            }else{
                //minimises score if player turn
                if (newScore < bestScore) {
                    bestScore = newScore;
                }
            }
            bestTuple = bestScore == newScore ? iterTuple : bestTuple;
        }

        return bestTuple;

    }

    public Move getBestMove() {
        BoardScoreTuple bestTuple = mostPoints(tuple, color, 0, score, false, maxDepth);
        Move bestMove = bestTuple.getMove();
        return bestMove;
    }



}
