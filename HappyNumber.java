import org.junit.Assert;
import org.junit.Test;

/**
 * Any number will be called a happy number if, after repeatedly replacing it
 *  with a number equal to the sum of the square of all of its digits, 
 * leads us to number ‘1’. All other (not-happy) numbers will never reach ‘1’. 
 * Instead, they will be stuck in a cycle of numbers which does not include ‘1’.
 * We will use the same fast & slow pointer strategy to find the cycle and once the 
 * cycle is found, we will see if the cycle is stuck on number ‘1’ to find out if the
 *  number is happy or not
 * 
 * Time complexity: O(logN)
 * Space complexity: O(1)
 */
public class HappyNumber {

    public static boolean isHappy(int number) {
        int slow = number;
        int fast = number;
        
        do {
            slow = findSquareSum(slow);
            fast = findSquareSum(findSquareSum(fast));
        } while (slow != fast);

        return slow == 1;

    }
    //12
    private static int findSquareSum(int number) {
        int sum = 0;
        int digit = 0;
        while (number > 0) {
            digit = number%10;
            sum += Math.pow(digit, 2);
            number /= 10;
        }
        return sum;
    }

    @Test
    public void validate() {
        Assert.assertTrue(isHappy(23));
        Assert.assertFalse(isHappy(12));
    }
}