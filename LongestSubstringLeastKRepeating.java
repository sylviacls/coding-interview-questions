import java.util.*;
import org.junit.*;

/**
 * Leetcode: 395. Longest Substring with At Least K Repeating Characters
 * 
 * Find the length of the longest substring T of a given string (consists of lowercase letters only)
 * such that every character in T appears no less than k times.
 * 
 * Example 1:
 * Input: s = "aaabb", k = 3
 * Output: 3
 * The longest substring is "aaa", as 'a' is repeated 3 times.
 * 
 * Example 2:
 * Input: s = "ababbc", k = 2
 * Output: 5
 * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 */
public class LongestSubstringLeastKRepeating {

    /**
     * Approach: Divide and conquer
     * 
     * We achieve this by recursively splitting the given String on characters whose do not 
     *  occur at least k times (since they cannot be part of the longest valid substring).
     * 
     * Time Complexity: O(n), each char is split at most once.
     * Space Complexity: O(n), call stack.
     * @param input
     * @param k
     * @return
     */
    public static int longestSubstring(String input, int k) {

        int n = input.length();
        //base cases
        if (input == null || n == 0 || n < k) return 0;
        if(k == 1) return input.length();

        return helper(input, k, 0, n-1);    
    }

    private static int helper(String input, int k, int start, int end) {
        //base cases
        if(start < 0 || end > input.length()) return 0;

        //Count the frequency of characters in this substring.
        //time complexity: O(N)
        Map<Character, Integer> frequences = new HashMap<Character, Integer>();
        for (int i = start; i <= end; i++) {
            char c = input.charAt(i);
            frequences.put(c, frequences.getOrDefault(c, 0)+1);
        }

        //time complexity: O(N)
        for (int i = start; i <= end; i++) {
            char c = input.charAt(i);
            if(frequences.get(c) != null && frequences.get(c) < k) {
                int lenSubLeft = helper(input, k, start, i-1);
                int lenSubRight = helper(input, k, i+1, end);
                return Math.max(lenSubLeft, lenSubRight);
            }
        }
        // If every character in this substring occurs at least k times,
        //then this is a valid substring, so return this substring's length
        return end-start+1;   
    }

    @Test
    public void validate() {
        Assert.assertEquals(3, longestSubstring("aaabb", 3));
        Assert.assertEquals(5, longestSubstring("aaabb", 2));
        Assert.assertEquals(0, longestSubstring("abcabcac", 3));
    }
}