/**
 * Leetcode: 543. Diameter of Binary Tree
 * https://leetcode.com/problems/diameter-of-binary-tree/
 * 
 * Given a binary tree, you need to compute the length of the diameter of the tree. 
 * The diameter of a binary tree is the length of the longest path between any two nodes
 * in a tree. This path may or may not pass through the root.
 * 
 * Example:
 * Given a binary tree
          1
         / \
        2   3
       / \     
      4   5    
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * 
 * Note: The length of path between two nodes is represented by the number of edges 
 * between them.
 */
public class BinaryTreeDiameter {

    static int maxDiameter;

    /**
     * Approach: DFS
     * 
     * At every step, we need to find the height of both children of the current node.
     *  For this, we will make two recursive calls similar to DFS.
     * The height of the current node will be equal to the maximum of the heights of its 
     * left or right children, plus ‘1’ for the current node.
     * The tree diameter at the current node will be equal to the height of the left child 
     * plus the height of the right child plus ‘1’ for the current node: 
     * diameter = leftTreeHeight + rightTreeHeight + 1. 
     * To find the overall tree diameter, we will use a class level variable.
     * This variable will store the maximum diameter of all the nodes visited so far, 
     * hence, eventually, it will have the final tree diameter.
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        maxDiameter = 0;
        heightDFS(root);
        return maxDiameter;  
    }

    private static int heightDFS(TreeNode node) {
        if(node == null) return 0;

        int leftCount = heightDFS(node.left);
        int rightCount = heightDFS(node.right);

        // diameter at the current node will be equal to the height of left subtree +
        // the height of right sub-trees + '1' for the current node
        int diameter = (leftCount + rightCount) + 1;
        // update the global tree diameter
        maxDiameter = Math.max(maxDiameter, diameter);

        // height of the current node will be equal to the maximum of the hights of
        // left or right subtrees plus '1' for the current node
        return Math.max(leftCount, rightCount) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        System.out.println("Tree Diameter: " + BinaryTreeDiameter.diameterOfBinaryTree(root));
        root.left.left = null;
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        root.right.left.right.left = new TreeNode(10);
        root.right.right.left.left = new TreeNode(11);
        System.out.println("Tree Diameter: " + BinaryTreeDiameter.diameterOfBinaryTree(root));
      }
}
