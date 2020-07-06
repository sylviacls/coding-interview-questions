import org.junit.*;

/**
 * Given a sorted array of numbers, find if a given number ‘key’ is present in the array.
 * Though we know that the array is sorted, we don’t know if it’s sorted in ascending
 * or descending order. You should assume that the array can have duplicates
 * 
 * Write a function to return the index of the ‘key’ if it is present in the array,
 * otherwise return -1.
 * 
 * Time Complexity O(logN)
 * Space Complexity O(1)
 */
public class BinarySearchOrderAgnostic {
    public static int search(int[] arr, int key) {
        int begin = 0;
        int last = arr.length-1;
        boolean ascendig = arr[begin] < arr[last];
        while(begin <=last) {
            int mid = begin + (last-begin)/2;
            //instead of (begin + last)/2 due to potencial integer overflow
            if(key == arr[mid]) {
                return mid; 
            }
            if(ascendig) {//ascending order
                if(key < arr[mid]) {
                    last = mid - 1;
                } else{
                    begin = mid + 1;
                }
            } else {//descending order
                if(key < arr[mid]) {
                    begin = mid + 1;
                } else {
                    last = mid -1;
                }
            }
        }
        return -1; //element not found
      }
    
      @Test
      public void validate() {
        Assert.assertEquals(2, search(new int[] { 4, 6, 10 }, 10));
        Assert.assertEquals(4, search(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 5));
        Assert.assertEquals(0, search(new int[] { 10, 6, 4 }, 10));
        Assert.assertEquals(2, search(new int[] { 10, 6, 4 }, 4));
      }
}