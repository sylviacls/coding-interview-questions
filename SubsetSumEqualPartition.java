import java.util.Arrays;
import org.junit.*;

/**
 * Given a set of positive numbers, find if we can partition it into two subsets such that
 *  the sum of elements in both subsets is equal. 
 * 
 * Example 1:
 * Input: {1, 2, 3, 4}
 * Output: True
 * Explanation: The given set can be partitioned into two subsets with equal sum: 
 * {1, 4} & {2, 3}
 * 
 * Example 2:
 * Input: {1, 1, 3, 4, 7}
 * Output: True
 * Explanation: The given set can be partitioned into two subsets with equal sum:
 * {1, 3, 4} & {1, 7}
 * 
 * 
 * Two main steps to solve this problem:
 * 1) Calculate sum of the array. If sum is odd, there can not be two subsets with equal sum, 
 *    so return false.
 * 2) If sum of array elements is even, calculate sum/2 and find a subset of array with sum
 *    equal to sum/2.
 *    This step can be solved either using recursion or Dynamic Programming.
 */
public class SubsetSumEqualPartition {

    /**
     * Approaach: Brute-Force with Recursion and memoization
     * 
     * Time Complexity: O(2^N)
     * Space Complexity: O(N), for the recursion stack
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        //base cases
        // if 'sum' is a an odd number, we can't have two subsets with equal sum
        if(nums.length <= 1 || sum%2 != 0) return false;

        Boolean[][] state = new Boolean[nums.length][sum/2 + 1];
        return canPartitionHelper(nums, 0, sum/2, state);
    }

    private static boolean canPartitionHelper(int[] nums, int index, int sum, Boolean[][] state) {
        //base cases
        if(sum == 0) return true;
        if(index >= nums.length || nums[index] > sum) return false;

        //if we have had processed this state before, return it
        if(state[index][sum] != null) return state[index][sum];

        boolean sumWithCurrent = canPartitionHelper(nums, index+1, sum - nums[index], state);
        boolean sumWithoutCurrent = canPartitionHelper(nums, index+1, sum, state);

        state[index][sum] = sumWithCurrent || sumWithoutCurrent;
        return state[index][sum];
    }

    /**
     * Approach: Bottom-Up Dynamic Programming
     * 
     * Essentially, we want to find if we can make all possible sums with every subset. 
     * This means, dp[i][s] will be ‘true’ if we can make the sum ‘s’ from the first ‘i’ numbers.
     * 
     * Time Complexity : O(N*S), where ‘N’ represents total numbers and ‘S’ is the total sum/2 
     *                  of all the numbers.
     * Space Complexity: O(N*S)
     * @param nums
     * @return
     */
    public static boolean canPartitionBottomUp(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        //base cases
        // if 'sum' is a an odd number, we can't have two subsets with equal sum
        if(nums.length <= 1 || sum%2 != 0) return false;

        sum /= 2;
        Boolean dp[][] = new Boolean[nums.length][sum+1];

        // populate the sum=0 columns, as we can always have '0' sum with an empty set
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }
        //with only '1' number we can form a subset only when the required sum is equal to its value
        for (int s = 1; s <= sum; s++) {
            if(nums[0] == s) {
                dp[0][s] = true;
            } else{
                dp[0][s] = false;            
            }
        }  
        // process all subsets for all sums   
        //dp[i][s] will be ‘true’ if we can make the sum ‘s’ from the first ‘i’ numbers.
        for (int i = 1; i < dp.length; i++) {
            for (int s = 1; s <= sum; s++) {
               // Including the number if its value is not more than ‘s’. In this case, we will see if 
               //we can find a subset to get the remaining sum: dp[i-1][s-num[i]]
               boolean sumWithCurrent = false;
               if(nums[i] <= s) {
                  sumWithCurrent = dp[i-1][s-nums[i]];
               }
               // Excluding the number. In this case, we will see if we can get ‘s’ from the subset 
               //excluding this number: dp[i-1][s]
               boolean sumWithoutCurrent = dp[i-1][s];

               dp[i][s] = sumWithCurrent || sumWithoutCurrent;
            }
        } 
        return dp[nums.length-1][sum];
    }

    @Test
    public void validate() {
        int[] num = {1, 2, 3, 4};
        Assert.assertTrue(canPartition(num));
        Assert.assertTrue(canPartitionBottomUp(num));       

        num = new int[]{1, 1, 3, 4, 7};
        Assert.assertTrue(canPartition(num));
        Assert.assertTrue(canPartitionBottomUp(num));  

        num = new int[]{2, 3, 4, 6};
        Assert.assertFalse(canPartition(num));
        Assert.assertFalse(canPartitionBottomUp(num));  

        num = new int[]{3, 1, 5, 9, 12};
        Assert.assertTrue(canPartition(num));
        Assert.assertTrue(canPartitionBottomUp(num));  
        
        num = new int[]{3, 10, 2, 1, 18};
        Assert.assertFalse(canPartition(num));
        Assert.assertFalse(canPartitionBottomUp(num));  

    }
}