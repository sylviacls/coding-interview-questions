import org.junit.Assert;
import org.junit.Test;

/**
 * Leetcode: 876. Middle of the Linked List
 * 
 * Given the head of a Singly LinkedList, write a method to return the middle node of
 * the LinkedList. If the total number of nodes in the LinkedList is even,
 * return the second middle node.
 * 
 * Example 1:
 * Input: 1 -> 2 -> 3 -> 4 -> 5 -> null
 * Output: 3
 * 
 * Example 2:
 * Input: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
 * Output: 4
 */
public class LinkedListFindMidle {

    /**
     * Approach: Two Pointers slow/fast
     * 
     * The fast pointer is always twice the nodes ahead of the slow pointer.
     * This way, when the fast pointer reaches the end of the LinkedList, the slow pointer
     * will be pointing at the middle node.
     * 
     * Time complexity: O(n)
     * Space complexity: O(1) 
     */
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