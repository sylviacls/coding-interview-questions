public class Pow11Count1 {
    public static long pow11Count1(int n) {
        String pow = Double.toString(Math.pow(11, n));
        return pow.chars().filter(c-> c =='1').count();

    }
    
    public static void main(String[] args) {
        System.out.println(Double.toString(Math.pow(11, 0)));
        System.out.println(pow11Count1(0));

    }
}