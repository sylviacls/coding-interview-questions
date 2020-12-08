/**
 * Leetcode: 110. Balanced Binary Tree
 * https://leetcode.com/problems/balanced-binary-tree/
 * 
 * Check if a binary tree is height-balanced
 * 
 * Consider a height-balancing scheme where following conditions should be checked to determine 
 * if a binary tree is balanced.
 * An empty tree is height-balanced. A non-empty binary tree T is balanced if:
 * 1) Left subtree of T is balanced
 * 2) Right subtree of T is balanced
 * 3) The difference between heights of left subtree and right subtree is not more than 1.
 */
public class BinaryTreeCheckBalance {
    
    boolean isBalanced = true;
    public boolean isBalanced(TreeNode root) {
        if(root == null) return isBalanced;
        backtrack(root);
        return isBalanced;
    }
    private int backtrack(TreeNode root){
        if(root == null) return 0;

        int left = backtrack(root.left);
        int right = backtrack(root.right);

        if(Math.abs(left-right) > 1) isBalanced = false;
        
        return Math.max(left,right)+1;
    }

}
