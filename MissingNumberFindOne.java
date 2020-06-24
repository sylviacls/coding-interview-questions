import org.junit.Assert;
import org.junit.Test;

/**
 * We are given an array containing ‘n’ distinct numbers taken from the range 0 to ‘n’.
 *  The array has only ‘n’ numbers out of the total ‘n+1’ numbers, 
 * find the missing number(s)
 * 
 *  Time Complexity: O(n)
 * Space complexity: O(1)
 */
public class MissingNumberFindOne {

    
    public static int findOne(int[] input) {
        int start = 0;
        //ordering the numbers
        while(start <input.length) {
            if(input[start] < input.length //to not allow the number "n"
                 && input[start]!= input[input[start]]) {
                swap(input, start, input[start]);
            } else{
                start++;
            }
        }

        for (int i = 0; i < input.length; i++) {
            if(input[i] != i) return i;
        }
        return -1;
    }
    

    private static void swap(int[] input,int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    @Test
    public void validate() {
        Assert.assertEquals(2,MissingNumberFindOne.findOne(new int[] { 4, 0, 3, 1 }));
        Assert.assertEquals(7,MissingNumberFindOne.findOne(new int[] { 8, 3, 5, 2, 4, 6, 0, 1 }));
      }

    
}