import java.util.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * Leetcode: 437. Path Sum III
 * https://leetcode.com/problems/path-sum-iii/
 * 
 * Given a binary tree and a number ‘S’, find all paths in the tree such that
 * the sum of all the node values of each path equals ‘S’. Please note that the
 * paths can start or end at any node but all paths must follow direction from
 * parent to child (top to bottom).
 * 
 */
public class BinaryTreeCountAllPathSum {

    /**
     * Approach: DFS Recursive
     * 
     *  Time Complexity: O(N²), we traverse each node once, but for each node
     *                  we iterate the current path (that can be O(N) for a skewed tree)
     * Space Complexity: O(N) recursion stack
     */
    public static int countPaths(TreeNode root, int sum) {
        List<Integer> currentPath = new ArrayList<Integer>();   
        return findPaths(root, currentPath, sum);
      }
      
      private static int findPaths(TreeNode currentNode, List<Integer> currentPath, int sum) {
        if(currentNode == null) return 0;

        // add the current node to the path
        currentPath.add(currentNode.val); 

        int pathSum = 0;
        int pathCount = 0;
        // find the sums of all sub-paths in the current path list
        for(int i = currentPath.size() -1 ; i >=0; i--) {
          pathSum += currentPath.get(i);
          // if the sum of any sub-path is equal to 'S' we increment our path count.
          if(pathSum == sum) 
              pathCount++;
      }
        /*ListIterator<Integer> iterator = currentPath.listIterator(currentPath.size());

        while(iterator.hasPrevious()) {
          pathSum += iterator.previous();
          if(pathSum == sum)  pathCount++;
        } */

        pathCount += findPaths(currentNode.left, currentPath, sum);
        pathCount += findPaths(currentNode.right, currentPath, sum);

        // remove the current node from the path to backtrack,      
        // we need to remove the current node while we are going up the recursive call stack.
        currentPath.remove(currentPath.size()-1);

        return pathCount;
      }

      @Test
      public void validate() {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        Assert.assertEquals(1,countPaths(root, 12));
        Assert.assertEquals(2,countPaths(root, 23));
        Assert.assertEquals(2,countPaths(root, 11));
        Assert.assertEquals(1,countPaths(root, 7));
        Assert.assertEquals(1,countPaths(root, 10));
      }
}