import java.util.*;

import org.junit.*;

/**
 * Given an unsorted array of numbers, find the top ‘K’ frequently occurring
 * numbers in it.
 * 
 * Example 1: Input: [1, 3, 5, 12, 11, 12, 11], K = 2 Output: [12, 11]
 * Explanation: Both '11' and '12' apeared twice.
 * 
 * Input: [5, 12, 11, 3, 11], K = 2
 * Output: [11, 5] or [11, 12] or [11, 3]
 * Explanation: Only '11' appeared twice, all other numbers appeared once.
 */
public class TopKFrequentNumbers {

    /**
     * Approach: Min-Heap + HashMap 
     * 
     * We first need to know the frequency of each number, for which we can use a HashMap.
     * Once we have the frequency map, we can use a Min Heap to find the ‘K’ most frequently occurring number.
     * In the Min Heap, instead of comparing numbers we will compare their frequencies in order to get
     * frequently occurring numbers
     * 
     * Time Complexity: O(N + N*LogK)
     * Space Complexity: O(N) (hashmap + min-heap)
     * @param nums
     * @param k
     * @return
     */
    public static int[] findTopKFrequentNumbers(int[] nums, int k) {

        Map<Integer, Integer> frequences = new HashMap<Integer,Integer>();
        //This step takes O(N) time where N is a number of elements in the list.
        for (int num : nums) {
            frequences.put(num, frequences.getOrDefault(num, 0)+1);
        }
        //setting the minHeap to consider the frequence(value from HashMap) as the sorting criteria
        PriorityQueue<Map.Entry<Integer,Integer>> minHeap = new PriorityQueue<Map.Entry<Integer,Integer>>((e1,e2) -> e1.getValue()- e2.getValue());
        
        // go through all numbers of the numFrequencyMap and push them in the minHeap, which will have 
        // top k frequent numbers. If the heap size is more than k, we remove the smallest (top) number
        
        //To add the first k elements takes a linear time O(K) in the average case, and =O(K logK) in the worst case. 
        for (Map.Entry<Integer,Integer> num: frequences.entrySet()) {
            minHeap.add(num);
            if(minHeap.size() > k) {
                //The time complexity of heap push/pop is O(logN) , and we need do it N-K times
                minHeap.poll();
            }
        }

        int[] topNumbers = new int[k];
        int i = 0;
        //creating a list of top k numbers
        //this step takes O(K log K)
        while(!minHeap.isEmpty()) {
            topNumbers[i] = minHeap.poll().getKey();
            i++;
        }
        return topNumbers;
      }
    
      @Test
      public void validate() {
        Assert.assertArrayEquals(new int[]{12,11}, findTopKFrequentNumbers(new int[] { 1, 3, 5, 12, 11, 12, 11 }, 2));

        Assert.assertArrayEquals(new int[]{12,11}, findTopKFrequentNumbers(new int[] { 5, 12, 11, 3, 11 }, 2));

        Assert.assertArrayEquals(new int[]{2,3,1}, findTopKFrequentNumbers(new int[] { 1, 2, 6, 7, 1, 1, 2, 3, 8, 3 }, 3));

      }
}