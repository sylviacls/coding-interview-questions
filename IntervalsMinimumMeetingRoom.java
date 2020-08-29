import java.util.Arrays;
import java.util.PriorityQueue;

import org.junit.*;

/**
 * Leetcode: LeetCode – Meeting Rooms II
 * 
 * Given a list of intervals representing the start and end time of ‘N’ meetings, 
 * find the minimum number of rooms required to hold all the meetings.
 * 
 * Example 1: 
 * Meetings: [[1,4], [2,5], [7,9]]
 * Output: 2
 * Explanation: Since [1,4] and [2,5] overlap, we need two rooms to hold these two meetings. 
 * [7,9] can occur in any of the two rooms later.
 * 
 * Example 2: 
 * Meetings: [[6,7], [2,4], [8,12]]
 * Output: 1
 * Explanation: None of the meetings overlap, therefore we only need one room 
 * 
 * Example 3: 
 * Meetings: [[1,4], [2,3], [3,6]]
 * Output:2
 * Explanation: Since [1,4] overlaps with the other two meetings [2,3] and [3,6], 
 * we need two rooms to hold all the meetings.
 * 
 * Example 4:
 * Meetings: [[4,5], [2,3], [2,4], [3,5]]
 * Output: 2
 * Explanation: We will need one room for [2,3] and [3,5], and another room for 
 * [2,4] and [4,5].
 */
public class IntervalsMinimumMeetingRoom {
    
    /**
     * When a room is taken, the room can not be used for anther meeting until the current
     * meeting is over. As soon as the current meeting is finished, the room can be used 
     * for another meeting. We can sort the meetings by start timestamps and sequentially
     * assign each meeting to a room. Each time when we assign a room for a meeting,
     * we check if any meeting is finished so that the room can be reused.
     * In order to efficiently track the earliest ending meeting, we can use a min heap. 
     * Whenever an old meeting ends before a new meeting starts, we reuse the room 
     * (i.e., do not add more room). Otherwise, we need an extra room (i.e., add a room).
     * 
     * Time Complexity: O(N log N): Sorting (N Log N) + pooll/offer heap (Log N)
     * Space Complexity: O(N)
     * 
     */
    public static int findingMinimumMeetingRoom(int[][] intervals) {
        //base cases
        if(intervals == null || intervals.length == 0) return 0;
        if(intervals.length == 1) return 1;

        Arrays.sort(intervals, (i1,i2) -> Integer.compare(i1[0], i2[0]));
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();

        //reserve a room for the first meeting (keeping track of the end)
        int prevEnd = intervals[0][1];
        heap.add(prevEnd);
        for (int i = 1; i < intervals.length; i++) {
            int currStart = intervals[i][0];
            int currEnd = intervals[i][1];
            if(currStart < heap.peek()) { // no avaliable another room
                heap.add(currEnd);
            } else { //there is a room avaliable 
                //he have to update the end-time for the new meeting
                heap.poll();
                heap.add(currEnd);
            }
        }
        return heap.size();
    }

    @Test
    public void validate() {
        Assert.assertEquals(2, findingMinimumMeetingRoom(new int[][]{{4,5},{2,3},{2,4},{3,5}}));
        Assert.assertEquals(2, findingMinimumMeetingRoom(new int[][]{{1,4},{2,5},{7,9}}));
        Assert.assertEquals(1, findingMinimumMeetingRoom(new int[][]{{6,7},{2,4},{8,12}}));
        Assert.assertEquals(2, findingMinimumMeetingRoom(new int[][]{{1,4},{2,3},{3,6}}));
    }
}