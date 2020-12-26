/**
 * Cracking the PM interview
 * 16.7 Insert a node into a sorted linked list (in order).
 * 
 * Don’t forget about what happens when the new element is at the start or end!
 * Assume that the list can contain duplicate numbers
 */
public class LinkedListInsertNumberInOrder {
    /**
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static ListNode insertNumber(ListNode head, int number) {
        ListNode newNode = new ListNode(number);
        if(head == null || newNode.value <= head.value) {
            newNode.next = head;
            head = newNode;
            return head;
        }

        ListNode curr = head;
        ListNode prev = curr;
        while(curr != null) {
            if(number <= curr.value) {
                newNode.next = curr;
                prev.next = newNode;
                return head;
            } else {
                prev = curr;
                curr = curr.next;
            }
        }
        //if this point has been reached, thus, the new data will be placed at the tail
        prev.next = newNode;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);

        head = insertNumber(head,5);
        head = insertNumber(head,1);
        head = insertNumber(head,11);
        String result = "";
        while (head != null) {
            result += head.value + " ";
            head = head.next;
        }
        System.out.println(result);
    }
}

/*class ListNode {
    int value = 0;
    ListNode next;
  
    ListNode(int value) {
      this.value = value;
    }
  }*/