import java.util.*;

/**
 * Leetcode: 138. Copy List with Random Pointer
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 * 
 * A linked list is given such that each node contains an additional random
 * pointer which could point to any node in the list or null. Return a deep copy
 * of the list.
 * 
 * The Linked List is represented in the input/output as a list of n nodes. Each
 * node is represented as a pair of [val, random_index] where: val: an integer
 * representing Node.val random_index: the index of the node (range from 0 to
 * n-1) where random pointer points to, or null if it does not point to any
 * node.
 */
public class LinkedListRandomPointer {

    /**
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     * @param head
     * @return
     */
    public static Node copyRandomList(Node head) {
        HashMap<Node, Node> copies = new HashMap<Node, Node>();
        Node pointerOrig = head;
        while(pointerOrig != null) {
            copies.put(pointerOrig, new Node(pointerOrig.val));
            pointerOrig = pointerOrig.next;
        }

        pointerOrig = head;
        while(pointerOrig != null) {
            Node pointerCopy = copies.get(pointerOrig);
            pointerCopy.next = copies.get(pointerOrig.next);
            pointerCopy.random = copies.get(pointerOrig.random);
            pointerOrig = pointerOrig.next;
        }

        return copies.get(head);
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}