package in.tictactoe;

public class AI {

    /**
     * The function `GetBestMove` uses the minimax algorithm to determine the best
     * move for a player in a
     * board game based on the current board state.
     * 
     * @param turn  The `turn` parameter in the `GetBestMove` method is a boolean
     *              value that indicates
     *              whose turn it is in the game. If `turn` is `true`, it represents
     *              the current player's turn, and if
     *              `turn` is `false`, it represents the opponent's turn. This
     * @param board The `board` parameter in the `GetBestMove` method represents the
     *              game board on which
     *              the game is being played. It contains information about the
     *              current state of the game, such as the
     *              positions of the pieces and any previous moves that have been
     *              made.
     * @return The method `GetBestMove` is returning a `String` which represents the
     *         best move calculated
     *         by the minimax algorithm based on the current state of the board and
     *         the player's turn.
     */
    public static String GetBestMove(boolean turn, Board board) {
        String bestMove = "";
        int bestScore = Integer.MAX_VALUE;
        // long time = System.nanoTime();
        // System.out.println("---------------------------------");
        for (Object move : board.getValidMoves()) {
            board.move(turn, (String) move, false);
            int score = minimax(board, !turn, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (bestMove == "") {
                bestMove = (String) move;
                bestScore = score;

            } else {
                if (turn) {
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = (String) move;
                    }
                } else {
                    if (score < bestScore) {
                        bestScore = score;
                        bestMove = (String) move;
                    }
                }
            }
            board.remove((String) move, false);
            // System.out.print(move + " ->" + score+" | ");
        }
        // System.out.println("\n"+(System.nanoTime() - time) / 100000 +
        // "---------------------------------");
        return bestMove;
    }

    /**
     * The minimax function is a recursive algorithm used for decision-making in
     * two-player games, where it
     * evaluates all possible moves to determine the best move for the maximizing
     * player and the worst move
     * for the minimizing player.
     * 
     * @param board The `board` parameter represents the current state of the game
     *              board. It contains
     *              information about the positions of game pieces, valid moves, and
     *              the game state.
     * @param max   The `max` parameter in the `minimax` method is a boolean flag
     *              that indicates whether the
     *              current player is maximizing or minimizing the score. When `max`
     *              is `true`, it means that the
     *              current player is trying to maximize their score, and when `max`
     *              is `false`, it
     * @param alpha Alpha is the best value that the maximizing player can currently
     *              guarantee at that
     *              level or above. It represents the minimum score that the
     *              maximizing player is assured of.
     * @param beta  Beta is the best value that the minimizing player can guarantee
     *              at that level or above.
     *              It represents the upper bound of possible scores for the
     *              minimizing player. The algorithm uses beta
     *              to prune branches that are not promising, as it indicates that
     *              the maximizing player already has a
     *              better choice elsewhere.
     * @return The `minimax` function is returning the best score calculated based
     *         on the minimax algorithm
     *         for the given board state. The function recursively explores possible
     *         moves and their outcomes to
     *         determine the best move for the player represented by the `max`
     *         boolean parameter.
     */

    public static int minimax(Board board, boolean max, int alpha, int beta) {
        if (board.isGameOver()) {
            return board.result();
        }
        if (max) {
            int bestScore = Integer.MIN_VALUE;
            for (Object move : board.getValidMoves()) {
                board.move(max, (String) move, false);
                int score = minimax(board, false, alpha, beta);
                board.remove((String) move, false);
                bestScore = Math.max(bestScore, score);
                alpha = Math.max(score, alpha);
                if (beta <= alpha) {
                    break;
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (Object move : board.getValidMoves()) {
                board.move(max, (String) move, false);
                int score = minimax(board, true, alpha, beta);
                board.remove((String) move, false);
                bestScore = Math.min(bestScore, score);
                beta = Math.min(beta, score);
                if (beta <= alpha) {
                    break;
                }
            }
            return bestScore;
        }
    }

    /**
     * The `move` function in Java takes a board and a turn as input, and moves the
     * player to the best
     * available position on the board.
     * 
     * @param board The `board` parameter represents the game board on which the
     *              move is being made. It
     *              likely contains the current state of the game, including the
     *              positions of pieces or tokens.
     * @param turn  The `turn` parameter in the `move` method indicates which
     *              player's turn it is. It is a
     *              boolean value where `true` typically represents Player 1's turn
     *              and `false` represents Player 2's
     *              turn.
     */
    public static void move(Board board, boolean turn) {
        board.move(turn, GetBestMove(turn, board), true);
    }

}
