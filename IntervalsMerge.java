import java.util.*;
import java.util.stream.Stream;

import org.junit.*;

/**
 * Leetcode: 56. Merge Intervals
 * 
 * Given a list of intervals, merge all the overlapping intervals to produce a
 * list that has only mutually exclusive intervals.
 * 
 */
public class IntervalsMerge {

    /**
     * Approach: Sorting
     * 
     * If we sort the intervals by their start value, then each set of intervals that can
     * be merged will appear as a contiguous "run" in the sorted list.
     * 
     * Time Complexity: O(n logn): n is the number of intervals. We must sort first
     * Space complexity: O (n) we need to return a new list, also sorting uses O(n)
     */
    public static int[][] merge(int[][] intervals) {
        if(intervals.length <= 1) return intervals;

		// Sort by ascending starting point
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
        List<int[]> result = new LinkedList<int[]>();

        // we must keep tracking of start and end values for continguous merging
        // only when we found a non-overlapping case we add a interval with the start
        // and end stored so far
        int prevStart = intervals[0][0];
        int prevEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            //if the start of curr interval is <= end of previous interval
            int currStart = intervals[i][0];
            int currEnd = intervals[i][1];
            if( currStart<= prevEnd) { //there is a overlap
                //take the min end between them
                prevEnd = Math.max(prevEnd, currEnd);
            } else { //no overlap
                result.add(new int[]{prevStart, prevEnd});
                prevStart = currStart;
                prevEnd = currEnd;
            }
        }
        // add the last interval
        result.add(new int[]{prevStart, prevEnd});
        return result.toArray(new int[result.size()][]);
    }

    @Test
    public void validate() {
        int[][] result = merge(new int[][]{{1,3},{2,6},{8,10},{15,18}});
        Stream.of(result).map(Arrays::toString).forEach(System.out::println);
        Assert.assertArrayEquals(new int[][]{{1,6}, {8,10},{15,18}}, result);

        int[][] result2 = merge(new int[][]{{1,4}, {4,5}});
        Stream.of(result2).map(Arrays::toString).forEach(System.out::println);
        Assert.assertArrayEquals(new int[][]{{1,5}}, result2);
    }
}
/*class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}*/