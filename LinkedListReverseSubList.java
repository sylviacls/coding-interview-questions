/**
 * Given the head of a LinkedList and two positions ‘p’ and ‘q’, reverse the
 * LinkedList from position ‘p’ to ‘q’.
 * 
 * Time Complexity: O(n) Space Complexity: O(1)
 */
public class LinkedListReverseSubList {

  /**
   * Skip the first p-1 nodes, to reach the node at position p. Remember the node
   * at position p-1 to be used later to connect with the reversed sub-list. Next,
   * reverse the nodes from p to q using the same approach discussed in Reverse a
   * LinkedList. Connect the p-1 and q+1 nodes to the reversed sub-list.
   */
  public static ListNode reverseSubList(ListNode head, int p, int q) {

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

    lastNodeCurrentSublist.next = next;

    return head;
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);

    ListNode result = LinkedListReverseSubList.reverseSubList(head, 2, 5);
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