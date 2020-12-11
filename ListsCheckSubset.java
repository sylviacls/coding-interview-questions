import java.util.Hashtable;

/**
 * Cracking the PM interview
 * 
 * Given two lists (A and B) of unique strings, write a program to determine if
 * A is a subset of B. That is, check if all the elements from A are contained
 * in B.
 */
public class ListsCheckSubset {

    /**
     * Time Complexity: O(a+b) time, where a is the length of A and b is the length of B. 
     * Space Complexity: O(b) additional memory to hold the hashtable
     */
    public static boolean isSubset(String[] bigger, String[] smaller) {
        Hashtable<String, Boolean> map = new Hashtable<String, Boolean>();
        for (String string : bigger) {
            map.put(string, true);
        }

        for (String string : smaller) {
            if(!map.containsKey(string)) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        String[] bigger = new String[]{"one", "two", "three", "four"};
        String[] smaller = new String[]{"two", "four"};
        System.out.println(isSubset(bigger, smaller));
    }
}
