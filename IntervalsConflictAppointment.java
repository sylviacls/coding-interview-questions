import java.util.Arrays;

/**
 * Given a list of appointments, find all the conflicting appointments.
 * 
 * Appointments: 
 *  [[4,5], [2,3], [3,6], [5,7], [7,8]]
 * Output: 
 *  [4,5] and [3,6] conflict. 
 *  [3,6] and [5,7] conflict
 */
public class IntervalsConflictAppointment {

    public static void findConflicts(int[][] input) {

        Arrays.sort(input, (a,b) -> Integer.compare(a[0], b[0]));
        // we must keep tracking of the current intervals for continguous comparasion
        // only when we found a non-overlapping interval, we update current
        int i = 1;
        int[] interval = input[0];
        while(i < input.length){
            if( interval[1] > input[i][0]) {
                System.out.println(format(interval) + " and " 
                                + format(input[i]) + " conflict.");
            } else {
                interval = input[i];
            }
            i++;
        }
    } 

    public static String format(int[] interval) {
        return "[" + interval[0] + "," + interval[1] + "]";
    }

    public static void main(String[] args) {
       int[][] intervals = {{1, 4}, {1, 3}, {7, 9}};
       IntervalsConflictAppointment.findConflicts(intervals);
       System.out.println("-----");
        
       int[][] intervals2 = {{4, 5}, {2, 3}, {3, 6}, {5,7}, {7,8}};
       IntervalsConflictAppointment.findConflicts(intervals2);
    }
    
}