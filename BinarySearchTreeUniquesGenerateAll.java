/**
 * Leetcode: 95. Unique Binary Search Trees II
 * https://leetcode.com/problems/unique-binary-search-trees-ii/
 * 
 * Given an integer n, generate all structurally unique BST's (binary search trees) that 
 * store values 1 ... n.
 * 
 * Example:
 * Input: 3
 * Output:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 
 * Explanation:
 * The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 */
import java.util.*;
public class BinarySearchTreeUniquesGenerateAll {
    
    /**
     * Picking the i-th node as root, the left subtree will contain elements 1 to (i-1),
     * and the right subtree will contain elements (i+1) to n. 
     * It uses recursive calls to get back all possible trees for left and right subtrees 
     * and combine them in all possible ways with the root.
     */
    public static List<TreeNode> generateTrees(int n) {
        if(n <= 0) return new ArrayList<TreeNode>();
        return generateTrees(1,n);
    }

    private static List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> result = new ArrayList<TreeNode>();

        //base case
        // if start > end then subtree will be empty so returning NULL in the list 
        if(start > end) {
            result.add(null);
            return result;
        }
           
        ///iterating through all values from start to end  for constructing 
        //left and right subtree recursively 
        for (int i = start; i <= end; i++) {
            //making i the root of subtree
            List<TreeNode> leftSubTrees = generateTrees(start, i - 1);
            List<TreeNode> rightSubTrees = generateTrees(i + 1, end);
            ///now looping through all left and right subtrees and connecting  
            //them to ith root  below 
            for (TreeNode nodeLeft : leftSubTrees) {
                for (TreeNode nodeRight : rightSubTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = nodeLeft;
                    root.right = nodeRight;
                    result.add(root);
                }
            }
        }
        return result;
    }

    public static void traverse(TreeNode root) {
        if(root != null ) {
            System.out.print(root.val);
            traverse(root.left);         
            traverse(root.right);
        }
    }
    public static void main(String[] args) {
       List<TreeNode> results = generateTrees(3);
       for (TreeNode treeNode : results) {
           traverse(treeNode);
           System.out.println();
       }
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