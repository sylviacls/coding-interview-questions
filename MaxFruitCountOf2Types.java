import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Given an array of characters where each character represents a fruit tree, 
 * you are given two baskets and your goal is to put maximum number of fruits in each basket. 
 * The only restriction is that each basket can have only one type of fruit.
 * Time Complexity: O(n)
 * Space Complexity:O(1) as there can be a maximum of three types of fruits stored in the frequency map
 */
public class MaxFruitCountOf2Types {

    public static int findMaximumCount(char[] input) {
        int maxCount = 0;
        int windowStart = 0;
        Map<Character, Integer> fruitsMap = new HashMap<Character,Integer>();
        for (int windowEnd = 0; windowEnd < input.length; windowEnd++) {
            char rightFruit = input[windowEnd];
            fruitsMap.put(rightFruit, fruitsMap.getOrDefault(rightFruit, 0)+1);

            while(fruitsMap.size() > 2) {
                char leftFruit = input[windowStart];
                fruitsMap.put(leftFruit, fruitsMap.get(leftFruit)-1);
                if(fruitsMap.get(leftFruit) == 0) {
                    fruitsMap.remove(leftFruit);
                }
                windowStart++;
            }

            int currentCount = 0;
            for (Map.Entry<Character,Integer> entry : fruitsMap.entrySet()) {
                currentCount += entry.getValue();
            }
            maxCount = Math.max(maxCount, currentCount);
        }

        return maxCount;
    }


    @Test   
    void testfindMaximumCount() {
        assertEquals(3, findMaximumCount(new char[] { 'A', 'B', 'C', 'A', 'C' }));
    }

    public static void main(String[] args) {
        System.out.println("Maximum number of fruits: " + 
                              MaxFruitCountOf2Types.findMaximumCount(new char[] { 'A', 'B', 'C', 'A', 'C' }));
        System.out.println("Maximum number of fruits: " + 
                              MaxFruitCountOf2Types.findMaximumCount(new char[] { 'A', 'B', 'C', 'B', 'B', 'C' }));
         
    }
}
