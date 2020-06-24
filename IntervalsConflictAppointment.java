import java.util.Arrays;

/**
 * Given a list of appointments, find all the conflicting appointments.
 * Appointments: [[4,5], [2,3], [3,6], [5,7], [7,8]]
*  Output: 
*  [4,5] and [3,6] conflict. 
*  [3,6] and [5,7] conflict
 */
public class IntervalsConflictAppointment {

    public static void findConflicts(Interval[] input) {

        Arrays.sort(input, (a,b) -> Integer.compare(a.start, b.start));
        //             i          i           
        //2,3 - 3,6 - 4,5 - 5,7 - 7,8
        // we must keep tracking of the current intervals for continguous comparasion
        // only when we found a non-overlapping interval, we update current
        int i = 1;
        Interval interval = input[0];
        while(i < input.length){
            if( interval.end > input[i].start) {
                System.out.println(format(interval) + " and " 
                                + format(input[i]) + " conflict.");
            } else {
                interval = input[i];
            }
            i++;
        }
        //handle the last interval

    } 

    public static String format(Interval interval) {
        return "[" + interval.start + "," + interval.end + "]";
    }

    public static void main(String[] args) {
        Interval[] intervals = { new Interval(1, 4), new Interval(1, 3), new Interval(7, 9) };
        IntervalsConflictAppointment.findConflicts(intervals);
        
        Interval[] intervals2 = { new Interval(4, 5), new Interval(2, 3), new Interval(3, 6),  
            new Interval(5, 7),  new Interval(7, 8)};
        IntervalsConflictAppointment.findConflicts(intervals2);
    
    }
    
}