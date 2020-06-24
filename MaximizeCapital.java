import java.util.Collections;
import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given a set of investment projects with their respective profits, 
 * we need to find the most profitable projects. We are given an initial capital 
 * and are allowed to invest only in a fixed number of projects. 
 * Our goal is to choose projects that give us the maximum profit. 
 * 
 * We can start an investment project only when we have the required capital. 
 * Once a project is selected, we can assume that its profit has become our capital. 
 * 
 * Example 1: 
 * Input: Project Capitals=[0,1,2], Project Profits=[1,2,3], Initial Capital=1, 
 * Number of Projects=2
 * 
 * Output: 6 
 * 
 * Explanation: 
 *  With initial capital of ‘1’, we will start the second project which will give 
 * us profit of ‘2’. Once we selected our first project, our total capital will become 
 * 3 (profit + initial capital). With ‘3’ capital, we will select the third project, 
 * which will give us ‘3’ profit. After the completion of the two projects, 
 * our total capital will be 6 (1+2+3).
 * 
 * Time complexity: O (NlogN + KlogN): Since, at the most, all the projects will be pushed 
 *                  to both the heaps once, the time complexity of our algorithm is 
 *                  O(NlogN + KlogN) where ‘N’ is the total number of
 *                  projects and ‘K’ is the number of projects we are selecting.
 */
public class MaximizeCapital {

    /**
     * Here are the steps of our algorithm: 
     * 1)  Add all project capitals to a min-heap, so that we can select a project with 
     *     the smallest capital requirement.
     * 2)  Go through the top projects of the min-heap and filter the projects that can be completed 
     *     within our available capital. Insert the profits of all these projects into a max-heap, 
     *     so that we can choose a project with the maximum profit.
     * 3)  Finally, select the top project of the max-heap for investment.
     *     Repeat the 2nd and 3rd steps for the required number of projects.
     */
    public static int findMaximumCapital(int[] capital, int[] profits, int numberOfProjects, int initialCapital) {
        PriorityQueue<Integer> capitalProjects = new PriorityQueue<Integer>();
        PriorityQueue<Integer> profitProjects = new PriorityQueue<Integer>(Collections.reverseOrder());

        // 1)  Adding all project capitals to a min-heap
        for (int i : capital) {
            capitalProjects.offer(i);
        }
        //Filtering the projects that can be completed withing our avaiable capital
        int availableCapital = initialCapital;
        while (numberOfProjects > 0) {
          while(!capitalProjects.isEmpty() && availableCapital >= capitalProjects.peek()) {
                profitProjects.offer(profits[capitalProjects.poll()]);
          }
          // terminate if we are not able to find any project that can be completed within the available capital
          if(profitProjects.isEmpty()) {
            break;
          } 
           // select the project with the maximum profit
          availableCapital += profitProjects.poll();
          numberOfProjects--;
        } 
        return availableCapital;
      }
    
      @Test
      public void validate() {
        int result = MaximizeCapital.findMaximumCapital(new int[] { 0, 1, 2 }, new int[] { 1, 2, 3 }, 2, 1);
        Assert.assertEquals(6, result);
        result = MaximizeCapital.findMaximumCapital(new int[] { 0, 1, 2, 3 }, new int[] { 1, 2, 3, 5 }, 3, 0);
        Assert.assertEquals(8, result);
      }
    
}