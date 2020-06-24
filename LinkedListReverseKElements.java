/**
 * Given the head of a LinkedList and a number ‘k’, reverse every ‘k’ sized sub-list 
 * starting from the head. If, in the end, you are left with a sub-list with less than ‘k’ elements,
 *  reverse it too.
 * 
 *  * Time Complexity: O(n) Space Complexity: O(1)
 */

public class LinkedListReverseKElements {

    public static boolean END_REACHED = false;

    public static ListNode reverseKElements(ListNode head, int K) {

        END_REACHED = false;
        int start = 1;
        int end = K;
        //index -> K
        // K+ 1 -> 2K+1
        // up to current == null
        ListNode current = head;
        while(current != null && !END_REACHED) {
          current = reverseSublist(current, start, end);
          start = start + K;
          end = end + K;
        }
        return current;

    }

    public static ListNode reverseSublist(ListNode head, int p, int q){

        if (p == q)
        return head;
  
      //finding the starting node of the sublist and saving the previous for late connection  
      ListNode lastNodeCurrentSublist = head;
      ListNode lastNodeOfPreviousPart = null;
  
      int indexStart = 1;
      while (lastNodeCurrentSublist != null && indexStart < p) {
        lastNodeOfPreviousPart = lastNodeCurrentSublist;
        lastNodeCurrentSublist = lastNodeCurrentSublist.next;
        indexStart++;
      }
  
      //reversing the sublist
      ListNode current = lastNodeCurrentSublist;
      ListNode previous = null;
      ListNode next = null;
  
      int indexEnd = q - p;
      while (current != null && indexEnd >= 0) {
        next = current.next;
        current.next = previous;
        previous = current;
        current = next;
        indexEnd--;
      }
      //at this point previous is pointing to the begin of the reverse sublist 
      //and next is pointing to next node outside the sublist
  
       // we have to check if the previousSublist node was the head or not
      if (lastNodeOfPreviousPart != null) {
        lastNodeOfPreviousPart.next = previous;
      } else {
        head = previous;
      }
  
      //handling the case where the sublist's lenght is lesser than K
      // and flagging the END_REACHED
      if(lastNodeCurrentSublist != null) {
        lastNodeCurrentSublist.next = next;
      } else {
         END_REACHED = true;
      }
  
      return head;
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
  
      ListNode result = LinkedListReverseKElements.reverseKElements(head, 3);
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
      result = LinkedListReverseKElements.reverseKElements(head, 2);
      System.out.print("Nodes of the reversed LinkedList are: ");
      while (result != null) {
        System.out.print(result.value + " ");
        result = result.next;
      }
    }
}

/*class ListNode {
    int value = 0;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
}*/