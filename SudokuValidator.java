import java.util.*;

/**
 * Leetcode: 36. Valid Sudoku https://leetcode.com/problems/valid-sudoku/
 * 
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be
 * validated according to the following rules:
 * 
 * Each row must contain the digits 1-9 without repetition. Each column must
 * contain the digits 1-9 without repetition. Each of the nine 3 x 3 sub-boxes
 * of the grid must contain the digits 1-9 without repetition.
 * 
 * Note: A Sudoku board (partially filled) could be valid but is not necessarily
 * solvable. Only the filled cells need to be validated according to the
 * mentioned rules.
 * 
 * Constraints: board.length == 9 board[i].length == 9 board[i][j] is a digit or
 * '.'
 */
public class SudokuValidator {
    
    public static boolean isValidSudoku(char[][] board) {
       //init data
        HashSet<Character>[] rows = new HashSet[9];
        HashSet<Character>[] columns = new HashSet[9];
        
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<Character>();
            columns[i] = new HashSet<Character>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char curr = board[i][j];
                if( curr != '.') {
                    //checking row
                    if(!rows[i].add(curr)) return false;  
                   
                    //checking column
                    if(!columns[j].add(curr)) return false;   
                   
                    //checking grid
                    int startRow = i - i%3;
                    int startColumn = j - j%3;
                    HashSet<Character> grid = new HashSet<Character>();
                    for (int x = 0; x < 3; x++) {
                        for (int y = 0; y < 3; y++) {
                            char charGrid = board[startRow+x][startColumn+y];
                            if(charGrid != '.') {
                                if(!grid.add(charGrid)) return false;
                            }
                        }  
                    }    
                }
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
        System.out.println(isValidSudoku(board));

        char[][] board2 = new char[][] {
        {'.','.','4','.','.','.','6','3','.'},
        {'.','.','.','.','.','.','.','.','.'},
        {'5','.','.','.','.','.','.','9','.'},
        {'.','.','.','5','6','.','.','.','.'},
        {'4','.','3','.','.','.','.','.','1'},
        {'.','.','.','7','.','.','.','.','.'},
        {'.','.','.','5','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.'}
        };
        System.out.println(isValidSudoku(board2));
    }
}

