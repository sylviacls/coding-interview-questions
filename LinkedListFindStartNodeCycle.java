import org.junit.*;
import java.util.*;

/**
 * 
 * 142. Linked List Cycle II
 * 
 * Given the head of a Singly LinkedList that contains a cycle, write a function to find 
 * the starting node of the cycle. If there is no cycle, return null.
 * 
 */
public class LinkedListFindStartNodeCycle {


    /**
     * Approach: Using a HashMap
     * 
     * Time complexity: O(N)
     * Space complexity: O(N)
     * 
     */
    public static ListNode findStartNodeCycleII(ListNode head) {
      if(head == null || head.next == null) return null;
      Set<ListNode> visited = new HashSet<ListNode>();
      
      ListNode pointer = head;
      
      while (pointer != null) {
          if(visited.contains(pointer)) return pointer;
          
          visited.add(pointer);
          pointer = pointer.next;
      }
      return null;       
    }
    
    /**
     * Approach: Two Pointers: slow/fast
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public static ListNode findStartNodeCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;
        int length = 0;
        
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast =  fast.next.next;

            if(fast == slow) { // found the cycle
                length = calculateLength(slow);
                break;
            } 
        }
        return  findStart(head, length);
    }

    private static ListNode findStart(ListNode head, int cycleLength) {
        ListNode pointer1 = head;
        ListNode pointer2 = head;
        // move pointer2 ahead 'cycleLength' nodes
        while (cycleLength > 0) {
          pointer2 = pointer2.next;
          cycleLength--;
        }
    
        // increment both pointers until they meet at the start of the cycle
        while (pointer1 != pointer2) {
          pointer1 = pointer1.next;
          pointer2 = pointer2.next;
        }
    
        return pointer1;
    }

    private static int calculateLength(ListNode slow) {

        ListNode current = slow;
        int count = 0;
        do {
          current = current.next;
          count++;
            
        }while(current != slow);
        return count;
    }

    
    @Test
    public void validate(){

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
    
        head.next.next.next.next.next.next = head.next.next;
        Assert.assertEquals(head.next.next, findStartNodeCycle(head));
        Assert.assertEquals(head.next.next, findStartNodeCycleII(head));

        head.next.next.next.next.next.next = head.next.next.next;
        Assert.assertEquals(head.next.next.next, findStartNodeCycle(head));
        Assert.assertEquals(head.next.next.next, findStartNodeCycleII(head));
    
        head.next.next.next.next.next.next = head;
        Assert.assertEquals(head, findStartNodeCycle(head));
        Assert.assertEquals(head, findStartNodeCycleII(head));
    }

}

/*class ListNode {
    int value = 0;
    ListNode next;
  
    ListNode(int value) {
      this.value = value;
    }
}  */