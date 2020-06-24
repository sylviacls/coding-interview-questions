import org.junit.Assert;
import org.junit.Test;

/**
 * Given an unsorted array of numbers and a target ‘key’, 
 * remove all instances of ‘key’ in-place and return the new length of the array.
 * Time complexity O(n)
 * Space complexity O(1) in-place
 */
public class RemoveTargetFromArray {
    public static int removeTargetFromArray(int[] input, int target) {
        int count = 0;
        for (int i = 0; i < input.length; i++) {
            if(input[i] != target) {
                input[count] = input[i];
                count++;
            }             
        }

        return count;
    }

    @Test
    public void validate() {
        Assert.assertEquals(4, removeTargetFromArray(new int[]{3, 2, 3, 6, 3, 10, 9, 3}, 3));
        Assert.assertEquals(2, removeTargetFromArray(new int[]{2, 11, 2, 2, 1}, 2));
    }
    
}