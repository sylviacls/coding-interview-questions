/**
 * Leetcode: 235. Lowest Common Ancestor of a Binary Search Tree
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/solution/
 * 
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in 
 * the BST. According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined
 * between two nodes p and q as the lowest node in T that has both p and q as descendants 
 * (where we allow a node to be a descendant of itself).”
 */
public class BinarySearchTreeFindLowestAncestor {

    /**
     * - Start traversing the tree from the root node.
     * - If both the nodes p and q are in the right subtree, then continue the search
     *   with right subtree starting step 1.
     * - If both the nodes p and q are in the left subtree, then continue the search with 
     *   left subtree starting step 1.
     * - If both step 2 and step 3 are not true, this means we have found the node which 
     *   is common to node p's and q's subtrees. and hence we return this common node as the LCA.
     * 
     * Time Complexity: O(N), where NN is the number of nodes in the BST. 
     *                  In the worst case we might be visiting all the nodes of the BST.
     * Space Complexity: O(N). This is because the maximum amount of space utilized by 
     *                  the recursion stack would be NN since the height of a skewed BST could be N.
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        //both values are lesser than root, then they are in the left subtree
        if(p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        //both values are greatr than root, then they are in the right subtree
        else if(p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        else { //We have found the split point
            return root;
        }
    }

    /**
     * Time Complexity : O(N)
     * Space Complexity : O(1)
     */
    public static TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {

        while (root != null) {
            //both values are lesser than root, then they are in the left subtree
            if(p.val < root.val && q.val < root.val) {
                root = root.left;
            }
            //both values are greatr than root, then they are in the right subtree
            else if(p.val > root.val && q.val > root.val) {
                root = root.right;
            }
            else { //We have found the split point
                return root;
            }
        }
        return null;
    }

    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(0);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(5);
        
        System.out.println((lowestCommonAncestor(root, root.left, root.right.right).val));
        System.out.println((lowestCommonAncestorII(root, root.left, root.right.right).val));

        
        System.out.println((lowestCommonAncestor(root, root.right.left, root.right.right).val));
        System.out.println((lowestCommonAncestorII(root, root.right.left, root.right.right).val));

        System.out.println((lowestCommonAncestor(root, root.left, root.right.left).val));
        System.out.println((lowestCommonAncestorII(root, root.left, root.right.left).val));
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