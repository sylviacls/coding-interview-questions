/**
 * Leetcode: 25. Reverse Nodes in k-Group
 * 
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified
 * list.
 * k is a positive integer and is less than or equal to the length of the linked list. 
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain
 * as it is.
 *
 * Example:
 * 
 * Given this linked list: 1->2->3->4->5
 * For k = 2, you should return: 2->1->4->3->5
 * For k = 3, you should return: 3->2->1->4->5 
 */
public class LinkedListReverseKElementsII {

    /**
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */ 
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode current = head;
        ListNode previous = null;

        while(true) {
            //calculate the number of nodes
            int numNodes = 0;
            ListNode dumbCount = current;
            do {
                numNodes++;
                dumbCount = dumbCount.next;
            } while (dumbCount != null) ;

            //check if it's possible to make another reversing
            if( numNodes >= k) {
                //reversking k-nodes
                ListNode lastNodePrevPart = previous;
                ListNode lastNodeCurrList = current;
                ListNode next = null;
                int count = k;
                while(current != null && count > 0) {
                    next = current.next;
                    current.next = previous;
                    previous = current;
                    current = next;
                    count--;
                }
                if(lastNodePrevPart == null) {
                    head = previous;
                } else {
                    lastNodePrevPart.next = previous;
                }
                lastNodeCurrList.next = next;
                
                // break, if we've reached the end of the LinkedList
                if(current == null) break;
                
                // prepare for the next sub-list
                previous = lastNodeCurrList;

            } else {
                break;
            }
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
    
        ListNode result = reverseKGroup(head, 2);
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
        result = reverseKGroup(head, 3);
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