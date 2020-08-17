import org.junit.Assert;
import org.junit.Test;

/**
 * Leetcode: 26. Remove Duplicates from Sorted Array
 * 
 * Given an array of sorted numbers, remove all duplicates from it. 
 * You should not use any extra space; after removing the duplicates in-place return the
 * new length of the array.
 * 
 */
public class RemoveDuplicate {

    /**
     * Approach: Two Pointers
     * 
     * We will keep one pointer for iterating the array and one pointer for placing the next 
     * non-duplicate number. So our algorithm will be to iterate the array and whenever we see 
     * a non-duplicate number we move it next to the last non-duplicate number we’ve seen.
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     * 
     */
    public static int removeDuplicate(int[] input) {
        int nextNonDuplicate = 1;
        for (int i = 0; i < input.length -1; i++) {
            if(input[i] != input[i+1]) {
                input[nextNonDuplicate] = input[i+1];
                nextNonDuplicate++;
            }     
        }
        return nextNonDuplicate;
    }
 
    @Test
    public void validate() {
        int returned1 =  removeDuplicate(new int[]{2, 3, 3, 3, 6, 9, 9});
        Assert.assertEquals(4,returned1);

        int returned2 =  removeDuplicate(new int[]{2, 2,2,11});
        Assert.assertEquals(2,returned2);
    }
}