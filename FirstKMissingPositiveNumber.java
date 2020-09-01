import java.util.*;
import org.junit.*;

/**
 * Given an unsorted array containing numbers and a number ‘k’, find the first
 * ‘k’ missing positive numbers in the array.
 * 
 * Example 1: Input: [3, -1, 4, 5, 5], k=3 Output: [1, 2, 6] Explanation: The
 * smallest missing positive numbers are 1, 2 and 6.
 * 
 * Example 2: Input: [2, 3, 4], k=3 Output: [1, 5, 6] Explanation: The smallest
 * missing positive numbers are 1, 5 and 6.
 * 
 * Example 3: Input: [-2, -3, 4], k=2 Output: [1, 2] Explanation: The smallest
 * missing positive numbers are 1 and 2.
 */
public class FirstKMissingPositiveNumber {

    /**
     * Approach: Cyclic Sort + HashSet
     * 
     * Time Complexity: O(n + k)
     * Space Complexity: O(k), for extraNumbers
     * 
     */
    public static List<Integer> findNumbers(int[] nums, int k) {
        List<Integer> missingNumbers = new ArrayList<Integer>();
        Set<Integer> extraNumbers = new HashSet<>();

        //perfor cyclic sort ignoring negative and out-of-range (length) numbers
        int start = 0;
        while(start < nums.length) {
            if(nums[start] > 0 && nums[start] <= nums.length && nums[start] != nums[nums[start]-1]) {
                swap(nums,start, nums[start]-1);
            } else {
                start++;
            }
        }
        // iterate the array and the indexes that do not have the correct number are missing numbers
        int i = 0;
        while(i < nums.length && k > 0) {
            if(nums[i] != i + 1) {
                missingNumbers.add(i+1);
                extraNumbers.add(nums[i]);
                k--;
            }
            i++;
        }
        //If we are not able to find ‘k’ missing numbers from the array, we need to add additional
        // numbers to the output array. To find these additional numbers we will use the length 
        //of the array. One tricky aspect is that any of these additional numbers could be part 
        //of the array. Remember, while sorting, we ignored all numbers that are greater than or 
        //equal to the length of the array. That's the reason of using extraNumbers Set
        while (k > 0) {
            int candidateNumber = i + 1;
            if(!extraNumbers.contains(candidateNumber)) {
                missingNumbers.add(candidateNumber);
                k--;
            }
            i++;
        }
        return missingNumbers;
      }

      private static void swap(int[] nums, int i, int j) {
          int temp = nums[i];
          nums[i] = nums[j];
          nums[j] = temp;
      }

      
  @Test    
  public void validate() {
    Assert.assertEquals(Arrays.asList(1,2,6), findNumbers(new int[] { 3, -1, 4, 5, 5 }, 3));
    Assert.assertEquals(Arrays.asList(1,5,6), findNumbers(new int[] { 2, 3, 4 }, 3));
    Assert.assertEquals(Arrays.asList(1,2), findNumbers(new int[] { -2, -3, 4 }, 2));
  }
}