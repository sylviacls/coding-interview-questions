import java.util.PriorityQueue;

import org.junit.*;

/**
 * Given n ropes of different lengths, we need to connect these ropes into one rope. 
 * We can connect only 2 ropes at a time. 
 * The cost required to connect 2 ropes is equal to sum of their lengths. 
 * The length of this connected rope is also equal to the sum of their lengths. 
 * This process is repeated until n ropes are connected into a single rope. 
 * Find the min possible cost required to connect all ropes.
 * 
 * Solution approach:
 * We can notice that the lengths of the ropes which are picked first are included more 
 * than once in total cost. Therefore, the idea is to connect the smallest two ropes first 
 * and recur for remaining ropes. 
 */
public class ConnectNRopesMinCost {

    /**
     * We are gonna create a min-heap and insert all ropes into the min-heap.
     * While the number of elements in min-heap is not one, We will do:
     * 
     * Extract the minimum and second minimum from min-heap 
     * Add the above two extracted values and insert the added value to the min-heap. 
     * Maintain a variable for total cost and keep incrementing it by the sum of extracted values.
     * 
     * Time Complexty: O(N*LogN) to insert all the ropes in the heap
     *                  In each step, while processing the heap, we take out two elements from the heap and insert one.
     * Space Complexity: O(N), we need to store all the ropes
     * 
     * @param ropes
     * @return the min cost
     */
    public static int minimumCostToConnectRopes(int[] ropes) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        int totalCost = 0;

        for (int rope : ropes) {
            minHeap.add(rope);
        }

        while(minHeap.size() > 1) {
            int rope1 = minHeap.poll();
            int rope2 = minHeap.poll();

            int currentCost = rope1 + rope2;
            totalCost += currentCost;

            minHeap.add(currentCost);
        }
        return totalCost;
    }

    @Test
    public void validate() {
        Assert.assertEquals(33, minimumCostToConnectRopes(new int[] { 1, 3, 11, 5 }));
        Assert.assertEquals(36, minimumCostToConnectRopes(new int[] { 3, 4, 5, 6 }));
        Assert.assertEquals(42,  minimumCostToConnectRopes(new int[] { 1, 3, 11, 5, 2 }));
        Assert.assertEquals(29, minimumCostToConnectRopes(new int[]{ 4, 3, 2, 6 }));
      }
    
}