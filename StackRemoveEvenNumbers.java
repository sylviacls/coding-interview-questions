/**
 * Cracking the PM interview
 * 
 * 16.10 Write a function which removes all the even numbers from a stack. 
 * You should return the original stack, not a new one.
 */
import java.util.*;

public class StackRemoveEvenNumbers {

    /**
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static void removeEven(Stack<Integer> stack) {
        Stack<Integer> temp = new Stack<Integer>();
        while(!stack.isEmpty()) {
            int x = stack.pop();
            if(x % 2 != 0) {
                temp.push(x);
            }
        }
        while(!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> original = new Stack<Integer>();
        original.push(1);
        original.push(2);
        original.push(3);
        original.push(4);

        removeEven(original);
        original.stream().forEach(System.out::println);
    }
}
