import java.util.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given a binary tree and a number sequence, find if the sequence is present as
 * a root-to-leaf path in the given tree.
 * 
 * Time Complexity: O(N) we traverse each node once
 * Space complexity: O(N) recursion stack
 */
public class BinaryTreePathWithGivenSequence {

    public static boolean findPathWithSequence(TreeNode root, int[] sequence) {
        List<Integer> currentPath = new ArrayList<Integer>();
        List<List<Integer>> pathsFound = new ArrayList<>();
        findPath(root, sequence, currentPath, pathsFound);

        return pathsFound.size() > 0;
      }
      
      private static void findPath(TreeNode currentNode, int[] sequence,
                             List<Integer> currentPath, List<List<Integer>> pathFound) {
          if(currentNode == null) return;

          currentPath.add(currentNode.val);

          if(currentNode.left == null && currentNode.right == null &&
                sequenceMath(currentPath, sequence)) {
                    pathFound.add( new ArrayList<>(currentPath));
          }

          findPath(currentNode.left, sequence, currentPath, pathFound);
          findPath(currentNode.right, sequence, currentPath, pathFound);
        
          currentPath.remove(currentPath.size()-1);
      }

      private static boolean sequenceMath(List<Integer> currentPath, int[] sequence) {
          List<Integer> sequenceList = new ArrayList<Integer>();
          for (int iSequence : sequence) {
            sequenceList.add(iSequence);
          }
          return currentPath.equals(sequenceList);
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