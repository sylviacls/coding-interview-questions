import org.junit.Assert;
import org.junit.Test;

/**
 * We are given an array containing ‘n’ objects. Each object, when created, 
 * was assigned a unique number from 1 to ‘n’ based on their creation sequence.
 * Numbers are unique: from 1 to n.
 * 
 * Write a function to sort the objects in-place on their creation sequence number in O(n)
 * and without any extra space.
 * 
 */

public class CyclicSort {

    /**
     * We can use the indexes as ordering indexes for each value:
     *  Ex: 1 -> 0 index, 1 -> 1 index, 2 -> 3 index
     * 
     * Time Complexity: O(n)
     * Space complexity: O(1)
     */
    public static void cyclicSort(int[] input) {
        
        // { 3, 1, 5, 4, 2 };
        int start = 0;
        while (start <input.length) {
            if(input[start] != input[input[start]-1]) {
                swap(input, start, input[start]-1);
            } else {
                start++;
            }
        }

    }

    private static void swap(int[] input,int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    @Test
    public void validate() {
        int[] arr = new int[] { 3, 1, 5, 4, 2 };
        CyclicSort.cyclicSort(arr);
        Assert.assertArrayEquals(new int[]{1,2,3,4,5},arr);
    
        arr = new int[] { 2, 6, 4, 3, 1, 5 };
        CyclicSort.cyclicSort(arr);
        Assert.assertArrayEquals(new int[]{1,2,3,4,5,6},arr);
    
        arr = new int[] { 1, 5, 6, 4, 3, 2 };
        CyclicSort.cyclicSort(arr);
        Assert.assertArrayEquals(new int[]{1,2,3,4,5,6},arr);
    }
    
    
}
