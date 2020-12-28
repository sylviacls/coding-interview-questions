/**
 * Cracking the PM interview
 * 16.9 Write a function which takes a stack as input and returns a new stack which 
 * has the elements reversed.
 * 
 * The original stack must maintain its value.
 */
import java.util.*;

public class StackReverseElements { 

    /**
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static Stack<Integer> reverse(Stack<Integer> stack)  {
        Stack<Integer> reversed = new Stack<Integer>();
        Stack<Integer> temp = new Stack<Integer>();
        while(!stack.isEmpty()) {
            int x = stack.pop();
            reversed.push(x);
            temp.push(x);
        }
        while(!temp.isEmpty()) {
            stack.push(temp.pop());
        }

        return reversed;
    }

    public static void main(String[] args) {
        Stack<Integer> original = new Stack<Integer>();
        original.push(1);
        original.push(2);
        original.push(3);

        Stack<Integer> reversed = reverse(original);
        reversed.stream().forEach(System.out::println);
        original.stream().forEach(System.out::println);
    }
}