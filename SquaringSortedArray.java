import org.junit.Assert;
import org.junit.Test;

/**
 * Leetcode: 977. Squares of a Sorted Array
 * 
 * Given an array of integers A sorted in non-decreasing order, return an array of the squares
 * of each number, also in sorted non-decreasing order.
 * 
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * 
 * Example 1:
 * Input: [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * 
 * Example 2:
 * Input: [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 * 
 */
public class SquaringSortedArray {

    /**
     * Approach: Two Pointers
     * 
     * One pointer will move forward to iterate the non-negative numbers and the other pointer 
     * will move backward to iterate the negative numbers. At any step, whichever number gives
     * us a bigger square will be added to the output array. 
     * 
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int[] squaringSortedArray(int[] input) {
        int[] result = new int[input.length];
        int resultIndex = 0;

        int positiveIndex = 0;
        while(positiveIndex < input.length && input[positiveIndex] < 0) {
            positiveIndex++;
        }

        int negativeIndex = positiveIndex -1;

        while(negativeIndex >= 0 && positiveIndex <input.length-1) {

            int negativeSquare = input[negativeIndex]*input[negativeIndex];
            int positiveSquare = input[positiveIndex]*input[positiveIndex];

            if( negativeSquare < positiveSquare ) {
                result[resultIndex] = negativeSquare;
                negativeIndex--;
            } else {
                result[resultIndex] = positiveSquare;
                positiveIndex++;
            }
            resultIndex++;
        }

        //dealing with leftovers
        while(negativeIndex >=0) {
            result[resultIndex] = input[negativeIndex]*input[negativeIndex];
            negativeIndex--;
            resultIndex++;
        }
        while(positiveIndex < input.length) {
            result[resultIndex] = input[positiveIndex]*input[positiveIndex];
            positiveIndex++;
            resultIndex++;
        }
        return result;
    }

    /**
     * Approach: Two Pointers
     * 
     * Since the numbers at both the ends can give us the largest square, an alternate approach
     * could be to use two pointers starting at both the ends of the input array. At any step, 
     * whichever pointer gives us the bigger square we add it to the result array and move to 
     * the next/previous number according to the pointer.
     */
    public static int[] squaringSortedArrayII(int[] input) {
        int start = 0;
        int end = input.length - 1;
        int[] result = new int[input.length];
        int resultIndex = result.length-1;

        while (start <= end) {
            int negativeSquared = input[start]*input[start];
            int positiveSquared = input[end]*input[end];

            if(negativeSquared >= positiveSquared) {
                result[resultIndex] = negativeSquared;
                start++;
            } else {
                result[resultIndex] = positiveSquared;
                end--;
            }
            resultIndex--;
        }
        return result;
    }


    @Test
    public void validate() {
        int[] input = new int[]{-2, -1, 0, 2, 3};
        int[] expected = new int[]{0, 1, 4, 4, 9};
        Assert.assertArrayEquals(expected, squaringSortedArray(input));
        Assert.assertArrayEquals(expected, squaringSortedArrayII(input));

        int[] input2 = new int[]{-2, -1, 0, 2, 3};
        int[] expected2 = new int[]{0, 1, 4, 4, 9};        
        Assert.assertArrayEquals(expected2, squaringSortedArray(input2));
        Assert.assertArrayEquals(expected2, squaringSortedArrayII(input2));

        int[] input3 = new int[]{-4,-1,0,3,10};
        int[] expected3 = new int[]{0, 1, 9, 16, 100};
        Assert.assertArrayEquals(expected3, squaringSortedArray(input3));
        Assert.assertArrayEquals(expected3, squaringSortedArrayII(input3));

        int[] input4 = new int[] {-7,-3,2,3,11};
        int[] expected4 = new int[] {4,9,9,49,121};
        Assert.assertArrayEquals(expected4, squaringSortedArray(input4));
        Assert.assertArrayEquals(expected4, squaringSortedArrayII(input4));

    }
}