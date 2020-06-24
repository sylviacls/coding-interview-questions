public class SmallestSubArrayWithGivenSum {
    
    
    //    Input: [2, 1, 5, 2, 3, 2], S=7 
    //    Output: 2
    public static int smallestSubArrayWithGivenSum(int[] input, int target) {

        int minLength = Integer.MAX_VALUE;
        int sum = 0;
        int start = 0;
        for (int end = 0; end < input.length; end++) {
            sum += input[end];
            while(sum >= target) {
                int lengthTemp = end-start+1;
                minLength = Math.min(minLength, lengthTemp);
                sum -= input[start];
                start++;
            }
        }
        return minLength == Integer.MIN_VALUE? 0 : minLength;
    }

    public static void main(String[] args) {
        int[] list = {2, 1, 5, 2, 3, 2};
        int[] list2 = {2, 1, 5, 2, 8};
        int[] list3 = {3, 4, 1, 1, 6};

        System.out.println(smallestSubArrayWithGivenSum(list, 7));
        System.out.println(smallestSubArrayWithGivenSum(list2, 7));
        System.out.println(smallestSubArrayWithGivenSum(list3, 8));
    }
}