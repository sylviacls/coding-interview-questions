import java.util.*;


/**
 * Leetcode: 73. Set Matrix Zeroes
 * https://leetcode.com/problems/set-matrix-zeroes/
 * 
 * 
 * Given an m x n matrix. If an element is 0, set its entire row and column to 0. Do it in-place
 * Follow up:
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 */
public class MatrixSetZeros {

    /**
     * Time Complexity:  O(M × N) where M and N are the number of rows and columns respectively.
     * Space Complexity: O(M + N)
     * @param matrix
     */
    public static void setZeroes(int[][] matrix) {
        Set<Integer> rows = new HashSet<Integer>();
        Set<Integer> columns = new HashSet<Integer>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == 0) {
                    if(!rows.contains(i)) rows.add(i);
                    if(!columns.contains(j)) columns.add(j);
                }
            }
        }

        for (Integer row : rows) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[row][i] = 0;
            }
        }

        for (Integer column : columns) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][column] = 0;
            }
        }
    }


    /**
     * Rather than using additional variables to keep track of rows and columns to be reset, 
     * we use the matrix itself as the indicators.
     * The idea is that we can use the first cell of every row and column as a flag. 
     * This flag would determine whether a row or column has been set to zero. 
     * 
     * Time Complexity:  O(M × N) where M and N are the number of rows and columns respectively.
     * Space Complexity: O(1)
     * @param matrix
     */
    public static void setZeroesII(int[][] matrix) {
        //TODO
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        Arrays.stream(matrix).map(Arrays::toString).forEach(System.out::println);
        setZeroes(matrix);
        Arrays.stream(matrix).map(Arrays::toString).forEach(System.out::println);
    }
}
