import java.util.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Leetcode: 102. Binary Tree Level Order Traversal
 * 
 * Given a binary tree, populate an array to represent its level-by-level traversal.
 * You should populate the values of all nodes of each level from left to right in 
 * separate sub-arrays.
 * 
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
  return its level order traversal as:
 [
  [3],
  [9,20],
  [15,7]
 ]
 * 
 */
public class BinaryTreeLevelOrderTraversal {

  /**
   * Approach: BFS - Breadth First Search
   * 
   * Start by pushing the root node to the queue. 
   * Keep iterating until the queue is empty. 
   * In each iteration, first count the elements in the queue (let’s call it levelSize). 
   * We will have these many nodes in the current level. 
   * Next, remove levelSize nodes from the queue and push their value in an array to represent
   * the current level. 
   * After removing each node from the queue, insert both of its children into the queue.
   * 
   * Time complexity O(N): where ‘N’ is the total number of nodes in the tree
   * Space complexity O(N): as we need to return a list containing the level order traversal. 
   *                       We will also need O(N)O(N) space for the queue. 
   */
  public static List<List<Integer>> traverse(TreeNode root) {

    List<List<Integer>> result = new ArrayList<List<Integer>>();
    if (root == null) return result;

    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    
    queue.offer(root);
    while(!queue.isEmpty()) {
        int levelSize = queue.size();
        List<Integer> level = new ArrayList<Integer>(levelSize);

        while(levelSize > 0) {
            TreeNode current = queue.poll();
            level.add(current.val);
           
            if(current.left != null) {
                queue.offer(current.left);
            }
            if(current.right != null) {
                queue.offer(current.right);
            }
            levelSize--;       
        }
        result.add(level);
    }

    return result;
  }
  
  @Test
  public void validate() {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(9);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    List<List<Integer>> result = BinaryTreeLevelOrderTraversal.traverse(root);
    Assert.assertEquals("[[12], [7, 1], [9, 10, 5]]", result.toString());
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

