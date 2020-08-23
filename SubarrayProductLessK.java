import org.junit.*;

/**
 * 713. Subarray Product Less Than K
 * https://leetcode.com/problems/subarray-product-less-than-k/
 * 
 * Your are given an array of positive integers nums.
 * Count and print the number of (contiguous) subarrays where the product of all the 
 * elements in the subarray is less than k.
 * 
 * Example 1:
 * Input: nums = [10, 5, 2, 6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are:
 *  [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 */
public class SubarrayProductLessK {
    /**
     * Approach: Sliding Window
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if( k <= 1) return 0;

        int count = 0;
        int prod = 1;
        int start = 0;
        for (int end = 0; end < nums.length; end++) {
            prod *= nums[end];
            //expading the window at its maximum (prod >=k) and then shrink it
            // in a valid window (n = end - start + 1), we will have "n" possible subarrays 
            // since the product of all numbers from left to right is less than the target therefore,
            // all subarrays from left to right will have a product less than the target too;
            while(prod >= k && start < nums.length) {
                prod /= nums[start];
                start++;
            }
            count += end - start + 1;
        } 
        return count;
    }

    @Test
    public void validate() {
        Assert.assertEquals(6, numSubarrayProductLessThanK(new int[] { 2, 5, 3, 10 }, 30));
        Assert.assertEquals(7, numSubarrayProductLessThanK(new int[] { 8, 2, 6, 5 }, 50));
      }
}