import org.junit.Assert;
import org.junit.Test;

/**
 * Given an array containing 0s, 1s and 2s, sort the array in-place. You should
 * treat numbers of the array as objects, hence, we can’t count 0s, 1s, and 2s
 * to recreate the array.
 * 
 * The flag of the Netherlands consists of three colors: red, white and blue;
 * and since our input array also consists of three different numbers that is
 * why it is called Dutch National Flag problem
 * 
 * Time Complexity: O(n) as we are iterating the input array only once.
 * Space Complexity: O(1)
 */
public class DutchNacionalFlag {
         //      l  i  h
    //Input: [0, 1, 0, 1, 2]
    //Output: [0 0 1 1 2]
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
             //   i++;
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