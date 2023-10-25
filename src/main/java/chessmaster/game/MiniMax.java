package chessmaster.game;

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

    public MiniMax(ChessBoard board, Color color, int maxDepth, int score) {
        this.board = board;
        this.color = color;
        this.opponentColor = color == Color.WHITE ? Color.BLACK : Color.WHITE;
        this.maxDepth = maxDepth;
        this.score = score;
        this.tuple = new BoardScoreTuple(board, score, null);
    }
    
    public static BoardScoreTuple mostPoints(
            BoardScoreTuple tuple, Color color, int depth, int score, boolean isMax, int maxDepth){
        
        ChessBoard board = tuple.getBoard();
        Move[] moves = board.getAllMoves(color);
        assert moves.length > 0 : "No moves available for " + color + " at depth " + depth;
        BoardScoreTuple[] boards = new BoardScoreTuple[moves.length];
        int bestScore = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        BoardScoreTuple bestTuple = null;

        if(depth == maxDepth){
            return new BoardScoreTuple(board,score, null);
        }
        
        for(int i = 0; i < moves.length; i++){
            ChessBoard newBoard = board.clone();
            Move move = moves[i];
            try {
                newBoard.executeMove(move);
                int newScore = newBoard.getPoints(color);
                boards[i] = new BoardScoreTuple(newBoard, newScore, move);
            } catch(ChessMasterException e){
                continue;
            }
        }
        
        //clear nulls from boards
        int nullCount = 0;
        for(BoardScoreTuple iterTuple : boards){
            if(iterTuple == null){
                nullCount++;
            }
        }
        BoardScoreTuple[] newBoards = new BoardScoreTuple[boards.length - nullCount];
        int index = 0;
        for(BoardScoreTuple iterTuple : boards){
            if(iterTuple != null){
                newBoards[index] = iterTuple;
                index++;
            }
        }

        nullCount = 0;
        for(BoardScoreTuple iterTuple : newBoards){
            if(iterTuple == null){
                nullCount++;
            }
        }
        assert nullCount == 0 : "Nulls still exist in newBoards";

        for(BoardScoreTuple iterTuple : newBoards){
            assert iterTuple != null : "iterTuple is null";
            BoardScoreTuple tuple1 = mostPoints(
                    iterTuple, color.getOppositeColour(), depth + 1, score, !isMax, maxDepth);
            int newScore = iterTuple.getScore();
            if(isMax){
                bestScore = Math.max(bestScore, newScore);
            }else{
                bestScore = Math.min(bestScore, newScore);
            }
            bestTuple = bestScore == newScore ? iterTuple : tuple1;
        }

        return bestTuple;

    }

    public Move getBestMove() {
        Move bestMove = MiniMax.mostPoints(tuple, color, 0, score, true, maxDepth).getMove();
        return bestMove;
    }



}
