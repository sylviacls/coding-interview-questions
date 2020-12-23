import java.util.LinkedList;
import java.util.Queue;

/**
 * Cracking the PM interview 16.5 Insert an element into a binary search tree
 * (in order). You may assume that the binary search tree contains integers.
 * 
 */
public class BinarySearchTreeInsertElement {
    public static boolean insert(TreeNode node, int data) {
        if(node.val == data) return false; //duplicate value

        if(data > node.val) {
            if(node.right == null) {
                node.right = new TreeNode(data);
                return true;
            } else {
                return insert(node.right, data);
            }
        } else {
            if(node.left == null) {
                node.left = new TreeNode(data);
                return true;
            } else {
                return insert(node.left, data);
            }
        }
    }

    public static void printTree(TreeNode root) {
        System.out.print(root.val + ", ");

        if(root.left != null) {
            printTree(root.left);
        }

        if(root.right != null) {
            printTree(root.right);
        }
    }

    public static void printLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        queue.add(root);
        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            while(levelSize > 0) {
                TreeNode curr = queue.poll();
                System.out.print(curr.val + " ");
                if(curr != null) {
                    if(curr.left != null) queue.add(curr.left);
                    if(curr.right != null) queue.add(curr.right);
                }
                levelSize--;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
  
        System.out.println(insert(root, 8));
        printTree(root);
        System.out.println(insert(root, 10));
        printLevelOrder(root);
      }


}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
  
    TreeNode(int x) {
      val = x;
    }

};