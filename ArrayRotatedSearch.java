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
 
    @Test
    public void validate() {
        Assert.assertEquals(1, search(new int[] { 10, 15, 1, 3, 8 }, 15));
        Assert.assertEquals(4, search(new int[] { 4, 5, 7, 9, 10, -1, 2 }, 10));
        Assert.assertEquals(1, search(new int[] { 1, 3}, 3));
        Assert.assertEquals(1, search(new int[] { 1,3}, 3));
    }

}
