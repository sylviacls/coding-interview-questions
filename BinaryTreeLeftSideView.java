import java.util.*;

/**
 * Given a binary tree, return an array containing nodes in its left view. 
 * The left view of a binary tree is the set of nodes visible when the tree is seen from 
 * the left side.
 * 
 * See: BinaryTreeRightSideView.java
 * 
 */
public class BinaryTreeLeftSideView {

    /**
     * Approach: BFS
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     * 
     */
    public static List<Integer> leftSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int items = queue.size();
            int count = queue.size();
            TreeNode current = null;
            while(items > 0) {
                current = queue.poll();
                //items == count means that's first node of curr level 
                if(items == count) result.add(current.val);
                if(current.left != null) queue.offer(current.left);
                if(current.right != null) queue.offer(current.right);
                items--;
            }
        }
        return result;
      }
    
      public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        root.left.left.left = new TreeNode(3);
        List<Integer> result = BinaryTreeLeftSideView.leftSideView(root);
        result.forEach(System.out::println);
      }
}
