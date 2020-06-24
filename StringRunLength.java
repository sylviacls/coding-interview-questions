import org.junit.Assert;
import org.junit.Test;

/**
 * Run-length encoding is a fast and simple method of encoding strings.
 *  The basic idea is to represent repeated successive characters as a single count
 *  and character. For example, the string "AAAABBBCCDAA" would be encoded as "4A3B2C1D2A".
 * 
 * Implement run-length encoding and decoding. You can assume the string to be encoded have 
 * no digits and consists solely of alphabetic characters. You can assume the string to be 
 * decoded is valid.
 */
public class StringRunLength {


    public  static String runLengthEnconding(String input) {

        StringBuilder inputEnconded = new StringBuilder();
        char[] arrayChar = input.toCharArray();

        for (int i = 0; i < arrayChar.length; i++) {
            int count = 1;
            char current = arrayChar[i];
            while (i <arrayChar.length -1  && current == arrayChar[i+1] ) {
                count++;
                i++;
            }
            inputEnconded.append(count);
            inputEnconded.append(current);
        }

        return inputEnconded.toString();
    }

    public static String runLengthDecoding(String input) {
        String inputDecoded = "";
        char[] arrayChar = input.toCharArray();

        for (int i = 0; i < arrayChar.length-1; i++) {
            char current = arrayChar[i];
            if(Character.isDigit(current)) {
                int count = Integer.parseInt(current+"");
                char nextChar = arrayChar[i+1];
                while(count > 0) {
                    inputDecoded += nextChar;
                    count--;
                }
            }
        }
        return inputDecoded;
    }

    @Test
    public void validate() {
        Assert.assertEquals("4A3B2C1D2A",runLengthEnconding("AAAABBBCCDAA"));
        Assert.assertEquals("AAAABBBCCDAA",runLengthDecoding("4A3B2C1D2A"));
    }
}
