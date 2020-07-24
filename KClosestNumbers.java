import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a sorted number array and two integers ‘K’ and ‘X’, find ‘K’ closest
 * numbers to ‘X’ in the array. Return the numbers in the sorted order. ‘X’ is
 * not necessarily present in the array.
 * 
 * Example 1:
 * 
 * Input: [5, 6, 7, 8, 9], K = 3, X = 7 Output: [6, 7, 8]
 * 
 * Example 2: Input: [2, 4, 5, 6, 9], K = 3, X = 10 Output: [5, 6, 9]
 * 
 * https://leetcode.com/problems/find-k-closest-elements/discuss/?currentPage=1&orderBy=most_votes&query=
 */
public class KClosestNumbers {

    /**
     * Approach: Binary Search 
     * 
     * If the target x is less or equal than the first element in the sorted array,
     *  the first k elements are the result.
     * Similarly, if the target x is more or equal than the last element in the sorted array,
     *  the last k elements are the result
     * Otherwise, we can use binary search to find the index of the element, which is equal 
     * (when this list has x) or a little bit larger than x (when this list does not have it). 
     * 
     * Time Complexity: O(logN)
     * @param nums
     * @param K
     * @param X
     * @return
     */
    public static List<Integer> findClosestElements(int[] nums, int k, int x) {

        if (x <= nums[0]) {
            int[] result = Arrays.copyOfRange(nums, 0, k);
            return Arrays.stream(result).boxed().collect(Collectors.toList());
        }

        if (x >= nums[nums.length-1]) {
            int[] result = Arrays.copyOfRange(nums, nums.length-k, nums.length);
            return Arrays.stream(result).boxed().collect(Collectors.toList());
        }

        int index = binarySearch(nums, x);
        //we will start our 'window' with the closest elements with 1
        //and then we will expande it
        int low = index;
        int high = index;
        //we keep iterating ad expanding the window while the number of the windows 
        // is less than k and avoiding getting out of bounds 
        while(high - low + 1 < k && low > 0 && high < nums.length-1) {
            if(Math.abs(nums[low-1] - x) <= Math.abs(nums[high+1] - x)) {
                low--;
            } else {
                high++;
            }
        }

        //we have to check if we have enough elements (k)
        while (high - low + 1 < k) {
            if(low > 0) {
                low--;
            } else {
                high++;
            }
        }

        return Arrays.stream(nums, low, high+1).boxed().collect(Collectors.toList());
    }


    /**
     * Binary Search that keeps track of the "closest" element to target
     * @param nums
     * @param target
     * @return
     */
    private static int binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;
    
        int closest = Integer.MAX_VALUE;
        int minDiff = Integer.MAX_VALUE;

        while(start <= end) {
            int mid = end + (start-end)/2;

            if(Math.abs(nums[mid] - target) <= minDiff ) {
                //this will guarantee the min index will be stored
                if(Math.abs(nums[mid] - target) == minDiff ) {
                    closest = Math.min(mid, closest);
                    //TODO FIX BUG!
                } else {
                    closest = mid;
                }
                minDiff = Math.abs(nums[mid] - target);
            }
            if(target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return closest;

    }

    public static void main(String[] args) {
        List<Integer> result = findClosestElements(new int[] { 5, 6, 7, 8, 9 }, 3, 7);
        System.out.println("'K' closest numbers to 'X' are: " + result);
    
        result = findClosestElements(new int[] { 2, 4, 5, 6, 9 }, 3, 6);
        System.out.println("'K' closest numbers to 'X' are: " + result);
    
        result = findClosestElements(new int[] { 2, 4, 5, 6, 9 }, 3, 10);
        System.out.println("'K' closest numbers to 'X' are: " + result);

        result = findClosestElements(new int[] {1,2,3,4,5 }, 4, 3);
        System.out.println("'K' closest numbers to 'X' are: " + result);

        result = findClosestElements(new int[] {0,1,1,1,2,3,6,7,8,9 }, 9, 4);
        System.out.println("'K' closest numbers to 'X' are: " + result);

        result = findClosestElements(new int[] {1,3}, 1, 2);
        System.out.println("'K' closest numbers to 'X' are: " + result);

        result = findClosestElements(new int[] {0,0,0,1,3,5,6,7,8,8}, 2, 2);
        System.out.println("'K' closest numbers to 'X' are: " + result);
    }
}