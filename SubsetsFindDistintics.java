import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.*;

/**
 * Leetcode: 90. Subsets II
 * https://leetcode.com/problems/subsets-ii/
 * 
 * Given a set of numbers that might contain duplicates, find all of its distinct subsets.
 * 
 * Ex.
 * Input: [1, 3, 3]
 * Output: [], [1], [3], [1,3], [3,3], [1,3,3]
 * 
 */
public class SubsetsFindDistintics {

    /**
     * Approach: Backtracking
     * 
     * Driver-method 
     */
    public static List<List<Integer>> findSubsetsBack(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // This will ensure that all duplicate numbers are next to each other.
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }
    /**
     * The main idea is to simulate the insertion and exclusion of each number in each subset
     * using a backtracking approach.
     * 
     * Time Complexity: O(N* 2^N) : Since, in each step, the number of subsets doubles (if not duplicate)
     *                           as we add each element to all the existing subsets, therefore,
     *                           we will have a total of O(2^N) subsets, where ‘N’ is the total number of 
     *                           elements in the input set. And since we construct a new subset from an 
     *                          existing set, therefore, the time complexity of the above algorithm will be
     *                          O(N*2^N)
     * Space Complexity: O(2^N)
     * 
     * Leetcode: Runtime 1 ms	Memory 39.4 MB
     * 
     * @param list the result list
     * @param tempList the current subset
     * @param nums the input
     * @param start the start index
     */
    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start){
        //we add the current subset into the result
        list.add(new ArrayList<>(tempList));
        //and then we'll go through the rest of the numbers simulating adding the current number "i"
        // and move forward in the list and once the recursive function returns we are gonna
        //simulating removing it
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
            //we make a choice to insert the current number
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            //we make a choice not including the current number
            tempList.remove(tempList.size() - 1);
        }
    } 

    /**
     * Approach: Iterative
     * 
     * 	Leetcode: Runtime 1 ms	Memory 39.8 MB
     */
    public static List<List<Integer>> findSubsetsDistincts(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        if(nums == null) return subsets;

        // This will ensure that all duplicate numbers are next to each other.
        Arrays.sort(nums);
        subsets.add(new ArrayList<Integer>()); //adding the first empty set

        //startIndex and endIndex will handle the duplicate cases which 
        //we have to access previous subset
        int startIndex = 0;
        int endIndex = 0;

        for (int i = 0; i <nums.length; i++) {
            startIndex = 0;
            // if current and the previous elements are same, create new subsets only 
            // from the subsets added in the previous step
            if(i > 0 && nums[i] == nums[i-1]) {
                //out startindex will be the last index from current subset
                startIndex = endIndex + 1;
            }
            
            //endIndex will always store the last index before we start to create new subsets
            //for the current number
            endIndex = subsets.size()-1;
            for (int j = startIndex; j <= endIndex; j++) {
                List<Integer> newSet = new ArrayList<Integer>(subsets.get(j));
                newSet.add(nums[i]);
                subsets.add(newSet);
            }
   
        }
        return subsets;
      }

      @Test
      public void validate() {
        List<List<Integer>> result = SubsetsFindDistintics.findSubsetsDistincts(new int[] { 1, 3, 3 });
        String expected = "[[], [1], [3], [1, 3], [3, 3], [1, 3, 3]]";
        Assert.assertEquals(expected, result.toString());
    
        result = SubsetsFindDistintics.findSubsetsDistincts(new int[] { 1, 5, 3, 3 });
        expected = "[[], [1], [3], [1, 3], [3, 3], [1, 3, 3], [5], [1, 5], [3, 5], [1, 3, 5], [3, 3, 5], [1, 3, 3, 5]]";
        Assert.assertEquals(expected, result.toString());
      }

      public static void main(String[] args) {
          System.out.println(SubsetsFindDistintics.findSubsetsDistincts(new int[] { 1, 2, 2 }));
      }
}