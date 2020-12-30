/**
 * Leetcode: 17. Letter Combinations of a Phone Number
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * 
 * Given a string containing digits from 2-9 inclusive, return all possible letter 
 * combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Note that 1 does not map to any letters.
 */
import java.util.*;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class CombinationLetterPhoneNumber {

    /**
     * Approach: Backtracking DFS
     * 
     * Time Complexity: O(3^N * 4^M) where N is the number of digits in the input that maps
     *                   to 3 letters (e.g. 2, 3, 4, 5, 6, 8) and M is the number of digits
     *                   in the input that maps to 4 letters (e.g. 7, 9), and N+M
     *                   is the total number digits in the input.
     * Space Complexity: O(3^N * 4^M)
     * 
     * Runtime: 4ms Memory: 39,7MB
     */
    public static List<String> letterCombination(String digits) {
        HashMap<Character, char[]> dict = new HashMap<Character, char[]>();
        dict.put('2',new char[]{'a','b','c'});
        dict.put('3',new char[]{'d','e','f'});
        dict.put('4',new char[]{'g','h','i'});
        dict.put('5',new char[]{'j','k','l'});
        dict.put('6',new char[]{'m','n','o'});
        dict.put('7',new char[]{'p','q','r','s'});
        dict.put('8',new char[]{'t','u','v'});
        dict.put('9',new char[]{'w','x','y','z'});

        List<String> result = new ArrayList<String>();
        if(digits == null||digits.length() == 0){
            return result;
        }

        combinations(digits, 0, "", dict, result);
        return result;
    }

    /**
     * 
     * @param digits original input
     * @param index current digit index
     * @param current current string
     * @param dict map
     * @param result
     */
    private static void combinations(String digits, int index, String current, HashMap<Character, char[]> dict,
            List<String> result) {
        //base case
        if(index == digits.length()) {
            result.add(current);
            return;
        }
        //get the digit from digits at current index and its correspondent list of chars
        char[] listChar = dict.get(digits.charAt(index));
        //for each possible char of the current digit, pick it and make a recursive call to next digit
        for (int i = 0; i < listChar.length; i++) {
            combinations(digits, index+1, current+listChar[i], dict, result);
        }
    }


    /**
     * Approach: BFS
     * 
     * Time Complexity: O(3^N * 4^M) where N is the number of digits in the input that maps
     *                   to 3 letters (e.g. 2, 3, 4, 5, 6, 8) and M is the number of digits
     *                   in the input that maps to 4 letters (e.g. 7, 9), and N+M
     *                   is the total number digits in the input.
     * Space Complexity: O(3^N * 4^M)
     * 
     * Runtime: 5ms Memory: 39,6MB
     */
    public static List<String> letterCombinationII(String digits) {
        HashMap<Character, char[]> dict = new HashMap<Character, char[]>();
        dict.put('2',new char[]{'a','b','c'});
        dict.put('3',new char[]{'d','e','f'});
        dict.put('4',new char[]{'g','h','i'});
        dict.put('5',new char[]{'j','k','l'});
        dict.put('6',new char[]{'m','n','o'});
        dict.put('7',new char[]{'p','q','r','s'});
        dict.put('8',new char[]{'t','u','v'});
        dict.put('9',new char[]{'w','x','y','z'});

        List<String> result = new ArrayList<String>();
        if(digits == null||digits.length() == 0){
            return result;
        }
        Queue<String> combinations = new LinkedList<String>();
        combinations.add("");

        //tak each digit from digits
        for (int i = 0; i < digits.length(); i++) {
            //take the size of the curr combination queue
            int currSizeComb = combinations.size();
            for(int k = 0; k <currSizeComb; k++) {     
                //for each stored combination we will iterate adding each char correspondent
                //to digit
                String currComb = combinations.poll();
                char[] charList = dict.get(digits.charAt(i));
                for (int j = 0; j < charList.length; j++) {
                    char currChar = charList[j];
                    String newComb = currComb + currChar;
                    combinations.add(newComb);
                    //the new combination is valid if it has the same length of input
                    if (newComb.length() == digits.length())
                        result.add(newComb);              
                }
            }       
        }

        return result;
    }

    @Test
    public void validate() {
        List<String> expected = Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
        Assertions.assertIterableEquals(expected, letterCombination("23"));
        Assertions.assertIterableEquals(expected, letterCombinationII("23"));
    }
}
