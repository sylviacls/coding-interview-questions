import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.*;

/**
 * In a non-empty array of numbers, every number appears exactly twice except
 * two numbers that appear only once. Find the two numbers that appear only
 * once.
 * 
 * Example 1: Input: [1, 4, 2, 1, 3, 5, 6, 2, 3, 5] Output: [4, 6]
 */

public class FindTwoSingleNumbers {

    /**
     * Let a and b be the two unique numbers
     * XORing all numbers gets you (a xor b)
     * (a xor b) must be non-zero otherwise they are equal
     * if a bit in (a xor b) is ‘1’, this means that a and b have different bits in that place
     * We can take any bit which is ‘1’ in (a xor b) and partition all numbers in the given array
     *   into two groups based on that bit.
     * Find bit_i using the low bit formula m & -m 
     *      Find any bit which is set in (a xor b). We can take the rightmost bit which is ‘1’.
     * Partition the numbers into two groups: one group with bit_i == 1 
     *   and the other group with bit_i == 0. 
     * a is in one group and b is in the other. 
     * a is the only single number in its group. 
     * b is also the only single number in its group. 
     * XORing all numbers in a's group to get a 
     * XORing all numbers in b's group to get b
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     * @param nums
     * @return
     */
    public static int[] findSingleNumbersXor(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor =  xor ^ num;
        }
        // find the lowest bit of the result
        // let's say 6 (0110), -6 = 1010  0110 & 1010 = 0010
        int lowBit = xor & -xor;

        int n1 = 0;
        int n2 = 0;
        // since this bit from the result is 1, we can be sure that 
        // a & lowbit and b & lowbit have different result
        for (int num : nums) {
            if( (num & lowBit) == 0) { //  the bit is not set 
                n1 = n1 ^ num;
            } else {
                // the bit is set
                n2 = n2 ^num;
            }
        }
        return new int[]{n1,n2};
    }

    /**
     * Using a hashmap to store the single numbers
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     * @param nums
     * @return
     */
    public static int[] findSingleNumbers(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer,Integer>();
        for (int num : nums) {
            if(map.containsKey(num)) {
                map.remove(num);
            } else {
                map.put(num, num);
            }
        }

        Iterator<Integer> ite = map.keySet().iterator();
        int n1 = ite.next();
        int n2 = ite.next();
        return new int[] {n1,n2};
        
    }

    @Test
    public void validate() {
        int[] arr = new int[] { 1, 4, 2, 1, 3, 5, 6, 2, 3, 5 };
        int[] expected = new int[]{4,6};

        int[] result = findSingleNumbersXor(arr);
        Assert.assertArrayEquals(expected, result);

        result = findSingleNumbers(arr);
        Assert.assertArrayEquals(expected, result);

        arr = new int[] { 2, 1, 3, 2 };
        expected = new int[]{1,3};

        result = findSingleNumbersXor(arr);
        Assert.assertArrayEquals(expected, result);

        result = findSingleNumbers(arr);
        Assert.assertArrayEquals(expected, result);
    }
}