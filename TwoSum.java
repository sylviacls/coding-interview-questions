import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

/**
 * Leetcode: 1. Two Sum
 * 
 * Given an array of integers (UNSORTED), return indices of the two numbers such that they add up to a 
 * specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same
 * element twice.
 */
public class TwoSum {

    /**
     * Approach: Searching the complement with a HashMap
     * 
     * Time complexity O(N) : We traverse the list containing nn elements exactly twice.
     *                      Since the hash table reduces the look up time to O(1), the time complexity is O(n).
     * Space complexity O(N): he extra space required depends on the number of items stored in the hash table, which stores exactly nn elements.
     *
     * */
    public static int[] checkSum(int[] input, int target) {
        //storing the number and its index
        HashMap<Integer, Integer> map = new HashMap<Integer,Integer>();
        for (int i = 0; i < input.length; i++) {
            map.put(input[i], i);
        }

        //for each number, check if its complement exists
        for (int i = 0; i < input.length; i++) {
            int complement = target - input[i];
            //checking if the complement found is a different number (!= index)
            if(map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        return new int[]{-1,-1};
    }
    
    @Test
    public void validate() {
        int[] expected = new int[]{1,3};
        int[] returned = checkSum(new int[] {1, 2, 3, 4, 6}, 6);
        Assert.assertArrayEquals(expected, returned);

        int[] expected2 = new int[]{0,2};
        int[] returned2 = checkSum(new int[] {2,5,9,11}, 11);
        Assert.assertArrayEquals(expected2, returned2);    

        int[] expected3 = new int[]{1,2};
        int[] returned3 = checkSum(new int[] {3,2,4}, 6);
        Assert.assertArrayEquals(expected3, returned3);    
    
    }
}