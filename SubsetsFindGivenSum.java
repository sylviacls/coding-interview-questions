import java.util.ArrayList;
import java.util.List;

/**
 * Find subset of elements that are selected from a given set whose sum adds up
 * to a given number K. We are considering the set contains non-negative values.
 * It is assumed that the input set is unique (no duplicates are presented).
 * 
 * This problem can be solved using following algorithms: 
 * Recursive method
 * Backtracking 
 * Dynamic Programing
 */
public class SubsetsFindGivenSum {
    /**
     * Driver-method: backtracking approach
     * @param nums input
     * @param target sum
     * @return
     */
    public static List<List<Integer>> findSubsetsSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        findSubsets(nums, 0, target, 0, new ArrayList<Integer>(), result);
        return result;
    }

    /**
     * It uses DFS
     * The ideia is to simulate the insertion or exclusion of each number 
     * and for each depth-level we update the required sum
     * @param nums input
     * @param currTarget stores the current set sum 
     * @param finalTarget 
     * @param start points to next index
     * @param currentSet
     * @param result
     */
    private static void findSubsets(int[] nums, int currTarget, int finalTarget, int start, ArrayList<Integer> currentSet, List<List<Integer>> result) {
        //base case: if our subset has the target sum
        if(currTarget == finalTarget) {
            result.add(new ArrayList<>(currentSet));
            return;
        }
        //base case: if the last level is reached, we backtrack
        if (start == nums.length) {
            return;
        }
        
        //for each number we simulate its insertion
        for (int i = start; i < nums.length; i++) {
            //we make a choice to insert the current number
            currentSet.add(nums[i]);
            //and we go further, udpating the current target
            findSubsets(nums, currTarget+nums[i], finalTarget, i+1, currentSet, result);
            //we make a choice not including the current number
            currentSet.remove(currentSet.size()-1);
        }
    }
    
    public static void main(String[] args) {
        int[] set = {1,2,3,4,5};
        System.out.println(findSubsetsSum(set, 3));
    }
}