/**
 * Leetcode: 96. Unique Binary Search Trees
 * https://leetcode.com/problems/unique-binary-search-trees/
 * 
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 - n?
 * 
 * Example:
 * Input: 3
 * Output: 5
 * Explanation:
 * 
 * Given n = 3, there are a total of 5 unique BST's:
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *   2     1         2                 3
 */
import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTreeUniquesCount {
    /**
     * Approach: Recursion
     * 
     * Time Complexity: O(2^N)  since we are iterating from ‘1’ to ‘n’ and ensuring that each 
     *                          sub-problem is evaluated only once. 
     * Space Complexity: O(N) for the memoization map.
     * 
     * Without memoization: Runtime 2050 ms	Memory 35.8 MB
     * With memoization: Runtime 0 ms	Memory 35.9 MB
     */
    
    public static int numTrees(int n) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0,1);
        map.put(1,1);
        return numTreesRec(n, map);
    }
    public static int numTreesRec(int n, Map<Integer, Integer> map) {
        if (map.containsKey(n))
            return map.get(n);

        if(n <= 1) 
            return 1;
        
        int count = 0;
        //takinh each i-th number as a root
        for (int i = 1; i <= n; i++) {
            int leftCount = numTreesRec(i-1, map);
            int rigthCount = numTreesRec(n-i, map);
            count += leftCount*rigthCount;
        }
        map.put(n, count);
        return count;
    }

    /**
     * Approach: Dynamic Programming
     * 
     * Given a sequence 1…n, we pick a number i out of the sequence as the root, then the 
     * number of unique BST with the specified root F(i), is the cartesian product of the
     *  number of BST for its left and right subtrees. For example, F(3, 7): the number of 
     * unique BST tree with number 3 as its root.
     * To construct an unique BST out of the entire sequence [1, 2, 3, 4, 5, 6, 7] with 3 
     * as the root, which is to say, we need to construct an unique BST out of its left 
     * subsequence [1, 2] and another BST out of the right subsequence [4, 5, 6, 7], and
     * then combine them together (i.e. cartesian product)
     * The tricky part is that we could consider the number of unique BST out of sequence 
     * [1,2] as G(2), and the number of of unique BST out of sequence [4, 5, 6, 7] as G(4).
     * Therefore, F(3,7) = G(2) * G(4).
     * 
     * G(n) = G(0) * G(n-1) + G(1) * G(n-2) + … + G(n-1) * G(0) 
     * i.e: G(3) = G(0)*G(2) + G(1)*G(1) + G(2)*G(0)
     * 
     * Time Complexity: O(N^2)
     * Space Complexity: O(N)
     */
    public static int numTreesII(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j]*dp[i - j - 1];
            }
        }
        return dp[n];
    }


    @Test
    public void validate() {
        Assert.assertEquals(14, numTrees(4));
        Assert.assertEquals(14, numTreesII(4));
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
  
    TreeNode(int x) {
      val = x;
    }

};