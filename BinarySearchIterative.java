import java.util.Arrays;
import org.junit.*;

/**
 * Leetcode: 704. Binary Search
 * https://leetcode.com/problems/binary-search/
 * 
 * Given a sorted (in ascending order) integer array nums of n elements and a target value,
 * write a function to search target in nums. If target exists, then return its index, 
 * otherwise return -1.
 * 
 * Example 1:
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * 
 * Example 2:
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 */
public class BinarySearchIterative {

    /**
     * Time Complexity: O(Log N)
     * Space Complexity: O(1)
     */
    public static int binarySearch(int[] list, int number) {
        int begin = 0;
        int last = list.length - 1;

        while (begin <= last) {
            int mid = begin + (last-begin)/2;
            //instead of (begin + last)/2 due to potencial integer overflow
            if (number > list[mid]) {
                begin = mid + 1;
            } else if (number < list[mid]) {
                last = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    @Test
    public void validate() {

        int[] list = new int[] { 7, 10, 43, 22, 16, 9, 25 };
        Arrays.sort(list);
        System.out.printf("Sorted list = %s", Arrays.toString(list));

        Assert.assertEquals(-1, binarySearch(list, 12));
        Assert.assertEquals(1, binarySearch(list, 9));

    }
}