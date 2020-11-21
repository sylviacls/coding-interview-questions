import org.junit.Assert;
import org.junit.Test;

/**
 * Leetcode: 121. Best Time to Buy and Sell Stock
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * 
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell
 * one share of the stock), design an algorithm to find the maximum profit.
 * 
 * Note that you cannot sell a stock before you buy one.
 * 
 * Example 1:
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *              Not 7-1 = 6, as selling price needs to be larger than buying price.
 * 
 * Example 2:
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class BestTimeBuySellStock {
    /**
     * Approach> Brute-force
     * 
     * Time Complexity: O(N^2)
     * Space Complexity: O(1)
     * 
     * Runtime 264 ms Memory 38.8 MB
     */
    public static int maxProfit(int[] prices) {
        int maxProfit = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            int currProfit = 0;
            for (int j = i; j < prices.length; j++) {
                if(prices[j] > prices[i]) {
                    currProfit = prices[j]-prices[i];
                    maxProfit = Math.max(maxProfit, currProfit);
                }
            }
        }
        return maxProfit == Integer.MIN_VALUE? 0 : maxProfit;
    } 

    /**
     * Approach: One pass
     * 
     * minPrice is the minimum price from day 0 to day i. 
     * currProf is the profit we can get selling at curr day with the minPrice stored.
     * maxProf keeps track of the maxprof so far.
     * 
     * Time Complexity: O(N^2)
     * Space Complexity: O(1)
     * 
     * Runtime 1 ms  Memory 38.6 MB
     */
    public static int maxProfitII(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProf = 0;
        for (int i = 0; i < prices.length; i++) {
            if(prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                int currProf = prices[i]-minPrice;
                maxProf = Math.max(maxProf, currProf);
            }
        }
        return maxProf;

    }
    @Test
    public void validate() {
        Assert.assertEquals(5, maxProfit(new int[]{7,1,5,3,6,4}));
        Assert.assertEquals(0, maxProfit(new int[]{7,6,4,3,1}));
    }
}
