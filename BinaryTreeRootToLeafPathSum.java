import java.util.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * Leetcode: 112. Path Sum
 * https://leetcode.com/problems/path-sum/
 * 
 * Given a binary tree and a number ‘S’, find if the tree has a path from root-to-leaf
 * such that the sum of all the node values of that path equals ‘S’.
 * 
 */
public class BinaryTreeRootToLeafPathSum {

    /**
     * Approach: DFS
     * 
     * Time complexity O(N): where ‘N’ is the total number of nodes in the tree
     *                      This is due to the fact that we traverse each node once. (Recursive method)
     * Space complexity O(N):  This space will be used to store the recursion stack. 
     */
    public static boolean hasPath(TreeNode root, int sum) {

        if(root == null) return Boolean.FALSE;

        Stack<TreeNode> stack = new Stack<TreeNode>(); 
        Set<TreeNode> visited = new HashSet<TreeNode>();

        stack.add(root);

        int currentSum = sum;
        while (!stack.empty()) {
            // peek the node without removing
            TreeNode current = stack.peek();

            //if current is a leaf and its value is equal to the current sum
            if (current.val == currentSum && current.left == null && current.right == null) {
                return true;
            }
            // if not visited, update the current sum by decreasing its value with current node
            if (!visited.contains(current)) {
                visited.add(current);
                currentSum -= current.val;
            } else { // in this case, the node has been alread visited and we are backtracking
                //so we need to remove it from the stack and "return" its value to current sum
                current = stack.pop();
                currentSum += current.val;
            }
            if(current.left != null && !visited.contains(current.left)){
                stack.push(current.left);
            }
            if(current.right != null && !visited.contains(current.right)){
                stack.push(current.right);
            }
        }

        return Boolean.FALSE;
    }

    /**
     * Approach: DFS Recursive
     * Time complexity O(N): where ‘N’ is the total number of nodes in the tree
     *                      This is due to the fact that we traverse each node once. (Recursive method)
     * Space complexity O(N):  This space will be used to store the recursion stack. 
     */
    public static boolean hasPathRec(TreeNode root, int sum) {

        if (root == null)
          return false;
    
        // if the current node is a leaf and its value is equal to the sum, we've found a path
        if (root.val == sum && root.left == null && root.right == null)
          return true;
    
        // recursively call to traverse the left and right sub-tree
        // return true if any of the two recursive call return true
        return hasPathRec(root.left, sum - root.val) || hasPathRec(root.right, sum - root.val);
      }

    @Test
    public void validate() {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        Assert.assertTrue(hasPath(root, 23));
        Assert.assertTrue(hasPathRec(root, 28));  
        Assert.assertTrue(hasPathRec(root, 18));
        Assert.assertFalse(hasPath(root, 16)); 
        Assert.assertFalse(hasPath(root, 17));
        Assert.assertFalse(hasPath(root, 35));
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