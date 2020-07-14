import java.util.HashMap;
import java.util.Map;

import org.junit.*;

/**
 * In a non-empty array of integers, every number appears twice except for one,
 *  find that single number.
 * 
 * Example 1: 
 * Input: 1, 4, 2, 1, 3, 2, 3
 * Output: 4
 */
public class FindSingleNumber {

    /**
     * One straight forward solution can be to use a HashMap kind of data structure 
     * and iterate through the input
     * If number is already present in HashMap, remove it. 
     * If number is not present in HashMap, add it.
     * In the end, only number left in the HashMap is our required single number.
     * 
     * Time complexity: O(N)
     * Space complexity: O(N)
     * @param arr input
     * @return the single number
     */
    public static int findSingleNumber(int[] arr) {
        Map<Integer, Integer> map = new HashMap<Integer,Integer>();
        for (int i : arr) {
            if(map.containsKey(i)) {
                map.remove(i);
            } else {
                map.put(i, i);
            }
        }
            return map.keySet().iterator().next();
      }

      /**
       * Properties of XOR:
       * It returns zero if we take XOR of two same numbers. 
       * It returns the same number if we XOR with zero. 
       * So we can XOR all the numbers in the input; duplicate numbers will zero 
       * out each other and we will be left with the single number.
       * 
       * Time complexity: O(N)
       * Space complexity: O(1)
       * @param arr
       * @return
       */
      public static int findSingleNumberXOR(int[] arr) {
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor = xor ^ arr[i];
        }
        return xor;
      }
    
      @Test
      public void validate() {
        Assert.assertEquals(4, findSingleNumber(new int[]{1, 4, 2, 1, 3, 2, 3}));
        Assert.assertEquals(2, findSingleNumber(new int[]{1, 2, 3, 3, 4, 1, 4}));

        Assert.assertEquals(4, findSingleNumberXOR(new int[]{1, 4, 2, 1, 3, 2, 3}));
        Assert.assertEquals(2, findSingleNumberXOR(new int[]{1, 2, 3, 3, 4, 1, 4}));
      }
    
}