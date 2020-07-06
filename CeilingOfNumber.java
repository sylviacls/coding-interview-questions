import org.junit.*;

/**
 * Given an array of numbers sorted in an ascending order, find the ceiling of a 
 * given number ‘key’. 
 * The ceiling of the ‘key’ will be the smallest element in the
 * given array greater than or equal to the ‘key’.
 * 
 * Write a function to return the index of the ceiling of the ‘key’. 
 * If there isn’t any ceiling return -1.
 * 
 * Example 1: 
 * Input: [4, 6, 10], 
 * key = 6
 * Output: 1
 * Explanation: The smallest number greater than or equal to '6' is '6' having index '1'.
 * 
 * Time Complexity: O(logN)
 * Space Complexity: O(1)
 */
public class CeilingOfNumber {

    /**
     * We will try to search for the ‘key’ in the given array. If we find the ‘key’, 
     * we return its index as the ceiling. If we can’t find the ‘key’, 
     * the next big number will be pointed out by the index start. 
     * @param list
     * @param key
     * @return
     */
    public static int searchCeilingOfANumber(int[] list, int key) {
        int begin = 0;
        int last = list.length - 1;

        if(key > list[last]) return -1;

        while (begin <= last) {
            int mid = begin + (last-begin)/2;
            //instead of (begin + last)/2 due to potencial integer overflow
            if(key == list[mid]) {
                return mid; //found the key
            } else if (key > list[mid]) {
                begin = mid + 1;
            } else  {
                last = mid - 1;
            } 
        }
        // since the loop is running until 'begin <= last', so at the end of the while loop,
        // 'begin == last+1'
        // we are not able to find the element in the given array, 
        //so the next big number will be arr[begin]
        return begin;
      }
    
      @Test
      public void validate() {
        Assert.assertEquals(1, searchCeilingOfANumber(new int[] { 4, 6, 10 }, 6));
        Assert.assertEquals(4, searchCeilingOfANumber(new int[] { 1, 3, 8, 10, 15 }, 12));
        Assert.assertEquals(-1, searchCeilingOfANumber(new int[] { 4, 6, 10 }, 17));
        Assert.assertEquals(0, searchCeilingOfANumber(new int[] { 4, 6, 10 }, -1));
      }
}