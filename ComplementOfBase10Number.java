import org.junit.*;

/**
 * Every non-negative integer N has a binary representation, for example, 
 * 8 can be represented as “1000” in binary and 7 as “0111” in binary.
 * 
 * The complement of a binary representation is the number in binary that we get when we change 
 * every 1 to a 0 and every 0 to a 1. For example, the binary complement of “1010” is “0101”.
 * 
 * For a given positive number N in base-10, return the complement of its binary 
 * representation as a base-10 integer.
 * 
 * Input: 8
 * Output: 7
 * Explanation: 8 is 1000 in binary, its complement is 0111 in binary, which is 7 in base-10.
 */
public class ComplementOfBase10Number {

    /**
     * Properties of XOR:
     * It will return 1 if we take XOR of two different bits i.e. 1^0 = 0^1 = 1.
     * It will return 0 if we take XOR of two same bits i.e. 0^0 = 1^1 = 0. 
     *    In other words, XOR of two same numbers is 0.
     * It returns the same number if we XOR with 0.
     * 
     * We can conclude that XOR of a number with its complement will result in
     * a number that has all of its bits set to 1. 
     * 
     * 1) number ^ complement = all_bits_set
     * 2) number ^ number ^ complement = number ^ all_bits_set (adding number in both sides)
     * 3) 0 ^ complement = number ^ all_bits_set
     * 4) complement = number ^ all_bits_set
     * 
     * Finding mask (all_bits_set)
     *   input  1000 (8)
     *   mask    1111 (XOR) 
     *      we can find the mask using pow(2, number bit) -1 
     *          (1111 = 15 = 2^4 - 1) (2^number of bits - 1)
     *     or 1 << number of bits - 1 (left shift)
     *            1 << 1 = 10
     *            1<<2 = 100
     *  resul = input ^ mask =     0111 (its complement)
     * @param n
     * @return
     */
    public static int bitwiseComplement(int n) {
        int mask = 1; // mask = pow(2, x) – 1; mask is the smallest number >= N
        while (mask < n) {
          mask = (mask << 1) | 1; 
        }
          //mask = mask*2 + 1;  same as above

        return n ^ mask;  
        //return mask - n; // also ok  
    }

    public static int bitwiseComplementII(int num) {
        // count number of total bits in 'num'
        int bitCount = 0;
        int n = num;
        while (n > 0) {
          bitCount++;
          n = n >> 1; //same as:  n = n/2;
        }
    
        // for a number which is a complete power of '2' i.e., it can be written as pow(2, n), if we
        // subtract '1' from such a number, we get a number which has 'n' least significant bits set to '1'.
        // For example, '4' which is a complete power of '2', and '3' (which is one less than 4) has a binary 
        // representation of '11' i.e., it has '2' least significant bits set to '1' 
        int all_bits_set = (int) Math.pow(2, bitCount) - 1;
    
        // from the solution description: complement = number ^ all_bits_set
        return num ^ all_bits_set;
      }

    /**
     * Naive simulation: find each single digit, then flip it
     * @param num
     * @return
     */
    public static int findComplement(int num) {
        int res = 0, ind = 0;
        while(num > 0){
           // System.out.println(Integer.toBinaryString(num));
            //find single digit from low to high significance
            int digit = num % 2; 
            int flippedDigit = (digit == 1? 0 : 1); // flip digit
         //   System.out.println(Integer.toBinaryString(flippedDigit << ind));
            res += (flippedDigit << ind);
        //    System.out.println(Integer.toBinaryString(res));
            num /= 2;            
            ind++;
        }
        return res;
    }
  
    public static void main(String[] args) {
        System.out.println(1<<0);
        System.out.println(4>>1);
        findComplement(8);
    }

    @Test
    public void validate() {
        Assert.assertEquals(7, bitwiseComplement(8));
        Assert.assertEquals(5, bitwiseComplement(10));

        Assert.assertEquals(7, findComplement(8));
        Assert.assertEquals(5, findComplement(10));

        Assert.assertEquals(7, bitwiseComplementII(8));
        Assert.assertEquals(5, bitwiseComplementII(10));

    }
    
}