import java.util.*;
import org.junit.*;

/**
 * Leetcode: 904. Fruit Into Baskets https://leetcode.com/problems/fruit-into-baskets/
 * 
 * Given an array of characters where each character represents a fruit tree, 
 * you are given two baskets and your goal is to put maximum number of fruits in each basket. 
 * The only restriction is that each basket can have only one type of fruit.
 * 
 */
public class MaxFruitCountOf2Types {

    /**
     * Approach: Sliding Window
     * We want the longest subarray with at most two different "types"
     * 
     * Time Complexity: O(n)
     * Space Complexity:O(1) as there can be a maximum of three types of fruits stored in the
     *                      frequency map
     * @param input
     * @return
     */
    public static int findMaximumCount(char[] input) {
        int maxCount = 0;
        int windowStart = 0;
        Map<Character, Integer> fruitsMap = new HashMap<Character,Integer>();
        for (int windowEnd = 0; windowEnd < input.length; windowEnd++) {
            char rightFruit = input[windowEnd];
            fruitsMap.put(rightFruit, fruitsMap.getOrDefault(rightFruit, 0)+1);
            //if the window has more types-per-basket than allowed, shrink the window
            while(fruitsMap.size() > 2) {
                //taking the first element of curr window
                char leftFruit = input[windowStart];
                //decreasing his frequence
                fruitsMap.put(leftFruit, fruitsMap.get(leftFruit)-1);
                //if the frequence gets 0, then remove the element
                if(fruitsMap.get(leftFruit) == 0) {
                    fruitsMap.remove(leftFruit);
                }
                //moving the start index forward
                windowStart++;
            }
            //after the window has the current size/itens allowed, calculate the count of the basket
            int currentCount = 0;
            for (Map.Entry<Character,Integer> entry : fruitsMap.entrySet()) {
                currentCount += entry.getValue();
            }
            //storing the max count
            maxCount = Math.max(maxCount, currentCount);
        }
        return maxCount;
    }

    public int findMaximumCount(int[] input) {
        int maxCount = 0;
        int windowStart = 0;
        Map<Integer, Integer> fruitsMap = new HashMap<Integer, Integer>();
        for (int windowEnd = 0; windowEnd < input.length; windowEnd++) {
            int rightFruit = input[windowEnd];
            fruitsMap.put(rightFruit, fruitsMap.getOrDefault(rightFruit, 0) + 1);
            // if the window has more types-per-basket than allowed, shrink the window
            while (fruitsMap.size() > 2) {
                // taking the first element of curr window
                int leftFruit = input[windowStart];
                // decreasing his frequence
                fruitsMap.put(leftFruit, fruitsMap.get(leftFruit) - 1);
                // if the frequence gets 0, then remove the element
                if (fruitsMap.get(leftFruit) == 0) {
                    fruitsMap.remove(leftFruit);
                }
                // moving the start index forward
                windowStart++;
            }
            // after the window has the current size/itens allowed, calculate the count of
            // the basket
            int currentCount = 0;
            for (Map.Entry<Integer, Integer> entry : fruitsMap.entrySet()) {
                currentCount += entry.getValue();
            }
            // storing the max count
            maxCount = Math.max(maxCount, currentCount);
        }
        return maxCount;
    }

    @Test   
    public void testfindMaximumCount() {
        Assert.assertEquals(3, findMaximumCount(new char[] { 'A', 'B', 'C', 'A', 'C' }));
        Assert.assertEquals(4, findMaximumCount(new char[] { 'A', 'B', 'C', 'B', 'B' }));
        Assert.assertEquals(5, findMaximumCount(new char[] { 'A', 'B', 'C', 'B', 'B', 'C' }));

        Assert.assertEquals(3, findMaximumCount(new int[] {1,2,1}));
        Assert.assertEquals(3, findMaximumCount(new int[] {0,1,2,2}));
        Assert.assertEquals(5, findMaximumCount(new int[] {3,3,3,1,2,1,1,2,3,3,4}));
    }
}
