import java.util.HashMap;
import java.util.Map;

/**
 *  A Least Recently Used (LRU) Cache organizes items in order of use, allowing you to
 *  quickly identify which item hasn't been used for the longest amount of time.
 *  We'll set up our linked list with the most-recently used item at the head of the list
 *  and the least-recently used item at the tail;
 * 
 * Implementation: Doubly Linked List + HashMap
 * 
 * Strengths:
 * Fast accesses. LRU caches store items in order from most-recently used to 
 *          least-recently used.  That means both can be accessed in O(1) time.
 * Fast updates. Each time an item is accessed, updating the cache takes O(1) time.
 * To speed up the search:  finding an item in a linked list is O(n) time, 
 *          since we need to walk the whole list.
 *          We'll add in a hash map that maps items to linked list nodes
 * 
 * Weakness:
 * Space heavy. An LRU cache tracking nn items requires a linked list of length n,
 *  and a hash map holding n items. 
 * That's O(n) space, but it's still two data structures
 * 
 * Accessing and Evicting:
 * 
 * 1- look up the item in the HashMap
 * 1.1 - Cache hit: 
 *      use the hash to find the corresponding linked list node
 *      move the node for the head
 * 1.2 - Cache miss: 
 *      load the element into the linked list
 *      if linkedlist is full, evict the least used (tail) removing from list and hash
 *      create a new node for this item and insert it at the head
 *      add it to the hashmap
 * 
 *  We will remove element from bottom and add element on start of LinkedList 
 *  and whenever any entry is accessed , it will be moved to top. 
 *  so that recently used entries will be on Top and Least used will be on Bottom
 */
public class LRUCache<K,V> {

    private Node<K,V> head;
    private Node<K,V> tail;
    private Map<K, Node<K,V>> map; //the item will be the key and its position, the value
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<K, Node<K,V>>();
    }

    public void put(K key, V value) {
        if(!this.map.containsKey(key)) {
            Node<K,V> newNode = new Node<K,V>(key, value);
            if(map.size() >= this.capacity) {
                this.map.remove(this.tail.key);
                remove(this.tail);
            } 
            setHead(newNode);
            map.put(key, newNode);
        } else { //update the old value
            Node<K,V> oldNode = get(key);
            oldNode.value = value;
            remove(oldNode);
            setHead(oldNode);
        }

    }

    public Node<K, V> get(K key) {
        if(this.map.containsKey(key)) {
           Node<K,V> accessedNode =  this.map.get(key);
           remove(accessedNode);
           setHead(accessedNode);
           return accessedNode;
        } 
        return null;
    }

    private void setHead(Node<K, V> node) {
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

    private void remove(Node<K, V> node) {
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

    public void print() {
        Node<K,V> current = this.head;
        while (current != null) {
            System.out.println(current.key + " - " + current.value);
            current = current.next;
        }
    }

    public static void main(String[] args) {

        LRUCache<Integer, String> cache = new LRUCache<Integer,String>(4);
        cache.put(1, "Banana Bread");
        cache.put(2, "Chocolate Cake");
        cache.put(3, "Vanilla Cupcake");
        cache.put(4, "Nutella Brownie");
        System.out.println("Inicial cache..");
        cache.print();
        System.out.println();

        System.out.println("Accessing some items");
        System.out.println("Accessed item:" + cache.get(3).value);
        System.out.println("Accessed item:" + cache.get(1).value); 
        System.out.println();
        
        cache.print();
         
        System.out.println("Adding a new item"); cache.put(5, "Caramel Fudge");
        System.out.println(); 
        cache.print();
        
        System.out.println("Accessing some items");
        System.out.println("Accessed item:" + cache.get(4).value);
        cache.print();
        
         
    }
}

class Node<K,V> {
    K key;
    V value;
    Node<K,V> previous;
    Node<K,V> next;

    Node(K key, V value) {
        this.key = key;
        this.value = value;
    }
}