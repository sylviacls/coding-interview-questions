import java.util.*;

import org.junit.Assert;
import org.junit.Test;

/* 
 * Given a string s, remove duplicate letters so that every letter appears once
 * and only once. 
 * 
 */
public class StringRemoveDuplicates {
    
    public static String removeDuplicateLetters(String s) {
        Set<Character> duplicates = new HashSet<Character>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(!duplicates.contains(c)) {
                sb.append(c);
                duplicates.add(c);
            }
        }
        return sb.toString();
    }

    @Test
    public void validate(){
        Assert.assertEquals("abcd", removeDuplicateLetters("aabbccddaaaaaac"));
    }
}
