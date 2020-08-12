import org.junit.*;

/**
 * 
 * Leetcode 209. Minimum Size Subarray Sum
 * 
 * Given an array of positive numbers and a positive number ‘S’, find the length of the smallest
 * contiguous subarray whose sum is greater than or equal to ‘S’. 
 * Return 0, if no such subarray exists.
 * 
 * Example 1:
 * Input: [2, 1, 5, 2, 3, 2], S=7 
 * Output: 2
 * Explanation: The smallest subarray with a sum great than or equal to '7' is [5, 2].
 * 
 * Example 2:
 * Input: [2, 1, 5, 2, 8], S=7  
 * Output: 1
 * Explanation: The smallest subarray with a sum greater than or equal to '7' is [8].
 */
public class MinimumSizeSubarraySum {
    
    
    /**
     * Approach: Sliding Window
     * 
     * Time complexity: O(n) - Each element can be visited atmost twice, once by the right pointer(end) and 
     *                          (atmost)once by the start pointer
     * Space complexity: O(1) - No extra space is used
     * 
     * @param input
     * @param target
     * @return
     */
    public static int minSubArrayLen(int[] input, int target) {

        int minLength = Integer.MAX_VALUE;
        int sum = 0;
        int start = 0;
        //First, we will add-up elements from the beginning of the array until their sum becomes
        // greater than or equal to target. These elements will constitute our sliding window
        for (int end = 0; end < input.length; end++) {
            sum += input[end];
            //we will keep adding one element in the sliding window in a stepwise fashion
            // In each step, we will also try to shrink the window from the beginning. 
            //We will shrink the window until the window’s sum is smaller than ‘S’ again
            while(sum >= target) {
                //calculating the current length and storing the minLength
                int lengthTemp = end-start+1;
                //Check if the current window length is the smallest so far
                minLength = Math.min(minLength, lengthTemp);
                //Subtract the first element of the window from the running sum to shrink the sliding window.
                sum -= input[start];
                start++;
            }
        }
        return minLength == Integer.MIN_VALUE? 0 : minLength;
    }

    @Test
    public void validate() {
        int[] list = {2, 1, 5, 2, 3, 2};
        int[] list2 = {2, 1, 5, 2, 8};
        int[] list3 = {3, 4, 1, 1, 6};
        int[] list4 = {2,3,1,2,4,3};
        int[] list5 = {1,4,4};
        Assert.assertEquals(2, minSubArrayLen(list, 7));
        Assert.assertEquals(1, minSubArrayLen(list2, 7));
        Assert.assertEquals(3, minSubArrayLen(list3, 8));
        Assert.assertEquals(2, minSubArrayLen(list4 , 7));
        Assert.assertEquals(1, minSubArrayLen(list5, 4));
    }
}