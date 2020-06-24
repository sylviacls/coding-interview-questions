import org.junit.Assert;
import org.junit.Test;

/**
 * We are given an unsorted array containing ‘n’ numbers taken from the range 1 to ‘n’. 
 * The array originally contained all the numbers from 1 to ‘n’, but due to a data error,
 *  one of the numbers got duplicated which also resulted in one number going missing.
 *  Find both these numbers.
 * 
 * Time Complexity: O(n)
 * Space complexity: O(1) 
 */
public class CorruptPair {
    
    /**
     * Input: [3, 1, 2, 5, 2]
     * Output: [2, 4]
     * Explanation: '2' is duplicated and '4' is missing.
     */
    public static int[] findCorruptPair(int[] input){

        if(input == null || input.length <1) return new int[]{-1,-1};

        int i = 0;
        while(i < input.length) {
            if(input[i] != input[input[i]-1]) {
                swap(input, i, input[i]-1);
            } else {
                i++;
            }
        }

        for (int j = 0; j < input.length; j++) {
            if(input[j] != j+1) {
                return new int[]{input[j], j+1};
            }
        }

        return new int[]{-1,-1};
    }

    private static void swap(int[] input,int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    @Test
    public void validate(){
        int[] nums = CorruptPair.findCorruptPair(new int[] { 3, 1, 2, 5, 2 });
        Assert.assertArrayEquals(new int[]{2,4}, nums);
        
        nums = CorruptPair.findCorruptPair(new int[] { 3, 1, 2, 3, 6, 4 });
        Assert.assertArrayEquals(new int[]{3,5}, nums);
    }
}