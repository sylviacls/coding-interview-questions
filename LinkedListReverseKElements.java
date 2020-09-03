/**
 * 
 * Given the head of a LinkedList and a number ‘k’, reverse every ‘k’ sized sub-list 
 * starting from the head. 
 * If, in the end, you are left with a sub-list with less than ‘k’ elements, reverse it too.
 * 
 */

public class LinkedListReverseKElements {

    /**
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */  
    public static ListNode reverseKElementsIterative(ListNode head, int k) {
      //base cases
      if (k <= 1 || head == null || head.next == null) return head;

      ListNode current = head, previous = null;
      while(true) {
        ListNode lastNodeCurrentSublist = current;
        ListNode lastNodeOfPreviousPart = previous;
        ListNode next = null;
        for (int i = 0; i < k && current != null; i++) {
          next = current.next;
          current.next = previous;
          previous = current;
          current = next;  
        }
        //at this point previous is pointing to the begin of the reverse sublist 
        //and next is pointing to next node outside the sublist

        // connect with the previous part
        if (lastNodeOfPreviousPart != null) {
          lastNodeOfPreviousPart.next = previous; // 'previous' is now the first node of the sub-list
        } else { // this means we are changing the first node (head) of the LinkedList
          head = previous;
        }
        // connect with the next part
        lastNodeCurrentSublist.next = next;

        // break, if we've reached the end of the LinkedList
        if(current == null) break;
        // prepare for the next sub-list
        previous = lastNodeCurrentSublist;
      }
      return head;
    }

    /**
     * Approach: Using Recursion
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     */
    public static ListNode reverseKElementsRecursive(ListNode head, int k) {
        
      if (head == null || head.next == null || k <= 1) 	return head;
      
      ListNode current = head; 
      ListNode next = null; 
      ListNode prev = null; 
        
      int count = 0; 
      /* Reverse first k nodes of linked list */
      while (count < k && current != null)  { 
          next = current.next; 
          current.next = prev; 
          prev = current; 
          current = next; 
          count++; 
      } 
 
      /* next is now a pointer to (k+1)th node  
         Recursively call for the list starting from current. 
         And make rest of the list as next of first node */
      if (next != null)  
         head.next = reverseKElementsRecursive(next, k); 
 
      // prev is now head of input list 
      return prev;    
   }

    public static void main(String[] args) {
      ListNode head = new ListNode(1);
      head.next = new ListNode(2);
      head.next.next = new ListNode(3);
      head.next.next.next = new ListNode(4);
      head.next.next.next.next = new ListNode(5);
      head.next.next.next.next.next = new ListNode(6);
      head.next.next.next.next.next.next = new ListNode(7);
      head.next.next.next.next.next.next.next = new ListNode(8);
  
      ListNode result = LinkedListReverseKElements.reverseKElementsIterative(head, 3);
      System.out.print("Nodes of the reversed LinkedList are: ");
      while (result != null) {
        System.out.print(result.value + " ");
        result = result.next;
      }

      head = new ListNode(1);
      head.next = new ListNode(2);
      head.next.next = new ListNode(3);
      head.next.next.next = new ListNode(4);
      head.next.next.next.next = new ListNode(5);
      head.next.next.next.next.next = new ListNode(6);
      head.next.next.next.next.next.next = new ListNode(7);
      head.next.next.next.next.next.next.next = new ListNode(8);
      result = LinkedListReverseKElements.reverseKElementsRecursive(head, 2);
      System.out.print("Nodes of the reversed LinkedList are: ");
      while (result != null) {
        System.out.print(result.value + " ");
        result = result.next;
      }
    }
}

class ListNode {
    int value = 0;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
}