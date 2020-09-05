/**
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 * 
 * Example 1:
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * 
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 * 
 * Example 2:
 * Input: 0->1->2->NULL, k = 4
 * Output: 2->0->1->NULL
 * Explanation:
 * rotate 1 steps to the right: 2->0->1->NULL
 * rotate 2 steps to the right: 1->2->0->NULL
 * rotate 3 steps to the right: 0->1->2->NULL
 * rotate 4 steps to the right: 2->0->1->NULL
 */

public class LinkedListRotate {

    /**
     * Approch: Making n-rotations in a stepwise way.
     * 
     * Time Complexity: O (N*K)
     * Space Complexity: O(1)
     */
    public static ListNode rotate(ListNode head, int rotations) {

        if(head == null || head.next == null || rotations <=0 ) return head;

        ListNode tail = null;
        ListNode previousTail = null;
        int listLength = 1;

        // find the length and the last node of the list
        ListNode dumb = head;
        while (dumb.next != null) {
          listLength++;
          dumb = dumb.next;
        }
        // no need to do rotations more than the length of the list
        rotations %= listLength; 

        while(rotations > 0) {

          // find the length and the last node of the list
          dumb = head;
          while (dumb.next != null) {
            previousTail = dumb;
            dumb = dumb.next;
          }
          // at the end of the while loop dumb will be point to tail
          tail = dumb;
          // make rotation
          // first update head
          tail.next = head;
          head = tail;
          // update tail
          previousTail.next = null;
          tail = previousTail;
          rotations--;
        }
        return head;
      }

  /**
   * Approach: Take the sub-list of ‘k’ ending nodes of the LinkedList and connect them to 
   * the beginning.
   * 
   * Time Complexity: O (N)
   * Space Complexity: O(1) 
   */
  public static ListNode rotateII(ListNode head, int rotations) {
      if (head == null || head.next == null || rotations <= 0)
        return head;

      // find the length and the last node of the list
      ListNode lastNode = head;
      int listLength = 1;
      while (lastNode.next != null) {
        lastNode = lastNode.next;
        listLength++;
      }

      lastNode.next = head; // connect the last node with the head to make it a circular list
      rotations %= listLength; // no need to do rotations more than the length of the list
      int skipLength = listLength - rotations;
      ListNode lastNodeOfRotatedList = head;
      for (int i = 0; i < skipLength - 1; i++)
        lastNodeOfRotatedList = lastNodeOfRotatedList.next;

      // 'lastNodeOfRotatedList.next' is pointing to the sub-list of 'k' ending nodes
      head = lastNodeOfRotatedList.next;
      lastNodeOfRotatedList.next = null;
      return head;
  }

    
      public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
    
        ListNode result = LinkedListRotate.rotate(head, 3);
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