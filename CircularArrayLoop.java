import org.junit.*;

/**
 * Leetcode: 457. Circular Array Loop
 * 
 * You are given a circular array nums of positive and negative integers. 
 * If a number k at an index is positive, then move forward k steps.
 * Conversely, if it's negative (-k), move backward k steps. Since the array is circular,
 * you may assume that the last element's next element is the first element, and the first
 * element's previous element is the last element.
 * 
 * Determine if there is a loop (or a cycle) in nums.
 * A cycle must start and end at the same index and the cycle's length > 1. 
 * Furthermore, movements in a cycle must all follow a single direction.
 * In other words, a cycle must not consist of both forward and backward movements.
 * 
 * Example 1:
 * Input: [2,-1,1,2,2]
 * Output: true
 * Explanation: There is a cycle, from index 0 -> 2 -> 3 -> 0. The cycle's length is 3.
 * 
 * Example 2:
 * Input: [-1,2]
 * Output: false
 * Explanation: The movement from index 1 -> 1 -> 1 ... is not a cycle, because the cycle's
 * length is 1. By definition the cycle's length must be greater than 1.
 */
public class CircularArrayLoop {

    /**
     * Approach: Two Pointers - slow/fast
     * 
     * We can start from each index of the array to find the cycle. If a number does not
     * have a cycle we will move forward to the next element.
     * 
     * The cycle should have more than one element. This means that when we move a pointer
     * forward, if the pointer points to the same element after the move, we have a
     * one-element cycle. Therefore, we can finish our cycle search for the current element.
     * 
     * The cycle should not contain both forward and backward movements. We will handle 
     * this by remembering the direction of each element while searching for the cycle.
     *  If the number is positive, the direction will be forward and if the number is 
     * negative, the direction will be backward. So whenever we move a pointer forward, 
     * if there is a change in the direction, we will finish our cycle search right there 
     * for the current element.
     * 
     * Time Complexity: O(N^2)
     * Space Complexity: O(1)
     * 
     */
    public static boolean circularArrayLoop(int[] nums) {
        
        for (int i = 0; i < nums.length; i++) {
            boolean isForward = nums[i] > 0;
            int slow = i;
            int fast = i;
            do {
                //slow makes 1 move
                slow = findNextIndex(nums, isForward, slow);
                //fast makes 2 moves
                fast = findNextIndex(nums, isForward, fast);
                if(fast != -1) fast = findNextIndex(nums, isForward, fast);

            } while (slow != -1 && fast != -1 && slow != fast );
            if(slow != -1 && slow == fast) return true; //there is a cycle
        }   
        return false;
    }

    private static int findNextIndex(int[] nums, boolean isForward, int currIndex) {
        //there is a change in the direction of current path
        if(isForward && nums[currIndex] < 0 || !isForward && nums[currIndex] > 0) return -1;

        //handle negative-backward indexes
        int nextIndex = 0;
        nextIndex = (currIndex + nums[currIndex]) % nums.length;
        //wrapping negative numbers
        if(nextIndex < 0) nextIndex = nums.length + nextIndex;

        //one-element cycle
        if(currIndex == nextIndex) return -1;

        return nextIndex;
    }

    @Test
    public void validate() {
        Assert.assertTrue(circularArrayLoop(new int[]{ 2, -1, 1, 2, 2}));
        Assert.assertTrue(circularArrayLoop(new int[] { 1, 2, -1, 2, 2 }));
        Assert.assertTrue(circularArrayLoop(new int[] { 2, 2, -1, 2 }));
        Assert.assertFalse(circularArrayLoop(new int[] { 2, 1, -1, -2 }));
        Assert.assertFalse(circularArrayLoop(new int[] {-1, 2}));
        Assert.assertFalse(circularArrayLoop(new int[] {-1,-2,-3,-4,-5}));
    }
}