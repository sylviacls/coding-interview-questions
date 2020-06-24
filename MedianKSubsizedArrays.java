import java.util.Comparator;
import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given an array of numbers and a number ‘k’, find the median of all the ‘k’
 * sized sub-arrays (or windows) of the array.
 * 
 * Time complexity: O(N*K) where ‘N’ is the total number of elements in the input array and ‘K’ 
 *                  is the size of the sliding window
 * Space complexity: O(K): because, at any time, we will be storing all the numbers within
 *                   the sliding window.
 */

public class MedianKSubsizedArrays {
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    public MedianKSubsizedArrays() {
        this.minHeap = new PriorityQueue<Integer>();
        this.maxHeap = new PriorityQueue<Integer>(Comparator.reverseOrder());
    }

    public void inserNum(int num) {
        if (maxHeap.isEmpty() || num > maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }
        rebalanceHeap();
    }

    public void remove(int num) {
        if (maxHeap.contains(num)) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        rebalanceHeap();
    }

    private void rebalanceHeap() {
        // balance the heaps
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        } // else it would be the case where min size == max size, so there's no need for
          // action
    }

    public double median() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }

    public double[] findSlidingWindowMedian(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        int indexResult = 0;

        // first median
        for (int i = 0; i < k; i++) {
            inserNum(nums[i]);
        }
        double currMedian = median();
        result[indexResult] = currMedian;
        indexResult++;

        for (int i = k; i < nums.length; i++) {
            inserNum(nums[i]);
            remove(nums[i - k]);
            currMedian = median();
            result[indexResult] = currMedian;
            indexResult++;
    
        }

        return result;
    }

    @Test
    public void validate() {
        MedianKSubsizedArrays slidingWindowMedian = new MedianKSubsizedArrays();
        double[] result = slidingWindowMedian.findSlidingWindowMedian(new int[] { 1, 2, -1, 3, 5 }, 2);
        double[] expected = new double[] {1.5, 0.5, 1.0, 4.0};
        Assert.assertArrayEquals(expected,result, 0);

        slidingWindowMedian = new MedianKSubsizedArrays();
        result = slidingWindowMedian.findSlidingWindowMedian(new int[] { 1, 2, -1, 3, 5 }, 3);
        expected = new double[]{1.0, 2.0, 3.0};
        Assert.assertArrayEquals(expected,result, 0);

    }
}