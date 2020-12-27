/**
 * Cracking the PM interview
 * 
 * 16.8 “Sort” a linked list that contains just 0s and 1s. 
 * That is, modify the list such that all 0s come before all 1s.
 */
public class LinkedList01Sort {
     /**
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     * 
     * Since we only need to move the values, we can also just iterate through the
     * linked list, swapping the 0s and 1s as we find them.
     * This approach works by two pointers, p and q. The p pointer looks for 1s and the
     * q pointer looks for 0s. When they find their values, they swap.
     * 
     * In other words, p is always pointing to the first 1 and q is always pointing to the
     * first out of place 0 (which is the first 0 after p). Whenever q finds a 0, we know
     * the 0 is out of place. We swap its value with p and move p to the next node
     */
    public static ListNode sort(ListNode head) {
       ListNode p = findNextValue(head, 1); //find first 1
       ListNode q = findNextValue(p.next, 0); //find first 0

       while(p != null && q != null) {
         p.value = 0;
         q.value = 1;
         p = findNextValue(p, 1);
         q = findNextValue(p.next, 0);
       }
        return head;
    }

    public static ListNode findNextValue(ListNode node, int value) {
      while(node != null && node.value != value) {
        node = node.next;
      }
      return node;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(0);
        head.next.next.next.next = new ListNode(0);

        ListNode result = sort(head);
        while(result != null) {
          System.out.print(result.value+ " ");
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