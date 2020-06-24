import java.util.*;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

/**
 * Implement an autocomplete system. That is, given a query string s and a set
 * of all possible query strings, return all strings in the set that have s as a
 * prefix. For example, given the query string de and the set of strings [dog,
 * deer, deal], return [deer, deal]. Hint: Try preprocessing the dictionary into
 * a more efficient data structure to speed up queries.
 */

public class StringAutocomplete {
    
    public static List<String> autoComplete(List<String> set, String query ) {
        List<String> result = set.stream().filter(x -> x.startsWith(query))
                                .collect(Collectors.toList());
        return result;
    }

    @Test
    public void validate() {
        List<String> set = Arrays.asList(new String[]{"dog","deer", "deal", "lamp", "decret"});
        List<String> result = Arrays.asList(new String[]{"deer", "deal", "decret"});
        Assert.assertEquals(result, autoComplete(set, "de"));;
    }
}