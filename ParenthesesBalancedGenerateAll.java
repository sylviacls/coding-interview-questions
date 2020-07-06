import java.util.ArrayList;
import java.util.List;

/**
 * For a given number ‘N’, write a function to generate all combination of ‘N’
 * pairs of balanced parentheses.
 * 
 * Example 1: Input: N=2 Output: (()), ()()
 * 
 * Backtracking approach:
 * - Our Choice
 *   Place a '(' or place a ')' at each stage
 * - Our constrains
 *   We cannot have more  open brackets than N
 *   We cannot close until we open
 * - Our goal:
 *   To place N*2 characteres
 * 
 * 
 * At each point of constructing the string of length 2k we make a choice. 
 * We can place a "(" and recurse or we can place a ")" and recurse. 
 * 
 * But we can't just do that placement, we need 2 critical pieces of information. 
 *  - The amount of left parens left to place.
 *  - The amount of right parens left to place.
 * 
 * We have 2 critical rules at each placement step.
 *  - We can place a left parentheses if we have more than 0 left to place.
 *  - We can only place a right parentheses if there are left parentheses that we can match against.
 * 
 * We know this is the case when we have less left parentheses to place than right parentheses to place.
 * Once we establish these constraints on our branching we know that when we have 0 of both 
 * parens to place that we are done, we have an answer in our base case.
 * 
 * Time complexity:
 * Space complexity O(2*N) = O(N)
 */
public class ParenthesesBalancedGenerateAll {
    
    /**
     * Driver-method
     */
    public static List<String> generateValidParentheses(int num) {
        List<String> result = new ArrayList<String>();
        generateHelper(result, "", 0, 0, num);
        return result;
      }
    
      /**
       * 
       * @param result
       * @param string
       * @param open "(" count
       * @param close ")" count
       * @param max N
       */
      private static void generateHelper(List<String> result, String string, int open, int close, int max) {
        //base case
        //we've reached the maximum number of open and close parentheses, add to the result
        if(string.length() == 2*max) {
            result.add(string);
            return;
        }
        if(open < max) {  // if we can add an open parentheses, add it
            generateHelper(result, string+"(", open+1, close, max);
        }
        if(close < open) { // if we can add a close parentheses, add it
            generateHelper(result, string+")", open, close+1, max);
        }

      }

      public static void main(String[] args) {
        List<String> result = generateValidParentheses(2);
        System.out.println("All combinations of balanced parentheses are: " + result);
    
        result = generateValidParentheses(3);
        System.out.println("All combinations of balanced parentheses are: " + result);
      }
}