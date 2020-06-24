import java.util.Arrays;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

public class TwoSum {

    /**
     * Time complexity O(N)
     * Space complexity O(N)
     * */
    public static Boolean checkSumUnsortedArray(int[] input, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer,Integer>();
        for (int i = 0; i < input.length; i++) {
            map.put(input[i], input[i]);
        }

        for (int i = 0; i < input.length; i++) {
            int complement = target - input[i];
            if(map.containsKey(complement)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * Time complexity O(n log n) for sorting + 
     * Space complexity O(1)
     * */
    public static int[] checkSumSortedArray (int[] input, int target) {
        Arrays.sort(input);
        int start = 0;
        int end = input.length -1;

        while(start < end) {
            if(input[start] + input[end] == target)
                return new int[] {start, end};
            
            if(input[start] + input[end] < target) {
                start++;
            } else {
                end--;
            }
        }

        return new int[] {-1,-1};
    }
    
    @Test
    public void validate() {
        int[] expected = new int[]{1,3};
        int[] returned = checkSumSortedArray(new int[] {1, 2, 3, 4, 6}, 6);
        Assert.assertArrayEquals(expected, returned);

        int[] expected2 = new int[]{0,2};
        int[] returned2 = checkSumSortedArray(new int[] {2,5,9,11}, 11);
        Assert.assertArrayEquals(expected2, returned2);    
    
    }
}