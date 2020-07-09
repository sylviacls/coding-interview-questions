import org.junit.Assert;
import org.junit.Test;

/**
 * Given an array of numbers sorted in ascending order, find the element in the array that 
 * has the minimum difference with the given ‘key’.
 * 
 * Input: [4, 6, 10], key = 7
 * Output: 6
 * 
 * Input: [4, 6, 10], key = 4
 * Output: 4
 * 
 * Input: [4, 6, 10], key = 17
 * Output: 10
 * 
 * Input: [1, 3, 8, 10, 15], key = 12
 * Output: 10
 * 
 * Time Complexity: O(Log N)
 * Space Complexity: O(1)
 */
public class MinimumDifferenceElement {

    public static int searchMinDiffElement(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;
        //handling the edge-cases
        if (key > arr[end]) {
            return arr[end];
        } else if (key < arr[start]) {
            return arr[start];
        }
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key == arr[mid]) {
                return arr[mid];
            } else if (key < arr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        //if key is not found, at the end of the loop start = end + 1.
        // and start will be point to the element smaller than key
        // and end will be point to the element greater than key
        //then we have to find the smallest difference between key/arr[start] and key/[end]
        return ((key-arr[start]) < (end-arr[start]))? arr[start]: arr[end];
    }
  
    @Test
    public void validate() {
      Assert.assertEquals(6, searchMinDiffElement(new int[] { 4, 6, 10 }, 7));
      Assert.assertEquals(4,searchMinDiffElement(new int[] { 4, 6, 10 }, 4));
      Assert.assertEquals(10,searchMinDiffElement(new int[] { 1, 3, 8, 10, 15 }, 12));
      Assert.assertEquals(10,searchMinDiffElement(new int[] { 4, 6, 10 }, 17));
    }
  }