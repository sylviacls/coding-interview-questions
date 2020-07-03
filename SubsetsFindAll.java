import java.util.ArrayList;
import java.util.List;

import org.junit.*;

/**
 * Given a set with distinct elements, find all of its distinct subsets.
 * Ex:
 * Input: [1, 5, 3]
 * Output: [], [1], [5], [3], [1,5], [1,3], [5,3], [1,5,3]
 * 
 * To generate all subsets of the given set, we can use the Breadth First Search (BFS) approach. 
 * We can start with an empty set, iterate through all numbers one-by-one, and add them to 
 * existing sets to create new subsets.
 * 
 * Time complexity: O(2^N): the number of subsets doubles as we add each element to all the 
 *                  existing subsets, the time complexity of the above algorithm
 * Space complexity: O(2^N): All the additional space used by our algorithm is for the output 
 *                    list, and we will have a total of O(2^N) subsets
 * 
 * Backtracking/Recursion is about following a path to a base case...our target...our answer.
 * If a certain path ends up not meeting our constraints we will backtrack to an earlier 
 * state and try something else from there.
 * 
 * The 3 Keys To Backtracking Problems: 
 * Our Choice
 * -) What choice are we making at each call of the function
 * -) RECURSION REPRESENTS A DECISION.
 * -) RECURSION REPRESENTS A CHOICE & its associated state
 * -) Each function call represents a state. From that state decisions can be made.
 * 
 * Our Constraints
 * -) What tells us to stop following a certain path that we are searching on?
 * -) Have we exhausted all possibilities?
 * 
 * Our Goal
 * -) What is our target?
 * -) What are we trying to find?
 * -) These will craft our base cases.
 */
public class SubsetsFindAll {

    /**
     * Driver-method - it uses a backtracking function
     * @param nums
     * @return
     */
    public static List<List<Integer>> findSubsetsBack(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }
    /**
     * The main idea is that for each number we will make a choice about adding it or not in
     * the current subset 
     * Time complexity: O(2^N), cause for every number we have to simulute choosing it and not choosing it
     * Space complexity: O(N)
     * @param list
     * @param currentSet
     * @param nums
     * @param start
     */
    private static void backtrack(List<List<Integer>> list , List<Integer> currentSet, int [] nums, int start){
        //we add whatever our currentSet
        list.add(new ArrayList<>(currentSet));
        //and then we'll go through the rest of the numbers simulating adding the current number "i"
        // and move forward in the list and once the recursive function returns we are gonna
        //simulating removing it
        for(int i = start; i < nums.length; i++){
            currentSet.add(nums[i]);
            backtrack(list, currentSet, nums, i + 1);
            currentSet.remove(currentSet.size() - 1);
        }
    }

    /**
     * Alternative method with a iterative approach
     * @param nums
     * @return
     */
    public static List<List<Integer>> findSubsets(int[] nums) {
      List<List<Integer>> subsets = new ArrayList<>();
      //adding the first empty set
      subsets.add(new ArrayList<Integer>());
      for (int num : nums) {
          int subSetSize = subsets.size();
       // we will take all existing subsets and insert the current number in them to create new subsets
          for (int i = 0; i < subSetSize; i++) {
            // create a new subset from the existing subset and insert the current element to it
              List<Integer> newSet = new ArrayList<>(subsets.get(i));
              newSet.add(num);
              subsets.add(newSet);
          }
      }
      return subsets;
    }
    
      @Test
      public void validate() {
        List<List<Integer>> result = SubsetsFindAll.findSubsets(new int[] { 1, 3 });
        Assert.assertEquals("[[], [1], [3], [1, 3]]", result.toString());
    
        result = SubsetsFindAll.findSubsetsBack(new int[] { 1, 3 });
        Assert.assertEquals("[[], [1], [1, 3], [3]]", result.toString());

        result = SubsetsFindAll.findSubsets(new int[] { 1, 5, 3 });
        Assert.assertEquals("[[], [1], [5], [1, 5], [3], [1, 3], [5, 3], [1, 5, 3]]", result.toString());
      }

      public static void main(String[] args) {
        List<List<Integer>> result = SubsetsFindAll.findSubsetsBack(new int[] { 1, 2, 3});
        System.out.println(result.toString());
      }
    
}