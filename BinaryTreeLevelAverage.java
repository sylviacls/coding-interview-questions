import java.util.*;

import org.junit.*;

/**
 * Leetcode:  637. Average of Levels in Binary Tree
 * https://leetcode.com/problems/average-of-levels-in-binary-tree/
 * 
 * Given a binary tree, populate an array to represent the averages of all of
 * its levels. 
 */
public class BinaryTreeLevelAverage {

    /**
     * Approach: BFS
     * 
     * Time complexity O(N): where ‘N’ is the total number of nodes in the tree
     * Space complexity O(N): as we need to return a list containing the level order traversal. 
     *                       We will also need O(N)O(N) space for the queue.
     */
    public static List<Double> levelAverages(TreeNode root){
        List<Double> levelAverages = new ArrayList<Double>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        if(root == null) return levelAverages;

        queue.offer(root);
        while(!queue.isEmpty()){
            double levelSum = 0;
            int levelSize = queue.size();
            int levelCount = queue.size();
            while(levelSize > 0) {
                TreeNode current = queue.poll();
                levelSum += current.val;
                if(current.left != null) {
                    queue.offer(current.left);
                }
                if(current.right != null){
                    queue.offer(current.right);
                }
                levelSize--;
            }
            levelAverages.add(levelSum/levelCount); 
        }
        return levelAverages;
    }

    @Test
    public void validade() {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        List<Double> result = levelAverages(root);
        Assert.assertEquals("[12.0, 4.0, 6.5]", result.toString());
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