import java.util.*;

/**
 * Leetcode: 146. LRU Cache
 * https://leetcode.com/problems/lru-cache/
 * 
 * Design a data structure that follows the constraints of a Least Recently Used
 * (LRU) cache.
 * 
 * Implement the LRUCache class: - LRUCache(int capacity) Initialize the LRU
 * cache with positive size capacity. - int get(int key) Return the value of the
 * key if the key exists, otherwise return -1. - void put(int key, int value)
 * Update the value of the key if the key exists. Otherwise, add the key-value
 * pair to the cache. If the number of keys exceeds the capacity from this
 * operation, evict the least recently used key.
 * 
 * Follow up: Could you do get and put in O(1) time complexity?
 */
public class LRUCacheLeetcode {
    private NodeLRU head;
    private NodeLRU tail;
    private Map<Integer, NodeLRU> map;
    private int capacity;

    public LRUCacheLeetcode(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<Integer, NodeLRU>();
    }
    
    public int get(int key) {
         if(this.map.containsKey(key)) {
            NodeLRU accessedNode =  this.map.get(key);
            remove(accessedNode);
            setHead(accessedNode);
            return accessedNode.value;
         } 
         return -1;
    }
    private void remove(NodeLRU node) {
        if(node.previous != null) {
            node.previous.next = node.next;
        } else{
            this.head = node.next;
        }

        if(node.next != null) {
            node.next.previous = node.previous;
        } else {
            this.tail = node.previous;
        }
    }
    
    private void setHead(NodeLRU node) {
        node.next = this.head;
        node.previous = null;

        if(head != null) {
            this.head.previous = node;
        } 

        this.head = node;

        if(tail == null) {
            this.tail = node;
        } 
    }

    public void put(int key, int value) {
        if(!this.map.containsKey(key)) {
            NodeLRU newNode = new NodeLRU(key, value);
            if(map.size() >= this.capacity) {
                this.map.remove(this.tail.key);
                remove(this.tail);
            } 
            setHead(newNode);
            map.put(key, newNode);
        } else { //update the old value
            NodeLRU oldNode = this.map.get(key);
            oldNode.value = value;
            remove(oldNode);
            setHead(oldNode);
        }

    }

    public static void main(String[] args) {
        LRUCacheLeetcode lRUCache = new LRUCacheLeetcode(2);
        lRUCache.put(2, 1);
        lRUCache.put(3, 2); 
        System.out.println( lRUCache.get(3));   
        System.out.println( lRUCache.get(2));   
        lRUCache.put(4, 3); 
        System.out.println(lRUCache.get(2));
        System.out.println( lRUCache.get(3));   
        System.out.println( lRUCache.get(3));    
        System.out.println( lRUCache.get(4));    
    }
}
class NodeLRU {
    int value;
    int key;
    NodeLRU next;
    NodeLRU previous;
    NodeLRU(int key, int value) {
        this.key = key;
        this.value = value;
    }
}