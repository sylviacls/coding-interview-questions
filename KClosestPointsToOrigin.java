import java.util.Arrays;
import java.util.PriorityQueue;

import org.junit.*;

/**
 * We have a list of points on the plane. Find the K closest points to the
 * origin (0, 0). (Here, the distance between two points on a plane is the
 * Euclidean distance.)
 
 * The Euclidean distance of 2 points X, Y : 
 * distance = sqrt((y2 - y1) *(y2 - y1) + (x2 - x1)* (x2 - x1))
 * 
 * The Euclidean distance of a point P(x,y) from the origin can be calculated
 * through the following formula: sqrt(x^2 + y^2), 
 * 
 * Input: points = [[1,3],[-2,2]], K = 1 Output: [[-2,2]]
 * 
 * Explanation: The distance between (1, 3) and the origin is sqrt(10). The
 * distance between (-2, 2) and the origin is sqrt(8). Since sqrt(8) < sqrt(10),
 * (-2, 2) is closer to the origin. We only want the closest K = 1 points from
 * the origin, so the answer is just [[-2,2]].
 */


public class KClosestPointsToOrigin {
  
    
    /**
     * Approach: Partition 
     * This solution is based on quick sort, we can also call it quick select. 
     * In an ordered array the Kth smallest number will be placed at kth-index
     * @param nums
     * @param k
     * @return
     */
    public static int[][] findClosestPointsIII(int[][] points, int k) {
        int left = 0;
        int right = points.length-1;
        while (left <= right) {
            int mid = partition(points, left, right);
            if (mid == k) {
                break;
            } else if(k < mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return Arrays.copyOfRange(points, 0, k);
    }

    private static int partition(int[][] points, int start, int end) {
        int[] pivot = points[start];
        int i = start + 1;
        int j = end;

        while(i <= j) {
            while( i <=  end && compare(points[i], pivot) < 0) {
                i++;
            }
            while( j >=  start && compare(points[j], pivot) > 0) {
                j--;
            }
            if (i <= j) {
                swap(points, i, j);
                i++;
                j--;
            }
        }
        swap(points, start, j);
        return j;
    }
    
    private static void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }

    private static int compare(int[] point, int[] pivot) {
        return (point[0]*point[0] + point[1]*point[1]) - (pivot[0]*pivot[0] + pivot[1]*pivot[1]);
    }

    /**
     * Naive approach: Sort the all points by their distance to the origin point
     * directly, then get the top k closest points.
     * 
     * Time Complexity: O(NlogN)
     * 
     * @param points
     * @param k
     * @return
     */
    public static int[][] findClosestPoints(int[][] points, int k) {
        //using a Comparator:
        // the lambda function override the method compare()
        //if compare() returns negative int, the first argument is less than second
        //if compare() returns positive int, the first argument is greater than second
        //if compare() returns 0, first is equal to the second
        Arrays.sort(points, (a,b) -> a[0]*a[0] + a[1]*a[1] - b[0]*b[0]- b[1]*b[1]);
  
        return Arrays.copyOfRange(points, 0, k);
    }
      
    /**
     * we can use a Max Heap to find ‘K’ points closest to the origin. 
     * While iterating through all points, if a point (say ‘P’) is closer to the origin than
     * the top point of the max-heap, we will remove that top point from the heap 
     * and add ‘P’ to always keep the closest points in the heap.
     * 
     * Time Complexity: O(N logK)
     * Space Complexity: O(logK)
     * @param points
     * @param k
     * @return
     */
    public static int[][] findClosestPointsII(int[][] points, int k) {
      //because we want a maxHeap we have to 'reverse' the comparator
      PriorityQueue<int[]> maxHeap = new PriorityQueue<int[]>((a,b) -> (b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1]));
      for (int i = 0; i < k; i++) {
          maxHeap.add(points[i]);
      }

      for (int i = k; i < points.length; i++) {
          if(distanceToOrigin(points[i]) < distanceToOrigin(maxHeap.peek())) {
            maxHeap.poll();
            maxHeap.add(points[i]);
          }
      } 
      //alternatively to the 2 "for" above we can do:
     /* for (int i = 0; i < points.length; i++) {
        maxHeap.add(points[i]);
        if(maxHeap.size()> k) {
            //so each time we will eject the largest distance point
            //and inside the heap will remain the smallest distance point
            maxHeap.poll();
        } 
        }*/

      int[][] result = new int[k][2];
      for (int i = 0; i < k; i++) {
          result[i] = maxHeap.poll();
      }
      return result;
    }
  
    private static int distanceToOrigin(int[] point) {
        return point[0]*point[0] + point[1]*point[1];
    }

    @Test
    public void validate() {
        int[][] points = new int[][]{{1,3},{-2,2}};
        int[][] expected = new int[][]{{-2,2}};
        Assert.assertArrayEquals(expected, findClosestPoints(points, 1));
        Assert.assertArrayEquals(expected, findClosestPointsII(points, 1));
        Assert.assertArrayEquals(expected, findClosestPointsIII(points, 1));

        int[][] points2 = new int[][]{{3,3},{5,-1},{-2,4}};
        Assert.assertArrayEquals(new int[][]{{3,3},{-2,4}}, findClosestPoints(points2, 2));
        Assert.assertArrayEquals(new int[][]{{-2,4},{3,3}}, findClosestPointsII(points2, 2));
        Assert.assertArrayEquals(new int[][]{{3,3},{-2,4}}, findClosestPointsIII(points2, 2));

        int[][] points3 = new int[][]{{1,3},{-2,2},{2,-2}};
        int[][] expected3 = new int[][]{{-2,2},{2,-2}};

        Assert.assertArrayEquals(expected3, findClosestPoints(points3, 2));
        Assert.assertArrayEquals(expected3, findClosestPointsII(points3, 2));
        Assert.assertArrayEquals(expected3, findClosestPointsIII(points3, 2));
    }

    public static void main(String[] args) {
        int[][] points = new int[][]{{3,3},{5,-1},{-2,4}};
        int[][] result = findClosestPointsIII(points, 2);
        for (int[] nums : result) {
            for (int num : nums) {
                System.out.println(num+",");
            }
            System.out.println();
        }
    }
  }