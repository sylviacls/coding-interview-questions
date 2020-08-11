import org.junit.*;

/**
 * Given an array, find the average of all contiguous subarrays of size ‘K’ in it.
 * 
 * Example: 
 * Input: [1, 3, 2, 6, -1, 4, 1, 8, 2], K=5
 * Output: [2.2, 2.8, 2.4, 3.6, 2.8]
 */
public class AverageSubArrayOfSizeK {

    /**
     * 
     * Approach: Brute-Force
     * Calculate the sum of every 5-element contiguous subarray of the given array and divide
     * the sum by ‘5’ to find the average.
     * 
     * Time Complexity: O(N∗K) 
     * Space Complexity: O(N)
     * 
     * @param input
     * @param sizeSub
     * @return
     */
    public static double[] findAveragesNaive( int[] input, int sizeSub) {
        double[] result = new double[input.length - sizeSub + 1];
        for (int i = 0; i <= input.length - sizeSub; i++) {
            double sum = 0;
            for (int j = i; j < i + sizeSub; j++) {
                sum += input[j];
            }
            result[i] = sum/sizeSub;
        }
        return result;
    }

    /**
     * Approach: Sliding Window
     * 
     * Time Complexity: O(N) 
     * Space Complexity: O(N)
     * 
     * @param input
     * @param k
     * @return
     */
    public static double[] findAverageSlidingWindow( int[] input, int k){
        double[] result = new double[input.length - k + 1];

        //calculating the average of the first window-size-k
        double windowAverage = 0;
        int indexResult = 0;
        for (int i = 0; i < k; i++) {
            windowAverage += input[i];
        }

        //storing the result
        result[indexResult] = windowAverage/k;
        indexResult++;

        //moving the sliding-window: removing the first element from previous window and
        //adding the next element
        for (int i = k; i < input.length; i++) {
            windowAverage = windowAverage + input[i] - input[i-k];
            result[indexResult] =  windowAverage/k;
            indexResult++; 
        }
        return result;
      }

    @Test  
    public void validate() {
        int[] list = { 1, 3, 2, 6, -1, 4, 1, 8, 2 };
        double[] result = AverageSubArrayOfSizeK.findAveragesNaive(list,5 );
        double[] expected = new double[] {2.2, 2.8, 2.4, 3.6, 2.8};

        Assert.assertArrayEquals(expected, result, 0);
        double[] result2 = AverageSubArrayOfSizeK.findAverageSlidingWindow(list,5 );
        Assert.assertArrayEquals(expected, result2, 0);
    }
}