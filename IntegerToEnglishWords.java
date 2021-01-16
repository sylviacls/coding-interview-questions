/**
 * Leetcode: 273. Integer to English Words
 * https://leetcode.com/problems/integer-to-english-words/
 * 
 * Example 1:
 * Input: num = 123
 * Output: "One Hundred Twenty Three"
 * 
 * Example 2:
 * Input: num = 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 * 
 * Example 3:
 * Input: num = 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * 
 * Example 4:
 * Input: num = 1234567891
 * Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 */
public class IntegerToEnglishWords {

    private static final String[] belowTen = new String[] {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static final String[] belowTwenty = new String[] {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] belowHundred = new String[] {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    
    public static String numberToWords(int num) {
        if (num == 0) return "Zero";
        return helper(num);         
    }

    private static String helper(int num) {
        String result = "";
        if(num < 10) result = belowTen[num];
        else if(num < 20) result = belowTwenty[num-10];
        else if(num < 100) result = belowHundred[num/10] + " " + helper(num%10);
        else if(num < 1000) result = helper(num/100) + " Hundred " + helper(num%100); //990
        else if(num < 1000000) result = helper(num/1000) + " Thousand " + helper(num%1000);//9.250 (9 thous) - 999.000  (990 thou)
        else if(num < 1000000000) result = helper(num/1000000) + " Million " + helper(num%1000000);//1.000,000 - 999.999.9999
        else result = helper(num/1000000000) + " Billion " +  helper(num%1000000000) ; // 1.000.000.000,00
        return result.trim();
    }

    public static void main(String[] args) {
        System.out.println(numberToWords(123));
        System.out.println(numberToWords(12345));
        System.out.println(numberToWords(1234567));
        System.out.println(numberToWords(1234567891));
    }
}
