import org.junit.Assert;
import org.junit.Test;

/**
 * Leetcode: 33. Search in Rotated Sorted Array
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * 
 * You are given an integer array nums sorted in ascending order, and an integer target.
 * Suppose that nums is rotated at some pivot unknown to you beforehand 
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * If target is found in the array return its index, otherwise, return -1.
 * 
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * 
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 */
public class ArrayRotatedSearch {

    /**
     * Time Complexity: O(2*LogN) (find pivot : LogN + perform regular binary search logN)
     * Space Complexity: O(1)
     */
    public static int search(int[] arr, int key) {
        if(arr == null || arr.length == 0) return -1;

        int n = arr.length-1;
        int pivot = findPivot(arr, 0, n);
        if(key == arr[pivot]) return pivot;

        if(key >= arr[pivot] && key <= arr[n]) {
            return binarySearch(arr, key, pivot, n);
        } else{
            return binarySearch(arr, key, 0, pivot);
        }

    }

    /**
     * Find the element with small value.
     * 
     * A rotated array will always have a regular increasing part 
     */
    public static int findPivot(int[] arr, int start, int end) {
        while(start < end) {
            int mid = start + (end-start)/2;
            if(arr[mid] > arr[end]) { //"unusual" condition, the last element is <= then mid
                start = mid+1;
            } else { //its the "usual" conditation (the left part has values <= than mid)
                end = mid;
            }
        }
        return start;
    }

    private static int binarySearch(int[] arr, int key, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key == arr[mid]) {
                return mid;
            } else if (key > arr[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    /**
     * After calculating the middle, we can compare the numbers at indices start and middle.
     * This will give us two options:
     * 1 - If arr[start] <= arr[middle], the numbers from start to middle are sorted in
     *    ascending order.
     * 2- Else, the numbers from middle+1 to end are sorted in ascending order.
     * 
     * Once we know which part of the array is sorted, it is easy to adjust our ranges. 
     * For example, if option-1 is true, we have two choices:
     * 1- By comparing the ‘key’ with the numbers at index start and middle we can easily 
     * find out if the ‘key’ lies between indices start and middle; if it does, we can skip
     * the second part => end = middle -1.
     * 2- Else, we can skip the first part => start = middle + 1.
     * 
     * Time Complexity: O(LogN)
     * Space Complexity: O(1)
     */
    public static int searchII(int[] arr, int key) {
        int start = 0, end = arr.length - 1;
        while (start <= end) {
          int mid = start + (end - start) / 2;
          if (arr[mid] == key)
            return mid;
    
          if (arr[start] <= arr[mid]) { // left side is sorted in ascending order
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
        return -1;
      }
 
    @Test
    public void validate() {
        Assert.assertEquals(1, search(new int[] { 10, 15, 1, 3, 8 }, 15));
        Assert.assertEquals(4, search(new int[] { 4, 5, 7, 9, 10, -1, 2 }, 10));
        Assert.assertEquals(1, search(new int[] { 1, 3}, 3));
        Assert.assertEquals(1, search(new int[] { 1,3}, 3));

        Assert.assertEquals(1, searchII(new int[] { 10, 15, 1, 3, 8 }, 15));
        Assert.assertEquals(4, searchII(new int[] { 4, 5, 7, 9, 10, -1, 2 }, 10));
        Assert.assertEquals(1, searchII(new int[] { 1, 3}, 3));
        Assert.assertEquals(1, searchII(new int[] { 1,3}, 3));
    }

}
