import java.util.*;

import org.junit.*;
/**
 * Given a string, find if its letters can be rearranged in such a way that no two same 
 * characters come next to each other.
 * 
 * Example 1:
 * Input: "aappp"
 * Output: "papap"
 * Explanation: In "papap", none of the repeating characters come next to each other.
 * 
 * Example 3:
 * Input: "aapa"
 * Output: ""
 * Explanation: In all arrangements of "aapa", atleast two 'a' will come together e.g., 
 * "apaa", "paaa".
 */
public class StringRearrengeWithNo2CharNext {
    /**
     * Approach: Using Max-heap + HashMap
     * We will first append the most frequent characters to the output strings,
     * for which we can use a Max Heap and HashMap. 
     * In each step, we should append one occurrence of the highest frequency character 
     * to the output string. We will not put this character back in the heap to ensure 
     * that no two same characters are adjacent to each other. 
     * In the next step, we should process the next most frequent character from the heap
     * in the same way and then, at the end of this step, insert the character from the 
     * previous step back to the heap after decrementing its frequency.
     * 
     * Time Complexity: (NlogN)
     * Space Complexity: O(N)
     * @param str
     * @return
     */
    public static String rearrangeString(String str) {
        //storing the frequence of each char
        Map<Character,Integer> frequences = new HashMap<Character, Integer>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            frequences.put(c, frequences.getOrDefault(c, 0)+1);
        }
        //creating a max heap and storing all entries
        PriorityQueue<Map.Entry<Character,Integer>> maxHeap = new PriorityQueue<Map.Entry<Character, Integer>>
                                                            ((e1,e2)-> e2.getValue() - e1.getValue());
        maxHeap.addAll(frequences.entrySet());

        StringBuilder sBuilder = new StringBuilder(str.length());
        Map.Entry<Character,Integer> previousEntry = null; 

        while(!maxHeap.isEmpty()){
            Map.Entry<Character,Integer> currEntry = maxHeap.poll();
            // add the previous entry back in the heap if its frequency is greater than zero
            if(previousEntry != null && previousEntry.getValue() > 0) {
                maxHeap.offer(previousEntry);
            }
            // append the current character to the result string and decrement its count
            sBuilder.append(currEntry.getKey());
            currEntry.setValue(currEntry.getValue()-1);
            previousEntry = currEntry;
        }
        // if we were successful in appending all the characters to the result string, return it
        return sBuilder.length() == str.length()? sBuilder.toString():"";
      }
    
      @Test
      public void validate() {
        Assert.assertEquals("aappp".length(), rearrangeString("aappp").length());
        Assert.assertEquals("Programming".length(), rearrangeString("Programming").length());
        Assert.assertNotEquals("aapa".length(), rearrangeString("aapa").length());
      }
}