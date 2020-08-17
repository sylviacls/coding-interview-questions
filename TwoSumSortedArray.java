import org.junit.*;
import java.util.*;

/**
 * Given an array of sorted numbers and a target sum, find a pair in the array
 * whose sum is equal to the given target.
 * 
 * Write a function to return the indices of the two numbers (i.e. the pair)
 * such that they add up to the given target.
 */
public class TwoSumSortedArray {

    /**
     * Approach: Two Pointers
     * 
     * Start with one pointer pointing to the beginning of the array and another pointing at the 
     * end. At every step, we will see if the numbers pointed by the two pointers add up to the
     * target sum. If they do, we have found our pair; otherwise, we will do one of two things:
     * 
     * If the sum of the two numbers pointed by the two pointers is greater than the target sum, 
     * this means that we need a pair with a smaller sum (decrement end-pointer)
     * 
     * If the sum of the two numbers pointed by the two pointers is smaller than the target sum, 
     * this means that we need a pair with a larger sum. (increment start-pointer)
     * 
     * Time complexity O(N) 
     * Space complexity O(1)
     * 
     * */
    public static int[] checkSum(int[] input, int target) {
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

    /**
     * Approach: Searching for complement using a Hashmap
     * 
     * Time complexity O(N) : We traverse the list containing nn elements exactly twice.
     *                      Since the hash table reduces the look up time to O(1), the time complexity is O(n).
     * Space complexity O(N): he extra space required depends on the number of items stored in the hash table, 
     *                      which stores exactly nn elements.
     */
    public static int[] checkSumII(int[] input, int target) {
         // to store numbers and their indices
        HashMap<Integer, Integer> nums = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
          if (nums.containsKey(target - input[i]))
            return new int[] { nums.get(target - input[i]), i };
          else
            nums.put(input[i], i); // put the number and its index in the map
        }
        return new int[] { -1, -1 }; // pair not found
      }

    @Test
    public void validate() {
        int[] expected = new int[]{1,3};
        Assert.assertArrayEquals(expected, checkSum(new int[] {1, 2, 3, 4, 6}, 6));
        Assert.assertArrayEquals(expected, checkSumII(new int[] {1, 2, 3, 4, 6}, 6));

        int[] expected2 = new int[]{0,2};
        Assert.assertArrayEquals(expected2, checkSum(new int[] {2,5,9,11}, 11));    
        Assert.assertArrayEquals(expected2, checkSumII(new int[] {2,5,9,11}, 11));    

        int[] expected3 = new int[]{0,2};
        Assert.assertArrayEquals(expected3, checkSum(new int[] {2,3,4}, 6));    
        Assert.assertArrayEquals(expected3, checkSumII(new int[] {2,3,4}, 6));    
    }
}