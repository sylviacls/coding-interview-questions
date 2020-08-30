import java.util.*;

import org.junit.*;

/**
 * We are given a list of Jobs. Each job has a Start time, an End time, and a CPU load 
 * when it is running. Our goal is to find the maximum CPU load at any time if all the 
 * jobs are running on the same machine.
 * 
 * Example 1:
 * Jobs: [[1,4,3], [2,5,4], [7,9,6]]
 * Output: 7
 * Explanation: Since [1,4,3] and [2,5,4] overlap, their maximum CPU load (3+4=7) will 
 * be when both the jobs are running at the same time i.e., during the time interval (2,4).
 * 
 * Example 2: 
 * Jobs: [[6,7,10], [2,4,11], [8,12,15]]
 * Output: 15
 * Explanation: None of the jobs overlap, therefore we will take the maximum load of any
 * job which is 15.
 */
public class IntervalsMaximumCPULoad {

    /**
     * The idea is to maintain min-heap for the jobs on the basis of their end times. 
     * Then, for each instance find the jobs which are complete and remove them from the
     *  Min-heap. That is, Check that the end-time of the jobs in the min-heap had ended 
     * before the start time of the current job. Also at each instance, find the maximum 
     * CPU Load on the machine by taking the sum of all the jobs that are present in the
     * min-heap.
     * 
     * Time Complexity: O(N log N): Sorting (N Log N) + pooll/offer heap (Log N)
     * Space Complexity: O(N)
     */
    public static int findMaxCPULoad(List<Job> jobs) {
        //base cases
        if(jobs == null || jobs.size() == 0) return 0;
        if(jobs.size() == 1) return jobs.get(0).cpuLoad;

        Collections.sort(jobs, (j1,j2) -> Integer.compare(j1.start, j2.start));

        //to keep track of the ending-time of jobs
        PriorityQueue<Job> heap = new PriorityQueue<Job>((j1, j2) -> 
                                Integer.compare(j1.end, j2.end));

        int maxCPULoad = 0;
        int currentCPULoad = 0;
        for (Job job : jobs) {
            // remove all jobs that have ended
            while(!heap.isEmpty() && job.start > heap.peek().end) {
                currentCPULoad -= heap.poll().cpuLoad;
            }
            //add current job at the cpu
            heap.add(job);
            currentCPULoad += job.cpuLoad;
            maxCPULoad = Math.max(maxCPULoad, currentCPULoad);
        }
        return maxCPULoad;
    }

    @Test
    public void validate() {
        List<Job> input = new ArrayList<Job>(Arrays.asList(new Job(1, 4, 3), new Job(2, 5, 4), new Job(7, 9, 6)));
        Assert.assertEquals(7, findMaxCPULoad(input));
    
        input = new ArrayList<Job>(Arrays.asList(new Job(6, 7, 10), new Job(2, 4, 11), new Job(8, 12, 15)));
        Assert.assertEquals(15,  findMaxCPULoad(input));
    
        input = new ArrayList<Job>(Arrays.asList(new Job(1, 4, 2), new Job(2, 4, 1), new Job(3, 6, 5)));
        Assert.assertEquals(8, findMaxCPULoad(input));
      }
}

class Job {
    int start;
    int end;
    int cpuLoad;
  
    public Job(int start, int end, int cpuLoad) {
      this.start = start;
      this.end = end;
      this.cpuLoad = cpuLoad;
    }
};