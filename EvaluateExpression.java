import java.util.*;

/**
 * Given an expression containing digits and operations (+, -, *), find all possible ways 
 * in which the expression can be evaluated by grouping the numbers and operators using
 * parentheses.
 * 
 * Example 1:
 * Input: "1+2*3"
 * Output: 7, 9
 * Explanation: 1+(2*3) => 7 and (1+2)*3 => 9
 * 
 * Example 2:
 * Input: "2*3-4-5"
 * Output: 8, -12, 7, -7, -3 
 * Explanation: 2*(3-(4-5)) => 8, 2*(3-4-5) => -12, 2*3-(4-5) => 7, 2*(3-4)-5 => -7, 
 * (2*3)-4-5 => -3
 */
public class EvaluateExpression {



    public static List<Integer> diffWaysToEvaluateExpression(String s) {
    // memoization map
      Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
      return diffWaysToEvaluateHelper(s, map);
    }
    public static List<Integer> diffWaysToEvaluateHelper(String s,  Map<String, List<Integer>> map) {
        if (map.containsKey(s))
            return map.get(s);

        List<Integer> result = new ArrayList<>();
        // base case: if the input string is a number, parse and return it.
        if(!s.contains("+") && !s.contains("*") && !s.contains("-")) { //s is a number
            result.add(Integer.parseInt(s));
        } else {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(c == '+' || c == '-' || c == '*') {
                    List<Integer> left = diffWaysToEvaluateHelper(s.substring(0, i), map);
                    List<Integer> right = diffWaysToEvaluateHelper(s.substring(i+1, s.length()),map);
                    for (int leftVal : left) {
                        for (int rightVal : right) {
                            if(c == '+') {
                                result.add(leftVal+rightVal);
                            } else if(c == '-') {
                                result.add(leftVal-rightVal);
                            } else {
                                result.add(leftVal*rightVal);
                            }
                        }
                    }
                }
            }
        }
        map.put(s, result);
        return result;
      }
    
      public static void main(String[] args) {
        List<Integer> result = EvaluateExpression.diffWaysToEvaluateExpression("1+2*3");
        System.out.println("Expression evaluations: " + result);
    
        result = EvaluateExpression.diffWaysToEvaluateExpression("2*3-4-5");
        System.out.println("Expression evaluations: " + result);
      }
}
