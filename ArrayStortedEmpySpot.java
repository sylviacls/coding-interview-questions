import java.util.Arrays;

/**
 * Cracking the PM Interview
 * 
 * Given a sorted array of positive integers with an empty spot (zero) at the end, 
 * insert an element in sorted order.
 */
public class ArrayStortedEmpySpot {
    public static void insertElement(int[] input, int num) {
        int index = input.length-2;
        while(index >= 0 && input[index] > num) {
            input[index+1] = input[index];
            index--;
        }
        input[index+1] = num;
    }



    public static void main(String[] args) {
        int[] input = new int[]{2,4,6,8,0};
        insertElement(input, 1);
        Arrays.stream(input).forEach(System.out::println);
    }
    
}
