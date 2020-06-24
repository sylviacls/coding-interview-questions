import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * We are given an unsorted array containing numbers taken from the range 1 to ‘n’. 
 * The array can have duplicates, which means some numbers will be missing. 
 * Find all those missing numbers.
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 */
public class MissingNumberFindAll {

    public static int[] findAll(int[] input) {

        int start = 0;
        //[2, 3, 1, 8, 2, 3, 5, 1]
        while(start <input.length) {
            if(input[start] != input[input[start]-1]){
                swap(input,start,input[start]-1);
            } else {
                start++;
            }
        }

        List<Integer> missing = new ArrayList<Integer>();
        for (int i = 0; i < input.length; i++) {
            if(input[i] != i + 1) {
                missing.add(i+1);
            }
        }
        return missing.stream().mapToInt(i->i).toArray();
    }

    private static void swap(int[] input,int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    @Test
    public void validate() {
        int[] missing = MissingNumberFindAll.findAll(new int[] { 2, 3, 1, 8, 2, 3, 5, 1 });
        Assert.assertArrayEquals(new int[]{4, 6, 7}, missing);

    
        missing = MissingNumberFindAll.findAll(new int[] { 2, 4, 1, 2 });
        Assert.assertArrayEquals(new int[]{3}, missing);
    
        missing = MissingNumberFindAll.findAll(new int[] { 2, 3, 2, 1 });
        Assert.assertArrayEquals(new int[]{4}, missing);
      }
}