import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * Leetcode:31. Next Permutation
 * https://leetcode.com/problems/next-permutation/
 * 
 * Implement next permutation, which rearranges numbers into the lexicographically next 
 * greater permutation of numbers.
 * 
 * If such arrangement is not possible, it must rearrange it as the lowest possible order
 * (ie, sorted in ascending order).
 * 
 * The replacement must be in-place and use only constant extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs
 * are in the right-hand column.
 * 
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class PermutationSetFindNext {
    
    /**
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static void nextPermutation(int[] nums) {
        int i;
        int n = nums.length;
        // Start from the right most digit and find the first digit that is smaller  
        // than the digit next to it. (at its right will be a decreasing sequence)
        //the decreasing sequence is at its "max value" permutation
        for (i = n-2; i >= 0; i--) {
            if(nums[i] < nums[i+1])
                break;
        }
        //If no such digit is found, then all digits are in descending order means  
        // there cannot be a greater number with  same set of digits 
        if( i < 0) { 
            Arrays.sort(nums);
            return;
        } else {
        //Find the smallest digit on right side of (i-1)'th digit that is greater than number[i-1] 
        //once the right sequence is at its "max value", we can start search from its end
        //and the first value found will be the min value of this sequence
            int j;
           for (j = n-1; j > i; j--) {
                if(nums[j] > nums[i])
                    break;
            }
            swap(nums, i, j);

            //Reverse the sub-array nums[j + 1, so the right sequence stays at its "min value" permutation
            Arrays.sort(nums, i+1, n);
        }

    }

	private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;

    }
    
    @Test
    public void validate() {
        int[] input = new int[]{1,3,2};
        nextPermutation(input);
        Assert.assertArrayEquals(new int[]{2,1,3}, input);
    }
}

