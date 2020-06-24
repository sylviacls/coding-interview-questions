import org.junit.Test;
import org.junit.Assert;

/**
 * There are three types of edits that can be performed on strings: insert a
 * character, remove a character, or replace a character. Given two strings,
 * write a function to check if they are one edit (or zero edits) away
 */
public class StringsCheckEdition {

    public static Boolean stringsCheckEdition(String input1, String input2) {
        if (input1.equals(input2))
            return Boolean.TRUE;

        if (input1.length() - input2.length() > 1)
            return Boolean.FALSE;

        for (int i = 0; i < input1.length(); i++) {
            if (input1.charAt(i) == input2.charAt(i)) {
                input2.replaceFirst(String.valueOf(input2.charAt(i)), "");
                input1.replaceFirst(String.valueOf(input1.charAt(i)), "");
            }
        }

        return Boolean.FALSE;
    }

    public Boolean checkSingleReplacement(String input1, String input2) {

        if (input1 == null || input2 == null)
            return Boolean.TRUE;

        if (input1.equals(input2))
            return Boolean.TRUE;

        if (Math.abs(input1.length() - input2.length()) > 1)
            return Boolean.FALSE;

        int i = 0;
        int j = 0;
        int count = 0;
        while (i < input1.length() && j < input2.length()) {
            // If current characters don't match
            if (input1.charAt(i) != input2.charAt(j)) {

                if (count == 1)
                    return false;
                // If length of one string is more, then only possible edit
                // is to remove a character
                if (input1.length() > input2.length()) {
                    i++;
                } else if (input1.length() < input2.length()) {
                    j++;
                } else {// If lengths of both strings is same
                    i++;
                    j++;
                }
                // Increment count of edits
                count++;
            } else  { // If current characters match
                i++;
                j++;
            }
        }
         // If last character is extra  in any string 
        if (i < input1.length() || j < input2.length())  {
            count++; 
        }
        return count == 1; 
    }

    @Test
    public void validate() {
        Assert.assertTrue(checkSingleReplacement("sylvia", "syvia"));
        Assert.assertTrue(checkSingleReplacement("sylvia", "xylvia"));
        Assert.assertTrue(checkSingleReplacement("sylvia", "sylvi"));
        Assert.assertTrue(!checkSingleReplacement("sylvia", "silva"));
    }

}