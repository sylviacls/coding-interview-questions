import org.junit.Assert;
import org.junit.Test;

/**
 * Cracking the PM interview
 * 
 * 16.13 Given two sorted arrays, write a function to merge them in sorted order into a new array
 */
public class MergeTwoArrays {
    /**
     * Time Complexity: O(n1+ n2)
     * Space Complexity: O(n1 + n2)
     */
    public static int[] merge(int[] input1, int[] input2) {
        int n1 = input1.length;
        int n2 = input2.length;
        int[] merged = new int[n1 + n2];

        int i = 0;
        int j = 0;
        int index = 0;
        while(i < n1 && j < n2) {
            if(input1[i] <= input2[j]) {
                merged[index] = input1[i];
                i++;
            } else {
                merged[index] = input2[j];
                j++;
            }
            index++;
        }
        //handling left-overs
        while(i < n1) {
            merged[index] = input1[i];
            i++;
            index++;
        }
        while (j < n2) {
            merged[index] = input2[j];
            j++;
            index++;
        }
        return merged;
    }

    @Test
    public void Merge_TwoSortedArrays() {
        int[] input1 = new int[]{1,4,6,10,23};
        int[] input2 = new int[]{2,3,8,40};
        int[] expected = new int[]{1,2,3,4,6,8,10,23,40};
        
        Assert.assertArrayEquals(expected, merge(input1, input2));
    }
}
