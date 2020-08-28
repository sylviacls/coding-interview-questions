import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given an array of intervals representing ‘N’ appointments, 
 * find out if a person can attend all the appointments.
 * 
 * Example 1:
 * Appointments: [[1,4], [2,5], [7,9]]
 * Output: false
 * Explanation: Since [1,4] and [2,5] overlap, a person cannot attend both of these appointments.
 * 
 * Example 2:
 * Appointments: [[6,7], [2,4], [8,12]]
 * Output: true
 * Explanation: None of the appointments overlap, therefore a person can attend all of them.
 */
public class IntervalsAttendAppointment {
    
    /**
     * Time Complexity: O(n log n) sorting
     * Space complexity: O (n) used in sorting phase
     */
    public static boolean canAttendAllAppointments(int[][] input) {
        Arrays.sort(input, (a,b)-> Integer.compare(a[0], b[0]));
        int i = 0;
        while (i < input.length-1) {
            //with the input sorted we only have to campare the input[i].end with input[i+1]
            if(input[i][1] > input[i+1][0]) {
                return false;
            }
            i++;
        }
        return true;
    }
    
    @Test
    public void validate() {
        int[][] intervals1 = {{1, 4}, {2, 5}, {7, 9}};
        Assert.assertFalse(canAttendAllAppointments(intervals1));
    
        int[][] intervals2 = {{6, 7},{2, 4}, {8, 12}};
        Assert.assertTrue(canAttendAllAppointments(intervals2));
    
        int[][] intervals3 = {{4, 5}, {2, 3}, {3, 6}};
        Assert.assertFalse(canAttendAllAppointments(intervals3));
    }
}
