import org.junit.Assert;
import org.junit.Test;

/**
 * Given the head of a Singly LinkedList, reverse the LinkedList. Write a
 * function to return the new head of the reversed LinkedList.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class LinkedListReverse {

    public static ListNode reverse(ListNode head) {
        ListNode current = head; // current node that we will be processing
        ListNode previous = null; // previous node that we have processed
        ListNode next = null; // will be used to temporarily store the next node

        while (current != null) {
            next = current.next; // temporarily store the next node
            current.next = previous; // reverse the current node
            previous = current; // before we move to the next node, point previous to the current node
            current = next; // move on the next node
        }
        // after the loop current will be pointing to 'null' and 'previous' will be the new head
        return previous;
    }

    @Test
    public void validate() {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);

        head = LinkedListReverse.reverse(head);
        String result = "";
        while (head != null) {
            result += head.value + " ";
            head = head.next;
        }
        result = result.trim();
        Assert.assertEquals("10 8 6 4 2", result);
    }

}

/*class ListNode {
    int value = 0;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
}*/