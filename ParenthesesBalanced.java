import org.junit.*;

/**
 * Given a string containing only ( and ), are the parentheses in the string balanced?
 *  For the parentheses to be balanced, each open parenthesis must have a corresponding
 *  close parenthesis, in the correct order. 
 * 
 * For example:
 * ((())) is balanced.
 * (()(()())) is balanced.
 * )( is not balanced.
 * ((() is not balanced.
 * ())) is not balanced.
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
public class ParenthesesBalanced {

    public static boolean isBalanced(String input){
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
        Assert.assertTrue(isBalanced("()"));
        Assert.assertTrue(isBalanced("(()(()()))"));
        Assert.assertFalse(isBalanced(")("));
        Assert.assertFalse(isBalanced("((()"));
        Assert.assertFalse(isBalanced("()))"));
    }
}