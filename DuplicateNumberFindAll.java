import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * We are given an unsorted array containing ‘n’ numbers taken from the range 1 to ‘n’.
 *  The array has some duplicates, find all the duplicate numbers without using any 
 * extra space.
 * 
 * Time Complexity: O(n)
 * Space complexity: O(1) ignoring the array used to store the duplicates
 */
public class DuplicateNumberFindAll {

    /**
     * Input: [5, 4, 7, 2, 3, 5, 3]
     * Output: [3, 5]
     */
    public static int[] findAllDuplicates(int[] input){

        int i = 0;
        while(i < input.length) {
            if(input[i] != input[input[i]-1]){
                swap(input, i, input[i]-1);
            } else {
                i++;
            }
        }
        List<Integer> duplicates = new ArrayList<Integer>();
        for (int j = 0; j < input.length; j++) {
            if(input[j] != j+1) {
                duplicates.add(input[j]);
            }
        }

        return duplicates.stream().mapToInt(x->x).toArray();
    }

    private static void swap(int[] input,int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
    
    @Test
    public void validate() {
        int[] duplicates = DuplicateNumberFindAll.findAllDuplicates(new int[] { 3, 4, 4, 5, 5 });
        Assert.assertArrayEquals(new int[]{5,4}, duplicates);
    
        duplicates = DuplicateNumberFindAll.findAllDuplicates(new int[] { 5, 4, 7, 2, 3, 5, 3 });
        Assert.assertArrayEquals(new int[]{3,5}, duplicates);
    }
}