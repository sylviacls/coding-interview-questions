import org.junit.Assert;
import org.junit.Test;

/**
 * Given a sorted array, create a new array containing squares of all the number
 * of the input array in the sorted order. Time complexity O(n) Space complexity
 * O(n)
 */
public class SquaringSortedArray {

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
    @Test
    public void validate() {
        int[] result = squaringSortedArray(new int[]{-2, -1, 0, 2, 3});
        int[] expected = new int[]{0, 1, 4, 4, 9};

        int[] result2 = squaringSortedArray(new int[]{-2, -1, 0, 2, 3});
        int[] expected2 = new int[]{0, 1, 4, 4, 9};

        Assert.assertArrayEquals(expected, result);
        Assert.assertArrayEquals(expected2, result2);

    }
}