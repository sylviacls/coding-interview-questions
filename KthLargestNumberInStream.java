import java.util.*;

import org.junit.*;

/**
 * Design a class to efficiently find the Kth largest element in a stream of numbers.
 * 
 * The class should have the following two things:
 * 
 * The constructor of the class should accept an integer array containing initial numbers
 * from the stream and an integer ‘K’.
 * 
 * The class should expose a function add(int num) which will store the given number and return 
 * the Kth largest number.
 */
public class KthLargestNumberInStream {

  private PriorityQueue<Integer> minHeap;
  private int k;

  /**
   * Time Complexity: O(NlogK)
   * Space Complexity: O(K)
   * @param nums
   * @param k
   */
  public KthLargestNumberInStream(int[] nums, int k) {
    minHeap = new PriorityQueue<Integer>();
    this.k = k;
    // add the numbers in the min heap
    for (int num : nums) {
      add(num);
    }
  }

  public int add(int num) {
    minHeap.add(num);
    // if heap has more than 'k' numbers, remove one number
    if (minHeap.size() > k) {
      minHeap.poll();
    }
    // return the 'Kth largest number
    return minHeap.peek();
  }

  /**
   * The simplest approach is to sort the entire input array (N LogN) and then access the element 
   * by it's index (which is O(1)) operation:
   * @param nums
   * @param k
   * @return
   */
  public int findKthLargest(int[] nums, int k) {
    final int N = nums.length;
    Arrays.sort(nums);
    return nums[N - k];
  }

  public static void main(String[] args) {
    int[] input = new int[] { 3, 1, 5, 12, 2, 11 };
    KthLargestNumberInStream kthLargestNumber = new KthLargestNumberInStream(input, 4);
    System.out.println("4th largest number is: " + kthLargestNumber.add(6));
    System.out.println("4th largest number is: " + kthLargestNumber.add(13));
    System.out.println("4th largest number is: " + kthLargestNumber.add(4));
  }
}