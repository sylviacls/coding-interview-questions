import java.util.ArrayList;
import java.util.List;

/**
 * Given two lists of intervals, find the intersection of these two lists. Each
 * list consists of disjoint intervals sorted on their start time. 
 * Time complexity: O( n + m), n and m the number of intervals of each input
 * Space complexity: O(n)
 */
public class IntervalsIntersection {

    public static Interval[] intersection(Interval[] input1, Interval[] input2) {
       List<Interval> intersection = new ArrayList<Interval>();
        //find intersection and calculate it
        int i = 0;
        int j = 0;

        // whenever the two intervals overlap, 
        //one of the interval’s start time lies within the other interval.
        while (i <input1.length && j <= input2.length) {
            if(input1[i].start <= input2[j].end && input1[i].start >= input2[j].start
               || input2[j].start <= input1[i].end && input2[j].start >= input1[i].start) {
                intersection.add(new Interval(Math.max(input1[i].start, input2[j].start),
                Math.min(input1[i].end, input2[j].end)));
            }
            if(input1[i].end < input2[j].end) {
                i++;
            } else {
                j++;
            }
        }
        return intersection.toArray(new Interval[intersection.size()]);
    }

    public static void main(String[] args) {
        Interval[] input1 = new Interval[] { new Interval(1, 3), new Interval(5, 6), new Interval(7, 9) };
        Interval[] input2 = new Interval[] { new Interval(2, 3), new Interval(5, 7) };
        Interval[] result = IntervalsIntersection.intersection(input2, input1);
        System.out.print("Intervals Intersection: ");
        for (Interval interval : result)
          System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();
    
        input1 = new Interval[] { new Interval(1, 3), new Interval(5, 7), new Interval(9, 12) };
        input2 = new Interval[] { new Interval(5, 10) };
        result = IntervalsIntersection.intersection(input2, input1);
        System.out.print("Intervals Intersection: ");
        for (Interval interval : result)
          System.out.print("[" + interval.start + "," + interval.end + "] ");
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