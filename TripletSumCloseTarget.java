
import org.junit.Assert;
import org.junit.Test;

/**
* Given an array of unsorted numbers and a target number, find a triplet in the array 
* whose sum is as close to the target number as possible, return the sum of the triplet. 
* If there are more than one such triplet, return the sum of the triplet with the smallest sum.
* Time complexity: O(N2). There are only two nested loops traversing the array
* Space Compelxity: O(1).
*/

public class TripletSumCloseTarget {

    // Input: [-2, 0, 1, 2], target=2
    //Output: 1
    public static int tripletSumCloseTarget(int[] input, int target) {

        //Arrays.sort(input);

        int smallestSum = Integer.MAX_VALUE;

        for (int i = 0; i < input.length-1; i++) {

            int start = i + 1;
            int end = input.length - 1;

            while (start < end) {
                int currentSum = input[i] + input[start] + input[end];

                if(Math.abs(target - currentSum) < Math.abs(target- smallestSum)) {
                    smallestSum = currentSum;
                }
                if (currentSum< target) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return smallestSum;
    }

    @Test
    public void validate() {
        
        Assert.assertEquals(1, tripletSumCloseTarget(new int[]{-2, 0, 1, 2}, 2));
        Assert.assertEquals(0, tripletSumCloseTarget(new int[]{-3, -1, 1, 2},1));
        Assert.assertEquals(3, tripletSumCloseTarget(new int[]{1, 0, 1, 1},100));
    }
   
}