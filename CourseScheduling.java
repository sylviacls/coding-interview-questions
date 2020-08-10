/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to first take 
 * course 1, which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you 
 * to finish all courses?
 * 
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true 
 * Explanation: There are a total of 2 courses to take. 
 * To take course 1 you should have finished course 0. So it is possible.
 * 
 * Input: numCourses=3, Prerequisites=[0, 1], [1, 2]
 * Output: true
 * Explanation: To execute task '1', task '0' needs to finish first. Similarly, task '1' needs
 * to finish before '2' can be scheduled. A possible sceduling of tasks is: [0, 1, 2] 
 * 
 * Input: numCourses=3, Prerequisites=[0, 1], [1, 2], [2, 0]
 * Output: false
 * Explanation: The tasks have cyclic dependency, therefore they cannot be sceduled.
 * 
 * This problem is equivalent to detecting a cycle in the directed graph represented by prerequisites. 
 * Both BFS and DFS can be used to solve it using the idea of topological sort.
 */
import java.util.*;
import org.junit.*;

public class CourseScheduling {

    /**
     * Approach: Using DFS
     * 
     * Modifing DFS to check the existence of back-edges.
     * We are going to keep track of vertices that are currently in our recursion stack of DFS
     * traversal.
     * if we reach a vertex that is already in the recursion stack, then we have a cycle
     * 
     * Time Complexity: O(V+E), The above algorithm is simply DFS with an extra stack. 
     *              So time complexity is the same as DFS which is.
     * Space Complexity: O(V) for the stack recursion
     * 
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean isSchedulingPossibleDFS(int numCourses, int[][] prerequisites) {
        //building the graph
        HashMap<Integer,List<Integer>> graph = new HashMap<Integer,List<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<Integer>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            int parent = prerequisites[i][1];
            int child = prerequisites[i][0];
            graph.get(parent).add(child);
        }
        Set<Integer> visited = new HashSet<Integer>();    
        Set<Integer> onPath = new HashSet<Integer>();  
        for (int i = 0; i < numCourses;i++) { //this helps for the case of disconnect graphs
            if(hasCicleDFS(graph, i, visited, onPath)) return false;
        }
        return true;
    }

    /**
     * @param graph
     * @param vertex
     * @param visited
     * @param onPath
     * @return
     */
    private static boolean hasCicleDFS(HashMap<Integer, List<Integer>> graph, int vertex, Set<Integer>
                             visited, Set<Integer> onPath) {
        
        // We've seen the root node before, which means that it must 
        // have appeard as a root node earlier in the current path
        // we're walking in the sub-graph. In other words, there's a cycle.
        //this check must came before the visisted checking
        if(onPath.contains(vertex)) return true;

        //Don't recur for i if already visited 
        if(visited.contains(vertex)) return false;

        visited.add(vertex);
        // Mark the current in onPath. If we see it again before we're done with
        // its sub-graph, we know there's a cycle in its sub-graph.
        onPath.add(vertex);

        List<Integer> children = graph.get(vertex);
        //we do dfs for each children
        for (Integer child : children) {
            //if the curr child is onPath, then there is a cycle
            if (hasCicleDFS(graph, child, visited, onPath)) {
                return true;
            }        
        }
        //when finishing traversing all children of curr vertex we remove it from onPath
        onPath.remove(vertex);     
        //and at this point we haven't had found any cycles    
        return false;
    }

    /**
     * Approach: Using BFS
     * 
     * We will use the topological sorting approach.
     * First we have to find all sources (vertices with 0 in-degrees) 
     * Then, we put the sources into a queue and process each source:
     *   - removing it from the queue and updating the indegrees for each of their children
     *   - if some child reach 0 in-degree, put it in the queue
     * 
     * Time Complexity: O(V+E) here ‘V’ is the total number of tasks and ‘E’ is the total number
     *                   of prerequisites.
     * Space Complexity: O(V+E) since we are storing all of the prerequisites for each task in an 
     *                  adjacency list.
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean isSchedulingPossibleBFS(int numCourses, int[][] prerequisites){
        //initializing the graph and inDegrees
        HashMap<Integer, Integer> inDegrees = new HashMap<Integer, Integer>();      
        HashMap<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<Integer>());
            inDegrees.put(i, 0);
        }
        //building the graph
        for (int i = 0; i < prerequisites.length; i++) {
            int parent = prerequisites[i][1];
            int child = prerequisites[i][0];
            graph.get(parent).add(child);
            inDegrees.put(child, inDegrees.get(child)+1);
        }
        //finding sources
        Queue<Integer> sources = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if(inDegrees.get(i) == 0) sources.add(i);
        }

        List<Integer> topologicalSort = new ArrayList<Integer>();
        while(!sources.isEmpty()) {
            int vertex = sources.poll();
            topologicalSort.add(vertex);
            List<Integer> children = graph.get(vertex);
            for (Integer child : children) {
                inDegrees.put(child, inDegrees.get(child)-1);
                if(inDegrees.get(child) == 0) {
                    sources.add(child);
                }
            }
        }
        // if topologicalSort doesn't contain all tasks, there is a cyclic dependency between tasks, 
        //therefore, we will not be able to schedule all tasks
        return topologicalSort.size() == numCourses;
    }

    @Test
    public void validate() {
        int[][] input1 = new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } };
        Assert.assertTrue(isSchedulingPossibleDFS(3, input1));
        Assert.assertTrue(isSchedulingPossibleBFS(3, input1));

        int[][] input2 = new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 2, 0 } };
        Assert.assertFalse(isSchedulingPossibleDFS(3,input2));
        Assert.assertFalse(isSchedulingPossibleBFS(3,input2));   

        int[][] input3 = new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 },new int[] { 0, 4 }, 
                        new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } };
        Assert.assertTrue(isSchedulingPossibleDFS(6, input3));
        Assert.assertTrue(isSchedulingPossibleBFS(6, input3));
    }
      
}