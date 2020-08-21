import java.util.Arrays;

import org.junit.*;

/**
 * Leetcode: 16. 3Sum Closest 
 * 
 * Given an array of unsorted numbers and a target number, find a triplet in the array 
 * whose sum is as close to the target number as possible, return the sum of the triplet.
 * If there are more than one such triplet, return the sum of the triplet with the smallest sum.
 * 
 * You may assume that each input would have exactly one solution.
 * 
 * Example 1:
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * 
 * Example 2:
 * Input: [-2, 0, 1, 2], target=2
 * Output: 1
 * Explanation: The triplet [-2, 1, 2] has the closest sum to the target.
 */
public class TripletSumCloseTarget {
    /**
     * Approach: Two Pointers
     * 
     * Time complexity: O(N^2): Sorting the array takes O(nlogn) + two inner lops O(N^2)
     *                          so overall complexity O(nlogm + n^2), This is asymptotically 
     *                          equivalent to O(N^2). This is asymptotically equivalent 
     *                          to O(N^2).
     * Space Complexity: O(1).
     */
    public static int tripletSumCloseTarget(int[] input, int target) {

        Arrays.sort(input); 
        int smallestDiff = Integer.MAX_VALUE;
        int smallestSum = 0;
        for (int i = 0; i < input.length-2; i++) {
            int left = i + 1;
            int right = input.length - 1;
            while (left < right) {
                // comparing the sum of three numbers to the 'targetSum' can cause overflow
                // so, we will try to find a target difference
                int currSum = input[i] + input[left] + input[right];
                if (currSum == target) //  we've found a triplet with an exact sum
                    return currSum; 
                
                if(Math.abs(target - currSum) < Math.abs(smallestDiff)){
                    smallestDiff = target - currSum;
                    smallestSum = currSum;
                }
                if (currSum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return smallestSum;
    }

    @Test
    public void validate() {
        Assert.assertEquals(2, tripletSumCloseTarget(new int[]{-1, 2, 1, -4}, 1));        
        Assert.assertEquals(1, tripletSumCloseTarget(new int[]{-2, 0, 1, 2}, 2));
        Assert.assertEquals(0, tripletSumCloseTarget(new int[]{-3, -1, 1, 2},1));
        Assert.assertEquals(-2, tripletSumCloseTarget(new int[]{-3,-2,-5,3,-4},-1));
    }
}