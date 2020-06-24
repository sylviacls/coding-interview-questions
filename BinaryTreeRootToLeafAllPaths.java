import java.util.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given a binary tree, return all root-to-leaf paths.
 */
public class BinaryTreeRootToLeafAllPaths {

    public static List<List<Integer>> findAllPaths(TreeNode root) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<Integer>();
        findPath(root, currentPath,allPaths);
        return allPaths;
    }

    private static void findPath(TreeNode root, List<Integer> currentPath, List<List<Integer>> allPaths) {
        if(root == null) return;

        currentPath.add(root.val);

        if(root.left == null && root.right == null) {
            allPaths.add(new ArrayList<>(currentPath));
        }

        findPath(root.left, currentPath, allPaths);
        findPath(root.right, currentPath, allPaths);

        //after visiting its children, we remove the node from current path
        currentPath.remove(currentPath.size()-1);
    }
    
    @Test
    public void validate() {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        List<List<Integer>> result = findAllPaths(root);
        Assert.assertEquals(3, result.size());
        Assert.assertEquals("[[12, 7, 4], [12, 1, 10], [12, 1, 5]]", result.toString());
    }
}