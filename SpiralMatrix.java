import java.util.*;

import org.junit.internal.RealSystem;

import jdk.internal.jshell.tool.resources.l10n;

/**
 * Leetcode: 54. Spiral Matrix https://leetcode.com/problems/spiral-matrix/
 * 
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * 
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]] [1,2,3] [4,5,6] [7,8,9] Output:
 * [1,2,3,6,9,8,7,4,5]
 * 
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]] Output:
 * [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix {

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new LinkedList<Integer>();
        if (matrix == null || matrix.length == 0) {
            return result;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        
        int rowBegin = 0;
        int rowEnd = n - 1;
        int colBegin = 0;
        int colEnd = m - 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            //traversing right
            for (int i = colBegin; i <= colEnd && result.size() < n * m; i++) {
                result.add(matrix[rowBegin][i]);
            }
            rowBegin++;
            //traversing down
            for (int i = rowBegin; i <= rowEnd && result.size() < n * m; i++) {
                result.add(matrix[i][colEnd]);
            }
            colEnd--;

            //traversing left
            for (int i = colEnd; i >= colBegin && result.size() < n * m; i--) {
                result.add(matrix[rowEnd][i]);
            }
            rowEnd--;

            // traversing up
            for (int i = rowEnd; i >= rowBegin && result.size() < n * m; i--) {
                result.add(matrix[i][colBegin]);
            }
            colBegin++;
        }

        return result;
    }
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};
        spiralOrder(matrix).forEach(System.out::println);

        int[][] matrix2 = new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}};
        spiralOrder(matrix2).forEach(System.out::println);
    }
}
