import java.util.HashMap;
import java.util.Map;

import org.junit.*;

/**
 * Leetcode: 567. Permutation in String
 * 
 * Given a string and a pattern, find out if the string contains any permutation
 * of the pattern.
 * 
 * Example 1:
 * Input: s1 = "ab" s2 = "eidbaooo"
 * Output: True
 * Explanation: s2 contains one permutation of s1 ("ba").
 * 
 * Example 2
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 */
public class StringPermutationPattern {

    /**
     * Approach: Sliding Window
     * 
     * Create a HashMap to calculate the frequencies of all characters in the pattern.
     * Iterate through the string, adding one character at a time in the sliding window.
     * If the character being added matches a character in the HashMap, decrement its
     * frequency in the map. 
     * If the character frequency becomes zero, we got a complete match.
     * If at any time, the number of characters matched is equal to the number of distinct
     * haracters in the pattern (i.e., total characters in the HashMap), we have gotten our 
     * required permutation.
     * If the window size is greater than the length of the pattern, shrink the window to make
     * it equal to the size of the pattern. At the same time, if the character going out was
     * part of the pattern, put it back in the frequency HashMap.
     * 
     * Time complexity: O(N+M) where ‘N’ and ‘M’ are the number of characters in the input string 
     *                  and the pattern respectively.
     * Space complexity:O(M)O(M) since in the worst case, 
     *                 the whole pattern can have distinct characters which will go into the HashMap
     * @param str
     * @param pattern
     * @return
     */
    public static boolean findPermutation(String str, String pattern) {
        int windowStart = 0;
        int matched = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : pattern.toCharArray())
          charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);
    
        // our goal is to match all the characters from the 'charFrequencyMap' with the current window
        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
          char rightChar = str.charAt(windowEnd);
          if (charFrequencyMap.containsKey(rightChar)) {
            // decrement the frequency of the matched character
            charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
            if (charFrequencyMap.get(rightChar) == 0) // character is completely matched
              matched++;
          }
    
          if (matched == charFrequencyMap.size())
            return true;
    
          //if the size of window is >= of the pattern's lenght we have to shrink it 
          if (windowEnd - windowStart + 1 >= pattern.length()) { // shrink the window by one character
            char leftChar = str.charAt(windowStart);
            if (charFrequencyMap.containsKey(leftChar)) {
              if (charFrequencyMap.get(leftChar) == 0) {
                matched--; // before putting the character back, decrement the matched count
              } 
              // put the character back for matching
              charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
            }
            windowStart++;
          }
        }

        return false;
    }

    @Test
    public void validate() {
        Assert.assertTrue(StringPermutationPattern.findPermutation("oidbeabcaf", "aabc"));
        Assert.assertFalse(StringPermutationPattern.findPermutation("odicf", "dc"));
        Assert.assertTrue(StringPermutationPattern.findPermutation("bcdxabcdy", "bcdyabcdx"));
        Assert.assertTrue(StringPermutationPattern.findPermutation("aaacb", "abc"));
      }
}