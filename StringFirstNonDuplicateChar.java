import java.util.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * Write a function, which will accept a String and return first non-repeated
 * character, for example in the world "hello", except 'l' all are non-repeated,
 * but 'h' is the first non-repeated character. Similarly, in word "swiss" 'w'
 * is the first non-repeated character.
 */
public class StringFirstNonDuplicateChar {

    /**
     * LinkedHashMap preserves the insertion order
     * @param input
     * @return
     */
    public static char findFirstNonDuplicateChar(String input) {
        Map<Character,Integer> counts = new LinkedHashMap<Character,Integer>(input.length());

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            counts.put(current, counts.getOrDefault(current, 0)+1);
        }

        for(Map.Entry<Character,Integer> entry : counts.entrySet()) {
            if(entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return Character.MIN_VALUE;
    }

    @Test
    public void validate() {
        Assert.assertEquals('b', findFirstNonDuplicateChar("abcdefghija"));
        Assert.assertEquals('h', findFirstNonDuplicateChar("hello")); 
        Assert.assertEquals('J', findFirstNonDuplicateChar("Java")); 
        Assert.assertEquals('i', findFirstNonDuplicateChar("simplest"));
    }
    
}