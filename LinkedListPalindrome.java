import org.junit.Assert;
import org.junit.Test;

/**
 * Given the head of a Singly LinkedList, write a method to check if the
 * LinkedList is a palindrome or not. Your algorithm should use constant space
 * and the input LinkedList should be in the original form once the algorithm is
 * finished. The algorithm should have O(N) time complexity . Once we have the
 * middle of the LinkedList, we will reverse the second half. Then, we will
 * compare the first half with the reversed second half to see if the LinkedList
 * represents a palindrome. Finally, we will reverse the second half of the
 * LinkedList again to revert and bring the LinkedList back to its original
 * form.
 * 
 * Time Complexity: O(n)
 * Space Compexity: O(1)
 */
public class LinkedListPalindrome {

  public static Boolean isPalindrome(ListNode head) {

      if (head == null || head.next == null)  return true;

      ListNode start = head;
      ListNode middle = findMiddle(head);


      ListNode headSecondHalf = reverseList(middle);
      ListNode copyHeadSecondHalf = headSecondHalf;
      while(head != null && headSecondHalf != null) {
        if(start.value != headSecondHalf.value) {
          break;
        }
        start =  start.next;
        headSecondHalf = headSecondHalf.next;
      }


      reverseList(copyHeadSecondHalf); // revert the reverse of the second half
      if (head == null || headSecondHalf == null) // if both halves match
        return Boolean.TRUE;

      return Boolean.FALSE;

    }

  // 1-> 2-> 3-> null
  // 3-> 2-> 1-> null
  public static ListNode reverseList(ListNode node) {

    ListNode previous = null;
    ListNode current = node;
    ListNode next = null;

    while (current != null) {
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

    while (fast != null && fast.next != null) {
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
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(2);

    Assert.assertTrue(isPalindrome(head));

    head.next.next.next.next.next = new ListNode(2);
    Assert.assertFalse(isPalindrome(head));

  }
}

/*class ListNode {
  int value = 0;
  ListNode next;

  ListNode(int value) {
    this.value = value;
  }
}*/
