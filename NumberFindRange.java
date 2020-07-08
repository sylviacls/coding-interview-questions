import org.junit.*;

/**
 * Given an array of numbers sorted in ascending order, find the range of a given number ‘key’.
 *  The range of the ‘key’ will be the first and last position of the ‘key’ in the array.
 * 
 * Write a function to return the range of the ‘key’. If the ‘key’ is not present return [-1, -1]
 * Example 1:
 * Input: [4, 6, 6, 6, 9], 
 * key = 6
 * Output: [1, 3]
 * 
 * Time Complexity: O(logN)
 * Space Complexity: O(1)
 */
public class NumberFindRange {

    public static int[] findRange(int[] arr, int key) {
      int[] result = new int[] { -1, -1 };
      result[0] = findIndex(arr, key, false);

      if(result[0] != -1) {
        result[1] = findIndex(arr, key, true);
      } else {
        result[1] = result[0];
      }
      return result;
    }
    /**
     * It uses binary search.
     *  Once we find the key (at mid), we search in both sides (left and right)
     *  to find if there are duplicates and then we need to store the first and last index.
     * @param arr
     * @param key
     * @return
     */
    public static int findIndex(int[] arr, int key, boolean findLast) {
        int start = 0;
        int end = arr.length-1;
        int keyIndex = -1;
        while(start <= end) {
          int mid = start + (end-start)/2; //to avoid a potencial integer overflow
          if(key < arr[mid]) {
            end = mid-1;
          } else if (key > arr[mid]) {
            start = mid+1;
          } else { // key == arr[mid]
              keyIndex = mid;
              //we have to search for duplicate values looking for the first and last index of them.
              if(!findLast) { //searching for any duplicate in left-side (first index)
                end = mid-1;
              } else {  //searching for any duplicate in right-side (last index)
                start = mid+1;
              }
          }
        }
        return keyIndex;
      }
    
      @Test
      public void validate() {
        int[] result = findRange(new int[] { 4, 6, 6, 6, 9 }, 6);
        Assert.assertArrayEquals(new int[]{1,3}, result);

        result = findRange(new int[] { 1, 3, 8, 10, 15 }, 10);
        Assert.assertArrayEquals(new int[]{3,3}, result);

        result = findRange(new int[] { 1, 3, 8, 10, 15 }, 12);
        Assert.assertArrayEquals(new int[]{-1,-1}, result);
      }
}