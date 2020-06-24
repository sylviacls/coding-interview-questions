import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a list of non-overlapping intervals sorted by their start time, insert
 * a given interval at the correct position and merge all necessary intervals to
 * produce a list that has only mutually exclusive intervals.
 * Time Complexity: O(n)
 * Space complexity: O (n) we need to return a new list, 
**/
public class IntervalsInsert {

    public static List<Interval> insertInterval(List<Interval> intervals, Interval newInterval) {

        if (intervals == null || intervals.isEmpty())
            return Arrays.asList(newInterval);

        List<Interval> mergedIntervals = new ArrayList<Interval>();

        // skip (and add to output) all intervals that come before the 'newInterval'
        int i = 0;
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
            mergedIntervals.add(intervals.get(i));
            i++;
        }
        // merge all intervals that overlap with 'newInterval'
        int start = newInterval.start;
        int end = newInterval.end;
        while (i < intervals.size() && intervals.get(i).start <= end) {
            start = Math.max(start, intervals.get(i).end);
            end = Math.max(end, intervals.get(i).end);
            i++;
        }
        // insert the newInterval
        mergedIntervals.add(new Interval(start, end));

        // add all the remaining intervals to the output
        while (i < intervals.size()) {
            mergedIntervals.add(intervals.get(i));
            i++;
        }

        return mergedIntervals;
    }

    public static void main(String[] args) {
        List<Interval> input = new ArrayList<Interval>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : IntervalsInsert.insertInterval(input, new Interval(4, 6)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : IntervalsInsert.insertInterval(input, new Interval(4, 10)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(2, 3));
        input.add(new Interval(5, 7));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : IntervalsInsert.insertInterval(input, new Interval(1, 4)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

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