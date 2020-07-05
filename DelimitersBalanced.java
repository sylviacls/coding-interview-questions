import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.junit.*;

/**
 * Similiar to ParenthesesBalanced.java But, in this problem we have other types
 * of delimiters, like square brackets and curly braces.
 * 
 * All the delimiters have open and close pairs, and they must match up in
 * exactly the correct order in the input string.
 * 
 * Examples: 
 * ([]{}) is balanced. 
 * ({[]}) is balanced. 
 * ([)] is not balanced.
 * (} is not balanced. 
 * ()} is not balanced. 
 * ({} is not balanced.
 * 
 */
public class DelimitersBalanced {

    /**
     *  We need to track the sequence of open delimiters that got us to our current position.
     *  This is needed when we encounter a close delimiter, as we need to ensure the last 
     *  open delimiter that hasn’t been closed matches the current close delimiter.
     * @param input
     * @return
     */
    public boolean isBalanced(String input) {
        Stack<Character> stack = new Stack<Character>();
        Map<Character, Character> map = new HashMap<Character, Character>();
        map.put('{', '}');
        map.put('[', ']');
        map.put('(', ')');
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(map.containsKey(c)) {
                stack.push(c);
            } else if (map.containsValue(c)){
                if(stack.isEmpty()) {
                    return false;
               } 
                char lastOpen = stack.pop();
                if(c != map.get(lastOpen)) {
                   return false;
                }
            }
        }
        return stack.isEmpty();
    }
    
    @Test
    public void validate() {
        Assert.assertTrue(isBalanced("([]{})"));
        Assert.assertTrue(isBalanced("({[]})"));
        Assert.assertFalse(isBalanced("([)]"));
        Assert.assertFalse(isBalanced("(}"));
        Assert.assertFalse(isBalanced("()}"));
        Assert.assertFalse(isBalanced("({}"));
    }
}