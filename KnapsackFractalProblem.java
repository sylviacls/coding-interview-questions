import java.util.Arrays;
import org.junit.*;

/**
 * Given weights and values of n items, we need to put these items in a knapsack of capacity
 *  W to get the maximum total value in the knapsack.
 * 
 * In the 0-1 Knapsack problem, we are not allowed to break items. 
 * We either take the whole item or don’t take it.
 *
 */
public class KnapsackFractalProblem {

    /**
     * Approach: Greedy Algorithm
     * 
     * Order (desc) the elements by ratio profit/weight and take the first largest until capacity.
     * was reached,  then take the correct fraction.
     * 
     * Time Complexity: O(N log N), as main time taking step is sorting
     * Space Complexity:
     * 
     * @param profits
     * @param weights
     * @param capacity
     * @return
     */
    public static int knapsack(int[] profits, int[] weights, int capacity){
        ItemRatio[] items = new ItemRatio[profits.length];

        for (int i = 0; i < items.length; i++) {
            items[i] = new ItemRatio(i, profits[i], weights[i]);
        }

        Arrays.sort(items, (e1,e2) -> Double.compare(e2.ratio, e1.ratio));
        int result = 0;
        for (ItemRatio item : items) {
            // this weight can be picked while 
            if(item.weight <= capacity) {
                result += item.profit;
                capacity = capacity - item.weight;
                System.out.println("Weight " + item.weight + " , " + "Profit " + item.profit);
            } else  { // item cant be picked whole 
                double weightFract = (double)capacity/ (double)item.weight;
                double profitFract = weightFract* item.profit;
                result += profitFract;
                break;
            }
        }
        return result;
    }
    
 
    @Test
    public void validate() { 
        int[] wt = {10, 40, 20, 30}; 
        int[] val = {60, 40, 100, 120}; 
        int capacity = 50; 
  
        Assert.assertEquals(240, knapsack(val, wt, capacity)); 
    } 
}

class ItemRatio {
     int index;
     int profit;
     int weight;
     double ratio;

     public ItemRatio(int index, int profit, int weight) {
         this.index = index;
         this.profit = profit;
         this.weight = weight;
         this.ratio = profit/weight;
     }
}