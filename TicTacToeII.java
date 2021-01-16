/**
 * Leetcode: 348	Design Tic-Tac-Toe
 * https://leetcode.com/problems/design-tic-tac-toe
 * 
 * Design a Tic-tac-toe game that is played between two players on a n x n grid.
 * You may assume the following rules:
 * - A move is guaranteed to be valid and is placed on an empty block.
 * - Once a winning condition is reached, no more moves is allowed.
 * - A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 *
 * Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.
 * 
 * TicTacToe toe = new TicTacToe(3);
 * toe.move(0, 0, 1); -> Returns 0 (no one wins)
 * |X| | |
 * | | | |    // Player 1 makes a move at (0, 0).
 * | | | |
 * toe.move(0, 2, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 2 makes a move at (0, 2).
 * | | | |
 * 
 * (...) game continues
 * 
 * toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
 * |X| |O|
 * |O|O| |    // Player 1 makes a move at (2, 1).
 * |X|X|X|
 * 
 * Could you do better than O(n2) per move() operation.
 */
public class TicTacToeII {
    private int[] row;
    private int[] col;
    private int diagLeft;
    private int diagRight;
    private int size;

    public TicTacToeII(int n) {
        this.size = n;
        this.row = new int[n];
        this.col = new int[n];
    }

    public int move(int i, int j, int player) {
        if(player == 1) {
            row[i]++;
            col[j]++;
            if(i == j) diagLeft++;
            if(i+j == size-1) diagRight++;
            if(row[i] == size ||col[j] == size || diagLeft == size || diagRight == size)
                return player;
        } else {
            row[i]--;
            col[j]--;
            if(i == j) diagLeft--;
            if(i+j == size-1) diagRight--;
            if(row[i] == -size ||col[j] == -size || diagLeft == -size || diagRight == -size)
            return player;
        }
        return 0;
    }

    public static void main(String[] args) {
        TicTacToeII toe = new TicTacToeII(3);
        System.out.println(toe.move(0, 0, 1)); 
        System.out.println(toe.move(0, 2, 2));
        System.out.println(toe.move(2, 2, 1));
        System.out.println(toe.move(1, 1, 2));
        System.out.println(toe.move(2, 0, 1));
        System.out.println(toe.move(1, 0, 2));
        System.out.println(toe.move(2, 1, 1));
    }

    
}
