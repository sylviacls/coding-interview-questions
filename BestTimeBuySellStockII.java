import org.junit.*;

/**
 * Leetcode: 122. Best Time to Buy and Sell Stock II
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 * 
 * Say you have an array prices for which the ith element is the price of a
 * given stock on day i. Design an algorithm to find the maximum profit. You may
 * complete as many transactions as you like (i.e., buy one and sell one share
 * of the stock multiple times).
 * 
 * Note: You may not engage in multiple transactions at the same time (i.e., you
 * must sell the stock before you buy again).
 * 
 * Example 1: Input: [7,1,5,3,6,4] Output: 7 Explanation: Buy on day 2 (price =
 * 1) and sell on day 3 (price = 5), profit = 5-1 = 4. Then buy on day 4 (price
 * = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * 
 * Example 2: Input: [1,2,3,4,5] Output: 4 Explanation: Buy on day 1 (price = 1)
 * and sell on day 5 (price = 5), profit = 5-1 = 4. Note that you cannot buy on
 * day 1, buy on day 2 and sell them later, as you are engaging multiple
 * transactions at the same time. You must sell before buying again.
 */
public class BestTimeBuySellStockII {
 
    /**
     * Approach: Valleys and Peaks
     * 
     * The main idea is to identify the valleys (best time to buy) and peaks (best time to sell)
     * And doing this our profit will be maximized.
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static int maxProfit(int[] prices) {
        
        int maxProfit = 0;
        for (int i = 0; i < prices.length -1; i++) {
            //check if the price of the next day is higher than ith, if so, we buy it at i-th
            //and sell it on i+1 day and add the profit
            if(prices[i+1] > prices[i]) { 
                maxProfit += prices[i+1] - prices[i];
            }
        }
        return maxProfit;
    }
    
    @Test
    public void validate() {
        Assert.assertEquals(7, maxProfit(new int[]{7,1,5,3,6,4}));
        Assert.assertEquals(4, maxProfit(new int[]{1,2,3,4,5}));
        Assert.assertEquals(0, maxProfit(new int[]{7,6,4,3,1}));
    }
}
