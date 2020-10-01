import org.junit.*;

/**
 * Leetcode: 34. Find First and Last Position of Element in Sorted Array
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * 
 * Given an array of numbers sorted in ascending order, find the range of a given number ‘key’.
 * The range of the ‘key’ will be the first and last position of the ‘key’ in the array.
 * 
 * Write a function to return the range of the ‘key’. If the ‘key’ is not present return [-1, -1]
 * Example 1:
 * Input: [4, 6, 6, 6, 9], 
 * key = 6
 * Output: [1, 3]
 *
 */
public class NumberFindFirstLastPosition {

    /**
     * Approach: Binary Search
     * 
     * Time Complexity: O(logN)
     * Space Complexity: O(1)
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1,-1};
        result[0] = findFirstPosition(nums, target);
        if(result[0] != -1) {
          result[1] = findLastPosition(nums, target);
        } 
        return result;
    }

    private static int findFirstPosition(int[] nums, int target) {
        int start = 0;
        int end= nums.length-1;
        int firstPosition = -1;
        while(start <= end) {
          int mid = start +(end-start)/2;
          if(target == nums[mid]) {
            firstPosition = mid;
            end = mid - 1;
          } else if (target < nums[mid]){
            end = mid - 1;
          } else {
            start = mid + 1;
          }
        }
        return firstPosition;
    }
    
    private static int findLastPosition(int[] nums, int target) {
      int start = 0;
      int end= nums.length-1;
      int lastPosition = -1;
      while(start <= end) {
        int mid = start +(end-start)/2;
        if(target == nums[mid]) {
          lastPosition = mid;
          start = mid + 1;
        } else if (target < nums[mid]){
          end = mid - 1;
        } else {
          start = mid + 1;
        }
      }
      return lastPosition;
    }


  /**
   * Approach: Binary Search
   * 
   * We will try to search for the ‘key’ in the given array; if the ‘key’ is found
   * (i.e. key == arr[middle) we have two options: 1- When trying to find the
   * first position of the ‘key’, we can update end = middle - 1 to see if the key
   * is present before middle. 2- When trying to find the last position of the
   * ‘key’, we can update start = middle + 1 to see if the key is present after
   * middle.
   * 
   * In both cases, we will keep track of the last position where we found the
   * ‘key’. These positions will be the required range.
   * 
   * Time Complexity: O(logN) Space Complexity: O(1)
   */
    public static int[] searchRangeII(int[] arr, int key) {
      int[] result = new int[] { -1, -1 };
      result[0] = findIndex(arr, key, false);

      if(result[0] != -1) { // no need to search, if 'key' is not present in the input array
        result[1] = findIndex(arr, key, true);
      } else {
        result[1] = result[0];
      }
      return result;
    }
    /**
     * It uses binary search.
     * Once we find the key (at mid), we search in both sides (left and right)
     * to find if there are duplicates and then we need to store the first and last index.
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
        int[] result = searchRange(new int[] { 4, 6, 6, 6, 9 }, 6);
        Assert.assertArrayEquals(new int[]{1,3}, result);

        result = searchRange(new int[] { 1, 3, 8, 10, 15 }, 10);
        Assert.assertArrayEquals(new int[]{3,3}, result);

        result = searchRange(new int[] { 1, 3, 8, 10, 15 }, 12);
        Assert.assertArrayEquals(new int[]{-1,-1}, result);
      }
}