import java.util.*;
import org.junit.*;

/**Given an array, find the sum of all numbers between the K1’th and K2’th smallest 
 * elements of that array.
 * Assume that all numbers are distintic
 * 
 * Example 1:
 * 
 * Input: [1, 3, 12, 5, 15, 11], and K1=3, K2=6
 * Output: 23
 * Explanation: The 3rd smallest number is 5 and 6th smallest number 15. 
 * The sum of numbers coming between 5 and 15 is 23 (11+12). 
 */
public class SumElementsBetweenK1K2 {

    /**
     * Approach: Using min-heap 
     * 
     * We can find the sum in the following steps:
     * First, insert all numbers in a min-heap.
     * Remove the first K1 smallest numbers from the min-heap.
     * Now take the next K2-K1-1 numbers out of the heap and add them. 
     * This sum will be our required output.
     * 
     * Time Complexity: O(NLogN)
     * Space Complexity: O(N)
     * @param nums
     * @param k1
     * @param k2
     * @return
     */
    public static int findSumOfElements(int[] nums, int k1, int k2) {
        //insertinh all numbers in a min-heap
        //this step will take O(NLogN)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.add(num);
        }
        //removing the first k1 smallest elements
        for (int i = 0; i < k1; i++){
            minHeap.poll();
        }
        // taking the next k2-k1 (diff) numbers out and adding them
        int sum = 0;
        // sum next k2-k1-1 numbers
        for (int i = 0; i < k2 - k1 - 1; i++) {
            sum += minHeap.poll();
        }
        return sum;
      }
    
      /**
       * Approach: using a Max-heap
       * We can iterate the array and use a max-heap to keep track of the top K2 numbers. 
       * We can, then, add the top K2-K1-1 numbers in the max-heap to find the sum of all 
       * numbers coming between the K1’th and K2’th smallest numbers. 
       * 
       * Time Complexity: O(N logK2)
       * Space Complexity: O(K2)
       * @param nums
       * @param k1
       * @param k2
       */
      public static int findSumOfElementsII(int[] nums, int k1, int k2){
    
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((n1,n2)-> n2-n1);
        //storing k2 nums in the maxHeap
        for (int i = 0; i < nums.length; i++) {
            //if we hadn't stored K2 elements yet, we will store nums[i] not matter its value
            if(i < k2 -1) {
                maxHeap.add(nums[i]);
            } //when maxHeap has already k2 elements we will replace the largest of them (root)
            //if the current number is smaller than root
            else if(nums[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(nums[i]);
            }
        }
        // get the sum of numbers between k1 and k2 indices
        // these numbers will be at the top of the max heap
        int elementSum = 0;
        for (int i = 0; i < k2 - k1 - 1; i++)
            elementSum += maxHeap.poll();

        return elementSum;
      }

      @Test
      public void validate() {
        Assert.assertEquals(23, findSumOfElements(new int[] { 1, 3, 12, 5, 15, 11 }, 3, 6));    
        Assert.assertEquals(12, findSumOfElements(new int[] { 3, 5, 8, 7 }, 1, 4));

        Assert.assertEquals(23, findSumOfElementsII(new int[] { 1, 3, 12, 5, 15, 11 }, 3, 6));    
        Assert.assertEquals(12, findSumOfElementsII(new int[] { 3, 5, 8, 7 }, 1, 4));
      }
}