/**
 * Given a string, find the length of the longest substring without repeating characters. 
 * 
 * Example 1: 
 * Input: "abcabcbb"
 * Output: 3 
 * Explanation: The answer is "abc", with the length of 3. 
 * 
 * Example 2:
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * 
 * Example 3:
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
import java.util.*;
import org.junit.*;

public class LongestSubstringWithoutRepeatingChar {

    /**
     * Approach: Sliding-Window
     * 
     * Time complexity: O(N) - We iterate over the given nums array of length n once only.
     * Space complexity: O(K) - K is the number of distinct characters in the input string
     * 
     */
    public static int lengthOfLongestSubstring(String input) {
        Map<Character, Integer> frequences = new HashMap<Character, Integer>();  
        int windowStart = 0;
        int maxLen = 0;
        for (int windowEnd = windowStart; windowEnd < input.length(); windowEnd++) {
            char c = input.charAt(windowEnd);
            frequences.put(c, frequences.getOrDefault(c, 0)+1);
            while(frequences.get(c) > 1) {
                char firstChar = input.charAt(windowStart);
                frequences.put(firstChar, frequences.getOrDefault(firstChar, 0)-1);
                windowStart++;
            }
            maxLen = Math.max(maxLen, windowEnd-windowStart+1);
        }
        return maxLen;
    }
    
    @Test
    public void validate() {
        Assert.assertEquals(3, lengthOfLongestSubstring("aabccbb"));
        Assert.assertEquals(2, lengthOfLongestSubstring("abbbb"));
        Assert.assertEquals(3, lengthOfLongestSubstring("abccde"));
      }
}