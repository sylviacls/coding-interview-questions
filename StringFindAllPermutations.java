import java.util.ArrayList;
import java.util.List;


/**
 * Given a string, find all of its permutations
 * 
 * Example:
 * input: "ABC"
 * result: "ABC", "ACB", "BAC", "BCA", "CBA", "CAB"
 */
public class StringFindAllPermutations {

    /**
     * Approach: Recursion using swap
     * 
     * Time complexity: O(n * n!) 
     * Space complexity: O(n!)
     */
    public static List<String> findPermutation(String input) {
        List<String> result = new ArrayList<String>();
        char[] string = input.toCharArray();
        permutation(result,string, 0);
        return result;
    }

    /**
     * Recursive function to find permutations
     * @param string
     * @param start
     */
	private static void permutation(List<String> result,char[] string, int start) {
        //base case
        if(start == string.length) {
            result.add(String.valueOf(string));
            return;
        }

        for (int i = start; i < string.length; i++) {
            swap(string,start,i);
            permutation(result,string, start+1);
            swap(string,start,i);
        }
	}

    private static void swap(char[] string, int start, int i) {
        char temp = string[start];
        string[start] = string[i];
        string[i] =  temp;
    }

    /**
     * Approach: Recursion 
     * 
     * Time Complexity: O(N * N!)
     * Space Complexity:
     */
    public static List<String> findPermutationII(String input) {
        List<String> result = new ArrayList<String>();
        permutationII("",input,result);
        return result;
    }

    private static void permutationII(String prefix, String suffix, List<String> result) {
        if(suffix.length() == 0) {
            result.add(prefix);
            return;
        } 
        //N * N!
        for (int i = 0; i < suffix.length(); i++) {
            //substring concatenation will take o(n)
            permutationII(prefix + suffix.charAt(i), suffix.substring(0, i) + suffix.substring(i+1, suffix.length()), result);
        }
    }

    public static void main(String[] args) {
       List<String> result = findPermutationII("ABC");
       for (String string : result) {
           System.out.println(string);
       }
    }
}