import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import org.junit.*;

/**
 * Given a set of distinct numbers, find all of its permutations. Permutation is
 * defined as the re-arranging of the elements of the set. 
 * For example, {1, 2, 3} has the following six permutations:
 * {1, 2, 3} 
 * {1, 3, 2} 
 * {2, 1, 3} 
 * {2, 3, 1} 
 * {3, 1, 2}
 * {3, 2, 1} 
 * If a set has ‘n’ distinct elements it will have n! permutations.
 */
public class PermutationSetDistinctNumbers {


  public static List<List<Integer>> findPermutationsRecursive(int[] nums) {
      List<List<Integer>> result = new ArrayList<>();
      permute(result, nums, 0);
      return result;
  }

  /**
   * This is an example of the “decrease and conquer” algorithmic strategy. 
   * On each pass through the loop, we peel off a value, solve the rest of the problem, 
   * and then make a change.
   * Time complexity: O (N!)
   * @param result
   * @param input
   * @param start
   */
  private static void permute(List<List<Integer>> result, int[] input, int start) {
    if (start == input.length) {
        List<Integer> currentSet = Arrays.stream(input).boxed().collect(Collectors.toList());
        result.add(currentSet);
        return;
    }
    for (int i = start; i < input.length; i++) {
        swap(input, i, start);
        permute( result, input, start + 1);
        swap(input, i, start);
    }
  }   
    
  private static void swap(int[] input, int i, int start) {
    int temp = input[i];
    input[i] = input[start];
    input[start] = temp;
  }

  /**
   *  Time complexity: O(N * N!): 
   * We know that there are a total of N! permutations of a set with ‘N’ number. and we also
   * need to insert a number into a permutation of size ‘N’ which makes the overall 
   * time complexity of our algorithm O (N * N!)
   * @param nums
   * @return
   */
  public static List<List<Integer>> findPermutations(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Queue<List<Integer>> permutations = new LinkedList<>();
    permutations.add(new ArrayList<>());
    for (int currentNumber : nums) {
      // we will take all existing permutations and add the current number to create new permutations
      int n = permutations.size();
      for (int i = 0; i < n; i++) {
        List<Integer> oldPermutation = permutations.poll();
        // create a new permutation by adding the current number at every position
        for (int j = 0; j <= oldPermutation.size(); j++) {
          List<Integer> newPermutation = new ArrayList<Integer>(oldPermutation);
          newPermutation.add(j, currentNumber);
          if (newPermutation.size() == nums.length)
            result.add(newPermutation);
          else
            permutations.add(newPermutation);
        }
      }
    }
    return result;
  }

  public static List<List<Integer>> findPermutationRec(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    generatePermutationsRecursive(nums, 0, new ArrayList<Integer>(), result);
    return result;
  }

  private static void generatePermutationsRecursive(int[] nums, int index, List<Integer> currentPermutation,
      List<List<Integer>> result) {
    if (index == nums.length) {
      result.add(currentPermutation);
    } else {
      // create a new permutation by adding the current number at every position
      for (int i = 0; i <= currentPermutation.size(); i++) {
        List<Integer> newPermutation = new ArrayList<Integer>(currentPermutation);
        newPermutation.add(i, nums[index]);
        generatePermutationsRecursive(nums, index + 1, newPermutation, result);
      }
    }
  }

  @Test
  public void validate() {
  List<List<Integer>> result = findPermutations(new int[] { 1, 3, 5 });
  Assert.assertEquals("[[5, 3, 1], [3, 5, 1], [3, 1, 5], [5, 1, 3], [1, 5, 3], [1, 3, 5]]",
             result.toString());

   result = findPermutationsRecursive(new int[]{ 1, 2, 3});
   Assert.assertEquals("[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 2, 1], [3, 1, 2]]",
             result.toString());
  }
    
}