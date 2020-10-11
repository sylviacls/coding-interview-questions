import org.junit.*;

/**
 * Given the weights and profits of ‘N’ items, we are asked to put these items in a knapsack 
 * which has a capacity ‘C’. 
 * The goal is to get the maximum profit out of the items in the knapsack. 
 * Each item can only be selected once, as we don’t have multiple quantities of any item.
 * 
 * Items: { Apple, Orange, Banana, Melon }
 * Weights: { 2, 3, 1, 4 }
 * Profits: { 4, 5, 3, 7 }
 * Knapsack capacity: 5
 * 
 * Apple + Orange (total weight 5) => 9 profit
 * Apple + Banana (total weight 3) => 7 profit
 * Orange + Banana (total weight 4) => 8 profit
 * Best profit: Banana + Melon (total weight 5) => 10 profit
 */
public class KnapsackProblem {

    /**
     * Approach: Brute-Force with recursion
     * 
     * A simple solution is to consider all subsets of items and calculate the total
     * weight and value of all subsets. Consider the only subsets whose total weight
     * is smaller than capacity. From all such subsets, pick the maximum value
     * subset.
     * 
     * To consider all subsets of items, there can be two cases for every item: Case
     * 1: The item is included in the optimal subset. Case 2: The item is not
     * included in the optimal set.
     * 
     * Therefore, the maximum value that can be obtained from ‘n’ items is the max
     * of the following two values.
     * 
     * Time Complexity: O(2^N) Where ‘n’ represents the total number of items Space
     * Complexity: O(N) space used by recursion stack
     * 
     * !!! It can result in erros since it generates too many recursive calls
     * 
     * @param profits
     * @param weights
     * @param capacity
     * @return the max profit
     */
    public static int knapsackRecursive(int[] profits, int[] weights, int capacity) {
        return knapsackHelper(weights, profits, capacity, 0);
    }

    private static int knapsackHelper(int[] profits, int[] weights,int capacity, int currentIndex) {
        // base checks
        if(capacity <= 0 || currentIndex >= profits.length) return 0;

        // recursive call after choosing the element at the currentIndex
        // if the weight of the element at currentIndex exceeds the capacity, we shouldn't process this
        int profitWithCurrentIndex = 0;
        if(weights[currentIndex] <= capacity) {
            profitWithCurrentIndex = profits[currentIndex] + knapsackHelper(weights, profits, 
                    capacity - weights[currentIndex], currentIndex+1);
        }

        // recursive call after excluding the element at the currentIndex
        int profitWithoutCurrentIndex = 0;
        profitWithoutCurrentIndex = knapsackHelper(weights, profits, capacity, currentIndex+1);

        //choosing the max profit branch
        return Math.max(profitWithCurrentIndex, profitWithoutCurrentIndex);
    }

    /**
     * Approach: Top-down Dynamic Programming with Memoization
     * 
     * This method is basically an extension to the recursive approach so that we can overcome 
     * the problem of calculating redundant cases and thus increased complexity.
     * 
     * Memoization is when we store the results of all the previously solved sub-problems and 
     * return the results from memory if we encounter a problem that has already been solved.
     * 
     * Since we have two changing values (capacity and currentIndex) in our recursive function 
     * knapsackRecursive(), we can use a two-dimensional array to store the results of all the 
     * solved sub-problems. 
     * we need to store results for every sub-array (i.e. for every possible index ‘i’) 
     * and for every possible capacity ‘c’.
     * 
     * Time Complexity: O(N*C) Numbers of items, C capacity:
     *                  Since our memoization array cache[profits.length][capacity+1] stores the 
     *                  results for all subproblems, we can conclude that we will not have more 
     *                  than N*C subproblems
     * Space Complexity: O(N*C), N*C for the cache array + N for recursion stach, which is
     *                   asymptotically equivalent to O(N∗C)
     * 
     * !!! In some cases it can result in erros since it generates too many recursive calls
     * @param profits
     * @param weights
     * @param capacity
     * @return
     */
    public static int knapsackRecursiveMemoi(int[] profits, int[] weights, int capacity) {
        Integer[][] cache = new Integer[profits.length][capacity+1];
        return knapsackHelperMemoi(cache,profits, weights, capacity, 0);
    }
    
    private static int knapsackHelperMemoi(Integer[][] cache,  int[] profits, int[] weights, int capacity, int currentIndex) {
        //base cases
        if(currentIndex >= profits.length || capacity <= 0) return 0;

        // if we have already solved a similar problem, return the result from memory
        if(cache[currentIndex][capacity] != null) {
            return cache[currentIndex][capacity];
        }

        // recursive call after choosing the element at the currentIndex
        // if the weight of the element at currentIndex exceeds the capacity, we shouldn't process this
        int profitWithCurrentIndex = 0;
        if(weights[currentIndex] <= capacity){
            profitWithCurrentIndex = profits[currentIndex] + knapsackHelperMemoi(cache, profits, 
                                 weights, capacity - weights[currentIndex], currentIndex + 1);
        }

        // recursive call excluding the element at the currentIndex
        int profitWithoutCurrentIndex = knapsackHelperMemoi(cache, profits, weights, capacity, currentIndex + 1);

        cache[currentIndex][capacity] = Math.max(profitWithCurrentIndex, profitWithoutCurrentIndex);

        return cache[currentIndex][capacity];
    }

    /**
     * Approach: Bottom-Up Dynamic Programming with Tabulation
     * 
     * Essentially, we want to find the maximum profit for every sub-array and for every possible
     * capacity. This means that dp[i][c] will represent the maximum knapsack profit for capacity
     * ‘c’ calculated from the first ‘i’ items.
     * 
     * So, for each item at index ‘i’ (0 <= i < items.length) and capacity ‘c’ (0 <= c <= capacity),
     * we have two options:
     *      Exclude the item at index ‘i’. In this case, we will take whatever profit we get from
     *          the sub-array excluding this item => dp[i-1][c] 
     *      Include the item at index ‘i’ if its weight is not more than the capacity. In this case,
     *           we include its profit plus whatever profit we get from the remaining capacity and
     *           from remaining items => profit[i] + dp[i-1][c-weight[i]]
     * Finally, our optimal solution will be maximum of the above two values.
     * 
     * Time Complexity: O(N*M) (Max weight * Number of items)
     * @param profits
     * @param weights
     * @param capacity
     * @return
     */
    public static int knapsackBottomUP(int[] profits, int[] weights, int capacity) {

        // basic checks
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
            return 0;

        int n = profits.length; //number of items
        // Initialize a table where individual rows represent items
        // and columns represent the weight of the knapsack
        int[][] dp = new int[n][capacity+1]; 

        //if capacity is 0 (dp[i][0]), then we cannot carry any weight, so profit will be 0
        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }

        //for the first item we will take it in capacity position such as weight[0] >= capacity
        //this pre-processing will alow us to start our main loop with index 1 
        for (int c = 0; c <= capacity; c++) {
            if(weights[0] <= c)
                dp[0][c] = profits[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= capacity; j++) {
                //if the item can't be select (based on its weight)
                if(weights[i] > j) {
                    //the profit will be the best we can profit without this element
                    dp[i][j] = dp[i-1][j];
                } else {
                    //if we decide to include the current item, we look one row above BUT shifted 
                    //over by the weight of the current item
                     int included  = profits[i] + dp[i-1][j-weights[i]];

                    //if we decide to exclue the current item, we look one row above
                     int notIncluded = dp[i-1][j];
                     dp[i][j] = Math.max(included, notIncluded);
                }
            }
        }
        printKnapsack(dp, weights, profits, capacity);
        // maximum profit will be at the bottom-right corner.
        return dp[n-1][capacity];
    }
    
    /**
     * Auxiliar method to print all items selected
     * 
     * # How can we find the selected items? #
     * 
     * As we know, the final profit is at the bottom-right corner. Therefore, we
     * will start from there to find the items that will be going into the knapsack.
     * 
     * At every step we had two options: include an item or skip it: 
     * - If we skip an item, we take the profit from the remaining items 
     * (i.e. from the cell right  above it);
     * - If we include the item, then we jump to the remaining profit to find more
     * items.
     * 
     * Thus: 
     * 1) if the two values differ, so the item of current row must have been added, otherwise 
     *  the value wouldn't have changed. 
     * 2) once an item was found, we have to subtracted its weight and "shift" to previous item
     * (row above) - pointed by the current element's diagonal; 
     * and check again if its value differs from the value from above, as step 1)
     * 3) if the two compared values are equal, we don't select the item and we just move up the
     * row not changing the colum if we reach at point where the value is 0 we are done searching the items.
     * 
     * @param dp
     * @param weights
     * @param profits
     */
    private static void printKnapsack(int[][] dp, int[] weights, int[] profits, int capacity) {
        for (int i = dp.length - 1; i > 0; i--) {
            if (dp[i][capacity] != dp[i - 1][capacity]) {
                System.out.println("Weight " + weights[i] + " Profit " + profits[i]);
                capacity = capacity - weights[i];
            }
        }
        if(capacity != 0)
         System.out.println("Weight " + weights[0] + " Profit " + profits[0]);
    }

    @Test
    public void validate() {
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};

        Assert.assertEquals(22, knapsackRecursive(profits, weights, 7));
        Assert.assertEquals(22, knapsackRecursiveMemoi(profits, weights, 7));
        Assert.assertEquals(22, knapsackBottomUP(profits, weights, 7));


        Assert.assertEquals(17,  knapsackRecursive(profits, weights, 6));  
        Assert.assertEquals(17, knapsackRecursiveMemoi(profits, weights, 6));
        Assert.assertEquals(17,  knapsackBottomUP(profits, weights, 6));
    }
}