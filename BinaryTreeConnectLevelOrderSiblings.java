import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, connect each node with its level order successor. The
 * last node of each level should point to a null node.
 *
 * Time complexity O(N): where ‘N’ is the total number of nodes in the tree
 * Space complexity O(N): as we need to return a list containing the level order traversal. 
 *                       We will also need O(N)O(N) space for the queue. 
 */
public class BinaryTreeConnectLevelOrderSiblings {

  public static void connectSiblings(TreeNode root) {
    if (root == null)
      return;

    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      TreeNode previous = null;
      TreeNode current = null;

      int levelSize = queue.size();

      for (int i = 0; i < levelSize; i++) {
        current = queue.poll();

        if (previous != null) {
          previous.next = current;
        }
        previous = current;

        if (current.left != null) {
          queue.offer(current.left);
        }
        if (current.right != null) {
          queue.offer(current.right);
        }
      }
    }
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(9);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    BinaryTreeConnectLevelOrderSiblings.connectSiblings(root);
    System.out.println("Level order traversal using 'next' pointer: ");
    root.traverseLevelOrder();
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

  public void traverseLevelOrder() {
    TreeNode nextLevelNode = this;

    while (nextLevelNode != null) {
      TreeNode current = nextLevelNode;
      nextLevelNode = null;
      while (current != null) {
        System.out.print(current.val + " ");
        if (nextLevelNode == null) {
          if (current.left != null) {
            nextLevelNode = current.left;
          } else if (current.right != null) {
            nextLevelNode = current.right;
          }
        }
        current = current.next;
      }
      System.out.println();
    }
  }
};
