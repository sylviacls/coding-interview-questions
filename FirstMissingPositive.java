import java.util.Arrays;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given an unsorted array containing numbers, 
 * find the smallest missing positive number in it.
 * 
 * Time Complexity: O(n)
 * Space complexity: O(1) 
 */
public class FirstMissingPositive {

    /**
     * Input: [-3, 1, 5, 4, 2]
     * Output: 3
     */
    public static int firstMissingPositive(int[] input) {
        int i = 0;
        while(i < input.length) {
            if(input[i] >0 && input[i] <= input.length 
                && input[i] != input[input[i]-1]) {
                swap(input, i, input[i]-1);
            } else {
                i++;
            }
        }
        for (int j = 0; j < input.length; j++) {
            if(input[j] != j+1) {
                return j+1;
            }
        }
        return -1;
    }

    
    private static void swap(int[] input,int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    //this approach uses Hash
    // may take O(n) time on average, but it requires O(n) extra space
    public static int firstMissingPositiveWithHash(int[] input) {
        HashMap<Integer, Integer> map = new HashMap<Integer,Integer>();
        int maxValue = 1;
        for (int i = 0; i < input.length; i++) {
            if(input[i] > 0) {
                map.put(input[i], input[i]);
                if(input[i] > maxValue) {
                    maxValue = input[i];
                }
            }
        }
        for (int i = 1; i <= maxValue + 1; i++) {
            if(!map.containsKey(i)) {
                return i;
            }
        }
        return maxValue;
    }

    //this approach takes O(nLogn + n) for sorting time which is O(nLogn).
    public static int firstMissingPositiveWithSort(int[] input) {              
        Arrays.sort(input);
        int minPositive = 1;
        for (int i = 0; i < input.length; i++) {
            if(input[i] == minPositive) {
                minPositive++;
            }
        }
        return minPositive; 
    }

    @Test
    public void validate() {
        Assert.assertEquals(2,firstMissingPositive(new int[]{1, 1, 0, -1, -2}));
        Assert.assertEquals(4,firstMissingPositive(new int[]{2, 3, -7, 6, 8, 1, -10, 15 }));
        Assert.assertEquals(3, FirstMissingPositive.firstMissingPositive(new int[] { -3, 1, 5, 4, 2 }));
        Assert.assertEquals(4,FirstMissingPositive.firstMissingPositive(new int[] { 3, -2, 0, 1, 2 }));
        Assert.assertEquals(4,FirstMissingPositive.firstMissingPositive(new int[] { 3, 2, 5, 1 }));
    }
}