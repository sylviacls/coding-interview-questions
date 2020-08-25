import org.junit.*;
import java.util.*;

/**
 * Leetcode: 234. Palindrome Linked List
 * 
 * Given the head of a Singly LinkedList, write a method to check if the LinkedList is
 * a palindrome or not. 
 * Your algorithm should use constant space and the input LinkedList should be in the
 * original form once the algorithm is finished. 
 * 
 * The algorithm should have O(N) time complexity . 
 * 
 */
public class LinkedListPalindrome {

  /**
   * Approach: Two pointers: slow/fast
   * 
   * Once we have the middle of the LinkedList, we will reverse the second half.
   * Then, we will compare the first half with the reversed second half to see if the
   * LinkedList represents a palindrome. Finally, we will reverse the second half of the
   * LinkedList again to revert and bring the LinkedList back to its original form.
   * 
   * Time Complexity: O(n)
   * Space Compexity: O(1)
   */
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

  /**
   * Approach: Using Stack
   * 
   * Time Complexity: O(N)
   * Space Complexity: O(N)
   * 
   */
  public static Boolean isPalindromeII(ListNode head) {
      Deque<ListNode> stack = new LinkedList<ListNode>();
      ListNode pointer = head;
      while (pointer != null) {
        stack.push(pointer);
        pointer = pointer.next;
      }
      ListNode start = head;
      while(start != null) {
        if(start.value != stack.poll().value) return false;
        start = start.next;
      }
      return true;
  }

  @Test
  public void validade() {
    ListNode head = new ListNode(2);
    head.next = new ListNode(4);
    head.next.next = new ListNode(6);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(2);

    Assert.assertTrue(isPalindrome(head));
    Assert.assertTrue(isPalindromeII(head));

    head.next.next.next.next.next = new ListNode(2);
    Assert.assertFalse(isPalindrome(head));
    Assert.assertFalse(isPalindromeII(head));
  }
}

/*class ListNode {
  int value = 0;
  ListNode next;

  ListNode(int value) {
    this.value = value;
  }
}*/
