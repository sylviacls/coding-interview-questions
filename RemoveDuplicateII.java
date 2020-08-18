import org.junit.*;

/**
 * Leetcode: 80. Remove Duplicates from Sorted Array II
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 * 
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at
 * most twice and return the new length.
 * 
 * Do not allocate extra space for another array, you must do this by modifying the input array
 * in-place with O(1) extra memory.
 * 
 * Example 1:
 * Given nums = [1,1,1,2,2,3],
 * Your function should return length = 5, with the first five elements of nums being
 * 1, 1, 2, 2 and 3 respectively.
 * 
 * Example 2:
 * Given nums = [0,0,1,1,1,1,2,3,3],
 * Your function should return length = 7, with the first seven elements of nums being modified
 * to 0, 0, 1, 1, 2, 3 and 3 respectively.
 */
public class RemoveDuplicateII {
    /**
     * Approach: Two Pointers
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static int removeDuplicates(int[] nums) {
        int nextNumberAllowed = 1;
        int countDuplicate = 1;

        for (int i = 0; i < nums.length - 1; i++) {
            if(nums[i] == nums [i+1]) { //if numbers are equal, increase the count
                countDuplicate++;
            } else { // else reset the count
                countDuplicate = 1;
            }
            if(countDuplicate <= 2) { 
            //if count is not greater than 2, then place the next number at the next allowed index
                nums[nextNumberAllowed] = nums[i+1];
                nextNumberAllowed++;
            }
        }
        return nextNumberAllowed;
    }
    @Test
    public void validate() {
        Assert.assertEquals(5, removeDuplicates(new int[]{1,1,1,2,2,3}));
        Assert.assertEquals(7, removeDuplicates(new int[]{0,0,1,1,1,1,2,3,3}));
    }
}