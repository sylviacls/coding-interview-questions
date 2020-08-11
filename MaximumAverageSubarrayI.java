import org.junit.*;

/**
 * Leetcode: 643. Maximum Average Subarray I
 * 
 * Given an array consisting of n integers, find the contiguous subarray of given length k 
 * that has the maximum average value. And you need to output the maximum average value.
 * 
 * Example 1:
 * Input: [1,12,-5,-6,50,3], k = 4
 * Output: 12.75
 * Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
 */
public class MaximumAverageSubarrayI {
    
    /**
     * Approach: Sliding-Window
     * 
     * Time complexity: O(n) - We iterate over the given nums array of length n once only.
     * Space complexity: O(1) - No extra space is used
     * 
     * @param nums array of integers
     * @param k length of the sub-array
     * @return the max average of all sub-arrays of k size
     */
    public static double findMaxAverage(int[] nums, int k) {
        int maxSum = 0;
        //calculating the sum of the first window
        for (int i = 0; i < k; i++) {
            maxSum += nums[i];
        }

        //moving the sliding-window: in a setpwise, removing the first number from the current windows
        // and adding the next number from the input
        int windowSum = maxSum;
        for (int i = k; i < nums.length; i++) {
            windowSum = windowSum - nums[i-k] + nums[i];
            maxSum = Math.max(maxSum, windowSum);
        }
        return (double) maxSum/k;
    }

    @Test
    public void validate() {
        Assert.assertEquals(12.75, findMaxAverage(new int[]{1,12,-5,-6,50,3}, 4),0);
        Assert.assertEquals(4.0,findMaxAverage(new int[]{0,4,0,3,2}, 1),0);
    }
}