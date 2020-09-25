import org.junit.*;

/**
 * Leetcode: 20. Valid Parentheses
 * https://leetcode.com/problems/valid-parentheses/
 * 
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', 
 * determine if the input string is valid.
 * 
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * 
 * Example 1:
 * Input: s = "()"
 * Output: true
 * 
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 * 
 * Example 3:
 * Input: s = "([)]"
 * Output: false
 * 
 */
import java.util.*;
public class ParenthesesBalanced {

    /**
     * Approach: Stack
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static boolean isBalanced(String input){
        Map<Character, Character> map = new HashMap<Character,Character>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(map.containsKey(c)) { // its an open-char
                stack.add(c);
            } else { //its a close-char
                if (stack.isEmpty() || map.get(stack.peek()) != c) { //it's not the expected close-char
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    /**
     * Just '(' and ')'
     * 
     * One way to think about this problem is that every open parenthesis moves you away from 
     * your starting point in some direction, and every close parenthesis moves you back towards 
     * the start.
     * 
     * Each open parenthesis increases the height by one, and each close parenthesis decreases 
     * the height by one. At the end, the height needs to be zero, and we must never dip below zero.
     * 
     * Alternativelly, We could use a stack to control this approach. See: DelimiterBalanced.java
     */
    public static boolean isBalancedII(String input){
       // We keep around just the height, incrementing and decrementing as necessary.
       // Furthermore, if the height drops below zero, we can terminate early.
        int height = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == '(')  {
                height++;
            } else if (c == ')') {
                height--;
                //if height gets negative, the parentheses are not balanced
                if(height < 0) {
                    return false;
                }
            }
        }
        return height == 0;
    }
    
    @Test
    public void validate() {
        Assert.assertTrue(isBalanced("()[]{}"));
        Assert.assertTrue(isBalanced("([])"));
        Assert.assertTrue(isBalanced("(()(()()))"));
        Assert.assertFalse(isBalanced(")("));
        Assert.assertFalse(isBalanced("]"));
        Assert.assertFalse(isBalanced("([)]"));
        Assert.assertFalse(isBalanced("((()"));
        Assert.assertFalse(isBalanced("()))"));
    }
}