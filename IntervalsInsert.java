import java.util.*;
import java.util.stream.Stream;

import org.junit.*;

/**
 * Leetcode: 57. Insert Interval
 * 
 * Given a list of non-overlapping intervals sorted by their start time, insert
 * a given interval at the correct position and merge all necessary intervals to
 * produce a list that has only mutually exclusive intervals.
 * 
 * Example 1: 
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * 
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

**/
public class IntervalsInsert {

    /**
     * Time Complexity: O(n)
     * Space complexity: O (n) we need to return a new list
     */
    public static int[][] insertInterval(int[][] intervals, int[] newInterval) {
        //base cases
        if(intervals.length == 0) return new int[][]{newInterval};
        if(newInterval == null || newInterval.length == 0) return intervals;

        List<int[]> result = new LinkedList<int[]>();

        int newIntStart = newInterval[0];
        int newIntEnd = newInterval[1];

        // add all the intervals ending before newInterval starts
        int i = 0;
        while(i < intervals.length && intervals[i][1] < newIntStart) {
            result.add(intervals[i]);
            i++;
        }

        // merge all overlapping intervals to one considering newInterval
        int mergedStart = newIntStart;
        int mergedEnd = newIntEnd;
        while (i < intervals.length && intervals[i][0] <= mergedEnd) {
            mergedStart = Math.min(mergedStart, intervals[i][0]);
            mergedEnd = Math.max(mergedEnd, intervals[i][1]);
            i++;
        }
        result.add(new int[]{mergedStart, mergedEnd});

        // add all the remaining intervals to the output
        while(i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }
        return result.toArray(new int[result.size()][]);
    }

    @Test
    public void validate() {
        int[][] result = insertInterval(new int[][]{{1,3},{5,7},{8,12}}, new int[]{4,6});
        Stream.of(result).map(Arrays::toString).forEach(System.out::println);
        Assert.assertArrayEquals(new int[][]{{1,3}, {4,7},{8,12}}, result);

        int[][] result2 = insertInterval(new int[][]{{1,3},{5,7},{8,12}}, new int[]{4,10});
        Stream.of(result2).map(Arrays::toString).forEach(System.out::println);
        Assert.assertArrayEquals(new int[][]{{1,3}, {4,12}}, result2);
    }

}
