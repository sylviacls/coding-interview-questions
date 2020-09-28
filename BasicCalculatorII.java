/**
 * Leetcode: 227. Basic Calculator II
 * https://leetcode.com/problems/basic-calculator-ii/
 * 
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string contains only non-negative integers, +, -, *, / operators and 
 * empty spaces . The integer division should truncate toward zero.
 * 
 * Example 1:
 * Input: "3+2*2"
 * Output: 7
 * 
 * Example 2:
 * Input: " 3/2 "
 * Output: 1
 * 
 * Example 3:
 * Input: " 3+5 / 2 "
 * Output: 5
 */
import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class BasicCalculatorII {

    /**
     * Approach: Using Stack
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static int calculate(String s) {
        s = s.replaceAll("\\s+", "");
        int num = 0;
        char lastSign = '+';
        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < s.length(); i++) {
            if(Character.isDigit(s.charAt(i))) { //it is a digit
                num =  Character.getNumericValue(s.charAt(i));
                while(i+1 < s.length() && Character.isDigit(s.charAt(i+1))) {
                    num = num*10 + Character.getNumericValue(s.charAt(i+1));
                    i++;
                }
                switch(lastSign) {
                    case '+':
                        stack.add(num);
                        break;
                    case '-':
                        stack.add(-num);
                        break;
                    case '*':
                        stack.add(stack.pop()*num);
                        break;
                    case '/':
                        stack.add(stack.pop()/num);
                        break;
                }
                num = 0;
            } 
            if (!Character.isDigit(s.charAt(i))) {
                lastSign = s.charAt(i);
            }
        }
        int result = 0;
        while(!stack.isEmpty()){
            result += stack.pop();
        }

        return result;
    }

    public static int calculateII(String s) {
        s = s.replaceAll("\\s+", "");
        int num = 0;
        char lastSign = '+';
        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < s.length(); i++) {
            if(Character.isDigit(s.charAt(i))) { //it is a digit
                num =  Character.getNumericValue(s.charAt(i));
                while(i+1 < s.length() && Character.isDigit(s.charAt(i+1))) {
                    num = num*10 + Character.getNumericValue(s.charAt(i+1));
                    i++;
                }
            } 
            if (!Character.isDigit(s.charAt(i)) || i == s.length() - 1) {
                switch(lastSign) {
                    case '+':
                        stack.add(num);
                        break;
                    case '-':
                        stack.add(-num);
                        break;
                    case '*':
                        stack.add(stack.pop()*num);
                        break;
                    case '/':
                        stack.add(stack.pop()/num);
                        break;
                }
                num = 0;
                lastSign = s.charAt(i);
            }
        }

        int result = 0;
        while(!stack.isEmpty()){
            result += stack.pop();
        }

        return result;
    }

    @Test
    public void validate() {
        Assert.assertEquals(7, calculateII("3+2*2"));
        Assert.assertEquals(5, calculateII("3+5 / 2"));
    }  
}
