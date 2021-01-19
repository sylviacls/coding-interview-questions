import java.util.*;

/**
 * Leetcode: 199. Binary Tree Right Side View
 * https://leetcode.com/problems/binary-tree-right-side-view/
 * 
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 * 
 * Example: 
 * 
 * Input: [1,2,3,null,5,null,4] 
 * Output: [1, 3, 4] 
 * Explanation: 
 *     1 <--- 
 *   /  \ 
 *  2    3 <--- 
 *   \    \ 
 *    5    4 <---
 */
public class BinaryTreeRightSideView {

    /**
     * Approach: BFS
     * 
     * At each level we will be interest in the last element. 
     * That element will be our "right view" from this level.
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     * 
     */
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int items = queue.size();
            TreeNode current = null; //to store the last element of the level
            while(items > 0) {
                current = queue.poll();
                if(current.left != null) queue.offer(current.left);
                if(current.right != null) queue.offer(current.right);

                items--;
            }
            //at the end of the loop current will be the last node in the level
            //that is, it will be the righ view of this level
            result.add(current.val);
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
        List<Integer> result = BinaryTreeRightSideView.rightSideView(root);
        result.forEach(System.out::println);
      }
}
