import java.util.*;

import org.junit.*;

/**
 * Given a string, sort it based on the decreasing frequency of its characters.
 * 
 * Example 1:
 * 
 * Input: "Programming" Output: "rrggmmPiano" Explanation: 'r', 'g', and 'm'
 * appeared twice, so they need to appear before any other character.
 */

public class StringFrequencySort {

    /**
     * Approach: Max-Heap + HashMap
     * 
     * Time Complexity: O(N logN)
     * Space Complexity O(N)
     * @param str
     * @return
     */
    public static String sortCharacterByFrequency(String str) {
        //The hashmap will store the frequency of each char
        Map<Character, Integer> frequences = new HashMap<Character, Integer>();
        char[] chars = str.toCharArray();
        //This step takes O(N) time where N is a number of characteres
        for (char c : chars) {
            frequences.put(c, frequences.getOrDefault(c, 0)+1);
        }

        //the max-heap for keeping trach of most frequenty chars
        PriorityQueue<Map.Entry<Character,Integer>> maxHeap = new PriorityQueue<Map.Entry<Character,Integer>>
                                                                ((e1,e2) -> e2.getValue() - e1.getValue());
        
        //this step will take O(NlogN) where N is a number of characteres
        for (Map.Entry<Character,Integer> entry : frequences.entrySet()) {
            maxHeap.add(entry);
        }

        //collecting the most frequent char and putting them in desc order
        //this step takes O(N log N)
        StringBuilder builder = new StringBuilder(str.length());
        while(!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> entry = maxHeap.poll();
            char currentChar = entry.getKey();
            for (int i = 0; i < entry.getValue(); i++) {
                builder.append(currentChar);
            }
        }
        return builder.toString();
    }
    
    @Test
    public void validate() {
        Assert.assertEquals("rrggmmPiano", sortCharacterByFrequency("Programming"));  
        Assert.assertEquals("bbbaac", sortCharacterByFrequency("abcbab"));
        Assert.assertEquals("eert", sortCharacterByFrequency("tree"));
    }
}