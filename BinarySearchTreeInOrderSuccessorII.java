/**
 * Leetcode: 510. Inorder Successor in BST II
 * https://leetcode.com/problems/inorder-successor-in-bst-ii
 * 
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * The successor of a node p is the node with the smallest key greater than p.val. 
 * You will have direct access to the node but not to the root of the tree. 
 * Each node will have a reference to its parent node.
 */
public class BinarySearchTreeInOrderSuccessorII {

    /**
     *  In a BST, if a given node:
     * 1) has the right subtree -> the inorder successor will be the left most node in the right 
     *                           subtree (the minimum value of this subtree)
     * 2) has no right subtree -> the inorder succell is an ancestor in wich this node would be
     *                           in the left subtree
     * Time Complexity: O(H), Height of the tree
     */
    public static BSTNode inorderSuccessor(BSTNode node) {
        if(node == null) return null;

        //case 1: right child is not null -> go down to get the next
        if(node.right != null) { //case 1
            return findMin(node.right);
        } else {
            //case 2: right child is null -> go up to the parent, 
            //until the node is a left child, return the parent
      //      BSTNode successor = null;
            BSTNode ancestor = node.parent;
            while(ancestor != null && ancestor.val < node.val) {
        //        successor = ancestor;
                ancestor = ancestor.parent;
            }
            return ancestor;
        }
       
    }

    private static BSTNode findMin(BSTNode node) {
        if(node == null) return null;
        
        BSTNode current = node;;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public static void main(String[] args) {
        BSTNode root = new BSTNode(12);
        root.left = new BSTNode(8);
        root.left.parent = root;
        root.left.left = new BSTNode(4);
        root.left.left.parent = root.left;
        root.left.left.right = new BSTNode(6);
        root.left.left.right.parent = root.left.left;
        root.left.left.right.left = new BSTNode(5);
        root.left.left.right.left.parent = root.left.left.right;

        root.right = new BSTNode(15);
        root.right.parent = root;
        root.right.left = new BSTNode(13);
        root.right.left.parent = root.right;

        System.out.println(inorderSuccessor(root.left.left.right).val);//6
        System.out.println(inorderSuccessor(root.left.left).val);//4
        System.out.println(inorderSuccessor(root.right.left).val);//13
    }
}

// Definition for a Node.
class BSTNode {
    public int val;
    public BSTNode left;
    public BSTNode right;
    public BSTNode parent;

    public BSTNode(int val) {
        this.val = val;
    }
};
