/**
 * Leetcode: 224. Basic Calculator
 * https://leetcode.com/problems/basic-calculator/
 * 
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string may contain open ( and closing parentheses ), the plus + or 
 * minus sign -, non-negative integers and empty spaces .
 * 
 * Example 1:
 * Input: "1 + 1"
 * Output: 2
 * 
 * Example 2:
 * Input: " 2-1 + 2 "
 * Output: 3
 * 
 * Example 3:
 * Input: "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 */
import java.util.*;

import org.junit.Assert;
import org.junit.Test;
public class BasicCalculator {

    public static int calculate(String s) {
        s = s.replaceAll("\\s+", "");
        int result = 0;
        int lastSign = 1;
        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //is a Digit
            if(Character.isDigit(c)) {
                int num = Character.getNumericValue(c);
                while(i + 1 < s.length() && Character.isDigit(s.charAt(i+1))) {
                    num = num*10 + Character.getNumericValue(s.charAt(i+1));
                    i++;
                }
                result += lastSign*num;
            } else if(c == '+'){  //is '+'
                lastSign = 1;
            } else  if(c == '-'){  //is '-'
                lastSign = -1;
            } else if(c == '(') { //is '(''
                stack.push(result);
                stack.push(lastSign);
                result = 0;
                lastSign = 1;
            } else { //is '')''
                int sign = stack.pop();
                int num = stack.pop();
                result = result*sign + num;
            }
        }
        return result;
    }

    @Test
    public void validate() {
        Assert.assertEquals(10, calculate("12+(1-3)"));
        Assert.assertEquals(23, calculate("(1+(4+5+2)-3)+(6+8)"));
        Assert.assertEquals(2147483647, calculate("2147483647"));
        Assert.assertEquals(-4, calculate("1-(5)"));
    }
}
