import java.util.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * Leetcode: 113. Path Sum II
 * https://leetcode.com/problems/path-sum-ii/
 * 
 * Given a binary tree and a number ‘S’, find all paths from root-to-leaf such
 * that the sum of all the node values of each path equals ‘S’.
 * 
 */
public class BinaryTreeRootToLeafAllPathsSum {

    /**
     * Approach: DFS
     * 
     * Time Complexity: O(N logN) - O (N²)
     * Space Complexity: O(N Log N)
     *                  the depth (or height) of a balanced binary tree is O(logN)
     *                  at the most, each path can have logN nodes in it
     *                  allPaths list will be O(N*logN)
     */
    public static List<List<Integer>> findPaths(TreeNode root, int sum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<Integer>();
        findPath(root, sum, currentPath, allPaths);
        return allPaths;
    }

    public static void findPath(TreeNode root, int sum,  List<Integer> currentPath,
                        List<List<Integer>> allPaths) {

        if(root == null) return;

        // add the current node to the path
        currentPath.add(root.val);
        
        // if the current node is a leaf and its value is equal to sum, save the current path                    
        if(root.val == sum && root.left == null && root.right == null) {
            //we need store as a new/independent arraylist object 
            //cause we are at the same time changing the current path (adding and removing)
            //during the recursion
            allPaths.add(new ArrayList<Integer>(currentPath));
        }

        findPath(root.left, sum-root.val, currentPath, allPaths);
        findPath(root.right, sum-root.val,currentPath, allPaths);

        // remove the current node from the path to backtrack, 
        // we need to remove the current node while we are going up the recursive call stack.
        currentPath.remove(currentPath.size() - 1);
    }
    @Test
    public void validate() {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        int sum = 23;
        List<List<Integer>> result = findPaths(root, sum);
        Assert.assertEquals(2, result.size());
        Assert.assertEquals("[[12, 7, 4], [12, 1, 10]]", result.toString());
    }

}