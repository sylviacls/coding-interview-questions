import java.util.*;

/**
 * Cracking the PM interview
 * You are given a two-dimensional array of sales data where the first column is a product ID and
 * the second column is the quantity. 
 * Write a function to take this list of data and return a new two-dimensional array with the 
 * total sales for each product ID.
 * 
 * Example:
 * 211,4
 * 262,3
 * 211,5
 * 216,6
 * Output:
 * 211,9
 * 262,3
 */
public class ArraySalesData {

    public static int[][] totalSalesByProducts(int[][] sales) {
        Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();
        for (int[] pair : sales) {
            table.put(pair[0], table.getOrDefault(pair[0], 0) + pair[1]) ;
        }

        int[][] result = new int[table.size()][2];
        int index = 0;
        for(Map.Entry<Integer, Integer> entry : table.entrySet()) {
            result[index][0] = entry.getKey();
            result[index][1] = entry.getValue();
            index++;
        }

        return result;
    }
    public static void main(String[] args) {
        int[][] sales = new int[][]{{211,4}, {262,3}, {216,6}, {211,5}};
        int[][] result = totalSalesByProducts(sales);
        for (int[] is : result) {
            Arrays.stream(is).forEach(System.out::println);
        }
    }
}
