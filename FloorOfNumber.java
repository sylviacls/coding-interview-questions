import org.junit.*;

/**
 * Given an array of numbers sorted in ascending order, find the floor of a given number ‘key’.
 *  The floor of the ‘key’ will be the biggest element in the given array smaller than or 
 * equal to the ‘key’
 * 
 * Write a function to return the index of the floor of the ‘key’. 
 * If there isn’t a floor, return -1.
 * 
 * Similiar to: CeilingOfNumber.java
 * 
 * Example 1: 
 * Input: [1, 3, 8, 10, 15]
 * key = 12 
 * Output: 3
 * Explanation: The biggest number smaller than or equal to '12' is '10' having index '3'.
 * 
 * Time Complexity: O(logN)
 * Space Complexity: O(1)
 */
public class FloorOfNumber {

    public static int searchFloorOfANumber(int[] arr, int key) {
        int start = 0;
        int end = arr.length-1;

        if(key < arr[start]) return -1;

        while(start <= end) {
            //insted of (start+end)/2 to avoid potencial integer overflow
            int mid = start + (end-start)/2;
            if(key == arr[mid]) {
                return mid;
            } else if(key < arr[mid]) {
                end = mid-1;
            } else{
                start = mid+1;
            }
        }
        //the key hasn't been found, and start = end+1
        // and the biggest element smaller than key will be end
        return end;
    }

    @Test
    public void validate() {
        Assert.assertEquals(1, searchFloorOfANumber(new int[] { 4, 6, 10 }, 6));
        Assert.assertEquals(3,searchFloorOfANumber(new int[] { 1, 3, 8, 10, 15 }, 12));
        Assert.assertEquals(2,searchFloorOfANumber(new int[] { 4, 6, 10 }, 17));
        Assert.assertEquals(-1,searchFloorOfANumber(new int[] { 4, 6, 10 }, -1));
      }
    
}