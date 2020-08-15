import java.util.ArrayList;
import java.util.List;


/**
 * Given a string, find all of its permutations
 */
public class StringFindAllPermutations {
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

    public static void main(String[] args) {
       List<String> result = findPermutation("ABC");
       for (String string : result) {
           System.out.println(string);
       }
    }
}