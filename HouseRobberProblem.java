import org.junit.*;

/**
 * You are a professional robber planning to rob houses along a street. 
 * Each house has a certain amount of money stashed, the only constraint stopping you from
 * robbing each of them is that adjacent houses have security system connected and it will
 * automatically contact the police if two adjacent houses were broken into on the same night.
 * 
 * Given a list of non-negative integers representing the amount of money of each house, 
 * determine the maximum amount of money you can rob tonight without alerting the police.
 * 
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 */
public class HouseRobberProblem {

    /**
     * Approach: Brute-force with recursion
     * 
     * Time Complexity: O(2^N) Where ‘n’ represents the total number of items
     * Space Complexity: O(N) space used by recursion stack
     * 
     * @param nums
     * @return
     */
    public static int robRecursive(int[] nums) {
        return robRecursiveHelper(nums, 0);
    }

    private static int robRecursiveHelper(int[] nums, int start) {
        //checking base cases
        if(nums.length == 0 || start >= nums.length) return 0;

        //including the current element
        int profitWithCurrent = nums[start] + robRecursiveHelper(nums, start + 2);

        //excluding the current element 
        int profitWithoutCurrent = robRecursiveHelper(nums, start + 1);

        //choosing the max profit
        return  Math.max(profitWithCurrent, profitWithoutCurrent);
    }

    /**
     * Approach: Bottom-Up Dynamic Programming 
     * 
     * Create an extra space dp, DP array to store the sub-problems.
     * Handle some basic cases:
     *   - if the length of the array is 0, return 0, 
     *   - if the length of the array is 1, print the first element, 
     *   - if the length of the array is 2, print maximum of two elements.
     * Update dp[0] as array[0] and dp[1] as maximum of array[0] and array[1]
     *  Traverse the array from the second element to the end of array.
     *  For every index, update dp[i] as maximum of dp[i-2] + array[i] and dp[i-1],
     *  this step defines the two cases, if an element is selected then the previous element 
     *  cannot be selected and if an element is not selected then the previous element can be
     *  selected.
     * 
     * Time Complexity: O(n). Only one traversal of original array is needed.
     * Space Complexity: O(n). An extra array is required of size n
     * @param nums
     * @return the maximum profit
     */
    public static int robBottomUP(int[] nums) {
        //basic cases
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[1]);

        int[] dp = new int[nums.length];

        //setting up initial values
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < dp.length; i++) {
            int profitWithCurrent = nums[i] + dp[i-2];
            int profitWithoutCurrent =  dp[i-1];
            dp[i] = Math.max(profitWithCurrent, profitWithoutCurrent);
        }
        return dp[dp.length-1];
    }

    /* 
    * Approach: Bottom-Up Dynamic Programming 
    * 
    * An enhanced method that uses no extra space.
    * Observing the method above, it can be seen that the values of previous two indices in dp[]
    * matter while calculating the value for an index. To replace the total DP array by
    * two variables.

    * Time Complexity: O(n). Only one traversal of original array is needed.
    * Space Complexity: O(1). 
    */
    public static int robBottomUPII(int[] nums) {
        int value1 = nums[0];
        int value2 = Math.max(nums[0],nums[1]);
        //basic cases
        if(nums.length == 0) return 0;
        if(nums.length == 1) return value1;
        if(nums.length == 2) return value2;

        int maxValue = 0;
        for (int i = 2; i < nums.length; i++) {
            maxValue = Math.max(value2, nums[i]+ value1);
            value1 = value2;
            value2 = maxValue;
        }
        return maxValue;
    }

    @Test
    public void validate() {
        Assert.assertEquals(12, robRecursive(new int[]{2,7,9,3,1}));
        Assert.assertEquals(4, robRecursive(new int[]{1,2,3,1}));
        Assert.assertEquals(19, robRecursive(new int[]{6, 7, 1, 3, 8, 2, 4}));

        Assert.assertEquals(12, robBottomUP(new int[]{2,7,9,3,1}));
        Assert.assertEquals(4, robBottomUP(new int[]{1,2,3,1}));
        Assert.assertEquals(19, robBottomUP(new int[]{6, 7, 1, 3, 8, 2, 4}));

        Assert.assertEquals(12, robBottomUPII(new int[]{2,7,9,3,1}));
        Assert.assertEquals(4, robBottomUPII(new int[]{1,2,3,1}));
        Assert.assertEquals(19, robBottomUPII(new int[]{6, 7, 1, 3, 8, 2, 4}));
    }
}