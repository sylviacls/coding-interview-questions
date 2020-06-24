import org.junit.Assert;
import org.junit.Test;

/**
 * Given the head of a Singly LinkedList, write a method to return the middle node of
 * the LinkedList. If the total number of nodes in the LinkedList is even,
 *  return the second middle node.
 * 
 * t the fast pointer is always twice the nodes ahead of the slow pointer.
 *  This way, when the fast pointer reaches the end of the LinkedList, the slow pointer
 *  will be pointing at the middle node.
 * 
 * Time complexity: O(n)
 * Space complexity: O(1) 
 */
public class LinkedListFindMidle {

    public static ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        } 
        return slow;
    }

    @Test
    public void validade() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        Assert.assertEquals(head.next.next, LinkedListFindMidle.findMiddle(head));
    
        head.next.next.next.next.next = new ListNode(6);
        Assert.assertEquals( head.next.next.next, LinkedListFindMidle.findMiddle(head));
    
        head.next.next.next.next.next.next = new ListNode(7);
        Assert.assertEquals( head.next.next.next, LinkedListFindMidle.findMiddle(head));

    }
    
}

/*
class ListNode {
    int value = 0;
    ListNode next;
  
    ListNode(int value) {
      this.value = value;
    }
}  
*/