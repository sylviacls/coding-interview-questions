import java.util.Arrays;

/**
 * Leetcode 259: Three sum smaller  https://leetcode.com/problems/3sum-smaller/
 * 
 * Given an array arr of unsorted numbers and a target sum, count all triplets in it such that
 * arr[i] + arr[j] + arr[k] < target where i, j, and k are three different indices. 
 * 
 * Write a function to return the count of such triplets.
 * 
 * For example, given nums = [-2, 0, 1, 3], and target = 2.
 * Return 2. Because there are two triplets which sums are less than 2:
 * [-2, 0, 1]
 * [-2, 0, 3]
 * 
 */
public class TripletSmallestSum {

    /**
     * Approach: Two Pointers
     *  
     * Time complexity: O(N^2): Sorting the array takes O(nlogn) + two inner lops O(N^2)
     *                          so overall complexity O(nlogm + n^2), This is asymptotically 
     *                          equivalent to O(N^2). This is asymptotically equivalent 
     *                          to O(N^2).
     * Space Complexity: O(N): ) which is required for sorting if we are not using an in-place sorting algorithm.
     * 
     */
    public static int threeSumSmaller(int[] nums, int target) {
        int countTriplets = 0;
        Arrays.sort(nums); //-1,0,2,3
        for (int i = 0; i < nums.length-2; i++) {
            //if the curr number is >= than target, thus there will be no triplet possible for curr
            //and subsequent numbers in a sorted array
            if(nums[i] >= target) return countTriplets;

            int left = i+1;
            int right = nums.length -1;
            while(left < right) {
                int currSum = nums[i] + nums[left] + nums[right];
                if(currSum < target) {
                    // since nums[right] >= nums[left], therefore, we can replace nums[right] 
                    //by any number between left and right to get a sum less than the target sum
                    countTriplets += (right - left);
                     left++;
                 /* int tempRight = right-1;
                    while(left < tempRight && nums[i] + nums[left] + nums[tempRight] < target) {
                        countTriplets++;
                        tempRight--;
                    }
                    left++; */
                } else {
                // If sum of current triplet is more or equal,move right corner to look for smaller values
                    right--;
                }
            }       
        }
        return countTriplets;
    }

    public static void main(String[] args) {
        System.out.println(threeSumSmaller(new int[] { -1, 0, 2, 3 }, 3));
        System.out.println(threeSumSmaller(new int[] { -1, 4, 2, 1, 3 }, 5));
    }
    
}