import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

/*
 Check Permutation: Given two strings, write a method to decide if one is a permutation of the
other.
* Time complexity: It will depend on the sorting. Using sort() from Arrays O(n log(n))
*/
public class PermutationCheck2Strings {

    public boolean checkPermutation(String input1, String input2){
        if(input1.length() != input2.length()){
            return Boolean.FALSE;
        }
        char[] charsFromInput1 = input1.toCharArray();
        char[] charsFromInput2 = input2.toCharArray();

        Arrays.sort(charsFromInput1);
        Arrays.sort(charsFromInput2);

        for (int i = 0; i < charsFromInput1.length; i++) {
            if(charsFromInput1[i] != charsFromInput2[i])
                return Boolean.FALSE;
            
        }

        return Boolean.TRUE;
    }

    @Test
    public void validate(){
        String test1Input1 = "aabcde";
        String test1Input2 = "abcde";

        String test2Input1 = "qwertyuiopasdfghjklzxvbnm,.';";
        String test2Input2 = "abcdeeee";

        String test3Input1 = "abdefza";
        String test3Input2 = "zfzebda";

        String test4Input1 = "aaa";
        String test4Input2 = "a";

        String test5Input1 = "abdefza";
        String test5Input2 = "zafebda";

        Assert.assertTrue(!checkPermutation(test1Input1, test1Input2));
        Assert.assertTrue(!checkPermutation(test2Input1, test2Input2));
        Assert.assertTrue(!checkPermutation(test3Input1, test3Input2));
        Assert.assertTrue(!checkPermutation(test4Input1, test4Input2));
        Assert.assertTrue(checkPermutation(test5Input1, test5Input2));
    }
}