import java.util.ArrayList;
import java.util.List;

import org.junit.*;

/**
 * Given a string, find all of its permutations preserving the character
 * sequence but changing case.
 * 
 * Example: Input: "ad52" Output: "ad52", "Ad52", "aD52", "AD52"
 */
public class StringPermutationChangeCase {

    public static List<String> findPermutations(String input) {
        List<String> result = new ArrayList<>();
        findPermutations(input.toCharArray(), 0, result);
        return result;
      }

      /**
       * As soon as you find a letter character at index, check for all its possible combinations
       * by making it a lower case character and an upper case character. 
       * Add the new char array at the at the end of each recursion.
       * @param input
       * @param pos
       * @param result
       */
      private static void findPermutations(char[] input, int pos,List<String> result) {
          if(pos == input.length) {
              result.add(new String(input));
              return;
          }
          ///if we meet a char, then we have two options : uppercase or lowecase.
          if(!Character.isDigit(input[pos])) { 
            input[pos] = Character.toUpperCase(input[pos]); //Upper case branch
            findPermutations(input, pos+1, result);
            input[pos] = Character.toLowerCase(input[pos]); //Lower case branch 
            findPermutations(input, pos+1, result);
               
         }else {  //if we meet a number, go deeper, do nothing in current level
            findPermutations(input, pos+1, result);
        }

      }

    /**
     * An alternative method
     * @param input
     * @return
     */  
    public static List<String> findPermutations2(String input) {
        List<String> res = new ArrayList<>();
        char[] a = input.toLowerCase().toCharArray();
        helper(a, 0, res);
        return res;
    }
    
    private static void  helper(char[] a, int pos, List<String> res){
        if(pos==a.length){
            res.add(new String(a));
            return;
        }
        if(Character.isLetter(a[pos])) {
            a[pos] = Character.toUpperCase(a[pos]);
            helper(a, pos+1, res);
            a[pos] = Character.toLowerCase(a[pos]);
        } 
        helper(a, pos+1, res);
    }

    @Test
    public void validate() {
        List<String> result = findPermutations("ab7c");
        Assert.assertEquals("[AB7C, AB7c, Ab7C, Ab7c, aB7C, aB7c, ab7C, ab7c]", result.toString());
    
        result = findPermutations2("ab7c");
        Assert.assertEquals("[AB7C, AB7c, Ab7C, Ab7c, aB7C, aB7c, ab7C, ab7c]", result.toString());
    }
      

 
}