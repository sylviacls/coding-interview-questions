import org.junit.Assert;
import org.junit.Test;

/**
 * We are given an unsorted array containing ‘n+1’ numbers taken from the range 1 to ‘n’.
 * The array has only one duplicate but it can be repeated multiple times. 
 * Find that duplicate number without using any extra space. 
 * You are, however, allowed to modify the input array.
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 */
public class DuplicateNumberFindOne {
    
    /**
     * Input: [1, 4, 4, 3, 2]
     * Output: 4
     */
    public static int findDuplicate(int[] input) {

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
        Assert.assertEquals(4,DuplicateNumberFindOne.findDuplicate(new int[] { 1, 4, 4, 3, 2 }));
        Assert.assertEquals(3,DuplicateNumberFindOne.findDuplicate(new int[] { 2, 1, 3, 3, 5, 4 }));
        Assert.assertEquals(4,DuplicateNumberFindOne.findDuplicate(new int[] { 2, 4, 1, 4, 4 }));
      }
}