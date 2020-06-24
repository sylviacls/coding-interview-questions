import java.util.HashMap;
import java.util.Map;

public class LongestSubstringKDistinct {

    /* Given a string, find the length of the longest substring
    *  in it with no more than K distinct characters.
    * Time complexity: O(N)
    * Space complexity: O(K) will be storing a maximum of ‘K+1’ characters in the HashMap
    */
    public static int findLength(String input, int K) {

        if (input == null || input.length() == 0 || input.length() < K)
        throw new IllegalArgumentException();

        int windowStart = 0, maxLength = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<Character,Integer>();

        for (int windowEnd = 0; windowEnd < input.length(); windowEnd++) {
            char rightChar = input.charAt(windowEnd);
            charFrequencyMap.put(rightChar, charFrequencyMap.getOrDefault(rightChar, 0)+1);

            while(charFrequencyMap.size() > K) {
                char charLeft = input.charAt(windowStart);
                charFrequencyMap.put(charLeft, charFrequencyMap.get(charLeft)-1);
                if(charFrequencyMap.get(charLeft) == 0) {
                    charFrequencyMap.remove(charLeft);
                }
                windowStart++;
            }
            maxLength = Math.max(maxLength, windowEnd-windowStart+1);
        }

    
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println("Length of the longest substring: " + LongestSubstringKDistinct.findLength("araaci", 2));
        System.out.println("Length of the longest substring: " + LongestSubstringKDistinct.findLength("araaci", 1));
        System.out.println("Length of the longest substring: " + LongestSubstringKDistinct.findLength("cbbebi", 3));
      }
}


    
