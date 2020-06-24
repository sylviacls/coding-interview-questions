
//NOT WORKING


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class RelativeSortArray {

    /*
    Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
    Output: [2,2,2,1,4,3,3,9,6,7,19]
    */
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] arr1Sorted = new int[arr1.length];
        HashMap<Integer, Integer> map = new HashMap<>();

        // Mapping numbers and their count
        for (int i: arr1) {
            int countNumber = 1;
            if(!map.containsKey(i)) {
                map.put(i, countNumber);
            } else {
                countNumber += map.get(i);
                map.put(i, countNumber);
            }   


        }
     //   map.forEach((key,value) -> System.out.println(key + " = " + value));

        // Putting the common numbers in order 
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.sort(Comparator.naturalOrder());
        for(int j: arr2) {
           int x = map.get(j);
           for(int i = 1; i <= x; i++) {
                list.add(j);
           }
        }

        //Putting the left numbers in ascending order at the end

        for (int number : arr1) {
            if(!list.contains(new Integer(number))) {
                int x = map.get(number);
                for(int i = 1; i <= x; i++) {
                     list.add(number);
                }
            }
            
        }

        //list.forEach(i -> System.out.print(i+","));
        int count = 0;
        for (Integer integer : list) {
            arr1Sorted[count] = integer.intValue();
            count++;
        }
        
        return arr1Sorted;
    }

    public static void main(String[] args) {
        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = {2,1,4,3,9,6};

       int[] result = RelativeSortArray.relativeSortArray(arr1, arr2);
       for (int i : result) {
           System.out.print(i + ",");
       }

    }

}