/**
 * https://www.geeksforgeeks.org/reverse-alternate-k-nodes-in-a-singly-linked-list/
 * https://www.geeksforgeeks.org/reverse-alternate-k-nodes-in-a-singly-linked-list-iterative-solution/?ref=rp
 * 
 * Given the head of a LinkedList and a number ‘k’, reverse every alternating ‘k’ sized 
 * sub-list starting from the head.
 * 
 * If, in the end, you are left with a sub-list with less than ‘k’ elements, reverse it too.
 * 
 * Example:
 * Inputs:   1->2->3->4->5->6->7->8->9->NULL and k = 3
 * Output:   3->2->1->4->5->6->9->8->7->NULL. 
 */
public class LinkedListReverseAlternateKElements {
    
    /**
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static ListNode reverse(ListNode head, int k) {
        if(head ==  null || head.next == null || k <= 1) return head;
        //current and previous are out of while loop because we need to keep track their values
        //for each sublist processed in order to make late connections
        ListNode current = head;
        ListNode previous = null;
        boolean reverse = true;

        while(true) {
            ListNode lastNodePrevPart = previous;
            ListNode lastNodeCurrSublist = current;
            ListNode next = null;
            int count = k;
            if(reverse) {
                while(current != null && count > 0) {
                    next = current.next;
                    current.next = previous;
                    previous = current;
                    current = next;
                    count--;
                }
                //connect previous list, if it exists, with the the current reversed list.
                if(lastNodePrevPart != null) {
                    lastNodePrevPart.next = previous;
                } else {
                    head = previous;
                }
                //connecting the last node of current reversed list with the next node;
                lastNodeCurrSublist.next = next;

                if(current == null) break; //end of the list
                
                reverse = false;
            } else {
                //just move k nodes ahead
                while(current != null && count > 0) {
                    lastNodeCurrSublist = current;
                    current = current.next;
                    count--;
                }
                if(current == null) break; //end of the list

                reverse = true;
            }                
            //preparing for the next sublist
            previous = lastNodeCurrSublist;
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
    
        ListNode result = reverse(head, 2);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
          System.out.print(result.value + " ");
          result = result.next;
        }
    }
}
