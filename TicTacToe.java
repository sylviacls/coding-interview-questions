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
 */
public class TicTacToe {

    /**Initializar your data structure here */
    private char[][] board;
    private char currentPlayer;
    
    public TicTacToe(int n){
        this.board = new char[n][n];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '-';
            }
        }
    }

    /**
     * place a move
     * check if current player won (col, row, diag)
     */
    public int move(int row, int col, int player) {
        this.currentPlayer = player == 1? 'X':'O';
        this.board[row][col] = this.currentPlayer;
        if(winColumn(col) || winRow(row)|| winDiagonal(row)) return player;
        return 0;
    }

    private boolean winColumn(int colum) {
        for (int i = 1; i < board.length; i++) {
            if(board[i][colum] == '-' || board[i][colum] != board[i-1][colum])
                return false;
        }
        return true;
    }

    private boolean winRow(int row) {
        for (int i = 1; i < board.length; i++) {
            if(board[row][i] == '-' || board[row][i] != board[row][i-1])
                return false;
        }
        return true;
    }
    private boolean winDiagonal(int row) {
        boolean diagLeft = false;
        boolean diagRigh = false;
        for (int i = 1; i < board.length; i++) {
            if(board[i][i] == '-' || board[i][i] != board[i-1][i-1])
                diagLeft = false;
        }
        return diagLeft || diagRigh;
    }
    public static void main(String[] args) {
        TicTacToe toe = new TicTacToe(3);
        System.out.println(toe.move(0, 0, 1)); 
        System.out.println(toe.move(0, 2, 2));
        System.out.println(toe.move(2, 2, 1));
        System.out.println(toe.move(1, 1, 2));
        System.out.println(toe.move(2, 0, 1));
        System.out.println(toe.move(1, 0, 2));
        System.out.println(toe.move(2, 1, 1));
    }
}
