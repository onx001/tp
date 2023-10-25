package chessmaster.game;
import chessmaster.pieces.ChessPiece;

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
    
    public static BoardScoreTuple mostPoints(BoardScoreTuple tuple, 
            Color color, int depth, int score, boolean isMax, int maxDepth){
        
        ChessBoard board = tuple.getBoard();
        Move[] moves = board.getAllMoves(color);
        assert moves.length > 0 : "No moves available for " + color + " at depth " + depth;
        BoardScoreTuple[] boards = new BoardScoreTuple[moves.length];
        int bestScore = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        BoardScoreTuple bestTuple = null;

        if(depth == maxDepth){
            int newscore = board.getPoints(color);
            return new BoardScoreTuple(board,newscore, null);
        }
        
        for(int i = 0; i < moves.length; i++){
            ChessBoard newBoard = board.clone();
            Move move = moves[i];
            Coordinate from = move.getFrom();
            ChessPiece piece = newBoard.getPieceAt(from);
            move.setPiece(piece);
            try {
                newBoard.executeMove(move);
                int newScore = newBoard.getPoints(color);
                if (newScore != 0){
                }
                boards[i] = new BoardScoreTuple(newBoard, newScore, move);
                if (boards[i].getScore() != 0){
                }
            } catch (Exception e) {
                //do nothing
                assert false : "Exception caught";
                newBoard = newBoard.clone();
            }
        }
        
        

        for(int i = 0; i < boards.length; i++){
            BoardScoreTuple iterTuple = boards[i];
            assert iterTuple != null : "iterTuple is null";
            assert iterTuple.getMove() != null : "iterTuple move is null";
            BoardScoreTuple tuple1 = mostPoints(iterTuple, color.getOppositeColour(), 
                    depth + 1, score, !isMax, maxDepth);
            int newScore = tuple1.getScore();
            if(isMax){
                if (newScore > bestScore) {
                    bestScore = newScore;
                }
            }else{
                if (newScore < bestScore) {
                    bestScore = newScore;
                }
            }
            bestTuple = bestScore == newScore ? iterTuple : bestTuple;
        }

        return bestTuple;

    }

    public Move getBestMove() {
        BoardScoreTuple bestTuple = mostPoints(tuple, color, 3, score, false, maxDepth);
        Move bestMove = bestTuple.getMove();
        return bestMove;
    }



}
