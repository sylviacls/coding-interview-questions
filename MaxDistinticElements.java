import java.util.*;

/**
 * Given an array arr[] containing n elements. T
 * The problem is to find maximum number of distinct elements (non-repeating) after
 *  removing k elements from the array.
 * 
 * Note: 1 <= k <= n
 * 
 * Examples:
 * 
 * Input : arr[] = {5, 7, 5, 5, 1, 2, 2}, k = 3
 * Output : 4
 * Remove 2 occurrences of element 5 and 1 occurrence of element 2.
 * 
 * Input : arr[] = {1, 2, 3, 4, 5, 6, 7}, k = 5 
 * Output : 2
 * 
 * Input : arr[] = {1, 2, 2, 2}, k = 1
 * Output : 1
 */
public class MaxDistinticElements {

    /**
     * Approach: Max-Hep + HashMap
     * Time Complexity O(NLogN)
     * Space Complexity O(N)
     * @param nums
     * @param k
     * @return
     */
    public static int findMaximumDistinctElements(int[] nums, int k) {
        //finding the frequence of each number O(1)
        Map<Integer, Integer> frequences = new HashMap<Integer,Integer>();
        for (int num : nums) {
            frequences.put(num, frequences.getOrDefault(num, 0)+1);
        }
        
        //building a max heap to keep tracking of most frequent numbers (in order to remove them first)
        PriorityQueue<Map.Entry<Integer,Integer>> maxHeap = new PriorityQueue<Map.Entry<Integer,Integer>>
                                                    ((e1,e2) -> e2.getValue() - e1.getValue());

        //time complexity (NlogN)
        for (Map.Entry<Integer,Integer> entry : frequences.entrySet()) {
            maxHeap.add(entry);
        }

        //for k iteration, we will remove the max entry from the heap and decrease its frequence
        //if its frequence > 1, then we put it again into the heap
        //pool O(logN) add (logN), we do it K times --> O (KlogN)
        for (int i = 0; i < k; i++) {
            Map.Entry<Integer,Integer> currEntry =  maxHeap.poll();
            int currFrequence = currEntry.getValue();
            if(currFrequence > 1) {
                currEntry.setValue(currFrequence-1);
                maxHeap.add(currEntry);
            }             
        }

        //Now we count the number of distintic elements
        //O(Nlogn)
        int count = 0;
        while(!maxHeap.isEmpty()) {
            if(maxHeap.poll().getValue() < 2) {
                count++;
            }
        }
        return count;
      }
    
      public static void main(String[] args) {
        int result = findMaximumDistinctElements(new int[] { 7, 3, 5, 8, 5, 3, 3 }, 2);
        System.out.println("Maximum distinct numbers after removing K numbers: " + result);
    
        result = findMaximumDistinctElements(new int[] { 3, 5, 12, 11, 12 }, 3);
        System.out.println("Maximum distinct numbers after removing K numbers: " + result);

        result = findMaximumDistinctElements(new int[]  { 5, 7, 5, 5, 1, 2, 2 }, 3);
        System.out.println("Maximum distinct numbers after removing K numbers: " + result);
    
        result = findMaximumDistinctElements(new int[] { 1, 2, 3, 3, 3, 3, 4, 4, 5, 5, 5 }, 2);
        System.out.println("Maximum distinct numbers after removing K numbers: " + result);

        result = findMaximumDistinctElements(new int[] {32, 9, 55, 9, 22, 35, 9, 35}, 3);
        System.out.println("Maximum distinct numbers after removing K numbers: " + result);
        //5

        result = findMaximumDistinctElements(new int[] {35, 36, 32, 55, 50, 9}, 4);
        System.out.println("Maximum distinct numbers after removing K numbers: " + result);
        //2
      }
}