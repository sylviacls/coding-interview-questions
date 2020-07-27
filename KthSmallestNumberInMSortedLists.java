/**
 * Given ‘M’ sorted arrays, find the K’th smallest number among all the arrays.
 * 
 * Example 1:
 * Input: L1=[2, 6, 8], L2=[3, 6, 7], L3=[1, 3, 4], K=5
 * Output: 4
 * Explanation: The 5th smallest number among all the arrays is 4, this can be verified from
 * the merged list of all the arrays: [1, 2, 3, 3, 4, 6, 6, 7, 8]
 * 
 * We can start merging all the arrays, but instead of inserting numbers into a merged list, 
 * we will keep count to see how many elements have been inserted in the merged list. 
 * Once that count is equal to ‘K’, we have found our required number.
 */

import java.util.*;
import org.junit.*;

 /**
  * Auxiliar Class used by the findKthSmallestII method (Min-Heap Approach)
  */
class Node {
    int elementIndex;
    int arrayIndex;
    int value;
  
    Node(int arrayIndex,int elementIndex, int value) {
      this.elementIndex = elementIndex;
      this.arrayIndex = arrayIndex;
      this.value = value;
    }
  }
public class KthSmallestNumberInMSortedLists {

    /**
     * Naive approach: Using Sorting
     * A simple solution is to create an output array and and one by one copy all arrays to it.
     * Finally, sort the output array using. 
     * 
     * Time Complexity: O(N Logn N) time where N is count of all elements.
     * @param lists
     * @param k
     * @return
     */
    public static int findKthSmallest(List<Integer[]> lists, int k) {
        List<Integer> mergedList = new ArrayList<Integer>();
        for (Integer[] list : lists) {
            for (Integer num : list) {
                mergedList.add(num);
            }
        }
        Collections.sort(mergedList);
        return mergedList.get(k-1);
      }

      /**
       * Approach: Using a Min-Heap 
       * 
       * An efficient solution is to use heap data structure. 
       * The time complexity of heap based solution is O(N Log K).
       * 
       * 1. Create a min heap of size k and insert 1st element in all the arrays into the heap
       * 2. Repeat following steps K (cause we wanna the kth-smallest)
       *   a) Remove minimum element from heap (minimum is always at root). In this problem, we 
       *      don't need to store it in an output array.
       *   b) Insert next element from the array from which the element is extracted. 
       *    If the array doesn’t have any more elements, then do nothing.
       * 
       * Time Complexity: O(N Log K)
       *                Since we’ll be going through at most ‘K’ elements among all the arrays, 
       *                and we will remove/add one element in the heap in each step, the time 
       *                complexity of the algorithm will be O(N∗logK) where ‘N’ is the total number of input arrays.
       * @param lists
       * @param k
       * @return
       */
      public static int findKthSmallestII(List<Integer[]> lists, int k) {
        PriorityQueue<Node> minHeap = new PriorityQueue<Node>((e1,e2)->e1.value - e2.value);
        //inserting the first element of each array in a Min Heap.
        for (int i = 0; i < lists.size(); i++) {
            Node node = new Node(i,0,lists.get(i)[0]);
            minHeap.add(node);
        }

        int count = 0;
        Node node = null;
        while(!minHeap.isEmpty() && count < k) {
          //removing the current smallest
          node = minHeap.poll();
          //adding into the heap the next smallest number from the array which the 
          //current smallest came from (if it exists)
          if(lists.get(node.arrayIndex).length > node.elementIndex+1) {
            minHeap.add(new Node(node.arrayIndex, node.elementIndex+1, lists.get(node.arrayIndex)[node.elementIndex+1]));
          }
          count++;
        }
    
        return node.value;
      }
    
      @Test
      public void validate() {
        Integer[] l1 = new Integer[] { 2, 6, 8 };
        Integer[] l2 = new Integer[] { 3, 6, 7 };
        Integer[] l3 = new Integer[] { 1, 3, 4 };
        List<Integer[]> lists = new ArrayList<Integer[]>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        Assert.assertEquals(2, findKthSmallest(lists, 2));
        Assert.assertEquals(3, findKthSmallest(lists, 4));
        Assert.assertEquals(7, findKthSmallest(lists, 8));

        l1 = new Integer[] { 2, 6, 12};
        l2 = new Integer[] { 1,9 };
        l3 = new Integer[] { 23, 34, 90, 2000};
        lists = new ArrayList<Integer[]>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        Assert.assertEquals(9, findKthSmallest(lists, 4));  
        Assert.assertEquals(23, findKthSmallest(lists, 6));       
      }
}