import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * Leetcode: 268. Missing Number
 * 
 * We are given an array containing ‘n’ distinct numbers taken from the range 0 to ‘n’.
 * The array has only ‘n’ numbers out of the total ‘n+1’ numbers, 
 * find the missing number(s)
 * 
 * Example 1:
 * Input: [3,0,1]
 * Output: 2
 * 
 * Example 2:
 * Input: [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * 
 * Your algorithm should run in linear runtime complexity. Could you implement it using 
 * only constant extra space complexity?
 */
public class MissingNumberFindOne {

    
    /**
     * Time Complexity: O(n)
     * Space complexity: O(1)
     */
    public static int findOne(int[] input) {
        int start = 0;
        //ordering the numbers
        while(start <input.length) {
            if(input[start] < input.length //to not allow the number "n"
                 && input[start]!= input[input[start]]) {
                swap(input, start, input[start]);
            } else{
                start++;
            }
        }

        for (int i = 0; i < input.length; i++) {
            if(input[i] != i) return i;
        }
        return input.length;
    }
    

    private static void swap(int[] input,int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    
    /**
     * A straight forward approach to solve this problem can be:
     * Find the sum of all integers from 1 to n; let’s call it sum. 
     * Subtract all the numbers in the input array from sum; this will give us the missing number.
     */
    public static int findOneII(int[] arr) {

        int n = arr.length;
        int sum = (n * (n + 1))/2; 
      /*  int sum = 0;
        for (int i = 0; i <= arr.length; i++) {
            sum += i;
        }  */

        int sum2 = Arrays.stream(arr).sum();
       /* for (int i = 0; i < arr.length; i++) {
            sum -= arr[i];
        }*/

        return sum-sum2;
    }

    @Test
    public void validate() {
        Assert.assertEquals(2,MissingNumberFindOne.findOne(new int[] { 4, 0, 3, 1 }));
        Assert.assertEquals(2,MissingNumberFindOne.findOneII(new int[] { 4, 0, 3, 1 }));
        Assert.assertEquals(7,MissingNumberFindOne.findOne(new int[] { 8, 3, 5, 2, 4, 6, 0, 1 }));
        Assert.assertEquals(7,MissingNumberFindOne.findOneII(new int[] { 8, 3, 5, 2, 4, 6, 0, 1 }));  
    }

    
}