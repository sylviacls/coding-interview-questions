import org.junit.Assert;
import org.junit.Test;

/**
 * The fibonacci sequence is a sequence of numbers starting from 0 and 1, 
 * where each number is the sum of the two preceding numbers. 
 * For example, the next (3rd) number in the sequence will be 1, because 0 + 1 = 1, 
 * and so on: 0, 1, 1, 2, 3, 5, 8, 13, ...
 * 
 * for each new fibonacci number we only need the two previous fibonacci numbers.
 */
public class FibonacciSequence {

    /**
     * A bottom-up iterative approach
     * Time complexity O(n)
     * Space complexity O(1)
     * @param n the next n-th number
     * @return 
     */
    public static int fibonacci(int n) {

        //base cases
        if (n==0) return 0;
        if (n==1) return 1;

        //stores the last to numbers
        int a = 0;
        int b = 1;

        //we only need to start with 2, cause we already have the first 2 cases
        for (int i = 2; i < n-1; i++) {
            int fib = a + b;
            a = b;
            b = fib;
        }
        return a+b;
    }
    
    @Test
    public void validate(){
        Assert.assertEquals(13, fibonacci(8));
        Assert.assertEquals(89, fibonacci(12));
        Assert.assertEquals(1, fibonacci(3));
        Assert.assertEquals(4181, fibonacci(20));
    }
}