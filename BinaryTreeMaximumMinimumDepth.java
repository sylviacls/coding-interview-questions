import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;


/**
 * Find the minimum depth of a binary tree. The minimum depth is the number of nodes
 * along the shortest path from the root node to the nearest leaf node.
 *
 * Time complexity O(N): where ‘N’ is the total number of nodes in the tree
 * Space complexity O(N): as we need space for the queue.
 */
public class BinaryTreeMaximumMinimumDepth {

    public static int minimumDepth(TreeNode root) {
        if(root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        int minimumDepth = 0;
        queue.offer(root);

        while(!queue.isEmpty()) {
             minimumDepth++;
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                if(current.left == null && current.right == null) {
                    return minimumDepth;
                }
                if(current.left != null) {
                    queue.offer(current.left);
                }
                if(current.right != null) {
                    queue.offer(current.right);
                }
            }
        }
        return minimumDepth;
    }
    
    public static int maximumDepth(TreeNode root) {
        if(root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        int maxDepth = 0;
        queue.offer(root);

        while(!queue.isEmpty()) {
            maxDepth++;
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                if(current.left != null) {
                    queue.offer(current.left);
                }
                if(current.right != null) {
                    queue.offer(current.right);
                }
            }
        }
        return maxDepth;
    }

    @Test
    public void validate() {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        Assert.assertEquals(2, minimumDepth(root));
        Assert.assertEquals(3, maximumDepth(root));
        root.left.left = new TreeNode(9);
        root.right.left.left = new TreeNode(11);
        Assert.assertEquals(3, minimumDepth(root));
        Assert.assertEquals(4, maximumDepth(root));

      }
    
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
  
    TreeNode(int x) {
      val = x;
    }
}
