import org.junit.*;
import java.util.*;

/**
 *Leetcode: 141. Linked List Cycle

 *  Given the head of a Singly LinkedList, write a function to determine if 
 * the LinkedList has a cycle in it or not.

 */


public class LinkedListHasCycle {
    /**
     * Approach: Two Pointers - Slow/Fast
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static Boolean hasCycle(ListNode head) {

        if (head == null)  return false;

        ListNode slowPointed = head;
        ListNode fastPointed = head;
        while (fastPointed != null && fastPointed.next != null) {
            slowPointed = slowPointed.next;
            fastPointed = fastPointed.next.next;
            if(slowPointed == fastPointed)  return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
    
      /**
       * Appoach: Using a HashTable
       * Time Complexity: O(N)
       * Space Complexity: O(N)
       */
      public static Boolean hasCycleII(ListNode head) {
        Set<ListNode> visited = new HashSet<ListNode>();
        ListNode pointer = head;
        while(pointer != null) {
          if(visited.contains(pointer)) {
            return true;
          } else {
            visited.add(pointer);
          }
          pointer = pointer.next;
        }
        return false;
      }
      @Test
      public void validate() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        Assert.assertFalse(LinkedListHasCycle.hasCycle(head));
        Assert.assertFalse(LinkedListHasCycle.hasCycleII(head));
    
        head.next.next.next.next.next.next = head.next.next;
        Assert.assertTrue(LinkedListHasCycle.hasCycle(head));
        Assert.assertTrue(LinkedListHasCycle.hasCycleII(head));
    
        head.next.next.next.next.next.next = head.next.next.next;
        Assert.assertTrue(LinkedListHasCycle.hasCycle(head));
        Assert.assertTrue(LinkedListHasCycle.hasCycleII(head));
      }
}

/*class ListNode {
    int value = 0;
    ListNode next;
  
    ListNode(int value) {
      this.value = value;
    }
}  */