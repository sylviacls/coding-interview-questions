import java.util.LinkedList;
import java.util.Queue;
import org.junit.Assert;
import org.junit.Test;

/**
 * Given a binary tree and a node, find the level order successor of the given node
 * in the tree. The level order successor is the node that appears right after the 
 * given node in the level order traversal.
 * 
 */
public class BinaryTreeLevelOrderSuccessor {

    /**
     * Approach: BFS
     * 
     * Time complexity O(N): where ‘N’ is the total number of nodes in the tree
     * Space complexity O(N): as we need space for the queue.
     */
    public static TreeNode findSuccessor(TreeNode root, int key) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        boolean keyFound = false;

        queue.offer(root);

        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                if(keyFound) {
                    return current;
                }      
                if(current.val == key) {
                    keyFound = true;
                }
                if(current.left != null) {
                    queue.offer(current.left);
                }
                if(current.right != null) {
                    queue.offer(current.right);
                }
            }
        }
        return null;
    }

    @Test
    public void validate() {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        Assert.assertEquals(7, BinaryTreeLevelOrderSuccessor.findSuccessor(root, 12).val);
        Assert.assertEquals(10, BinaryTreeLevelOrderSuccessor.findSuccessor(root, 9).val);
        Assert.assertEquals(5, BinaryTreeLevelOrderSuccessor.findSuccessor(root, 10).val);
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