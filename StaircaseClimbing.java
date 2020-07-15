import org.junit.*;

/**
 * You are climbing a stair case. 
 * It takes n steps to reach to the top. 
 * Each time you can either climb 1 or 2 steps.
 * In how many distinct ways can you climb to the top?
 */
public class StaircaseClimbing {

    /**
     * Fibonnaci Number approach
     * Time complexity: O(n): Single loop upto n is required to calculate n^{th} fibonacci number.
     * Space complexity: O(1): Constant space is used.
     * @param steps
     * @return
     */
    public static int climbStairs(int steps) {
        if(steps < 3) {
            return steps;
        }
        int first = 1;
        int second = 2;

        for (int i = 3; i <= steps; i++) {
            int current = first + second;
            first = second;
            second = current;
        }
        return second;
    }

    /**
     * Dynamic Programming approach
     * Time complexity: O(n): Single loop upto n is required 
     * Space complexity: O(N): array of size n
     * @param steps
     * @return
     */
    public static int climbStairsII(int steps) {
        if (steps < 3) {
            return steps;
        }
        int[] memo = new int[steps+1];
        memo[0] = 0;
        memo[1] = 1;
        memo[2] = 2;
        for (int i = 3; i <= steps; i++) {
            memo[i] = memo[i-1] + memo[i-2];
        }
        return memo[steps];
    }

    @Test
    public void validate() {
        Assert.assertEquals(1, climbStairs(1));
        Assert.assertEquals(5, climbStairs(4));
        Assert.assertEquals(8, climbStairs(5));
        Assert.assertEquals(13, climbStairs(6));

        Assert.assertEquals(1, climbStairsII(1));
        Assert.assertEquals(5, climbStairsII(4));
        Assert.assertEquals(8, climbStairsII(5));
        Assert.assertEquals(13, climbStairsII(6));
    }
}