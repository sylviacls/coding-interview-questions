//https://www.geeksforgeeks.org/find-the-two-repeating-elements-in-a-given-array/
//https://www.geeksforgeeks.org/find-duplicates-in-on-time-and-constant-extra-space/
//https://www.geeksforgeeks.org/find-the-smallest-positive-number-missing-from-an-unsorted-array/

public class DuplicateFindAll {
    // Function to print duplicates
    public static void printRepeating(int[] arr) {
        System.out.println("The repeating elements are : ");
        //{ 1, 2, 3, 1, 3, 6, 6 };
        for (int i = 0; i < arr.length; i++) {
            if (arr[Math.abs(arr[i])] >= 0)
                arr[Math.abs(arr[i])] = -arr[Math.abs(arr[i])];
            else
                System.out.print(Math.abs(arr[i]) + " ");
        }
    }

    public static void printRepeating2(int[] arr)  { 
        int count[] = new int[arr.length]; 
        int i; 
  
        System.out.println("Repeated elements are : "); 
        for (i = 0; i < count.length; i++)  { 
            if (count[arr[i]] == 1) 
                System.out.print(arr[i] + " "); 
            else
                count[arr[i]]++; 
        } 
    } 
    public static void main(String[] args) {
    
    //    int[] list = { 1, 2, 3, 1, 3, 6, 6 };
        int[] list2 = {4, 2, 4, 5, 2, 3, 1}; 

    //    duplicate.printRepeating(list);
        DuplicateFindAll.printRepeating2(list2);
    }
}