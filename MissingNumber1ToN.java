import org.junit.*;

/**
 * Given an array of n-1n−1 integers in the range from 1 to N,
 *  find the one number that is missing from the array.
 */
public class MissingNumber1ToN {

    /**
     * A straight forward approach to solve this problem can be:
     * Find the sum of all integers from 11 to nn; let’s call it sum. 
     * Subtract all the numbers in the input array from sum; this will give us the missing number.
     * @param arr
     * @return
     */
    public static int findMissingNumber(int[] arr) {

        //int sum = (n*(n+1))/2
        int sum = 0;
        for (int i = 1; i <= arr.length+1; i++) {
            sum += i;
        }

        //int sum2 = Arrays.strem(arr).sum();
        for (int i = 0; i < arr.length; i++) {
            sum -= arr[i];
        }

        return sum;
    }
    
    /**
     * While finding the sum of numbers from 1 to n using the above method,
     * we can get integer overflow when nn is large.
     * 
     * An alternative solution is to use XOR
     * XOR:  it returns 0 if both the bits in comparison are the same.
     * This means that if we XOR all the numbers in the input array with all numbers from 
     * the range 1 to N then each number in the input is going to get zeroed out 
     * except the missing number
     * 
     * XOR all the numbers from 1 to nn, let’s call it xor1.
     * XOR all the numbers in the input array, let’s call it xor2. 
     * The missing number can be found by xor1 XOR xor2.
     * 
     * Time Complexity: O(n)
     * Space complexity: O(1)
     * @param arr
     * @return
     */
    public static int findMissingNumberXOR(int[] arr) { 
        int xor1 = 0;
        for (int i = 1; i <= arr.length+1; i++) {
            xor1 = xor1 ^ i;
        }

        int xor2 = 0;
        for (int i = 0; i < arr.length; i++) {
            xor2 = xor2 ^ arr[i];
        }
        return xor1^xor2;

        /*   Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (((i+1)^arr[i]) != 0) {
                return i+1;
            }
        } */
    }

    @Test
    public void validate() {
        int[] arr = new int[] { 1, 5, 2, 6, 4 };
        Assert.assertEquals(3, findMissingNumber(arr));
        Assert.assertEquals(3, findMissingNumberXOR(arr));

        int[] arr2 = new int[] {1,4,5,2,3,7};
        Assert.assertEquals(6, findMissingNumber(arr2));
        Assert.assertEquals(6, findMissingNumberXOR(arr2));
      }
}