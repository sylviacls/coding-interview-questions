/**
 * Leetcode: 47. Permutations II
 * https://leetcode.com/problems/permutations-ii/
 * 
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * Example:
 * Input: [1,1,2]
 * Output:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 */
import java.util.*;
import java.util.stream.Collectors;

public class PermutationSetDuplicateNumbers {

    /**
     * Apprach: Iterative
     * 
     * Time Complexity: O(N*N!)
     * Space Complexity: O(N*N!) N! permutations, each containg N numbers
     * 
     * Runtime: 48ms Memory: 40.4MB
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null) return result;
        Queue<List<Integer>> permutations = new LinkedList<>();
        permutations.add(new ArrayList<Integer>());

        for (int num : nums) {
            int permutSize = permutations.size();
            for (int i = 0; i < permutSize; i++) {
                List<Integer> oldPermutation = permutations.poll();
                for (int j = 0; j <= oldPermutation.size(); j++) {
                    List<Integer> newPermutation = new ArrayList<Integer>(oldPermutation);
                    newPermutation.add(j, num);

                    if(!permutations.contains(newPermutation)) {
                        permutations.add(newPermutation);

                        if(newPermutation.size() ==  nums.length) 
                            result.add(newPermutation);
                    }
                }
            }
        }

        return result;
    }

    /**
     * Approach: Recursive + SWAP
     * 
     * Time Complexity: O(N*N!)
     * Space Complexity: O(N*N!) N! permutations, each containg N numbers
     * 
     * Runtime: 7ms Memory: 39.9MB
     */
    public static List<List<Integer>> permuteUniqueII(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null) return result;

        permuteDFS(nums, 0, result);

        return result;
    }

    private static void permuteDFS(int[] nums, int start, List<List<Integer>> result) {
        if(start >= nums.length) {
            //choosing not to use a hashset in the for loop, and make the check of (result.contains)
            //would increase the runtime up to 600ms
            List<Integer> toList = new ArrayList<Integer>(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            result.add(toList);
            return;
        }
        HashSet<Integer> used = new HashSet<Integer>();
        for (int i = start; i < nums.length; i++) {
            if(!used.contains(nums[i])){
                used.add(nums[i]);
                swap(nums, start, i);
                permuteDFS(nums, start+1, result);
                swap(nums, start, i);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(permuteUnique(new int[]{1,1,2}));
        System.out.println(permuteUniqueII(new int[]{1,1,2}));
    }

}
