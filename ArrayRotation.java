import org.junit.Assert;
import org.junit.Test;

/**
 * Write a program to accomplish array rotation by left and right.
 */
public class ArrayRotation {

    /**
     * Approach: Using an auxiliar reverse function
     * This approach is based on the fact that when we rotate the array k times, 
     * k elements from the back end of the array come to the front and the rest of 
     * the elements from the front shift backwards.
     * 
     * In this approach, we firstly reverse all the elements of the array. 
     * Then, reversing the first k elements followed by reversing the rest n−k elements gives
     * us the required result.
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     * @param input
     * @param factor
     */
    public static int[] rotate(int[] input, int factor) {
        // to avoid unnecessary work when factor > input.lenght and array index out of bounds excep.
        factor = factor% input.length; 
        
        reverse(input, 0, input.length-1);
        reverse(input, 0, factor-1);
        reverse(input, factor, input.length-1);
        return input;
    }

    private static void reverse(int[] input, int start, int end) {
        while (start < end) {
            int temp = input[start];
            input[start] = input[end];
            input[end] = temp;
            start++;
            end--;
        }
    }
    /**
     * Approach: Creating a new array and using relative position: factor % input.lenght
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     * @param input
     * @param factor
     * @return
     */
    public static int[] rotateRight(int[] input, int factor) {
        int n = input.length;
        factor = factor % n; // to avoid unnecessary work when factor > input.lenght
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[(i+factor)%n] = input[i];
        }

        return result;
    }

    /**
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     * @param input
     * @param factor
     * @return
     */
    public static int[] rotateLeft(int[] input, int factor){
        factor = factor % input.length; // to avoid unnecessary work when factor > input.lenght
        int[] rotated = new int[input.length];
        int rotatedIndex = factor;
        int i = 0;

        //getting the elements from "factor" index until the end of the input
        //and putting them in the beginning of rotated result
        while(rotatedIndex < input.length) {
            rotated[i] = input[rotatedIndex];
            i++;
            rotatedIndex++;
        }

        //getting the first k (factor) elements of the original input
        //and putting them in the end of rotated result
        rotatedIndex = 0;
        while(rotatedIndex < factor) {
            rotated[i] = input[rotatedIndex];
            i++;
            rotatedIndex++;
        }
        return rotated;
    }

    
    
    @Test
    public void validate() {
        Assert.assertArrayEquals(new int[]{3,4,5,1,2}, rotateLeft(new int[]{1, 2, 3, 4, 5},12));
        Assert.assertArrayEquals(new int[]{4,5,1,2,3}, rotateLeft(new int[]{1, 2, 3, 4, 5},3));
        Assert.assertArrayEquals(new int[]{5,1,2,3,4}, rotateLeft(new int[]{1, 2, 3, 4, 5},4));

        Assert.assertArrayEquals(new int[]{4,5,1,2,3}, rotateRight(new int[]{1, 2, 3, 4, 5},12));
        Assert.assertArrayEquals(new int[]{3,4,5,1,2}, rotateRight(new int[]{1, 2, 3, 4, 5},3));
        Assert.assertArrayEquals(new int[]{2,3,4,5,1}, rotateRight(new int[]{1, 2, 3, 4, 5},4));

        
        Assert.assertArrayEquals(new int[]{4,5,1,2,3}, rotate(new int[]{1, 2, 3, 4, 5},12));
        Assert.assertArrayEquals(new int[]{3,4,5,1,2}, rotate(new int[]{1, 2, 3, 4, 5},3));
        Assert.assertArrayEquals(new int[]{2,3,4,5,1}, rotate(new int[]{1, 2, 3, 4, 5},4));
    }
}