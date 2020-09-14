import java.util.*;
import org.junit.*;

/**
 * Leetcode: 436. Find Right Interval
 * https://leetcode.com/problems/find-right-interval/
 * 
 * You are given an array of intervals, where intervals[i] = [starti, endi] and each starti is
 * unique.
 * The right interval for an interval i is an interval j such that startj >= endi and startj 
 * is minimized.
 * 
 * Return an array of right interval indices for each interval i. If no right interval exists 
 * for interval i, then put -1 at index i.
 * 
 * Example 1:
 * Input: intervals = [[1,2]]
 * Output: [-1]
 * Explanation: There is only one interval in the collection, so it outputs -1.
 * 
 * Example 2:
 * Input: intervals = [[3,4],[2,3],[1,2]]
 * Output: [-1,0,1]
 * Explanation: There is no right interval for [3,4].
 * The right interval for [2,3] is [3,4] since start0 = 3 is the smallest start that 
 * is >= end1 = 3.
 * The right interval for [1,2] is [2,3] since start1 = 2 is the smallest start that 
 * is >= end2 = 2.
 */
public class IntervalsFindRightInterval {
   

    /**
     * Approach: Sorting + Binary Search
     * 
     * Time complexity: O(N log N)
     * Space complexity: O (1)
     * 
     * Runtime: 11 ms Memory 43.5 MB
     */
    public static int[] findRightInterval(int[][] input) {

        if(input == null || input.length <= 1) return new int[]{-1};

        int[] result = new int[input.length];

        Interval[] intervals = new Interval[input.length];
        for (int i = 0; i < input.length; i++) {
            Interval newInt = new Interval(input[i][0], input[i][1], i);
            intervals[i] = newInt;
        }
        //N log N
        Arrays.sort(intervals, (i1,i2) -> Integer.compare(i1.start, i2.start));

        //binary search to find next right interval - O(logN)
        for (int i = 0; i < intervals.length; i++) {
            Interval intv = intervals[i];
            result[intv.index] = binarySearch(intervals, intv);
        }
        return result;
    }
   
    private static int binarySearch(Interval[] intervals, Interval intv) {
        
        if(intv.end > intervals[intervals.length-1].start) return -1;

        int start = 0;
        int end = intervals.length - 1;
        while (start <= end) {
            int mid = start + (end-start)/2;           
            if (intervals[mid].start >= intv.end) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        //return the original index;
        return intervals[start].index;
    }

    /**
     * Approach: Max Heap
     * 
     * Time complexity: O (N logN)
     * Space complexity: O(N)
     * 
     * Runtime: 62 ms Memory 53.1 MB
     */
    public static int[] findRightIntervalII(int[][] intervals) {
        if(intervals == null || intervals.length <= 1) return new int[]{-1};

        int[] result = new int[intervals.length];

        //create a max heap ordered by project's start
        PriorityQueue<Interval> maxHeapStart = new PriorityQueue<Interval>((i1,i2)-> Integer.compare(i2.start, i1.start));
        //create a max heap ordered by project's end
        PriorityQueue<Interval> maxHeapEnd = new PriorityQueue<Interval>((i1, i2) -> Integer.compare(i2.end, i1.end));
        for (int i = 0; i < intervals.length; i++) {
            Interval newInt = new Interval(intervals[i][0], intervals[i][1], i);
            maxHeapStart.offer(newInt);
            maxHeapEnd.offer(newInt);
        }

        while(!maxHeapEnd.isEmpty()) {
            // let's find the next interval of the interval which has the highest 'end' 
            Interval maxEnd = maxHeapEnd.poll();
            if(maxHeapStart.peek().start >= maxEnd.end){
                Interval maxStart = maxHeapStart.poll();
                // find the the interval that has the closest 'start'
                while(!maxHeapStart.isEmpty() && maxHeapStart.peek().start >= maxEnd.end) {
                    maxStart = maxHeapStart.poll();
                }
                result[maxEnd.index] = maxStart.index;
                // put the interval back as it could be the next interval of other intervals
                maxHeapStart.add(maxStart);      
            } else {
                result[maxEnd.index] = -1;
            }
        }
        return result;
    }

    @Test
    public void validate() {
        int[][] intervals = new int[][] { new int[]{2, 3}, new int[]{3, 4}, new int[]{5, 6}};
        int[] expected = new int[]{1,2,-1};
        Assert.assertArrayEquals(expected, findRightInterval(intervals));
        Assert.assertArrayEquals(expected, findRightIntervalII(intervals));
    
        intervals = new int[][] { new int[]{3, 4}, new int[]{1, 5}, new int[]{4, 6}};
        expected = new int[]{2,-1,-1};
        Assert.assertArrayEquals(expected, findRightInterval(intervals));
        Assert.assertArrayEquals(expected, findRightIntervalII(intervals));

        intervals = new int[][] {new int[]{1, 12},new int[]{2, 9},new int[]{3, 10},new int[]{13, 14}, 
                    new int[]{15, 16}, new int[]{16, 17}};
        expected = new int[]{3, 3, 3, 4, 5,-1};
        Assert.assertArrayEquals(expected, findRightInterval(intervals));
        Assert.assertArrayEquals(expected, findRightIntervalII(intervals));

        intervals = new int[][] { new int[] {3,4}, new int[] {2,3}, new int[] {1,2}};
        expected = new int[]{-1, 0, 1};
        Assert.assertArrayEquals(expected, findRightInterval(intervals));
        Assert.assertArrayEquals(expected, findRightIntervalII(intervals));
      }
}
class Interval {
    int start;
    int end;
    int index;
    Interval(int start, int end, int index){
        this.start = start;
        this.end = end;
        this.index = index;
    }
}