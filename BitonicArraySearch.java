import org.junit.*;

/**
 * Given a Bitonic array, find if a given ‘key’ is present in it. 
 * An array is considered bitonic if it is monotonically increasing and then monotonically
 * decreasing. Monotonically increasing or decreasing means that for any index i in the
 * array arr[i] != arr[i+1].
 * 
 * Write a function to return the index of the ‘key’. If the ‘key’ is not present, return -1.
 * 
 * Example 1:
 * Input: [1, 3, 8, 4, 3], key=4
 * Output: 3
 * 
 * Example 2:
 * Input: [3, 8, 3, 1], key=8
 * Output: 1
 */
public class BitonicArraySearch {
    /**
     * Approach: Binary Search
     * 
     * Time Complexity: O(logN)
     * Space Complexity: O(1)
     */
    public static int search(int[] arr, int key) {
        int maxIndex = findMaxValueIndex(arr, 0, arr.length-1);
        if(key == arr[maxIndex]) {
          return maxIndex;
        } 
        int index = binarySearch(arr, key, maxIndex+1, arr.length-1);
        if(index != -1) 
          return index;
        else 
          return binarySearch(arr, key, 0, maxIndex-1);
        
      }
    
      private static int binarySearch(int[] arr, int key, int start, int end) {
        while(start <= end) {
          int mid = start + (end-start)/2;
          if(key == arr[mid]) {
            return mid;
          } else if(key > arr[mid]) {
            start = mid + 1;
          } else {
            end = mid - 1;
          }
        }
        return -1;
      }

      public static int findMaxValueIndex(int[] arr, int start, int end) {
        if (start == end) return start;

        int mid = start + (end-start)/2;
        if (arr[mid] > arr[mid+1]) 
          return findMaxValueIndex(arr, start, mid);
        else
          return findMaxValueIndex(arr, mid+1, end);
      }

      @Test
      public void validate() {
        Assert.assertEquals(3, search(new int[] { 1, 3, 8, 4, 3 }, 4));
        Assert.assertEquals(1, search(new int[] { 3, 8, 3, 1 }, 8));
        Assert.assertEquals(3, search(new int[] { 1, 3, 8, 12 }, 12));
        Assert.assertEquals(0, search(new int[] { 10, 9, 8 }, 10)); 
      }
}
