import java.util.*;
import org.junit.*;

/**
 * Leetcode: 15. 3Sum
 * 
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * 
 * The solution set must not contain duplicate triplets.
 * Triplet must be in ascending order. (i.e. a ≤ b ≤ c)
 * 
 * Example:
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * A solution set is:[
 * [-1, 0, 1],
 *  [-1, -1, 2]
 * ]
 */

public class TripletSumToZero {

    /**
     * Approach: Two Problem
     * 
     * First, we sort the array, so we can easily move i around and know how to adjust l and r.
     * If the number is the same as the number before, we have used it as a target already, continue.
     * We always start the left pointer from i+1 because the combination of 0~i has already been tried.]
     * Now we calculate the total:
     * If the total is less than zero, we need it to be larger, so we move the left pointer.
     * If the total is greater than zero, we need it to be smaller, so we move the right pointer.
     * If the total is zero, then add it to our result
     * We need to move the left and right pointers to the next different numbers, so we do not
     * get repeating result.
     * We do not need to consider i after arr[i] > 0, since the sum of 3 positive will be always 
     * greater than zero.
     * We do not need to try the last two since there are no rooms for l and r pointers.
     * You can think of it as the last two have been tried by all others.
     * 
     * Time Complexity: O(N) Sorting the array will take O(N∗logN). The twoSum() function will
     *               take O(N)O(N). As we are calling searchPair() for every number in the input
     *               array, this means that overall twoSum() will take O(N * logN + N^2)
     *              which is asymptotically equivalent to O(N^2)
     * Space Complexity: O(N)
     */
    public static List<List<Integer>> threeSum(int[] nums) {
 
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        //we have to go untill length-2, cause we need to have option to left and right
        for (int i = 0; i < nums.length-2; i++) {
            //We do not need to consider i after num[i] > 0, since the sum of 3 positive will be always greater than zero.
            if(nums[i] > 0) break;
            //if(i > 0 && nums[i] == nums[i-1]) continue;
            if(i == 0 || nums[i] != nums[i-1]){
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (sum == 0) {
                        ArrayList<Integer> triplet = new ArrayList<>();
                        triplet.add(nums[i]);
                        triplet.add(nums[left]);
                        triplet.add(nums[right]);
                        result.add(triplet);
                        // We need to move the left and right pointers to the next different numbers,
                        // so we do not get repeating result.
                        while (left < right && nums[left] == nums[left + 1])
                            left++; // skip same element to avoid duplicate triplets
                        while (left < right && nums[right] == nums[right - 1])
                            right--; // skip same element to avoid duplicate triplets
                        left++;
                        right--;
                    } else if (sum > 0) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return result;    
    }

   /**
    * Approach: Only for non duplicate values
    */
    public static List<List<Integer>> threeSumNoDuplicates(int[] input) {
        Arrays.sort(input);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < input.length - 2; i++) {
            int start = i + 1;
            int end = input.length - 1;

            while (start < end) {
                if (input[i] + input[start] + input[end] == 0) {
                    ArrayList<Integer> triplet = new ArrayList<>();
                    triplet.add(input[i]);
                    triplet.add(input[start]);
                    triplet.add(input[end]);
                    result.add(triplet);
                    start++;
                    end--;
                } else if (input[i] + input[start] + input[end] < 0) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-3, 0, 1, 2, -1, 1, -2}));
        System.out.println(threeSum(new int[]{0, -1, 2, -3, 1}));
        System.out.println(threeSum(new int[]{-5, 2, -1, -2, 3}));   
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));   
        System.out.println(threeSum(new int[]{-2,0,0,2,2}));    
    }
}