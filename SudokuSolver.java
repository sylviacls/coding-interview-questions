/**
 * Leetcode: 37. Sudoku Solver
 * https://leetcode.com/problems/sudoku-solver
 * 
 * Write a program to solve a Sudoku puzzle by filling the empty cells. A sudoku
 * solution must satisfy all of the following rules: Each of the digits 1-9 must
 * occur exactly once in each row. Each of the digits 1-9 must occur exactly
 * once in each column. Each of the digits 1-9 must occur exactly once in each
 * of the 9 3x3 sub-boxes of the grid. The '.' character indicates empty cells.
 */
public class SudokuSolver {

    public static void solveSudoku(char[][] board) {
        if(board == null || board.length == 0) return;

        recursiveSolver(board);
    }

    private static boolean recursiveSolver(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(board[i][j] == '.') {
                    //try 1 to 9
                    for (char n = '1'; n <= '9'; n++) {
                        if(isValid(board, i, j, n)) { //if valid, proceed
                            board[i][j] = n;
                            //move forward
                            if(recursiveSolver(board)) return true;
                            else board[i][j] = '.'; //backtrack
                        } 
                    }
                    return false; // no attempt valid for the current board, backtrack
                }
            }
        } 
        return true;
    }

    private static boolean isValid(char[][] board, int row, int column, char digit) {
        //checking row
        for (int i = 0; i < board[row].length; i++) {
            if(board[row][i] == digit) return false;
        }
        //checking column
        for (int i = 0; i < board.length; i++) {
            if(board[i][column] == digit) return false;
        }

        //checking grid
        int startRow = row - row%3;
        int startColum = column - column%3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[startRow + i][startColum + 1] == digit) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        solveSudoku(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

    }
}
