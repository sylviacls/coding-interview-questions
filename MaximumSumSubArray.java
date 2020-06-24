import org.junit.Assert;
import org.junit.Test;

public class MaximumSumSubArray {

    public static int maximumSumSubArray(int[] input, int sizeK) {
        //compute sum of first window of sizeK
        int maxSum = 0;
        for (int i = 0; i < sizeK; i++) {
            maxSum += input[i];
        }
        //compute sum of remains windows by removing first element of previous 
        // window and adding last element of current window.
        
        int windowSum = maxSum;
        for (int i = sizeK; i < input.length; i++) {
            windowSum = windowSum + input[i] - input[i - sizeK];
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }

    @Test
    public void valide() { 
        int[] list = {1, 4, 2, 10, 2, 3, 1, 0, 20 }; 
        int[] list2 = {2, 1, 5, 1, 3, 2};
        Assert.assertEquals(21, maximumSumSubArray(list, 3)); 
        Assert.assertEquals(20, maximumSumSubArray(list, 2)); 
        Assert.assertEquals(9, maximumSumSubArray(list2, 3)); 
        Assert.assertEquals(11, maximumSumSubArray(list2, 4)); 
    } 
} 
 