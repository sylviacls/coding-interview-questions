import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.junit.Assert;
import org.junit.Test;

/**
 * Given a binary tree, populate an array to represent its level-by-level
 * traversal in reverse order, i.e., the lowest level comes first. You should
 * populate the values of all nodes in each level from left to right in separate
 * sub-arrays.
 * 
 * Time complexity O(N): where ‘N’ is the total number of nodes in the tree
 * Space complexity O(N): as we need to return a list containing the level order traversal. 
 *                       We will also need O(N)O(N) space for the queue. 
 */
public class BinaryTreeReverseLevelOrderTraversal {
    public static List<List<Integer>> traverseReverse(TreeNode root) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
     
       //insted of using a stack we can use the linkedList by placing the 
       // currentlevel at the 0 position. In a linked list we won't have any shiffiting
       // as we have in ArrayList
       // Stack<List<Integer>> stack = new Stack<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        
        queue.offer(root);
        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<Integer>(levelSize);
            TreeNode current = queue.peek();
    
            while(levelSize > 0) {
                current = queue.poll();
                level.add(current.val);
               
                if(current.left != null) {
                    queue.offer(current.left);
                }
                if(current.right != null) {
                    queue.offer(current.right);
                }
                levelSize--;       
            }
            result.add(0, level);
         //   stack.add(level);
        }
/*         while(!stack.empty()) {
            result.add(stack.pop());
        } */
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
        List<List<Integer>> result = traverseReverse(root);
        Assert.assertEquals("[[9, 10, 5], [7, 1], [12]]", result.toString());
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