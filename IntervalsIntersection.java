import java.util.ArrayList;
import java.util.List;

import org.junit.*;

/**
 * Leetcode: 986. Interval List Intersections
 * 
 * Given two lists of intervals, find the intersection of these two lists. Each
 * list consists of disjoint intervals sorted on their start time.
 * 
 * Input:
 * A = [[0,2],[5,10],[13,23],[24,25]], 
 * B = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]] 
 */
public class IntervalsIntersection {

    /** 
     * Approach: Two Pointers
     * 
     * Time complexity: O( n + m), n and m the number of intervals of each input
     * Space complexity: O(n)
     */
    public static int[][] intersection(int[][] A, int[][] B) {
        List<int[]> intersection = new ArrayList<int[]>();
        
        int i = 0;
        int j = 0;
        while (i < A.length && j < B.length) {
            // whenever the two intervals overlap, 
            int startA = A[i][0];
            int endA = A[i][1];
            int startB = B[j][0];
            int endB = B[j][1];
            //one of the interval’s start time lies within the other interval.
            if( (startA >= startB && startA <= endB) || // A'start is within B
                (startB >= startA && startB<= endA)){ //B'start is within A
                int startInter = Math.max(startA , startB);
                int endInter = Math.min(endA , endB);
                intersection.add(new int[]{startInter,endInter});
            }
            if(endA < endB) {
                i++;
            } else {
                j++;
            }
        }   
        return intersection.toArray(new int[intersection.size()][]);
    }


    @Test
    public void validate() {
        int[][] result = intersection(new int[][]{{0,2},{5,10},{13,23},{24,25}}, 
                    new int[][]{{1,5},{8,12},{15,24},{25,26}});
        int[][] expected = new int[][]{{1,2},{5,5},{8,10},{15,23},{24,24},{25,25}};
        Assert.assertArrayEquals(result, expected);
    }    
}
