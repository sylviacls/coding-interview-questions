import java.util.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given a binary tree, find the root-to-leaf path with the maximum sum.
 * 
 * Time Complexity: O(N logN) - O (N²) Space Complexity: O(N Log N) the depth
 * (or height) of a balanced binary tree is O(logN) at the most, each path can
 * have logN nodes in it allPaths list will be O(N*logN)
 */
public class BinaryTreeRootToLeafMaxSum {
    static int maxSum;
    static List<Integer> maxPath;

    public static List<Integer> findPathMaxSum(TreeNode root) {
        List<Integer> currentPath = new ArrayList<Integer>();
        maxPath = new ArrayList<Integer>();
        maxSum = Integer.MIN_VALUE;
        findPath(root, currentPath);
        return maxPath;
    }

    public static void findPath(TreeNode root, List<Integer> currentPath) {
        if (root == null)
            return;

        currentPath.add(root.val);

        if (root.left == null && root.right == null) {
            int currentSum = 0;
            for (Integer value : currentPath) {
                currentSum += value;
            }
            if(currentSum > maxSum) {
                maxSum = currentSum;
                maxPath = new ArrayList<>(currentPath);
            }
        }

        findPath(root.left, currentPath);
        findPath(root.right, currentPath);

        // after visiting its children, we remove the node from current path
        currentPath.remove(currentPath.size() - 1);
    }

    @Test
    public void validate() {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        List<Integer> result = findPathMaxSum(root);
        Assert.assertEquals(Arrays.asList(12,7,4), result);
    }

}