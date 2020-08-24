import org.junit.*;
import java.util.*;

/**
 * Leetcode: 202. Happy Number
 * 
 * A happy number is a number defined by the following process: 
 * Starting with any positive integer, replace the number by the sum of the squares of 
 * its digits, and repeat the process until the number equals 1 (where it will stay),
 * or it loops endlessly in a cycle which does not include 1. 
 * Those numbers for which this process ends in 1 are happy numbers.
 * 
 * Return True if n is a happy number, and False if not.
 * Example: 
 * Input: 19
 * Output: true
 * Explanation: 
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 * 
 */
public class HappyNumber {

    /**
     * Approach: Two Pointers - slow/fast
     * 
     * Time complexity: O(logN)
     * Space complexity: O(1)
     */
    public static boolean isHappy(int number) {
        int slow = number;
        int fast = number;
        
        do {
            slow = findSquareSum(slow);
            fast = findSquareSum(findSquareSum(fast));
        } while (slow != fast);

        return slow == 1;
    }

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

    /**
     * Approach: Using a HashSet
     * 
     * We will keep track of all calcutated "squared numbers" in a hashset
     * If we reach a number already stored in the set, then we have a cycle.
     * 
     */
    public static boolean isHappyII(int number) {
        Set<Integer> calculatedNumbers = new HashSet<Integer>();
        calculatedNumbers.add(number);

        while (number != 1) {
            number = findSquareSum(number);
            if(calculatedNumbers.contains(number)) return false;
            calculatedNumbers.add(number);
        }
         return true;
    }

    @Test
    public void validate() {
        Assert.assertTrue(isHappy(23));
        Assert.assertTrue(isHappyII(23));

        Assert.assertFalse(isHappy(12));
        Assert.assertFalse(isHappyII(12));
    }
}