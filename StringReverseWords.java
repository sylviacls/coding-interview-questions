import java.util.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * Leetcode: 151. Reverse Words in a String
 * https://leetcode.com/problems/reverse-words-in-a-string/
 * 
 */
public class StringReverseWords {


    public static String reverseWords(String s) {
        String[] words = s.trim().split(" +");//splitting on the regex for one-or-more whitespace
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    public static String reverseWordsII(String s) {
        String[] words = s.trim().split(" +");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length -1; i >= 0; i--) {
            sb.append(words[i]);
            sb.append(" ");
        }
        return sb.toString().trim();
    }


    public static String reverseWordsIII(String s) {
        char[] setence = s.toCharArray();
        int i = 0;
        int n = setence.length;
        String result = "";
        while ( i < n) {
            while( i < n && setence[i] == ' ') i++; //skipping with spaces
            if(i >= n) break;

            int j = i + 1; //i will be point to the first non-space char
            while(j < n && setence[j] != ' ') j++; //keeping the next non-space char
            //j will be point to the next space
            String currWord = s.substring(i, j);

            if(result.length() == 0) { //first word
                result = currWord;
            } else {
                result = currWord + " " + result; //placing the current word at the beggining
            }

            i = j + 1;
        }
        return result;
    }

  @Test
  public void validate() {
        String s = "  hello world  ";
        String expected = "world hello";
        Assert.assertEquals(expected, reverseWords(s));
        Assert.assertEquals(expected, reverseWordsII(s));
        Assert.assertEquals(expected, reverseWordsIII(s)); 

        String s2 = "the sky is blue";
        String expected2 = "blue is sky the";
        Assert.assertEquals(expected2, reverseWords(s2));
        Assert.assertEquals(expected2, reverseWordsII(s2));
        Assert.assertEquals(expected2, reverseWordsIII(s2)); 
  }

}
