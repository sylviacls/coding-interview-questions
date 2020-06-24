import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.*;

/**
 * Given a set of numbers that might contain duplicates, find all of its distinct subsets.
 * 
 * Ex.
 * Input: [1, 3, 3]
 * Output: [], [1], [3], [1,3], [3,3], [1,3,3]
 */
public class SubsetsFindDistintics {

    public static List<List<Integer>> findSubsetsDistincts(int[] nums) {
        // This will ensure that all duplicate numbers are next to each other.
        Arrays.sort(nums);

        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<Integer>()); //adding the first empty set

        //startIndex and endIndex will handle the duplicate cases which 
        //we have to access previous subset
        int startIndex = 0;
        int endIndex = 0;

        for (int i = 0; i <nums.length; i++) {
            startIndex = 0;
            // if current and the previous elements are same, create new subsets only 
            // from the subsets added in the previous step
            if(i > 0 && nums[i] == nums[i-1]) {
                //out startindex will be the last index from current subset
                startIndex = endIndex + 1;
            }
            
            //endIndex will always store the last index before we start to create new subsets
            //for the current number
            endIndex = subsets.size()-1;
            for (int j = startIndex; j <= endIndex; j++) {
                List<Integer> newSet = new ArrayList<Integer>(subsets.get(j));
                newSet.add(nums[i]);
                subsets.add(newSet);
            }
   
        }
        return subsets;
      }


      @Test
      public void validate() {
        List<List<Integer>> result = SubsetsFindDistintics.findSubsetsDistincts(new int[] { 1, 3, 3 });
        String expected = "[[], [1], [3], [1, 3], [3, 3], [1, 3, 3]]";
        Assert.assertEquals(expected, result.toString());
    
        result = SubsetsFindDistintics.findSubsetsDistincts(new int[] { 1, 5, 3, 3 });
        expected = "[[], [1], [3], [1, 3], [3, 3], [1, 3, 3], [5], [1, 5], [3, 5], [1, 3, 5], [3, 3, 5], [1, 3, 3, 5]]";
        Assert.assertEquals(expected, result.toString());
      }
}