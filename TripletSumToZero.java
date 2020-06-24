import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given an array of unsorted numbers, find all unique triplets in it that add
 * up to zero.
 */

public class TripletSumToZero {

    // Input: [-3, 0, 1, 2, -1, 1, -2]
    // ordered: [-3,-1,0,1,1,2]
    // Output: [-3, 1, 2], [-2, 0, 2], [-2, 1, 1], [-1, 0, 1]
    public static List<List<Integer>> tripletSumToZero(int[] input) {

        Arrays.sort(input);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < input.length-1; i++) {

            int start = i + 1;
            int end = input.length - 1;

            while (start < end) {
                if (input[i] + input[start] + input[end] == 0) {
                    ArrayList<Integer> triplet = new ArrayList<>();
                    triplet.add(input[i]);
                    triplet.add(input[start]);
                    triplet.add(input[end]);
                    result.add(triplet);
                    start++;
                    end--;

                } else if (input[i] + input[start] + input[end] < 0) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return result;
    }

    @Test
    public void validate() {
        List<List<Integer>> expected = new ArrayList<>();
        List<Integer> triple = new ArrayList<>();
        triple.add(-3);
        triple.add(1);
        triple.add(2);
        expected.add(triple);
        List<Integer> triple2 = new ArrayList<>();
        triple2.add(-1);
        triple2.add(0);
        triple2.add(1);
        expected.add(triple2);
        
        Assert.assertArrayEquals(expected.toArray(), tripletSumToZero(new int[]{0, -1, 2, -3, 1}).toArray());

        List<List<Integer>> expected2 = new ArrayList<>();
        List<Integer> triple3 = new ArrayList<>();
        triple3.add(-5);
        triple3.add(2);
        triple3.add(3);
        expected2.add(triple3);
        List<Integer> triple4 = new ArrayList<>();
        triple4.add(-2);
        triple4.add(-1);
        triple4.add(3);
        expected2.add(triple4);

        Assert.assertArrayEquals(expected2.toArray(), tripletSumToZero(new int[]{-5, 2, -1, -2, 3}).toArray());
    }

    public static void main(String[] args) {
        System.out.println(tripletSumToZero(new int[]{0, -1, 2, -3, 1}).toString());
    }
}