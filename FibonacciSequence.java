import java.util.HashMap;

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
     * A bottom-up iterative approach:
     *  - Store F0 and F1 (both simply 1) in local variables. 
     *  - At each point, these two variables will represent the last two Fibonacci numbers,
     *     as we’ll need to refer to them to calculate the next Fibonacci number.
     * Time complexity O(n)
     * Space complexity O(1)
     * @param n the next n-th number
     * @return 
     */
    public static int fibonnaciBU(int n) {

        //base cases
        if (n==0) return 0;
        if (n==1) return 1;

        //stores the last to numbers
        int a = 0;
        int b = 1;
        int sum = 0;

        //we only need to start with 2, cause we already have the first 2 cases
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            //Each calculation only depends on Fi−1 and Fi−2, so after you’re done,
            // you can throw away Fi−2, as you’ll never use it again. To throw away the value,
            // update the local variables to store only Fi−1 and Fi-2 for the next iteration
            a = b;
            b = sum;
        }
        return sum;
    }
    
    /**
     * Driver-method: A Top-down iterative approach:
     */
    public static int fibonnaciTD(int n) {
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        return fibonnaciTD(n, hm);
    }
        
    /**
     * A Top-down iterative approach:
     *  - Store F0 and F1 (both simply 1) in local variables. 
     *  - At each point, these two variables will represent the last two Fibonacci numbers,
     *     as we’ll need to refer to them to calculate the next Fibonacci number.
     * Time complexity O(n)
     * Space complexity O(n)
     * @param n the next n-th number
     */
    private static int fibonnaciTD(int n, HashMap<Integer, Integer> hm) {
        //base cases  
         if(hm.containsKey(n)) return hm.get(n);
         if (n==0) return 0;
         if (n==1) return 1;

         int result = fibonnaciTD(n-1, hm) + fibonnaciTD(n-2, hm);
         hm.put(n, result);

         return result;
    }

    @Test
    public void validate(){

        Assert.assertEquals(13, fibonnaciBU(7));
        Assert.assertEquals(2, fibonnaciBU(3));
        Assert.assertEquals(34, fibonnaciBU(9));
        Assert.assertEquals(55, fibonnaciBU(10));

        Assert.assertEquals(13, fibonnaciTD(7));
        Assert.assertEquals(2, fibonnaciTD(3));
        Assert.assertEquals(34, fibonnaciTD(9));
        Assert.assertEquals(55, fibonnaciTD(10));
    }
}