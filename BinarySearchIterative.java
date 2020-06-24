import java.util.Arrays;

import org.junit.*;

public class BinarySearchIterative {

    public static int binarySearch(int[] list, int number) {
        int begin = 0;
        int last = list.length - 1;

        while (begin <= last) {
            int mid = (begin + last) / 2;
            if (number > list[mid]) {
                begin = mid + 1;
            } else if (number < list[mid]) {
                last = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    @Test
    public void validate() {

        int[] list = new int[] { 7, 10, 43, 22, 16, 9, 25 };
        Arrays.sort(list);
        System.out.printf("Sorted list = %s", Arrays.toString(list));

        Assert.assertEquals(-1, binarySearch(list, 12));
        Assert.assertEquals(1, binarySearch(list, 9));

    }

}