import org.junit.Assert;
import org.junit.Test;

/**
 * Leetcode: 11. Container With Most Water
 * https://leetcode.com/problems/container-with-most-water/
 * 
 * Given n non-negative integers a1,a2,.., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0).
 * Find two lines, which, together with the x-axis forms a container, such that the container 
 * contains the most water.
 */
public class ContainerWithMostWater {
    /**
     * Approach: Brute-force
     * Points x, y
     * A(x,y) = Min(x,y)*(iY -iX)	
     * Time Complexity: O(N^2)
     * Space Complexity: O(1)
     */
    public static int maxArea(int[] height) {
        int maxArea = Integer.MIN_VALUE;
        if(height == null) return 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i+1; j < height.length; j++) {
                int currArea = Math.min(height[i], height[j]) * (j-i);
                maxArea = Math.max(maxArea, currArea);
            }
        }
        return maxArea;
    }

    /**
     * Approach: Two Pointers
     * 
     * The intuition behind this approach is that the area formed between the lines will always 
     * be limited by the height of the shorter line. Further, the farther the lines, 
     * the more will be the area obtained.
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static int maxAreaII(int[] height) {
        int maxArea = Integer.MIN_VALUE;
        if(height == null) return 0;

        int i = 0;
        int j = height.length-1;
        while (i < j) {
            int currArea = Math.min(height[i], height[j]) * (j-i);
            maxArea = Math.max(currArea, maxArea);
            if(height[i] < height[j]) i++;
            else j--;
        }
        return maxArea;
    }
    @Test
    public void validate() {
        int[] heights = new int[]{1,8,6,2,5,4,8,3,7};
        Assert.assertEquals(49, maxArea(heights));
        Assert.assertEquals(49, maxAreaII(heights));

        int[] heights2 = new int[]{1,1};
        Assert.assertEquals(1, maxArea(heights2));
        Assert.assertEquals(1, maxAreaII(heights2));
    }
}