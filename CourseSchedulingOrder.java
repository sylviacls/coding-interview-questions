/**
 * There are ‘N’ tasks, labeled from ‘0’ to ‘N-1’. Each task can have some prerequisite tasks 
 * which need to be completed before it can be scheduled. Given the number of tasks and a list
 * of prerequisite pairs, write a method to find the ordering of tasks we should pick to finish
 * all tasks.
 */

import java.util.*;

/**
 * Approach: Using BFS
 * 
 * We will use the topological sorting approach. First we have to find all
 * sources (vertices with 0 in-degrees) Then, we put the sources into a queue
 * and process each source: - removing it from the queue and updating the
 * indegrees for each of their children - if some child reach 0 in-degree, put
 * it in the queue
 * 
 * Time Complexity: O(V+E) here ‘V’ is the total number of tasks and ‘E’ is the
 * total number of prerequisites. Space Complexity: O(V+E) since we are storing
 * all of the prerequisites for each task in an adjacency list.
 * 
 */
public class CourseSchedulingOrder {
    public static List<Integer> findOrder(int tasks, int[][] prerequisites) {
        // initializing the graph and inDegrees
        HashMap<Integer, Integer> inDegrees = new HashMap<Integer, Integer>();
        HashMap<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < tasks; i++) {
            graph.put(i, new ArrayList<Integer>());
            inDegrees.put(i, 0);
        }
        // building the graph
        for (int i = 0; i < prerequisites.length; i++) {
            int parent = prerequisites[i][0];
            int child = prerequisites[i][1];
            graph.get(parent).add(child);
            inDegrees.put(child, inDegrees.get(child) + 1);
        }
        // finding sources
        Queue<Integer> sources = new LinkedList<Integer>();
        for (int i = 0; i < tasks; i++) {
            if (inDegrees.get(i) == 0)
                sources.add(i);
        }

        List<Integer> topologicalSort = new ArrayList<Integer>();
        while (!sources.isEmpty()) {
            int vertex = sources.poll();
            topologicalSort.add(vertex);
            List<Integer> children = graph.get(vertex);
            for (Integer child : children) {
                inDegrees.put(child, inDegrees.get(child) - 1);
                if (inDegrees.get(child) == 0) {
                    sources.add(child);
                }
            }
        }
        if(topologicalSort.size() != tasks) return new ArrayList<>();

        return topologicalSort;
      }
    
      public static void main(String[] args) {
        List<Integer> result = findOrder(3, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } });
        System.out.println(result);
    
        result = findOrder(3,
            new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 2, 0 } });
        System.out.println(result);
    
        result = findOrder(6, new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 }, new int[] { 0, 4 },
            new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } });
        System.out.println(result);
      }
}