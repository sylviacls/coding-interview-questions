/**
 * For every k continuous numbers in an array, return the largest numbers.
 * This challenge is easy to brute-force, but try to find the optimal solution.
 * 
 * Examples:
 * K = 3, [1, 3, 5, 7, 9, 2])
 * => [5, 7, 9, 9]
 * 
 * K= 4, [9, 3, 5, 1, 7, 10])
 * => [9, 7, 10]
 */
import java.util.*;
import org.junit.*;

public class MaxNumberSubArray {
    /**
     * Approach: Using a Dequeu
     * 
     * Time Complexity: O(N*K), O(N) for the sliding window and for each step we go O(K)
     * Space Complexity: O(N)
     * @param numbers
     * @param k
     * @return
     */
    public static Integer[] maxNumbers(int[] numbers, int k){
        List<Integer> result = new ArrayList<Integer>();
        Queue<Integer> queue = new LinkedList<Integer>();

        //putting the first k number into the queue
        for (int i = 0; i < k; i++) {
            queue.add(numbers[i]);
        }

        //for each step in the sliding window, we will add the current max element into the result
        // then, remove the first element of the queue and add the next number of the array
        for (int i = k; i < numbers.length; i++) {
            result.add(findMax(queue));
            queue.poll();
            queue.offer(numbers[i]);
        }
        //handling the last iteration
        result.add(findMax(queue));  
        return result.toArray(new Integer[result.size()]);
    }

    private static Integer findMax(Queue<Integer> queue) {
        int max = Integer.MIN_VALUE;
        for (Integer num : queue) {
            if(num > max) max = num;
        }
        return max;
    }

    @Test
    public void validate() {
        Assert.assertArrayEquals(new Integer[]{5, 7, 9, 9}, maxNumbers(new int[]{1, 3, 5, 7, 9, 2}, 3));
        Assert.assertArrayEquals(new Integer[]{9, 7, 10}, maxNumbers(new int[]{9, 3, 5, 1, 7, 10}, 4));
        Assert.assertArrayEquals(new Integer[]{3, 3, 4, 5, 5, 5, 6}, maxNumbers(new int[]{1, 2, 3, 1, 4, 5, 2, 3, 6}, 3));
        Assert.assertArrayEquals(new Integer[]{10, 10, 10, 15, 15, 90, 90}, maxNumbers(new int[]{8, 5, 10, 7, 9, 4, 15, 12, 90, 13}, 4));
    }
}