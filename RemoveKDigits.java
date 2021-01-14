import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * Leetcode: 402. Remove K Digits https://leetcode.com/problems/remove-k-digits/
 * 
 * Given a non-negative integer num represented as a string, remove k digits
 * from the number so that the new number is the smallest possible. Note: The
 * length of num is less than 10002 and will be ≥ k. The given num does not
 * contain any leading zero.
 * 
 * Example 1: Input: num = "1432219", k = 3 Output: "1219" Explanation: Remove
 * the three digits 4, 3, and 2 to form the new number 1219 which is the
 * smallest.
 * 
 * Example 2: Input: num = "10200", k = 1 Output: "200" Explanation: Remove the
 * leading 1 and the number is 200. Note that the output must not contain
 * leading zeroes.
 */
public class RemoveKDigits {
    /**
     * Approach: Stack
     * 
     * Traverse the string putting each digit into the stack while controlling:
     * - If the current number is less than previous (top of the stack):
     * --- remove the top of the stack, descrease k 
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static String removeKdigits(String num, int k) {
         //corner case
         if(k==num.length()) return "0";

        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < num.length(); i++) {
            char ch = num.charAt(i);
             //whenever meet a digit which is less than the previous digit, discard the previous one
            while(k > 0 && !stack.empty() && stack.peek() >ch) {
                stack.pop();
                k--;
            }
            stack.push(ch);
        }

        // corner case like "1111"
        while (k > 0) {
            stack.pop();
            k--;
        }

        //construct the number from the stack
        StringBuilder sb = new StringBuilder();
        while(!stack.empty()) {
            sb.append(stack.pop());
        }
        sb.reverse();

         //remove all the 0 at the head
         while(sb.length() > 1 && sb.charAt(0)=='0') {
            sb.deleteCharAt(0);
         }

        return sb.toString();
    }

    @Test
    public void validate(){
        Assert.assertEquals("1219", removeKdigits("1432219", 3));
        Assert.assertEquals("200", removeKdigits("10200", 1));
        Assert.assertEquals("11", removeKdigits("112", 1));
        Assert.assertEquals("0", removeKdigits("1234567890", 9));
    }
}
