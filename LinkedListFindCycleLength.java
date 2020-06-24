import org.junit.Assert;
import org.junit.Test;

/**
 * Given the head of a LinkedList with a cycle, find the length of the cycle.
 * Once the fast and slow pointers meet (the cycle node), we can save the slow pointer 
 * and iterate the whole cycle with another pointer until we see the slow pointer again 
 * to find the length of the cycle.
 */
public class LinkedListFindCycleLength {
    public static int findCycleLength(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
          fast = fast.next.next;
          slow = slow.next;
          if (slow == fast) // found the cycle
            return calculateLength(slow);
        }
        return 0;
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
    public void validade(){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = head.next.next;
        Assert.assertEquals(4, LinkedListFindCycleLength.findCycleLength(head));
    
        head.next.next.next.next.next.next = head.next.next.next;
        Assert.assertEquals(3, LinkedListFindCycleLength.findCycleLength(head));
      }
}

class ListNode {
    int value = 0;
    ListNode next;
  
    ListNode(int value) {
      this.value = value;
    }
}  