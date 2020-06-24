import org.junit.Assert;
import org.junit.Test;

/**
 * Write a program to accomplish array rotation by left and right.
 */
public class ArrayRotation {

    public static int[] rotateLeft(int[] input, int factor){
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
        Assert.assertArrayEquals(new int[]{3,4,5,1,2}, rotateLeft(new int[]{1, 2, 3, 4, 5},2));
        Assert.assertArrayEquals(new int[]{4,5,1,2,3}, rotateLeft(new int[]{1, 2, 3, 4, 5},3));
        Assert.assertArrayEquals(new int[]{5,1,2,3,4}, rotateLeft(new int[]{1, 2, 3, 4, 5},4));
    }
}