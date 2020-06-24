import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given an array of intervals representing ‘N’ appointments, 
 * find out if a person can attend all the appointments.
 * Time Complexity: O(n log n) sorting
 * Space complexity: O (n) used in sorting phase
 */
public class IntervalsAttendAppointment {
    
    public static Boolean canAttendAllAppointments(Interval[] input) {

        Arrays.sort(input, (a,b)-> Integer.compare(a.start, b.start));

        int i = 0;
        while (i < input.length-1) {
            //with the input sorted we only have to campare the input[i].end with input[i+1]
            if(input[i].end > input[i+1].start) {
                return Boolean.FALSE;
            }
            i++;
        }
        return Boolean.TRUE;
    }
    
    @Test
    public void validate() {
        Interval[] intervals1 = { new Interval(1, 4), new Interval(2, 5), new Interval(7, 9) };
        Assert.assertFalse(IntervalsAttendAppointment.canAttendAllAppointments(intervals1));
    
        Interval[] intervals2 = { new Interval(6, 7), new Interval(2, 4), new Interval(8, 12) };
        Assert.assertTrue(IntervalsAttendAppointment.canAttendAllAppointments(intervals2));
    
        Interval[] intervals3 = { new Interval(4, 5), new Interval(2, 3), new Interval(3, 6) };
        Assert.assertFalse(IntervalsAttendAppointment.canAttendAllAppointments(intervals3));
    }
}

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}