import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.*;
/**
 * Given an unsorted array of numbers, find the ‘K’ largest numbers in it.
 * 
 * Example: Input: [3, 1, 5, 12, 2, 11], K = 3 Output: [5, 12, 11]
 * 
 * The best data structure that comes to mind to keep track of ‘K’ elements is Heap. 
 * This pattern will make use of the Heap to solve multiple problems dealing with ‘K’ 
 * elements at a time from a set of given element
 * 
 */
public class TopKLargestNumbers {

    /**
     * Approach: Using a Min-heap
     * 
     * Time Complexity: O(n * KLogN): removing in a heap is O(logN), and we'll do it (N-k) times 
     * Space Complexity: O(K) since we need to store the top ‘K’ numbers in the heap.
     */
    public static List<Integer> findKLargestNumbers(int[] nums, int k) {
      PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

       // put first 'K' numbers in the min heap
      for (int i = 0; i < k; i++){
         minHeap.add(nums[i]);
      }
     // go through the remaining numbers of the array, if the number from the array is bigger than the
    // top (smallest) number of the min-heap, remove the top number from heap and add the number from array
      for (int i = k; i < nums.length; i++) {
        if(nums[i]> minHeap.peek() ) {
          minHeap.poll();
          minHeap.offer(nums[i]);
        } 
      }
      // the heap has the top 'K' numbers, return them in a list
      return new ArrayList<Integer>(minHeap);
    }
    /**
     * Approach: Using a Max-heap
     * 
     * Time Complexity: O(KLogN) - Extract Max k times to get k maximum elements 
     * Space Complexity: O(N)
     */
    public static List<Integer> findKLargestNumbersII(int[] nums, int k) {
        PriorityQueue<Integer> maxHep = new PriorityQueue<Integer>(Collections.reverseOrder());
        List<Integer> result = new ArrayList<>();
    
        for (int num : nums) {
            maxHep.offer(num);
        }

        for (int i = 1; i <= k; i++) {
            result.add(maxHep.poll());
        }
        return result;
    }
        
    /**
     * Approach: Using sorting (desc)
     * 
     * Time Complexity:O(N*LogN) (due to sorting)
     * Space Complexity: O(N)
     * @param nums
     * @param k
     * @return
     */
    public static List<Integer> findKLargestNumbersIII(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        //we need to convert int[] into Integer[], cause Arrays.sort doesn't accept int[]
        Integer[] integers = Arrays.stream(nums).boxed().toArray(Integer[]:: new);
        Arrays.sort(integers, Collections.reverseOrder());

        for (int i = 0; i < k; i++) {
          result.add(integers[i]);
        }
        return result;
    }
    
    @Test
    public void validate() {
      int[] input = new int[] { 3, 1, 5, 12, 2, 11 };
      int[] expected = new int[]{12,11,5};

      List<Integer> result = findKLargestNumbers(input, 3);
      Assert.assertArrayEquals(new int[]{5,12,11}, result.stream().mapToInt(i->i).toArray());

      result = findKLargestNumbersII(input, 3);
      Assert.assertArrayEquals(expected, result.stream().mapToInt(i->i).toArray());

      result = findKLargestNumbersIII(input, 3);
      Assert.assertArrayEquals(expected, result.stream().mapToInt(i->i).toArray());
    }
}