import org.junit.Assert;
import org.junit.Test;

/**
 * Given the head of a Singly LinkedList, write a method to modify the LinkedList 
 * such that the nodes from the second half of the LinkedList are inserted alternately 
 * to the nodes from the first half in reverse order. So if the LinkedList has nodes
 *  1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null, your method should return 
 *  1 -> 6 -> 2 -> 5 -> 3 -> 4 -> null.
 * Your algorithm should not use any extra space and the input LinkedList should 
 * be modified in-place.
 */
public class LinkedListRearrenged {

    public static void reorder(ListNode head) {

        if (head == null || head.next == null)
        return;

        ListNode firstHalf = head;
        ListNode middle = findMiddle(head);

        ListNode secondHalf = reverse(middle);

        while(firstHalf != null && secondHalf != null && 
              firstHalf.next != null && !firstHalf.next.equals(secondHalf)) {

            ListNode nextFH = firstHalf.next;
            firstHalf.next = secondHalf;
            firstHalf = nextFH;

            ListNode nextSH = secondHalf.next;
            secondHalf.next = nextFH;
            secondHalf = nextSH;
        }

    }
    
    public static ListNode reverse(ListNode node) {
        ListNode current = node;
        ListNode previous = null;
        ListNode next = null;
        while(current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }

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

        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);
        head.next.next.next.next.next = new ListNode(12);

        LinkedListRearrenged.reorder(head);
        String result = "";
        while(head != null) {
            result += head.value + " ";
            head = head.next;
        }
        result = result.trim();
        Assert.assertEquals("2 12 4 10 6 8", result);


        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(4);
        head2.next.next = new ListNode(6);
        head2.next.next.next = new ListNode(8);
        head2.next.next.next.next = new ListNode(10);

        LinkedListRearrenged.reorder(head2);
        String result2 = "";
        while(head2 != null) {
            result2 += head2.value + " ";
            head2 = head2.next;
        }
        result2 = result2.trim();
        Assert.assertEquals("2 10 4 8 6", result2);
        
    }
}


/*class ListNode {
  int value = 0;
  ListNode next;

  ListNode(int value) {
    this.value = value;
  } 
}*/