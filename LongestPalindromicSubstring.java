import org.junit.Assert;
import org.junit.Test;

/**
 * Leetcode: 5. Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring/
 * 
 * Given a string s, return the longest palindromic substring in s.
 * 
 * Example 1:
 * Input: s = "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * 
 * Example 2:
 * Input: s = "cbbd"
 * Output: "bb"
 */
public class LongestPalindromicSubstring {

    /**
     * We observe that a palindrome mirrors around its center.
     * The idea is start from each index and try to extend palindre (to left and right)
     * We have to deal with palidrome with odd length and with even length.     * 
     */
    public static String longestPalindrome(String s) {
        //start and end indexes of the longest substring
        int startLong = 0;
        int endLong = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = extendPalindrome(s, i, i); //odd length, both index should point to the middle
            int len2 = extendPalindrome(s, i, i+1);//even length

            int currLen = Math.max(len1, len2);
            if(currLen > endLong-startLong) {
                //index i is the "center" of our palindrome
                //thus, to find the start position we have to subract the (currLen-1) /2  from i
                //and the end will be i + currLen/2
                startLong = i - (currLen-1)/2;
                endLong = i + (currLen)/2;
            }
        }
        return s.substring(startLong, endLong+1);
    }

	private static int extendPalindrome(String s, int left, int right) {
        //prevent moving out of the bounds
        //keep expanding while the indexes point to equals values (mirror)
		while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right-left-1;
    }
    
    @Test
    public void validate() {
        Assert.assertEquals("aabaa", longestPalindrome("aabaa"));
        Assert.assertEquals("aba", longestPalindrome("babad"));
        Assert.assertEquals("bb", longestPalindrome("cbbd"));
        Assert.assertEquals("a", longestPalindrome("a"));
    }

}
