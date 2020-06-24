import org.junit.Assert;
import org.junit.Test;

/**
 * Given the head of a Singly LinkedList, write a function to determine if 
 * the LinkedList has a cycle in it or not.
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */


public class LinkedListHasCycle {
    public static Boolean hasCycle(ListNode head) {

        ListNode slowPointed = head;
        ListNode fastPointed = head;
        while (fastPointed != null && fastPointed.next != null) {

            slowPointed = slowPointed.next;
            fastPointed = fastPointed.next.next;

            if(slowPointed == fastPointed)  return Boolean.TRUE;
                      
        }
        return Boolean.FALSE;
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
    
        head.next.next.next.next.next.next = head.next.next;
        Assert.assertTrue(LinkedListHasCycle.hasCycle(head));
    
        head.next.next.next.next.next.next = head.next.next.next;
        Assert.assertTrue(LinkedListHasCycle.hasCycle(head));
      }
}

/*class ListNode {
    int value = 0;
    ListNode next;
  
    ListNode(int value) {
      this.value = value;
    }
}  */