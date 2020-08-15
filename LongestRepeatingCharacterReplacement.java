/**
 * Leetcode: 424. Longest Repeating Character Replacement
 * 
 * Given a string s that consists of only uppercase English letters, you can perform at most
 * k operations on that string.
 * 
 * In one operation, you can choose any character of the string and change it to any other 
 * uppercase English character.
 * 
 * Find the length of the longest sub-string containing all repeating letters you can get after
 * performing the above operations.
 */
import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class LongestRepeatingCharacterReplacement {
    
    /**
     * Approach: Sliding Window
     * 
     * The initial step is to extend the window to its limit, that is, the longest we can get to
     * with maximum number of modifications. Until then the variable start will remain at 0.
     * Then as end increase, the whole substring from 0 to end will violate the rule, so we need
     *  to update start accordingly (slide the window). We move start to the right until the 
     * whole string satisfy the constraint again. Then each time we reach such situation, we 
     * update our max length.
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(26) = O(1)
     */
    public static int characterReplacement(String input, int k) {

        Map<Character, Integer> frequences = new HashMap<Character, Integer>();
        int maxLen = 0;
        int maxRepeatLetterCount = 0;
        int windowStart = 0;
        // We’ll iterate through the string to add one letter at a time in the window
        for (int windowEnd = windowStart; windowEnd < input.length(); windowEnd++) {
            char rightChar = input.charAt(windowEnd);
            frequences.put(rightChar, frequences.getOrDefault(rightChar, 0)+1);
            // We’ll also keep track of the count of the maximum repeating letter in any window       
            maxRepeatLetterCount = Math.max(maxRepeatLetterCount, frequences.get(rightChar));

            // We know that a our current window is valid when the number of characters 
            //that need to be replaced is <= k. Ifwe have more than ‘k’ remaining letters, 
            //we should shrink the window as we are not allowed to replace more than ‘k’ letters.
            int replaceCount = windowEnd-windowStart+1 - maxRepeatLetterCount;
            if(replaceCount > k) { //invalid window
                char leftChar = input.charAt(windowStart);
                frequences.put(leftChar, frequences.get(leftChar) - 1);
                windowStart++;
            }
            //We know that a our current window/substring is valid when the number of characters 
            //that need to be replaced is <= k.
            maxLen = Math.max(maxLen, windowEnd-windowStart+1);
        }
        return maxLen;
    }

    @Test
    public void validate() {
        Assert.assertEquals(5, characterReplacement("aabccbb", 2));
        Assert.assertEquals(4, characterReplacement("abbcb", 1));
        Assert.assertEquals(3, characterReplacement("abccde", 1));
      }
}