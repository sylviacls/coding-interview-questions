import java.util.Arrays;

/**
 * Cracking the PM interview Reverse the order of elements in an array (without
 * creating a new array).
 */
public class ArrayReverseOrder {
   public static void reverse(int[] input) {
       int mid = input.length/2;
       for (int i = 0; i < mid; i++) {
           swap(input, i, input.length-1-i);
       }
   }

   private static void swap(int[] input, int i, int j) {
       int temp = input[i];
       input[i] = input[j];
       input[j] = temp;
   }

   
   public static void main(String[] args) {
    int[] input = new int[]{2,4,6,8,10};
    reverse(input);
    Arrays.stream(input).forEach(System.out::println);
}
}
