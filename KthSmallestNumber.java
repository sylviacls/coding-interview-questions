import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

import org.junit.*;

/**
 * Amazon's most ommonly asked interview question!
 * 
 * Given an unsorted array of numbers, find Kth smallest number in it.
 * 
 * Note that it is the Kth smallest number in the sorted order, not the Kth distinct element.
 * 
 * Input: [1, 5, 12, 2, 11, 5], K = 3 
 * Output: 5 
 * Explanation: The 3rd smallest number is '5', as the first two smaller numbers are [1, 2].
 */
public class KthSmallestNumber {

    /**
     * Approach: Using a Min-Heap
     * We can insert all the numbers in the min-heap and then extract the top ‘K’ numbers 
     * from the heap to find the Kth smallest number
     * 
     * Time Complexity: O(N) - adding an element in a priority queue is O(logN) worst case
     *                          - Initializing the min-heap with all numbers O(N)
     *                         - removing one elem is O(logN), so removing K is O(K* logN)
     *                         - O(N + K logN) = O(N)
     * Space Complexity: O(N)
     * @param nums
     * @param k
     * @return
     */
    public static int findKthSmallestNumber(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        int kSmallest = -1;
        for (int num : nums) {
            minHeap.add(num);
        }

        for (int i = 0; i < k; i++) {
            kSmallest = minHeap.poll();
        }

        return kSmallest;
    }

    /**
     * Approach: Using a MAX-Heap
     * Since we want to keep track of the ‘K’ smallest numbers, we can compare every number
     * with the root while iterating through all numbers, and if it is smaller than the root, 
     * we’ll take the root out and insert the smaller number.
     * 
     * At the end the root of the heap has the Kth smallest number
     * 
     * Time Complexity: o(N*logK)  - adding K element in the heap O(KlogK)
     *                            - removing (N-K) elements O((N-K)* logK)
     *                            - total = O(K*logK +  (N-K)* logK)  which is asymptotically equal to O(N*logK)
     * Space Complexity: O(K) -  we need to store ‘K’ smallest numbers in the heap
     * @param nums
     * @param k
     * @return
     */
    public static int findKthSmallestNumberII(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        for (int i = 0; i < k; i++) {
            maxHeap.add(nums[i]);
        }

        for (int i = k; i < nums.length; i++) {
            if(nums[i] < maxHeap.peek()) {
                maxHeap.remove();
                maxHeap.add(nums[i]);
            }
        }
        return maxHeap.peek();
    }

    /**
     * Approach: Brute-force with sorting
     * 
     * Time Complexity: O(N LogN) due to sorting algorithm
     * Space Complexity: O(N)
     * @param nums
     * @param k
     * @return
     */
    public static int findKthSmallestNumberIII(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[k-1];
    }
    @Test
    public void validate() {
        int[] input = new int[] { 1, 5, 12, 2, 11, 5 };
        Assert.assertEquals(5, findKthSmallestNumber(input, 3));
        Assert.assertEquals(5, findKthSmallestNumberII(input, 3));
        Assert.assertEquals(5, findKthSmallestNumberIII(input, 3));

        // since there are two 5s in the input array, our 3rd and 4th smallest numbers
        // should be a '5'
        int[] input2 = new int[] { 1, 5, 12, 2, 11, 5 };
        Assert.assertEquals(5, findKthSmallestNumber(input2, 4));
        Assert.assertEquals(5, findKthSmallestNumberII(input2, 4));
        Assert.assertEquals(5, findKthSmallestNumberIII(input2, 4));

        int[] input3 = new int[] { 5, 12, 11, -1, 12 };
        Assert.assertEquals(11, findKthSmallestNumber(input3, 3));
        Assert.assertEquals(11, findKthSmallestNumberII(input3, 3));
        Assert.assertEquals(11, findKthSmallestNumberIII(input3, 3));
    }
}