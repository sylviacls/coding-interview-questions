import org.junit.*;

/**
 * Given an infinite sorted array (or an array with unknown size), find if a given number 
 * ‘key’ is present in the array. Write a function to return the index of the ‘key’ if it 
 * is present in the array, otherwise return -1.
 * 
 * Since it is not possible to define an array with infinite (unknown) size, you will be 
 * provided with an interface ArrayReader to read elements of the array. 
 * ArrayReader.get(index) will return the number at index; if the array’s size is smaller 
 * than the index, it will return Integer.MAX_VALUE.
 * 
 * Example 1:
 * Input: [4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30], key = 16
 * Output: 6
 * Explanation: The key is present at index '6' in the array.
 */
public class InfiniteArraySearchNumber {

    /**
     * Approach: Binary Search
     * 
     * If the array is infinite, that means we don’t have proper bounds to apply binary search. 
     * So in order to find position of key, first we find bounds.
     * To find the boundaries:
     * An efficient way to find the proper bounds is to start at the beginning of the array 
     * with the bound’s size as ‘1’ and exponentially increase the bound’s size 
     * (i.e., double it) until we find the bounds that can have the key.
     * 
     * Time Complexity: O(Log N): In the first part, we keep increasing the bound’s size exponentially
     *                           (double it every time) while searching for the proper bounds. 
     *                           Therefore, this step will take O(logN).  In the second step, 
     *                           we perform the binary search which will take O(logN).
     *                           Thus, the overall time complexity is O(logN+logN) which is
     *                           asymptotically equivalent to O(logN)
     * Space Complexity: O(1)
     */
    public static int search(ArrayReader reader, int key) {

        int low = 0;
        int high = 1;
        while(key > reader.get(high)) {
            int newLow = high + 1;
            high += (high - low + 1) * 2;
            low = newLow;
        }

        return binarySearch(reader, key, low, high);
      }
    
      private static int binarySearch(ArrayReader reader, int key, int low, int high) {
        int index = -1;
        if(low <= high) {
            int mid = low + (high-low)/2;
            if(key == reader.get(mid)) {
                index = mid;
            } else if(key < reader.get(mid)) {
                index = binarySearch(reader, key, low, mid-1);
            } else {
                index = binarySearch(reader, key, mid+1, high);
            }
        }
        return index;
    }

    @Test
    public void validate() {
        ArrayReader reader = new ArrayReader(new int[] { 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30 });
        Assert.assertEquals(6, search(reader, 16));
        Assert.assertEquals(-1, search(reader, 11));

        reader = new ArrayReader(new int[] { 1, 3, 8, 10, 15 });
        Assert.assertEquals(4, search(reader, 15));
        Assert.assertEquals(-1, search(reader, 200));
      }
    
}

class ArrayReader {
  int[] arr;

  ArrayReader(int[] arr) {
    this.arr = arr;
  }

  public int get(int index) {
    if (index >= arr.length)
      return Integer.MAX_VALUE;
    return arr[index];
  }
}
