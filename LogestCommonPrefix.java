import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * Leetcode: 14. Longest Common Prefix
 * https://leetcode.com/problems/longest-common-prefix/
 * 
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 * 
 * Example 1:
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * 
 * Example 2: 
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 */
public class LogestCommonPrefix {


    /**
     * Approach: Sort and compare the first and the last string
     * 
     * Time Complexity: O(NogN) for sorting + O(S), s the length of the smallest string
     * Space Complexity: O(1)
     */
    public static String longestCommonPrefix(String[] strs) {

        if(strs == null || strs.length == 0) return "";

        Arrays.sort(strs);

        String start = strs[0];
        String end = strs[strs.length-1];

        int count = 0;
        int i = 0;
        int j = 0;
        while (i < start.length() && j < start.length()) {
            if(start.charAt(i) == end.charAt(j)) {
                i++;
                j++;
                count++;
            } else {
                break;
            }
        }
        return count > 0? start.substring(0, count): "";
    }

    @Test
    public void validate() {
        String[] strs = new String[]{"flower","flow","flight"};
        Assert.assertEquals("fl", longestCommonPrefix(strs));

        String[] strs2 = new String[]{"dog","racecar","car"};
        Assert.assertEquals("", longestCommonPrefix(strs2));
    }
    
}
