
/**
 * Leetcode: 687. Longest Univalue Path
 * https://leetcode.com/problems/longest-univalue-path/
 * 
 * Given a binary tree, find the length of the longest path where each node in the path has
 * the same value. This path may or may not pass through the root.
 * 
 * The length of path between two nodes is represented by the number of edges between them.
 * 
 * Example 1:
 * Input:
 *
              5
             / \
            4   5
           / \   \
          1   1   5
 * Output:2
 */

import org.junit.*;

public class BinaryTreeLongestUnivaluePath {

    static int longestPath;
    /**
     * Approach: DFS Recursive
     * 
     * Time Complexity: O(N), where N is the number of nodes in the tree. 
     *                  We process every node once.
     * Space Complexity: O(H), where HH is the height of the tree. 
     *                  Our recursive call stack could be up to HH layers deep.
     */
    public static int longestUnivaluePath(TreeNode root) {
        longestPath = 0;
        if(root == null) return 0;

        longestPathDFS(root);
        return longestPath;
    }

    private static int longestPathDFS(TreeNode node) {
        if(node == null) return 0;
        
        int left = longestPathDFS(node.left);
        int right = longestPathDFS(node.right);

        int arrowLeft = 0;
        int arrowRight = 0;
        if (node.left != null && node.left.val == node.val) {
            arrowLeft += left + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            arrowRight += right + 1;
        }
        longestPath = Math.max(longestPath, arrowLeft + arrowRight);

        return Math.max(arrowLeft, arrowRight);
    }

    @Test
    public void validate() {
      TreeNode root = new TreeNode(1);
      root.left = new TreeNode(4);
      root.right = new TreeNode(5);
      root.left.left = new TreeNode(4);
      root.left.right = new TreeNode(4);
      root.right.right = new TreeNode(5);
  
      Assert.assertEquals(2,longestUnivaluePath(root));

      root = new TreeNode(5);
      root.left = new TreeNode(4);
      root.right = new TreeNode(5);
      root.left.left = new TreeNode(1);
      root.left.right = new TreeNode(1);
      root.right.right = new TreeNode(5);

      Assert.assertEquals(2,longestUnivaluePath(root));

    }
}
