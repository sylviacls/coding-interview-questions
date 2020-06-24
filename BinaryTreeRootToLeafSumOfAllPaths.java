import org.junit.Assert;
import org.junit.Test;

/**
* Given a binary tree where each node can only have a digit (0-9) value, each root-to-leaf
* path will represent a number. Find the total sum of all the numbers represented by 
* all paths.
* Time Complexity: O(N) we traverse each node once
* Space complexity: O(N) recursion stack
*/
public class BinaryTreeRootToLeafSumOfAllPaths {

    public static int sumAllPaths(TreeNode root) {
        return findPath(root, 0);
    }

    private static int findPath(TreeNode currentNode, int pathSum) {
        if (currentNode == null) return 0;

      // calculate the path number of the current node
      pathSum = 10 * pathSum + currentNode.val;
  
      // if the current node is a leaf, return the current path sum.
      if (currentNode.left == null && currentNode.right == null) {
        return pathSum;
      }

      // traverse the left and the right sub-tree
      return findPath(currentNode.left, pathSum) + findPath(currentNode.right, pathSum);
    }
    
    @Test
    public void validate() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);
        Assert.assertEquals(332, sumAllPaths(root));

        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(7);
        root2.right = new TreeNode(1);
        root2.left.left = new TreeNode(4);
        root2.right.left = new TreeNode(9);
        root2.right.right = new TreeNode(5);
        Assert.assertEquals(1008, sumAllPaths(root2));
    }
}