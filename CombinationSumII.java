/**
 * Leetcode: 40. Combination Sum II
 * https://leetcode.com/problems/combination-sum-ii/
 * 
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 * 
 * Each number in candidates may only be used once in the combination. 
 * 
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * 
 * Example 1:
 * 
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 */

import java.util.*;
import org.junit.*;

public class CombinationSumII {
    /**
     * Approach: Backtracking
     * 
     * Time Complexity: O(k* 2^N)
     * Space Complexity: O(N)
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(candidates == null) return result;
        Arrays.sort(candidates);
        combinationSum2Rec(candidates, 0, target, new ArrayList<Integer>(), result);
        return result;
    }

    private static void combinationSum2Rec(int[] candidates, int start, int target, ArrayList<Integer> currSet,
            List<List<Integer>> result) {
        if(target < 0) return;

        if(target == 0) {
            result.add(new ArrayList<Integer>(currSet));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if(i > start && candidates[i] == candidates[i-1]) continue;
            currSet.add(candidates[i]);
            combinationSum2Rec(candidates, i+1, target-candidates[i], currSet, result);
            currSet.remove(currSet.size()-1);
        }
    }

    @Test
    public void validate() {
        Assert.assertEquals("[[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]",combinationSum2(new int[]{10,1,2,7,6,1,5}, 8).toString());
    }
}
