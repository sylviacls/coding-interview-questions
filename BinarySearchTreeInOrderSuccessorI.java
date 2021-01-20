/**
 * Leetcode: 285. Inorder Successor in BST II
 * https://leetcode.com/problems/inorder-successor-in-bst
 * 
 * Given a binary search tree and a node in it, 
 * find the in-order successor of that node in the BST.
 */
public class BinarySearchTreeInOrderSuccessorI {

    /**
     * In a BST, if a given node:
     * - 1) has the right subtree -> the inorder successor will be the left most node in the right 
     *                           subtree (the minimum value of this subtree)
     * - 2) has no right subtree -> the inorder succell is an ancestor in wich this node would be
     *                           in the left subtree
     * Time Complexity: O(H), Height of the tree
     * @param root
     * @param p
     * @return
     */
    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null) return null;

        TreeNode node = findNode(root, p); //O(H)
        if (node == null) return null;

        if(node.right != null) { //case 1
            return findMin(node.right);
        } else {
            // Case 2: p.right == null
            // Start from root and search for successor down the tree
            TreeNode succ = null;
            TreeNode pointer = root;
            while (pointer != null) {
                if (p.val < pointer.val) {
                    succ = pointer;
                    pointer = pointer.left;
                } else if (p.val > pointer.val)
                    pointer = pointer.right;
                else
                    break;
            }

            return succ;
        }
    }

    private static TreeNode findMin(TreeNode node) {
        if(node == null) return null;
        
        TreeNode current = node;;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private static TreeNode findNode(TreeNode root, TreeNode p) {
        TreeNode current = root;

        while(current != null) {
            if(current.val == p.val) return current;
            if(p.val < current.val) {
                current = current.left;
            } else {
                current = current.right;
            }

        }
        return null;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(8);
        root.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(6);
        root.left.left.right.left = new TreeNode(5);

        root.right = new TreeNode(15);
        root.right.left = new TreeNode(13);

        System.out.println(inorderSuccessor(root, new TreeNode(6)).val);
        System.out.println(inorderSuccessor(root, new TreeNode(4)).val);
        System.out.println(inorderSuccessor(root, new TreeNode(13)).val);
    }

}