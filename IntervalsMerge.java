import java.util.*;

/**
 * Given a list of intervals, merge all the overlapping intervals to produce a
 * list that has only mutually exclusive intervals.
 * 
 * Time Complexity: O(n logn): n is the number of intervals. We must sort first
 * Space complexity: O (n) we need to return a new list, also sorting uses O(n)
 */
public class IntervalsMerge {

    public static List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1)
            return intervals;

        // sort the intervals by start time
        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

        List<Interval> mergedIntervals = new LinkedList<Interval>();

        Iterator<Interval> intervalItr = intervals.iterator();
        Interval interval = intervalItr.next();
        // we must keep tracking of start and end values for continguous merging
        // only when we found a non-overlapping case we add a interval with the start
        // and end stored so far
        int start = interval.start;
        int end = interval.end;

        while (intervalItr.hasNext()) {
            interval = intervalItr.next();
            if (interval.start <= end) {// overlapping
                end = Math.max(end, interval.end);
            } else { // non-overlapping;
                mergedIntervals.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        // add the last interval
        mergedIntervals.add(new Interval(start, end));

        return mergedIntervals;

    }

    public static Boolean hasOverlapping(List<Interval> intervals) {

        if(intervals.size() <= 1) return Boolean.FALSE;

        Collections.sort(intervals, (a,b) -> Integer.compare(a.start, b.start));

        ListIterator<Interval> iterator = intervals.listIterator();
        Interval currentInterval = iterator.next();
      //  int start = currentInterval.start;
        int end = currentInterval.end;

        while(iterator.hasNext()) {
            currentInterval = iterator.next();
            if(currentInterval.start <= end) {
                return Boolean.TRUE;
            } else {
              //  start = currentInterval.start;
                end = currentInterval.end;
            }
        }

        return Boolean.FALSE;
    }
    public static void main(String[] args) {
        List<Interval> input = new ArrayList<Interval>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 5));
        input.add(new Interval(7, 9));
        System.out.println("Does it have an overlapping? :" + hasOverlapping(input));
        System.out.print("Merged intervals: ");
        for (Interval interval : IntervalsMerge.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(6, 7));
        input.add(new Interval(2, 4));
        input.add(new Interval(5, 9));
        System.out.println("Does it have an overlapping? :" + hasOverlapping(input));
        System.out.print("Merged intervals: ");
        for (Interval interval : IntervalsMerge.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 6));
        input.add(new Interval(3, 5));
        System.out.println("Does it have an overlapping? :" + hasOverlapping(input));
        System.out.print("Merged intervals: ");
        for (Interval interval : IntervalsMerge.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(1, 3));
        input.add(new Interval(4, 7));
        input.add(new Interval(10, 13));
        System.out.println("Does it have an overlapping? :" + hasOverlapping(input));
        System.out.print("Merged intervals: ");
        for (Interval interval : IntervalsMerge.merge(input))
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