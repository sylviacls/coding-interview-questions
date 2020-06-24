import org.junit.Assert;
import org.junit.Test;

/**
 * Find largest and smallest number from integer array
*/
public class MinimumMaximumArray {

    public static int[] findMinMax(int[] input) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < input.length; i++) {
            if(input[i] > max ) {
                max = input[i];
            } else if (input[i] < min) {
                min = input[i];
            }
        }
        return new int[]{min,max};
    }

    @Test
    public void validade() {
        Assert.assertArrayEquals(new int[]{-87, 2147483647}, findMinMax(new int[]{-20, 34, 21, -87, 92, 2147483647}));
    }
}