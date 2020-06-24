import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Assert;

public class StringsComparingBackspace {

    public static Boolean comparingStringsBackspace(String input1, String input2) {
        input1 = removeBackspace(input1);
        input2 = removeBackspace(input2);

        return input1.equals(input2);
    }

    //"xy#z" =  "xz" #xz#
    private static String removeBackspace(String input1) {

        Deque<Character> ans = new ArrayDeque<Character>();
        for (char c: input1.toCharArray()) {
            if (c != '#')
                ans.push(c);
            else if (!ans.isEmpty())
                ans.pop();
        }
        return String.valueOf(ans);
    }


    
    @Test
    public void validate(){
        Assert.assertTrue(comparingStringsBackspace("xy#z", "xzz#"));
    }
}