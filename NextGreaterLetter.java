import org.junit.*;

/**
 * Leetcode: 744. Find Smallest Letter Greater Than Target
 * https://leetcode.com/problems/find-smallest-letter-greater-than-target/
 * 
 * Given a list of sorted characters letters containing only lowercase letters,
 * and given a target letter target, find the smallest element in the list that is 
 * larger than the given target.
 * 
 * Letters also wrap around. For example, if the target is target = 'z' 
 * and letters = ['a', 'b'], the answer is 'a'.
 * 
 */
public class NextGreaterLetter {

    /**
     * Approach: Binary Search
     * 
     * Time Complexity: O(logN)
     * Space Complexity: O(1)
     */
    public static char searchNextLetter(char[] letters, char target) {
        int n = letters.length;
        if(target < letters[0] || target >= letters[n-1]) {
            return letters[0];
        }
        int start = 0;
        int end = letters.length-1;
        while(start <= end) {
            int mid = start + (end-start)/2; //to avoid a potencial integer overflow 
            //we cannot consider the case when target == letters[mid], 
            //because we can have duplicated letters
            if( target < letters[mid]) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        //the key hasn't been found, and start = end+1
        // and the next letter will be "end"
        return letters[start%n];
    }

    @Test
    public void validate() {
        Assert.assertEquals('f', searchNextLetter(new char[] {'c', 'f', 'j'}, 'c'));
        Assert.assertEquals('h', searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'f'));
        Assert.assertEquals('c', searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'b'));
        Assert.assertEquals('a', searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'm'));
        Assert.assertEquals('a', searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'h'));
      }
}