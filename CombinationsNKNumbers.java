/**
 * Leetcode: 77. Combinations
 * https://leetcode.com/problems/combinations/
 * 
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * You may return the answer in any order.
 * Example 1:
 * Input: n = 4, k = 2
 * Output:
 [
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
 ]
*/
import java.util.*;
import java.util.stream.IntStream;

import org.junit.*;

public class CombinationsNKNumbers {

    /**
     * Approach: Backtracking
     * 
     * Time Complexity: O (C(n,k)*k)
     * Space Complexity: O (C(n,k))
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if(k > n) return result;
        int[] nums = IntStream.range(1, n+1).toArray();
        combineRec(nums, 0, k, new ArrayList<Integer>(), result);
        return result;
    }

    private static void combineRec(int[] nums, int start, int k, ArrayList<Integer> currComb, List<List<Integer>> result) {
        if(currComb.size() == k) {
            result.add(new ArrayList<Integer>(currComb));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            currComb.add(nums[i]);
            combineRec(nums, i+1, k, currComb, result);
            currComb.remove(currComb.size()-1);
        }
    }

    @Test
    public void validate() {
       Assert.assertEquals("[[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]", combine(4, 2).toString());
    }

}
