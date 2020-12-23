/**
 * Leetcode: 647. Palindromic Substrings
 * Given a string, your task is to count how many palindromic substrings in this string.
 * The substrings with different start indexes or end indexes are counted as different substrings 
 * even they consist of same characters.
 * 
 * Example 1:
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * 
 * Example 2:
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 */
public class PalindromicSubstrings {
    static int count = 0;
    
    /**
     * The idea is start from each index and try to extend palindrome for both odd and even length
     * 
     * Time Complexity: O(N^2)
     * Space Complexity: O(1)
     */
    public static int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        
        for (int i = 0; i < s.length(); i++) { // i is the mid point
            extendPalindrome(s, i, i); // odd length;
            extendPalindrome(s, i, i + 1); // even length
        }
        
        return count;
    }

    /**
     * This auxiliar method checks whether the current substring is a palindrome or not
     * Left and right will point to "middle" of the substring and we're gonna try to expand
     * our current substring to find the longest palindrome
     * 
     * For the case of an odd lenght "racecar", both left and right will point to the middle 'e'
     * For the case of an even length "abba", left will point to the first b (index 1) and 
     * right will point to the second b (index 2)
     * 
     */
    private static void extendPalindrome(String s, int left, int right) {
        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++; 
            left--; 
            right++;
        }
    }

    public static void main(String[] args) {
        System.out.println(countSubstrings("aabaa"));
    }
}
