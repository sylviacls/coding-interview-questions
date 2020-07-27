import java.util.PriorityQueue;

/**
 * 
 * Given an array of ‘K’ sorted LinkedLists, merge them into one sorted list.
 * 
 * Example 1:
 * Input: L1=[2, 6, 8], L2=[3, 6, 7], L3=[1, 3, 4]
 * Output: [1, 2, 3, 3, 4, 6, 6, 7, 8]
 * 
 * Whenever we are given ‘K’ sorted arrays, we can use a Heap to efficiently perform a sorted 
 * traversal of all the elements of all arrays. 
 * We can push the smallest (first) element of  each sorted array in a Min Heap to get the 
 * overall minimum. 
 * While inserting elements to the Min Heap we keep track of which array the element came from.
 * We can, then, remove the top element from the heap to get the smallest element and push
 * the next element from the same array, to which this smallest element belonged, to the heap.
 * We can repeat this process to make a sorted traversal of all elements.
 */

class ListNode {
    int value;
    ListNode next;
  
    ListNode(int value) {
      this.value = value;
    }
}
public class MergeKSortedLists {
    /**
     * Time Complexity: O(NlogK), n total number and k total lists
     * Space Complexity: O(K),  because, at any time, our min-heap will be storing one number
     *                   from all the ‘K’ input arrays.
     * @param lists
     * @return
     */
    public static ListNode merge(ListNode[] lists) {
        if (lists == null|| lists.length == 0 ) return null;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>((e1,e2)-> e1.value - e2.value );
        //inserting the first element of each array in a Min Heap.
        for (ListNode root : lists) {
            if(root != null)
                minHeap.add(root);
        }
        // take the smallest (top) element form the min-heap and add it to the result; 
        // if the top element has a next element add it to the heap
        ListNode resultHead = null;
        ListNode resultTail = null;
        while(!minHeap.isEmpty()) {
            ListNode curr = minHeap.poll();
            // if final merged list is empty 
            if(resultHead == null) {
                resultHead = curr;
                // points to the last node so far of the final merged list 
                resultTail = curr;
            } else {
                //insert current node at the end of the final merged list so far
                resultTail.next = curr;
                //update the last pointer
                resultTail = curr;
            }
            //After removing the smallest element from the heap, we can insert the next element 
            //of the same list into the heap.
            if(curr.next != null) {
                minHeap.add(curr.next);
            }
        }
        return resultHead;
    }
    
      public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(6);
        l1.next.next = new ListNode(8);
    
        ListNode l2 = new ListNode(3);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(7);
    
        ListNode l3 = new ListNode(1);
        l3.next = new ListNode(3);
        l3.next.next = new ListNode(4);
    
        ListNode result = MergeKSortedLists.merge(new ListNode[] { l1, l2, l3 });
        System.out.print("Here are the elements form the merged list: ");
        while (result != null) {
          System.out.print(result.value + " ");
          result = result.next;
        }
      }
}