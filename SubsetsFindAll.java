import java.util.ArrayList;
import java.util.List;

import org.junit.*;

/**
 * Given a set with distinct elements, find all of its distinct subsets.
 * Ex:
 * Input: [1, 5, 3]
 * Output: [], [1], [5], [3], [1,5], [1,3], [5,3], [1,5,3]
 * 
 * To generate all subsets of the given set, we can use the Breadth First Search (BFS) approach. 
 * We can start with an empty set, iterate through all numbers one-by-one, and add them to 
 * existing sets to create new subsets.
 * 
 * Time complexity: O(2^N): the number of subsets doubles as we add each element to all the 
 *                  existing subsets, the time complexity of the above algorithm
 * Space complexity: O(2^N): All the additional space used by our algorithm is for the output 
 *                    list, and we will have a total of O(2^N) subsets
​N
​​ ) subsets,
 */
public class SubsetsFindAll {

    public static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        //adding the first empty set
        subsets.add(new ArrayList<Integer>());
        for (int num : nums) {
            int subSetSize = subsets.size();
         // we will take all existing subsets and insert the current number in them to create new subsets
            for (int i = 0; i < subSetSize; i++) {
              // create a new subset from the existing subset and insert the current element to it
                List<Integer> newSet = new ArrayList<>(subsets.get(i));
                newSet.add(num);
                subsets.add(newSet);
            }
        }

        return subsets;
      }
    
      @Test
      public void validate() {
        List<List<Integer>> result = SubsetsFindAll.findSubsets(new int[] { 1, 3 });
        Assert.assertEquals("[[], [1], [3], [1, 3]]", result.toString());
    
        result = SubsetsFindAll.findSubsets(new int[] { 1, 5, 3 });
        Assert.assertEquals("[[], [1], [5], [1, 5], [3], [1, 3], [5, 3], [1, 5, 3]]", result.toString());
      }
    
}