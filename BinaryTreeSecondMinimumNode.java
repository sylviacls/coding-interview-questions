import java.util.*;
import org.junit.*;

/**
 * Leetcode: 671. Second Minimum Node In a Binary Tree
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
 * 
 * Given a non-empty special binary tree consisting of nodes with the non-negative value, 
 * where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes,
 * then this node's value is the smaller value among its two sub-nodes. More formally, the 
 * property root.val = min(root.left.val, root.right.val) always holds.
 * 
 * Given such a binary tree, you need to output the second minimum value in the set made of 
 * all the nodes' value in the whole tree.
 * 
 * If no such second minimum value exists, output -1 instead.
 * 
 * Example 1:
 * Input: 
 *   2
 *  / \
 * 2   5
 *    / \
 *   5   7
 * 
 * Output: 5
 * Explanation: The smallest value is 2, the second smallest value is 5.
 */
public class BinaryTreeSecondMinimumNode {

    /**
     * Approach: BFS + Min Heap
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static int findSecondMinimumValue(TreeNode root) {

      if(root == null) return -1;

      PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
      Queue<TreeNode> queue = new LinkedList<TreeNode>();
      queue.offer(root);
      minHeap.add(root.val);

      while(!queue.isEmpty()) {
        int items = queue.size();
        while(items > 0) {
          TreeNode curr = queue.poll();
          if(curr.val > minHeap.peek()) {
            minHeap.add(curr.val);
          }
          if(curr.left != null) queue.offer(curr.left);
          if(curr.right != null) queue.offer(curr.right);
          items--;
        }
      }

      if(minHeap.size() < 2) return -1;
      int secondMin = 0;
      for (int i = 0; i < 2; i++) {
          secondMin = minHeap.poll();
      }

      return secondMin;
    }

    @Test
    public void validate() {
      TreeNode root = new TreeNode(2);
      root.left = new TreeNode(2);
      root.right = new TreeNode(5);
      root.right.left = new TreeNode(5);
      root.right.right = new TreeNode(7);

      Assert.assertEquals(5,findSecondMinimumValue(root));
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