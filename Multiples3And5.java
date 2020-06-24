public class Multiples3And5 {
    
    public static int multiples3And5(int below) {
        int result = 0;
        for (int i = 1; i < below; i++) {
            if(i%3 == 0 || i%5 == 0) {
                result += i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
       System.out.println(multiples3And5(1000)); 
    }
}