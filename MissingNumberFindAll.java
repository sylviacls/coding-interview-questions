import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Leetcode: 448. Find All Numbers Disappeared in an Array
 *  We are given an unsorted array containing numbers taken from the range 1 to ‘n’. 
 * The array can have duplicates, which means some numbers will be missing. 
 * Find all those missing numbers.
 * 
 * Example:
 * Input:
 * [4,3,2,7,8,2,3,1]
 * 
 * Output:
 * [5,6]
 * 
 */
public class MissingNumberFindAll {

    /**
     * Approach: Extra Array to order
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     * 
     * Faster and less memory usage than findAll()
     */
    public Integer[] findAll(int[] nums) {
        int[] ordered = new int[nums.length];
        List<Integer> result = new ArrayList<Integer>();

        for(int i = 0; i < nums.length; i++)  {
            ordered[nums[i]-1] = nums[i];
        }
        for(int i = 0; i < ordered.length; i++)  {
            if(ordered[i] == 0) result.add(i+1); 
        }        
     return result.toArray(new Integer[result.size()]);   
    }
    /**
     * Approach: Cyclic Sort
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public static Integer[] findAllCyclic(int[] input) {

        int start = 0;
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
        return missing.toArray(new Integer[missing.size()]);   
    }

    private static void swap(int[] input,int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }



    @Test
    public void validate() {
        int[] input =  new int[]{ 2, 3, 1, 8, 2, 3, 5, 1 };
        Assert.assertArrayEquals(new Integer[]{4, 6, 7}, findAll(input));
        Assert.assertArrayEquals(new Integer[]{4, 6, 7}, findAllCyclic(input));
    
        input =  new int[] { 2, 4, 1, 2 };
        Assert.assertArrayEquals(new Integer[]{3}, findAll(input));
        Assert.assertArrayEquals(new Integer[]{3}, findAllCyclic(input));

        input = new int[] { 2, 3, 2, 1 };
        Assert.assertArrayEquals(new Integer[]{4}, findAll(input));
        Assert.assertArrayEquals(new Integer[]{4}, findAllCyclic(input));
      }
}