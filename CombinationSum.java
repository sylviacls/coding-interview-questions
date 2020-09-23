/**
 * Leetcode: 39. Combination Sum
 * https://leetcode.com/problems/combination-sum/
 * 
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), 
 * find all unique combinations in candidates where the candidate numbers sums to target.
 * 
 * The same repeated number may be chosen from candidates unlimited number of times.
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * 
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 *  [7],
 *  [2,2,3]
 * ]
 *
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 */

 import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class CombinationSum {

    /**
     * Approach: Backtracking
     * 
     * Time Complexity: O(N^target) worst case - This is worst case and without any optimization, 
     *                  like moving position forward and sorting to stop early. Just assuming 
     *                  that each recursive step we go over all existing candidates, so base N.
     *                  And go as deep as target in our recursive calls (if candidates are close 
     *                  to 1), so power of target.
     * Space Complexity: O (target)
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(candidates == null) return result;

        combinationSumRec(candidates, 0, new ArrayList<Integer>(), target, result);
        return result;
    }

    private static void combinationSumRec(int[] candidates, int start, ArrayList<Integer> currSet, int target, List<List<Integer>> result) {
           
        if(target == 0) {
            result.add(new ArrayList<>(currSet));
            return;
        } else if(target <0) { //ignore this subproblem
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            int currNum = candidates[i];
            currSet.add(currNum);
            if(target/currNum > 0) {
                combinationSumRec(candidates, i, currSet, target-currNum, result);
            } else {
                combinationSumRec(candidates, i+1, currSet, target-currNum, result);
            }
            currSet.remove(currSet.size()-1);
        }
    }

    @Test
    public void validade() {
        Assert.assertEquals("[[2, 2, 2, 2], [2, 3, 3], [3, 5]]", combinationSum(new int[]{2,3,5}, 8).toString());
        Assert.assertEquals("[[2, 2, 3], [7]]", combinationSum(new int[]{2,3,6,7}, 7).toString());
        Assert.assertEquals("[[1, 1]]", combinationSum(new int[]{1}, 2).toString());
    }
}
