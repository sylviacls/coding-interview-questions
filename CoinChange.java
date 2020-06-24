import java.util.Arrays;

public class CoinChange {

    public static int coinChange(int[] coins, int amount) {
        /* Order the array
        * Set a "count" variable
        * Check backwards (the greatest first) each coin value,
        * Use % modulus to cheack if it's divisible and store the remainder value
        * If it's divisble, store in count the amount of such coin
        * Repeat
        * Input: coins = [1, 2, 5], amount = 11
        * Output: 3 
        */
        Arrays.sort(coins);
        int count = 0;
        int rest = amount;
        
        for(int i = coins.length - 1; i >=0; i--) {
            if (rest/coins[i] > 0) {
                count = count + (rest/coins[i]);
                rest = rest % coins[i];
            }
        }
       if (count == 0) {
           return -1;
       } 
       return count;
    }

    public static void main(String[] args) {

        int[] coins = {186,419,83,408};
        System.out.println(coinChange(coins,6249));
    }
}