import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Leetcode: 287. Find the Duplicate Number
 * 
 * We are given an unsorted array containing ‘n+1’ numbers taken from the range 1 to ‘n’.
 * The array has only one duplicate but it can be repeated multiple times. 
 * Find that duplicate number without using any extra space. 
 * You are, however, allowed to modify the input array.
 * 
 * Adding more difficulties:
 * 
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 * 
 * Example 1:
 * Input: [1,3,4,2,2]
 * Output: 2
 * 
 * Example 2:
 * Input: [3,1,3,4,2]
 * Output: 3
 */
public class DuplicateNumberFindOne {

    /**
     * Approach: Using HashSet
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int findDuplicateI(int[] nums) {
        Set<Integer> seen = new HashSet<Integer>();
        for (int num : nums) {
            if(!seen.contains(num)) {
                seen.add(num);
            } else {
                return num;
            }
        }
        return -1;
    }

    /**
     * Approach: Two Pointers - slow/fast
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public static int findDuplicateII(int[] nums) {
        if(nums.length <= 1) return -1;

        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast); //cycle found

        //calculate the cycle length
        int length = 0;
        int pointer = nums[slow];
        do {
            length++;
            pointer = nums[pointer];
        } while (pointer != nums[slow]);

        //find the start node/number
        int p1 = nums[0];
        int p2 = nums[0];
        // move pointer2 ahead 'cycleLength' steps
        while (length > 0) {
            p2 = nums[p2];
            length--;
        }        
        // increment both pointers until they meet at the start of the cycle
        while(p1 != p2){
            p1 = nums[p1];
            p2 = nums[p2];
        }
        return p1;
    }
    
    /**
     * Approach: Cyclic Sort - Modifing the input array
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public static int findDuplicateIII(int[] input) {

        int i = 0;
        while (i < input.length) {
            if(input[i] != i+1 ){
                if(input[input[i]-1] != input[i]) {
                    swap(input, i, input[i]-1);
                } else {
                    return input[i];
                }
            } 
            else {
                i++;
            }
        }
        return -1;  
    }

    private static void swap(int[] input,int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    @Test
    public void validate() {
        Assert.assertEquals(4,findDuplicateII(new int[] { 1, 4, 4, 3, 2 }));
        Assert.assertEquals(3,findDuplicateII(new int[] { 2, 1, 3, 3, 5, 4 }));
        Assert.assertEquals(4,findDuplicateII(new int[] { 2, 4, 1, 4, 4 }));
        Assert.assertEquals(3,findDuplicateII(new int[] { 3, 1, 3, 4, 2 }));
      }
}