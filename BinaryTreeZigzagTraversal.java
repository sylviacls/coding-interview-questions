import java.util.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Given a binary tree, populate an array to represent its zigzag level order traversal.
 * You should populate the values of all nodes of the first level from left to right, 
 * then right to left for the next level and keep alternating in the same manner for 
 * the following levels.
 */
public class BinaryTreeZigzagTraversal {
    
    /**
     * Approach: BFS
     * 
     * Time complexity O(N): where ‘N’ is the total number of nodes in the tree
     * Space complexity O(N): as we need to return a list containing the level order traversal. 
     *                       We will also need O(N)O(N) space for the queue.
     */
    public static List<List<Integer>> zigZagTraverse(TreeNode root) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if(root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        boolean leftToRight = true;

        queue.offer(root);
        while(!queue.isEmpty()) {
            int sizeLevel = queue.size();
            List<Integer> currentLevel = new ArrayList<Integer>(sizeLevel);

            while(sizeLevel > 0) {
                TreeNode currentNode = queue.poll();
                if(leftToRight) {
                    currentLevel.add(currentNode.val);
                } else {
                    currentLevel.add(0,currentNode.val);
                }
                if(currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if(currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
                sizeLevel--;
            }
            result.add(currentLevel);
            leftToRight = !leftToRight;
        }
        return result;
      }

      @Test
      public void validade() {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        root.right.left.left = new TreeNode(20);
        root.right.left.right = new TreeNode(17);
        Assert.assertEquals("[[12], [1, 7], [9, 10, 5], [17, 20]]", zigZagTraverse(root).toString());
    }
}
/*class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
  
    TreeNode(int x) {
      val = x;
    }
}*/