import java.util.Collections;
import java.util.PriorityQueue;


/**
 * Design a class to calculate the median of a number stream. The class should
 * have the following two methods:
 * 
 * insertNum(int num): stores the number in the class findMedian(): returns the
 * median of all numbers inserted in the class
 * 
 * If the count of numbers inserted in the class is even, the median will be the
 * average of the middle two numbers.
 * 
 * Assume ‘x’ is the median of a list. This means that half of the numbers in
 * the list will be smaller than (or equal to) ‘x’ and half will be greater than
 * (or equal to) ‘x’. we can divide the list into two halves: one half to store
 * all the smaller numbers and one half to store the larger numbers
 * 
 * The median of all the numbers will either be the largest number in the
 * smallNumList or the smallest number in the largNumList. If the total number
 * of elements is even, the median will be the average of these two numbers.
 * 
 * Time complexity: O(log N) due to the insertion in the heap
 * Space complexity: O(N) because, as at any time, we will be storing all the numbers
 * 
 */
public class MedianOfAStream {
  private PriorityQueue<Integer> maxHeap; // first half
  private PriorityQueue<Integer> minHeap; // second half

  public MedianOfAStream() {
    this.maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
    this.minHeap = new PriorityQueue<Integer>();
  }

  public void insertNum(int num) {
    // We can insert a number in the Max Heap if the number is smaller than the top
    // (largest) number of the heap
    if (maxHeap.isEmpty() || num < maxHeap.peek()) {
      maxHeap.add(num);
    } else {
      minHeap.add(num);
    }

    // After every insertion, we will balance the number of elements in both heaps
    // If the count of numbers is odd, let’s decide to have more numbers in max-heap
    if (maxHeap.size() != minHeap.size()) {
        if (minHeap.size() > maxHeap.size()) {
          maxHeap.add(minHeap.poll());
        } else if (maxHeap.size() > minHeap.size()+1) {
          minHeap.add(maxHeap.poll());
        } 
    }
  }

  public double findMedian() {
    if (!maxHeap.isEmpty()) {
      // with even number of elements the median will be the average of the top element of both the heaps 
      if ((maxHeap.size() + minHeap.size()) % 2 == 0) {
        return (double) (maxHeap.peek() + minHeap.peek()) / 2;
      } else {
        //with an odd number of elements, the median will be the top element of Max Heap
        return maxHeap.peek();
      }
    } else {
      return -1;
    }
  }

  public static void main(String[] args) {
    MedianOfAStream medianOfAStream = new MedianOfAStream();
    medianOfAStream.insertNum(3);
    medianOfAStream.insertNum(1);
    System.out.println("The median is: " + medianOfAStream.findMedian());
    medianOfAStream.insertNum(5);
    System.out.println("The median is: " + medianOfAStream.findMedian());
    medianOfAStream.insertNum(4);
    System.out.println("The median is: " + medianOfAStream.findMedian());
  }

}