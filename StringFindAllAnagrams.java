/**
 * Leetcode: 438. Find All Anagrams in a String
 * 
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * The order of output does not matter.
 * 
 * Example 1:
 * Input: s: "cbaebabacd" p: "abc"
 * Output: [0, 6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * 
 * Example 2:
 * Input: s: "abab" p: "ab"
 * Output: [0, 1, 2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
import java.util.*;
import org.junit.*;

public class StringFindAllAnagrams {

    /**
     * Approach: Sliding Window
     * Similar to "StringPermutationPattern.java"
     * 
     * Time complexity: O(N+M) where ‘N’ and ‘M’ are the number of characters in the input string 
     *                  and the pattern respectively.
     * Space complexity:O(M) since in the worst case, 
     *                 the whole pattern can have distinct characters which will go into the HashMap
     */
    public static List<Integer> findAnagrams(String input, String pattern) {
        List<Integer> result = new ArrayList<Integer>();

        //base case
        if(pattern.length() > input.length()) return result;

        //storing the frequency for each char in pattern
        Map<Character, Integer> charFrequencies = new HashMap<Character, Integer>();
        for (char c : pattern.toCharArray()) {
            charFrequencies.put(c, charFrequencies.getOrDefault(c, 0)+1);
        }

        int windowStart = 0;
        int matched = 0;
        for (int windowEnd = 0; windowEnd < input.length(); windowEnd++) {

            //if the current char is part of the pattern, then decrease its frequence 
            char charRight = input.charAt(windowEnd);
            if(charFrequencies.containsKey(charRight)) {
                charFrequencies.put(charRight, charFrequencies.get(charRight)-1);
                //if its frequency gets 0, then we increase the matched count
                if(charFrequencies.get(charRight)==0) matched++;
            }
            // if the matched count is equal to the size of frequencies map then we have a
            // valid window
            if (matched == charFrequencies.size()) {
                result.add(windowStart);
            }

            //if the size of the window is >= than the pattern, we have to shrink it
            //by removing the first element of the window, and updating its frequence when it is 
            //part of the pattern;
            if( windowEnd - windowStart + 1 >= pattern.length()) {
                char charLeft = input.charAt(windowStart);
                if(charFrequencies.containsKey(charLeft)) {
                    if(charFrequencies.get(charLeft)==0) matched--;
                    charFrequencies.put(charLeft, charFrequencies.get(charLeft)+1);
                } 
                windowStart++;
            }
        }
        return result;
    }

    @Test
    public void validate() {
        Assert.assertArrayEquals(new Integer[]{1,2}, findAnagrams("ppqp", "pq").toArray());
        Assert.assertArrayEquals(new Integer[]{2,3,4},findAnagrams("abbcabc", "abc").toArray());
        Assert.assertArrayEquals(new Integer[]{0,6},findAnagrams("cbaebabacd", "abc").toArray());
    }
    
}