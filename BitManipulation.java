public class BitManipulation {


    public static int setBit(int number, int position) {
        /**
         * number =   00000110
         * position = 00000101 (5)
         * mask =     0010000
         * 
         * result =   00100110
         */

        int mask = 1 << position;
        System.out.println("Mask " +Integer.toBinaryString(mask));
        return number | mask;
    }

    public static int clearBit(int number, int position) {
        /** position 2
         * number =   00000110
         * mask =     00000010
         * ~mask =    11111101
         * result =   00000100
         */

        int mask = (1 << position);
        System.out.println("Mask " +Integer.toBinaryString(mask));
        return number & ~mask;
    }

    public static int flipBit(int number, int position) {

        int mask = (1 << position);
        return number ^ mask;
    }

    public static int modify(int number, int position, int state) {
        // state 0, clear a bit - state 1, set a bit
        int mask = 1 << position;
        return (number & ~mask) | (-state & mask);

    }

    public static boolean isBitSet(int number, int position) {
        int mask = (1 << position);
        return ( (number & mask) == mask);

        /**
         * take the bit we want check and right-shift to the most-righted position and then & 1
         * int shifted = (number >> position)
         * return shifted & 1
         */
    }

    public static boolean isEven(int number) {
        /* a number is even is its most-righted bit is 0
        * number =   00010001
        * mask = 1 = 00000001
        */
        return ( number & 1) == 0;

    }

    public static boolean isPowerOfTwo(int number){
        /* a number is a power of two if it has only one bit as 1 (ex, 8 = 00001000) 
        * if we subtracte 1 of the number is the same as set 0 to this 1 and set '1's' all the way behind it
         8 =  00001000
        8-1 = 00000111
        */
        return (number & number-1) == 0;
    }

    public static void main(String[] args) {
        int number = 17;
        System.out.println(Integer.toBinaryString(number));
        System.out.println(isPowerOfTwo(number));
    }

}