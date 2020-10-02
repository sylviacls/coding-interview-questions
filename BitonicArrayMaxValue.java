import org.junit.*;

/**
 * Find the maximum value in a given Bitonic array. An array is considered bitonic
 * if it is monotonically increasing and then monotonically decreasing. 
 * 
 * Input: [1, 3, 8, 12, 4, 2]
 * Output: 12
 * 
 * Input: [1, 3, 8, 12]
 * Output: 12
 * 
 * Input: [10, 9, 8]
 * Output: 10
 * 
 * Time Complexity: Log(logN)
 * Space Complexity: O(1)
 * 
 */
public class BitonicArrayMaxValue {
  
  /**
   * It uses binary search.
   * Since no two consecutive numbers are same (as the array is monotonically increasing or 
   * decreasing), whenever we calculate the middle, we can compare the numbers pointed out
   * by the index middle and middle+1 to find if we are in the ascending or the descending part.
   * @param arr input
   * @return the max number
   */
  public static int findMax(int[] arr) {
    int start = 0;
    int end = arr.length-1;

    
    //We can break when start == end. both start and end will be pointing at the maximum number 
    while (start < end) {
      int mid = start + (end-start)/2;
      // we are in the second (descending) part of the array. Therefore, our required
      // number could either be pointed out by middle or will be before middle
      if (arr[mid] > arr[mid + 1]) {
        end = mid;
      } else {
        // we are in the first (ascending) part of the array. Therefore, the required
        // number
        // will be after middle.
        start = mid + 1;
      }
    }
    return arr[start];
  }

  @Test
  public void validate() {
    Assert.assertEquals(12, findMax(new int[] { 1, 3, 8, 12, 4, 2 }));
    Assert.assertEquals(8, findMax(new int[] { 3, 8, 3, 1 }));
    Assert.assertEquals(12, findMax(new int[] { 1, 3, 8, 12 }));
    Assert.assertEquals(10, findMax(new int[] { 10, 9, 8 }));
  }
}