/**
 * Cracking the PM interview
 * 16.12 Write a function to remove the 13th element from a queue
 * (but keep all the other elements in place and in the same order).
 */
import java.util.*;
import java.util.stream.IntStream;

public class QueueRemoveNth {
    /**
     * if we continuously remove elements from the front and add them to the
     * back, we’ll wind up with the exact same list.
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static void removeElement(Queue<Integer> queue, int position) {
        int size = queue.size();
        for (int i = 1; i <= size; i++) {
            int x = queue.remove();
            if(i != position) {
                queue.add(x);
            }
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<Integer>();
        IntStream.range(1, 6).forEach(x -> queue.add(x));
        queue.forEach(System.out::println);
        removeElement(queue, 3);
        queue.forEach(System.out::println);

    }
}