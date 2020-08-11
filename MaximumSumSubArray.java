import org.junit.Assert;
import org.junit.Test;

/**
 * Given an array of positive numbers and a positive number ‘k’, find the maximum sum of any
 * contiguous subarray of size ‘k’.
 * 
 * Example 1:
 * Input: [2, 1, 5, 1, 3, 2], k=3 
 * Output: 9
 * Explanation: Subarray with maximum sum is [5, 1, 3].
 * 
 * Example 2: 
 * Input: [2, 3, 4, 1, 5], k=2 
 * Output: 7
 * Explanation: Subarray with maximum sum is [3, 4].
 */
public class MaximumSumSubArray {

    /**
     * Approach: Sliding Window
     * 
     * Time complexity: O(n) - We iterate over the given nums array of length n once only.
     * Space complexity: O(1) - No extra space is used
     * @param input
     * @param sizeK
     * @return
     */
    public static int maximumSumSubArray(int[] input, int sizeK) {
        //compute sum of first window of sizeK
        int maxSum = 0;
        for (int i = 0; i < sizeK; i++) {
            maxSum += input[i];
        }
        //compute sum of remains windows by removing first element of previous 
        // window and adding last element of current window.
        
        int windowSum = maxSum;
        for (int i = sizeK; i < input.length; i++) {
            windowSum = windowSum + input[i] - input[i - sizeK];
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }

    @Test
    public void valide() { 
        int[] list = {1, 4, 2, 10, 2, 3, 1, 0, 20 }; 
        int[] list2 = {2, 1, 5, 1, 3, 2};
        Assert.assertEquals(21, maximumSumSubArray(list, 3)); 
        Assert.assertEquals(20, maximumSumSubArray(list, 2)); 
        Assert.assertEquals(9, maximumSumSubArray(list2, 3)); 
        Assert.assertEquals(11, maximumSumSubArray(list2, 4)); 
    } 
} 
 