import java.util.*;

/**
 * Given a binary tree, connect each node with its level order successor. 
 * The last node of each level should point to the first node of the next level.
 *
 */
public class BinaryTreeConnectAllLevelOrderSiblings {

    /**
     * Approach: BFS
     * 
     * Time complexity O(N): where ‘N’ is the total number of nodes in the tree
     * Space complexity O(N): as we need to return a list containing the level order traversal. 
     *                       We will also need O(N)O(N) space for the queue. 
     */
    public static TreeNode connectAllSiblings(TreeNode root) {

        if(root == null) return root;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        TreeNode currentNode = null;
        TreeNode previousNode = null;
        while (!queue.isEmpty()) {
          currentNode = queue.poll();
          if (previousNode != null) {
            previousNode.next = currentNode;
          }
          previousNode = currentNode;
    
          // insert the children of current node in the queue
          if (currentNode.left != null)
            queue.offer(currentNode.left);
          if (currentNode.right != null)
            queue.offer(currentNode.right);
        }
        return root;
    }

    public static void traverseLevelOrder(TreeNode root) {
        TreeNode current = root;
        while(current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
    }

    
  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(9);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    connectAllSiblings(root);
    System.out.println("Level order traversal using 'next' pointer: ");
    traverseLevelOrder(root);
  }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode next;
  
    TreeNode(int x) {
      val = x;
      left = right = next = null;
    }

}