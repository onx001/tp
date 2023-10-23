package chessmaster.game;

public class MiniMax {
    protected int depth;
    protected int maxDepth;
    protected int score;
    protected Move bestMove;
    protected ChessBoard board;
    protected Color color;
    protected Color opponentColor;

    public MiniMax(ChessBoard board, Color color, int maxDepth, int score) {
        this.board = board;
        this.color = color;
        this.opponentColor = color == Color.WHITE ? Color.BLACK : Color.WHITE;
        this.maxDepth = maxDepth;
        this.score = score;
    }
    
    public BoardScoreTuple mostPoints(ChessBoard board, Color color, int depth, int score, boolean isMax){
        Move[] moves = board.getAllMoves(color);
        ChessBoard[] boards = new ChessBoard[moves.length];
        int bestScore = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        if(depth == maxDepth){
            return new BoardScoreTuple(board,score);
        }
        
        for(Move move:moves){
            ChessBoard newBoard = board.clone();
            try {
                newBoard.executeMove(move);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        for(ChessBoard newBoard:boards){
            BoardScoreTuple tuple = mostPoints(newBoard, color, depth + 1, score, !isMax);
            int newScore = tuple.getScore();
            if(isMax){
                bestScore = Math.max(bestScore, newScore);
            }else{
                bestScore = Math.min(bestScore, newScore);
            }
        }
        return new BoardScoreTuple(board, bestScore);

    }


}
