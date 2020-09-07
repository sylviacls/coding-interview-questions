import java.util.*;

/**
 * Leetcode: 116. Populating Next Right Pointers in Each Node
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 * 
 * Given a binary tree, connect each node with its level order successor. The
 * last node of each level should point to a null node.
 * 
 * Follow up:
 * - You may only use constant extra space.
 * - Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
 */
public class BinaryTreeConnectLevelOrderSiblings {

  /**
   * Approach: BFS
   * 
   * Time complexity O(N): where ‘N’ is the total number of nodes in the tree
   * Space complexity O(N): as we need to return a list containing the level order traversal. 
   *                       We will also need O(N) space for the queue. 
   */
  public static TreeNode connectSiblings(TreeNode root) {
    if (root == null) return root;

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
    return root;
  }

  
  public static void traverseLevelOrder(TreeNode root) {
    TreeNode nextLevelNode = root;

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

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(9);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    BinaryTreeConnectLevelOrderSiblings.connectSiblings(root);
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
};
