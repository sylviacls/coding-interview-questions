import java.util.ArrayList;
import java.util.Arrays;
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

    public static List<List<Integer>> findSubsetsBack(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }
    
    private static void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
    
      @Test
      public void validate() {
        List<List<Integer>> result = SubsetsFindAll.findSubsets(new int[] { 1, 3 });
        Assert.assertEquals("[[], [1], [3], [1, 3]]", result.toString());
    
        result = SubsetsFindAll.findSubsetsBack(new int[] { 1, 3 });
        Assert.assertEquals("[[], [1], [1, 3], [3]]", result.toString());

        result = SubsetsFindAll.findSubsets(new int[] { 1, 5, 3 });
        Assert.assertEquals("[[], [1], [5], [1, 5], [3], [1, 3], [5, 3], [1, 5, 3]]", result.toString());
      }

      public static void main(String[] args) {
        List<List<Integer>> result = SubsetsFindAll.findSubsetsBack(new int[] { 1, 3 });
        List<List<Integer>> result2 = SubsetsFindAll.findSubsets(new int[] { 1, 3 });
        System.out.println(result.equals(result2));
        System.out.println(result.toString());
        System.out.println(result2.toString());
      }
    
}