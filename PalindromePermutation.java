import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.Assert;

/*
*/
public class PalindromePermutation {

    /*
    *  A palindrome is a word or phrase that is the same forwards and backwards. 
    */
    public static boolean checkPalindrome(String input1){
        return new StringBuilder(input1).reverse().toString().equals(input1);
    }


    /*
    * Check if a string s1 is a permutation of string s2
    * To be a permutation s1 must have exactly the same chars of s2
    * An easy way is check each char from s1 in s2, removing it
    * If in the end s2 has length 0, s1 is a permutation of s2
    */
    public static boolean checkPermutation(String input1, String input2){
        char[] charsFromInput1 = input1.toCharArray();
        for(Character charFromInput1 : charsFromInput1){
            if(input2.indexOf(charFromInput1)>=0){
                input2 = input2.replaceFirst(String.valueOf(charFromInput1), "");
            }
        }
        System.out.println(input2);
        return input2.length() == 0;
    }

    /*Given a string, determine if a permutation of the string could form a palindrome.
    *If a string with an even length is a palindrome, every character in the string must
    * always occur an even number of times. If the string with an odd length is a palindrome,
    * every character except one of the characters must always occur an even number of times. 
    * Thus, in case of a palindrome, the number of characters with odd number of occurences
    * can't exceed 1(1 in case of odd length and 0 in case of even length).
    */
    public static boolean checkIfPermutationFormPalindrome(String input) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        int countOdds = 0;

        for (int i = 0; i < input.length(); i++) {
            if(map.get(input.charAt(i)) % 2 != 0) {
                countOdds++;
            }
        }
        return countOdds <= 1;
    }

    @Test
    public void testCheckIfPermutationFormPalindrome() {
        Assert.assertTrue(!checkIfPermutationFormPalindrome("code"));
        Assert.assertTrue(checkIfPermutationFormPalindrome("aab"));
        Assert.assertTrue(checkIfPermutationFormPalindrome("carerac"));
        Assert.assertTrue(!checkIfPermutationFormPalindrome("abc"));
        Assert.assertTrue(!checkIfPermutationFormPalindrome("geeksforgeeks"));
        Assert.assertTrue(checkIfPermutationFormPalindrome("geeksogeeks"));

        Assert.assertTrue(checkPalindrome("abba"));
        Assert.assertTrue(!checkPalindrome("abcd"));
        
    }

    @Test
    public void testCheckPermutation() {
        Assert.assertTrue(!checkPermutation("abcd", "dgabc"));
        Assert.assertTrue(checkPermutation("abcd", "dabc"));

    }

    public static void main(String[] args) {
        System.out.println(checkPermutation("abcd", "dgabc"));
    }
}