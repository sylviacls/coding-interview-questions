import org.junit.Assert;
import org.junit.Test;

/**
 * Leetcode: 75. Sort Colors
 * 
 * "Given an array with n objects colored red, white or blue, sort them in-place so that 
 * objects of the same color are adjacent, with the colors in the order red, white and blue."
 * 
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue 
 * respectively.
 * 
 * The flag of the Netherlands consists of three colors: red, white and blue;
 * and since our input array also consists of three different numbers that is
 * why it is called Dutch National Flag problem
 * 
 */
public class DutchNacionalFlag {

    /**
     * Approach: Two Pointers
     * 
     * Time Complexity: O(n) as we are iterating the input array only once.
     * Space Complexity: O(1)
     * 
     * @param input unsorrted array
     * @return sorted array
     */
    public static int[] sortArrayOf012(int[] input) {
        int low= 0;
        int high = input.length-1;
        
        int i = 0;
        while (i <= high) {
            if(input[i] == 0) {
                swap(input, i, low);
                i++;
                low++;
            } else if (input[i] == 1) {
                i++;
            } else{ 
                swap(input, i, high);
             // decrement 'high' only, after the swap the number at index 'i' could be 0, 1 or 2
                high--;
            }
        }
        return input;
    }

    private static void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] =  temp;
    }

    @Test
    public void validate() {
        int[] result = sortArrayOf012(new int[]{1, 0, 2, 1, 0});
        int[] expected = new int[]{0, 0, 1, 1, 2};

        int[] result2 = sortArrayOf012(new int[]{2, 2, 0, 1, 2, 0});
        int[] expected2 = new int[]{0, 0, 1, 2, 2, 2};

        Assert.assertArrayEquals(expected, result);
        Assert.assertArrayEquals(expected2, result2);
    }
    
}