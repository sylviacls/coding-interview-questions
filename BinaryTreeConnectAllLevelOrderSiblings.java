import java.util.*;

/**
 * Given a binary tree, connect each node with its level order successor. 
 * The last node of each level should point to the first node of the next level.
 * 
 * Time complexity O(N): where ‘N’ is the total number of nodes in the tree
 * Space complexity O(N): as we need to return a list containing the level order traversal. 
 *                       We will also need O(N)O(N) space for the queue. 
 */
public class BinaryTreeConnectAllLevelOrderSiblings {

    public static void connectAllSiblings(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        queue.offer(root);
        TreeNode nextLevelNode =  null;
        TreeNode previous = null;

        while(!queue.isEmpty()) {
            TreeNode current = null;
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                current = queue.poll();

                //saving the first node of current level
                if(nextLevelNode == null) {
                    if(current.left != null) {
                         nextLevelNode = current.left;
                    } else if(current.right != null) {
                        nextLevelNode = current.right;
                    }
                }
                if(previous != null) {
                    previous.next = current;
                }
                //adding current node's children into queue
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }

                 previous = current;
            }
            // reached the end of the level
            //reseting nextLevelNode
            nextLevelNode = null;
        }
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