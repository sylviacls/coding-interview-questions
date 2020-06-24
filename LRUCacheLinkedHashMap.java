import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implemantation - LinkedHashMap/LinkHashSet
 * 
 * LinkedHashMap can be used to create LRU cache in Java. 
 * In constructor we can pass a boolean accessOrder: 
 *      - false: (default) insertion ordering 
 *      - true:  access ordering (for LRU behaviour) 
 * Iterator of LinkedHashMap returns elements in the order either insertion 
 *      or access from least-recently accessed to most-recently (access-order)
 * Also provides a method called removeEldestEntry(), that its invoked by put and putAll and by default
 *      removeEldestEntry() return false and dont make any removing. 
 *      By overriding it to return true, put and putall will remove the oldest entry. 
 */
public class LRUCacheLinkedHashMap<K, V> {

    private LinkedHashMap<K, V> cacheMap;

    public LRUCacheLinkedHashMap(int capacity) {
           this.cacheMap = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }

    // This method works in O(1)
    public V get(int key) {
        return cacheMap.getOrDefault(key, null);
    }

    // This method works in O(1)
    public void put(K key, V value) {
        cacheMap.put(key, value);
    }

    // least-recently accessed to most-recently (access-order)
    public void print() {
         cacheMap.entrySet().stream()
        .forEach(e-> System.out.println(e.getKey() + " - " + e.getValue()));
    }

    public static void main(String[] args) {

        LRUCacheLinkedHashMap<Integer, String> cache = new LRUCacheLinkedHashMap<Integer,String>(4);
        cache.put(1, "Banana Bread");
        cache.put(2, "Chocolate Cake");
        cache.put(3, "Vanilla Cupcake");
        cache.put(4, "Nutella Brownie");
        System.out.println("Inicial cache..");
        cache.print();
        System.out.println();

        System.out.println("Accessing some items");
        System.out.println("Accessed item:" + cache.get(3));
        System.out.println("Accessed item:" + cache.get(1)); 
        System.out.println();
        
        cache.print();
         
        System.out.println("Adding a new item"); cache.put(5, "Caramel Fudge");
        System.out.println(); 
        cache.print();
        
        System.out.println("Accessing some items");
        System.out.println("Accessed item:" + cache.get(4));
        cache.print();
        
         
    }

}