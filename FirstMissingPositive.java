import java.util.Arrays;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

/**
 * Leetcode: 41. First Missing Positive
 * 
 * Given an unsorted array containing numbers, find the smallest missing positive 
 * number in it.
 * 
 * Example 1:
 * Input: [1,2,0]
 * Output: 3
 * 
 * Example 2:
 * Input: [3,4,-1,1]
 * Output: 2
 * 
 * Example 3:
 * Input: [7,8,9,11,12]
 * Output: 1
 * 
 */
public class FirstMissingPositive {

    /**
     * Approach: Cyclic Sort
     * 
     * Place the numbers on their correct indices and ignore all numbers that are out of 
     * the range of the array (i.e., all negative numbers and all numbers greater than 
     * or equal to the length of the array). Once we are done with the cyclic sort we 
     * will iterate the array and the first index that does not have the correct number 
     * will be the smallest missing positive number.
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(1)
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
        // once we have ignored all numbers that are out of the range of the array
        //and we reach this point, thus it means the array contains all numbers 1..n
        return input.length + 1;
    }

    
    private static void swap(int[] input,int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    /**
     * Approach: Using Hash
     * 
     * We can build a hash table of all positive elements in the given array. Once the hash
     * table is built. We can look in the hash table for all positive integers, starting
     * from 1. As soon as we find a number which is not there in hash table, we return it.
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     * 
     */
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

    /**
     * Approach: Sorting
     * 
     * Time Complexity: (nLogn + n) for sorting time which is O(nLogn).
     * Space Complexity: O(1) (if the sort method is in-place)
     */
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
        Assert.assertEquals(2, firstMissingPositive(new int[]{1, 1, 0, -1, -2}));
        Assert.assertEquals(2, firstMissingPositiveWithHash(new int[]{1, 1, 0, -1, -2}));
        Assert.assertEquals(2, firstMissingPositiveWithSort(new int[]{1, 1, 0, -1, -2}));

        Assert.assertEquals(4, firstMissingPositive(new int[]{2, 3, -7, 6, 8, 1, -10, 15 }));
        Assert.assertEquals(4, firstMissingPositiveWithHash(new int[]{2, 3, -7, 6, 8, 1, -10, 15 }));
        Assert.assertEquals(4, firstMissingPositiveWithSort(new int[]{2, 3, -7, 6, 8, 1, -10, 15 }));

        Assert.assertEquals(3, firstMissingPositive(new int[] { -3, 1, 5, 4, 2 }));
        Assert.assertEquals(3, firstMissingPositiveWithHash(new int[] { -3, 1, 5, 4, 2 }));
        Assert.assertEquals(3, firstMissingPositiveWithSort(new int[] { -3, 1, 5, 4, 2 }));

        Assert.assertEquals(4, firstMissingPositive(new int[] { 3, -2, 0, 1, 2 }));
        Assert.assertEquals(4, firstMissingPositiveWithHash(new int[] { 3, -2, 0, 1, 2 }));
        Assert.assertEquals(4, firstMissingPositiveWithSort(new int[] { 3, -2, 0, 1, 2 }));

        Assert.assertEquals(4, firstMissingPositive(new int[] { 3, 2, 5, 1 }));
        Assert.assertEquals(4, firstMissingPositiveWithHash(new int[] { 3, 2, 5, 1 }));
        Assert.assertEquals(4, firstMissingPositiveWithSort(new int[] { 3, 2, 5, 1 }));

        Assert.assertEquals(1, firstMissingPositive(new int[] { }));
        Assert.assertEquals(1, firstMissingPositiveWithHash(new int[] { }));
        Assert.assertEquals(1, firstMissingPositiveWithSort(new int[] { }));
   
    }
}