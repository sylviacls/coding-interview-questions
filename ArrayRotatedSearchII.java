import org.junit.*;

/**
 * Leetcode: 81. Search in Rotated Sorted Array II
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 * 
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you
 * beforehand. (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]). 
 * 
 * You are given a target value to search. If found in the array return true, otherwise 
 * return false.
 * 
 * Example 1:
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * 
 * Example 2:
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 * 
 * Follow up:
 * This is a follow up problem to Search in Rotated Sorted Array, where nums may contain
 * duplicates. Would this affect the run-time complexity? How and why?
 */
public class ArrayRotatedSearchII {

    /**
     * Similiar Approach as ArrayRotatedSearch
     * 
     * The only problematic scenario is when the numbers at indices start, middle, and end
     * are the same, as in this case, we can’t decide which part of the array is sorted. 
     * In such a case, the best we can do is to skip one number from both ends:
     * start = start + 1 & end = end - 1.
     * 
     * Time Complexity: O(LogN)
     * Space Complexity: O(1)
     */
    public static boolean search(int[] arr, int key) {
        int start = 0, end = arr.length - 1;
        while (start <= end) {
          int mid = start + (end - start) / 2;
          if (arr[mid] == key)
            return true;
    
          // the only difference from the previous solution,
          // if numbers at indexes start, mid, and end are same, we can't choose a side
          // the best we can do, is to skip one number from both ends as key != arr[mid]
          if ((arr[start] == arr[mid]) && (arr[end] == arr[mid])) {
            ++start;
            --end;
          } else if (arr[start] <= arr[mid]) { // left side is sorted in ascending order
            if (key >= arr[start] && key < arr[mid]) {
              end = mid - 1;
            } else { //key > arr[mid]
              start = mid + 1;
            }
          } else { // right side is sorted in ascending order
            if (key > arr[mid] && key <= arr[end]) {
              start = mid + 1;
            } else {
              end = mid - 1;
            }
          }
        }
    
        // we are not able to find the element in the given array
        return false;
      }

    @Test
    public void validate() {
        Assert.assertFalse(search(new int[]{2,5,6,0,0,1,2}, 3));
        Assert.assertTrue(search(new int[]{3,1}, 3));
        Assert.assertTrue(search(new int[]{3,1,1}, 3));
    }
}
