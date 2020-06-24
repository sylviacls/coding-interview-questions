import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given two singly linked lists that intersect at some point, find the intersecting node. 
 * The lists are non-cyclical.
 * For example, given A = 3 -> 7 -> 8 -> 10 and B = 99 -> 1 -> 8 -> 10, return node 8.
 * In this example, assume nodes with the same value are the exact same node objects.
 * Do this in O(M + N) time (where M and N are the lengths of the lists) 
 * and constant space.
 */
public class LinkedListsFindIntersection {

    //In order to maintain the ListNode structure we will create a HashMap to keep tracking of visited nodes
    public static ListNode findIntersection(ListNode head1, ListNode head2) {
       
        if(head1 == null || head2 == null) return null;

        if(head1 == head2) return head1;

        ListNode current1 = head1;
        Map<Integer, ListNode> visitedMap = new HashMap<Integer, ListNode>();

        //Traversing list1 and keeo tracking of all nodes
        while(current1 != null) {
            visitedMap.put(current1.value, current1);
            current1 = current1.next;
        }

        ListNode current2 = head2;
        while(current2 != null) {
            if(visitedMap.containsKey(current2.value)) {
                return visitedMap.get(current2.value);
            } else{
                current2 = current2.next;
            }

        }
        return null;
    }

    @Test
    public void validate() { 
  
        // creating first linked list 
        ListNode head = new ListNode(3);
        head.next = new ListNode(6);
        head.next.next = new ListNode(9);
        head.next.next.next = new ListNode(15);
        head.next.next.next.next = new ListNode(30);

        // creating second linked list 
        ListNode head2 = new ListNode(10);
        head2.next = new ListNode(15);
        head2.next.next = new ListNode(30);

  
        Assert.assertEquals(15, findIntersection(head,head2).value); 
    } 
} 


/*class ListNode {
  int value = 0;
  ListNode next;

  ListNode(int value) {
    this.value = value;
  } 
}*/