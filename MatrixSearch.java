/**
 * Leetcode: 74. Search a 2D Matrix
 * https://leetcode.com/problems/search-a-2d-matrix/
 * 
 * Write an efficient algorithm that searches for a value in an m x n matrix. 
 * This matrix has the following properties:
 * - Integers in each row are sorted from left to right.
 * - The first integer of each row is greater than the last integer of the previous row.
 */
public class MatrixSearch {
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                row++;
            } else {
                col--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
        System.out.println(searchMatrix(matrix, 3));
    }
}
