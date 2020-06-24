import java.util.stream.IntStream;

/**
 * For a given natural number greater than zero return:
 * “Fizz” if the number is dividable by 3
 * “Buzz” if the number is dividable by 5
 * “FizzBuzz” if the number is dividable by 15
 * the same number if number is neither divisible by 3 nor 5.
 **/
public class FizzBuzz {

    public static void fizzBuzz() {
        IntStream.rangeClosed(0, 101)
        .mapToObj(
            i-> i% 3 == 0 ? 
               (i % 5 == 0 ? "FizzBuzz": "Fizz"): 
               (i % 5 == 0 ? "Buzz": i))
        .forEach(System.out::println);
    }

    public static void fizzBuzz2() {
        for (int i = 0; i <= 100; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
    }

    public static void fizzBuzz3() {
        for (int i = 1; i <= 100; i++) {
            String num = "";
            if (i % 3 == 0)
                num += "Fizz";
            if (i % 5 == 0)
                num += "Buzz";
            if (num.isEmpty())
                num = Integer.toString(i);
            System.out.println(num);
        }
    }

    public static void main(String[] args) {
        System.out.println("Fizz Buzzing!");
        fizzBuzz();
    }
}