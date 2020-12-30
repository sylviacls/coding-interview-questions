import org.junit.Assert;
import org.junit.Test;

/**
 * Given a binary tree and a number sequence, find if the sequence is present as
 * a root-to-leaf path in the given tree.
 */
public class BinaryTreePathWithGivenSequence {

    /**
     * Approach: DFS
     * 
     * Time Complexity: O(N) we traverse each node once
     * Space complexity: O(N) recursion stack
     */    
      public static boolean findPathWithSequence(TreeNode root, int[] sequence) {
        if (root == null)
          return sequence.length == 0;

        return findPathRecursive(root, sequence, 0);
      }

      private static boolean findPathRecursive(TreeNode currentNode, int[] sequence, int sequenceIndex) {

        if (currentNode == null)
          return false;

        if (sequenceIndex >= sequence.length || currentNode.val != sequence[sequenceIndex])
          return false;

        // if the current node is a leaf, add it is the end of the sequence, we have found a path!
        if (currentNode.left == null && currentNode.right == null && sequenceIndex == sequence.length - 1)
          return true;

        // recursively call to traverse the left and right sub-tree
        // return true if any of the two recusrive call return true
        return findPathRecursive(currentNode.left, sequence, sequenceIndex + 1)
            || findPathRecursive(currentNode.right, sequence, sequenceIndex + 1);
      }

      @Test
      public void validate() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);
    
        Assert.assertFalse(findPathWithSequence(root, new int[] { 1, 0, 7 }));
        Assert.assertTrue(findPathWithSequence(root, new int[] { 1, 1, 6 }));
        Assert.assertTrue(findPathWithSequence(root, new int[] { 1, 1, 5 }));
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