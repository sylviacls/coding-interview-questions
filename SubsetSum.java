import org.junit.*;

/**
 * Given a set of non-negative integers, and a value sum, determine if there is a subset of the
 *  given set with sum equal to given sum.
 * 
 * Example 1: 
 * Input: {1, 2, 3, 7}, S=6
 * Output: True
 * The given set has a subset whose sum is '6': {1, 2, 3}
 * 
 * Example 2: 
 * Input: {1, 2, 7, 1, 5}, S=10 
 * Output: True
 * The given set has a subset whose sum is '10': {1, 2, 7}
 * 
 * Example 3: 
 * Input: {1, 3, 4, 8}, S=6
 * Output: False
 * The given set does not have any subset whose sum is equal to '6'
 * 
 */
public class SubsetSum {    

    /**
     * Approach: Brute-Force using recursion and memoization
     * 
     * Try all subsets of the given numbers to see if any set has a sum equal to ‘S’.
     * 
     * @param nums the input
     * @param sum given sum
     * @return 
     */
    public static boolean hasSubset(int[] nums, int sum) {
        if(nums.length == 0) return false;
        Boolean[][] state = new Boolean[nums.length][sum+1];

        return hasSubsetRecursive(nums, sum, 0, state);
    }

    private static boolean hasSubsetRecursive(int[] nums, int sum, int index, Boolean[][] state) {
        //base cases
        if(sum == 0) return true;
        if(index >= nums.length) return false;
        
        //if this states has been processed, return it
        if(state[index][sum] != null) return state[index][sum];

        //including the current number if its value is <= current sum
        boolean sumWithCurrent = false;
        if(nums[index] <= sum) {
            sumWithCurrent = hasSubsetRecursive(nums, sum-nums[index], index+1, state);
        }
        //excluding the current number
        boolean sumWithoutCurrent = hasSubsetRecursive(nums, sum, index+1, state);

        state[index][sum] = sumWithCurrent || sumWithoutCurrent;
        return state[index][sum];
    }

    /**
     * Approach: Dynamic Programming Bottom-up  
     * 
     * We’ll try to find if we can make all possible sums with every subset to populate the
     * array state[totalNumbers][sum+1].
     * 
     * Time Complexity : O(N*S), where ‘N’ represents total numbers and ‘S’ is the given sum
     * Space Complexity: O(N*S)
     * 
     * @param nums the input
     * @param sum given sum
     * @return 
     */
    public static boolean hasSubsetII(int[] nums, int sum) {
        if(nums.length == 0) return false;
        Boolean[][] state = new Boolean[nums.length][sum+1];

        //for sum '0' we can have an empty set
        for (int i = 0; i < nums.length; i++) {
            state[i][0] = true;
        }
        //for the first num (index 0) we can make a subset with sum s, only if num == s
        for (int s = 1; s <= sum; s++) {
            if(nums[0] == s ) {
                state[0][s] = true;
            } else {
                state[0][s] = false;
            }
        }
        //processing all numbers
        for (int i = 1; i < state.length; i++) {
            for (int s = 1; s <= sum; s++) {
                //including current number if its value <= s :  In this case, we will see if we 
                // can find a subset to get the remaining sum => dp[index-1][s-num[index]]
                boolean sumWithCurrent = false;
                if(nums[i] <= s) {
                    sumWithCurrent = state[i-1][s-nums[i]];
                }
                //excluding current number: In this case, we will see if we can get the sum ‘s’ from 
                //the subset excluding this number => dp[index-1][s]
                boolean sumWithoutCurrent = state[i-1][s];

                state[i][s] = sumWithCurrent || sumWithoutCurrent;
            }
        }
        return state[nums.length-1][sum];
    }

    @Test
    public void validate() {
        int[] num = { 1, 2, 3, 7 };
        Assert.assertTrue(hasSubset(num, 6));
        Assert.assertTrue(hasSubsetII(num, 6));

        num = new int[] { 1, 2, 7, 1, 5 };
        Assert.assertTrue(hasSubset(num, 10));
        Assert.assertTrue(hasSubsetII(num, 10));

        num = new int[] { 1, 3, 4, 8 };
        Assert.assertFalse(hasSubset(num, 6));
        Assert.assertFalse(hasSubsetII(num, 6));

        num = new int[]{3, 34, 4, 12, 5, 2};
        Assert.assertTrue(hasSubset(num, 9));
        Assert.assertTrue(hasSubsetII(num, 9));
    }
    
}
