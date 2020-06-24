import org.junit.*;

public class AverageSubArrayOfSizeK {

    /**
     * O(N∗K) 
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
    
    public static double[] findAveragesDP( int[] input, int sizeSub){
        double[] result = new double[input.length - sizeSub + 1];
        double windowAverage = 0;
        int indexResult = 0;
        for (int i = 0; i < sizeSub; i++) {
            windowAverage += input[i];
        }

        result[indexResult] = windowAverage/sizeSub;
        indexResult++;

        for (int i = sizeSub; i < input.length; i++) {
            windowAverage = windowAverage + input[i] - input[i-sizeSub];
            result[indexResult] =  windowAverage/sizeSub;
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
        double[] result2 = AverageSubArrayOfSizeK.findAveragesDP(list,5 );
        Assert.assertArrayEquals(expected, result2, 0);
    }
}