import java.util.*;

import org.junit.*;

/**
 * Leetcode: 22. Generate Parentheses
 * https://leetcode.com/problems/generate-parentheses/
 * 
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
 */
public class ParenthesesBalancedGenerateAll {
    
    /**
     * Approach: Backtracking
     * 
     * Time complexity: O(4^N / squareOf(n) is bounded by the Catalan number and is beyond the scope of a coding interview
     * Space complexity O(2*N) = O(N)
     * 
     * Runtime 1m - Memory 39.1MB
     */
    public static List<String> generateValidParentheses(int num) {
        List<String> result = new ArrayList<String>();
        generateHelper(result, "", 0, 0, num);
        return result;
    }
    
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

    /**
     * Approach: DFS
     * 
     * Time complexity: O(4^N / squareOf(n) is bounded by the Catalan number and is beyond the scope of a coding interview
     * Space complexity: O(N* 2^N)
     * 
     * Runtime 2ms - Memory 39.5
     */
    public static List<String> generateValidParenthesesII(int num) {
      List<String> result = new ArrayList<String>();
      Queue<ParenthesesString> queue = new LinkedList<ParenthesesString>();
      queue.offer(new ParenthesesString("", 0, 0));

      while(!queue.isEmpty()) {
        ParenthesesString ps = queue.poll();
        if(ps.openCount == num && ps.closedCount == num) {
          result.add(ps.string);
        }
        if(ps.openCount < num) {
          queue.offer(new ParenthesesString(ps.string+"(", ps.openCount+1, ps.closedCount));
        }
        if(ps.openCount > 0 && ps.closedCount < ps.openCount) {
          queue.offer(new ParenthesesString(ps.string+")", ps.openCount, ps.closedCount+1)); 
        }
      }
      return result;
    }

    public static void main(String[] args) {
      System.out.println(generateValidParenthesesII(2));
    }

      @Test
      public void validate() {
        List<String> result = generateValidParentheses(2);
        String[] expected = new String[]{"(())","()()"};
        Assert.assertArrayEquals(expected, result.toArray(new String[result.size()]));
    
        result = generateValidParentheses(3);
        expected = new String[]{"((()))", "(()())", "(())()", "()(())", "()()()"};
        System.out.println("All combinations of balanced parentheses are: " + result);
      }

}

class ParenthesesString {
  String string;
  int openCount;
  int closedCount;
  public ParenthesesString(String string, int openCount, int closedCount) {
    this.string = string;
    this.openCount = openCount;
    this.closedCount = closedCount;
  }
}