import org.junit.*;
import java.util.*;


/**
 * We are given an unsorted array containing ‘n’ numbers taken from the range 1 to ‘n’. 
 * The array originally contained all the numbers from 1 to ‘n’, but due to a data error,
 * one of the numbers got duplicated which also resulted in one number going missing.
 * 
 * Find both these numbers.
 *
 * Example:
 * Input: [3, 1, 2, 5, 2]
 * Output: [2, 4]
 * Explanation: '2' is duplicated and '4' is missing.
 */
public class CorruptPair {
    
    /**
     * Approach: Cyclic Sort
     * 
     *  Once we are done with the cyclic sort, we will iterate through the array to find 
     *  the number that is not at the correct index. Since only one number got corrupted,
     *  the number at the wrong index is the duplicated number and the index itself
     *  represents the missing number.
     * 
     *  Time Complexity: O(n)
     *  Space complexity: O(1) 
     * 
     */
    public static int[] findCorruptPair(int[] input){

        if(input == null || input.length <1) return new int[]{-1,-1};

        int i = 0;
        while(i < input.length) {
            if(input[i] != input[input[i]-1]) {
                swap(input, i, input[i]-1);
            } else {
                i++;
            }
        }

        for (int j = 0; j < input.length; j++) {
            if(input[j] != j+1) {
                return new int[]{input[j], j+1};
            }
        }

        return new int[]{-1,-1};
    }

    private static void swap(int[] input,int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    /**
     * Approach: Using a Map
     * 
     * This method involves creating a HashSet. In this, the elements are mapped to their 
     *  natural index. In this process, if an element is mapped twice, then it is the 
     * repeating element. And if an element’s mapping is not there, then it 
     * is the missing element.
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static int[] findCorruptPairII(int[] nums){
        Set<Integer> map = new HashSet<Integer>();
        int repeating = -1;
        int missing = -1;
        for (int num : nums) {
            if(map.contains(num)) {
                repeating = num;
            } else {
                map.add(num);
            }
        }

        for (int i = 1; i <= nums.length; i++) {
            if(!map.contains(i)) missing = i;
        }
        return new int[]{repeating, missing};
    }

    @Test
    public void validate(){
        int[] expected  = new int[]{2,4};  
        Assert.assertArrayEquals(expected, findCorruptPair(new int[] { 3, 1, 2, 5, 2 }));
        Assert.assertArrayEquals(expected, findCorruptPairII(new int[] { 3, 1, 2, 5, 2 }));

        int[] expected2 = new int[]{3,5};
        Assert.assertArrayEquals(expected2, findCorruptPair(new int[] { 3, 1, 2, 3, 6, 4 }));
        Assert.assertArrayEquals(expected2, findCorruptPairII(new int[] { 3, 1, 2, 3, 6, 4 }));
    }
}