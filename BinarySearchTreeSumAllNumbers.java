/**
 * Cracking the PM interview
 * 16.6 Given a binary search tree which contains integers as values, calculate the sum of
 * all the numbers.
 * 
 */
public class BinarySearchTreeSumAllNumbers {

    /**
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     * @param root
     * @return the sum of all numbers
     */
     public static long sumAllNumberRecursive(TreeNode root) {
         if(root == null) {
           return 0;
         }
         long leftSum = sumAllNumberRecursive(root.left);
         long rightSum = sumAllNumberRecursive(root.right);
         
         return root.val + leftSum + rightSum;
    }

    public static void main(String[] args) {
      TreeNode root = new TreeNode(4);
      root.left = new TreeNode(2);
      root.right = new TreeNode(7);
      root.left.left = new TreeNode(1);
      root.left.right = new TreeNode(3);
      root.right.left = new TreeNode(5);

      System.out.println(sumAllNumberRecursive(root));
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