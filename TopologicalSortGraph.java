/**
 * Problem: Given a directed graph, find the topological ordering of its vertices.
 * Topological Sort of a directed graph (a graph with unidirectional edges) is a linear ordering 
 * of its vertices such that for every directed edge (U, V) from vertex U to vertex V, U comes 
 * before V in the ordering.
 * 
 * Topological Sort is used to find a linear ordering of elements that have dependencies on each
 * other. For example, if event ‘B’ is dependent on event ‘A’, ‘A’ comes before ‘B’ in topological
 *  ordering.
 * 
 * This pattern defines an easy way to understand the technique for performing topological sorting
 *  of a set of elements and then solves a few problems using it.
 * 
 * Input: Vertices=4, Edges=[3, 2], [3, 0], [2, 0], [2, 1]
 * Output: Following are the two valid topological sorts for the given graph:
 * 1) 3, 2, 0, 1
 * 2) 3, 2, 1, 0
 * 
 * Input: Vertices=5, Edges=[4, 2], [4, 3], [2, 0], [2, 1], [3, 1]
 * Output: Following are all valid topological sorts for the given graph:
 * 1) 4, 2, 3, 0, 1
 * 2) 4, 3, 2, 0, 1
 * 3) 4, 3, 2, 1, 0
 * 4) 4, 2, 3, 1, 0
 * 5) 4, 2, 0, 3, 1
 * 
 * Here are a few fundamental concepts related to topological sort:
 * 
 * - Source: Any node that has no incoming edge and has only outgoing edges is called a source.
 * - Sink: Any node that has only incoming edges and no outgoing edge is called a sink. 
 *   So, we can say that a topological ordering starts with one of the sources and ends at one of
 *   the sinks.
 * - A topological ordering is possible only when the graph has no directed cycles, i.e. if the 
 * graph is a Directed Acyclic Graph (DAG). If the graph has a cycle, some vertices will have 
 * cyclic dependencies which makes it impossible to find a linear ordering among vertices.
 * 
 */
import java.util.*;

public class TopologicalSortGraph {

    /**
     * Approach: Using BFS
     * 
     * To find the topological sort of a graph we can traverse the graph using Breadth First Search
     * We will start with all the sources, and in a stepwise fashion, save all sources to a sorted list.
     * We will then remove all sources and their edges from the graph. 
     * After the removal of the edges, we will have new sources, so we will repeat the above process
     * until all vertices are visited.
     * 
     * We will store the graph in Adjacency Lists (each parent vertex will have a list containing all 
     * of its children.
     * 
     * To find the sources, we will keep a HashMap to count the in-degrees i.e., count of
     * incoming edges of each vertex. Any vertex with ‘0’ in-degree will be a source.
     * 
     * Time Complexity: O(V+E), each vertex will become a source only once and each edge will be
     *                  accessed and removed once.
     * Space Complexity: O(V+E), we are storing all of the edges for each vertex in an adjacency list. 
     * 
     * @param vertices
     * @param edges
     * @return
     */
    public static List<Integer> sortBFS(int vertices, int[][] edges) {
        List<Integer> sortedOrder = new ArrayList<>();
        //base cases
        if(vertices <= 0 || edges.length == 0) return sortedOrder;

        //Build the graph and find in-degrees of all vertices
        HashMap<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
        HashMap<Integer, Integer> inDegree = new HashMap<Integer, Integer>();

        for (int i = 0; i < vertices; i++) {
            inDegree.put(i, 0);
            graph.put(i, new ArrayList<Integer>());
        }
        for (int i = 0; i < edges.length; i++) {
            int parent = edges[i][0];
            int child = edges[i][1];
            graph.get(parent).add(child); // put the child into it's parent's list
            inDegree.put(child, inDegree.getOrDefault(child, 0)+1); // increment child's inDegree
        }
        // Find all sources (all vertices with 0 in-degrees) and put them into a queue
        Queue<Integer> queue = new LinkedList<Integer>();
        for (Map.Entry<Integer,Integer> entry : inDegree.entrySet()) {
            if(entry.getValue() == 0) queue.add(entry.getKey());
        }
        // For each source, add it to the sortedOrder and subtract one from all of its children's 
        //in-degrees. if a child's in-degree becomes zero, add it to the sources queue
        while(!queue.isEmpty()) {
            Integer currVertex = queue.poll();
            sortedOrder.add(currVertex);
            List<Integer> children = graph.get(currVertex); // get the node's children to decrement their in-degrees
            for (int child : children) {
              inDegree.put(child, inDegree.get(child) - 1);
              if (inDegree.get(child) == 0)
                queue.add(child);
            }
        }
        // topological sort is not possible as the graph has a cycle
        if (sortedOrder.size() != vertices) return new ArrayList<>();
        return sortedOrder;
    }
    
    /**
     * Approach: Using DFS
     * 
     * We can modify DFS to find Topological Sorting of a graph. 
     * In DFS, we start from a vertex, we first print it and then recursively call DFS for its
     * adjacent vertices. 
     * In topological sorting, we use a temporary stack. 
     * We don’t print the vertex immediately, we first recursively call topological sorting for
     * all its adjacent vertices, then push it to a stack. 
     * Finally, print contents of stack. Note that a vertex is pushed to stack only when all 
     * of its adjacent vertices (and their adjacent vertices and so on) are already in stack.
     * 
     * @param vertices
     * @param edges
     * @return
     */
    public static List<Integer> sortDFS(int vertices, int[][] edges) {
        List<Integer> sortedOrder = new ArrayList<>();
        //base cases
        if(vertices <= 0 || edges.length == 0) return sortedOrder;
        
        //building the graph
        HashMap<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < vertices; i++) {
            graph.put(i, new ArrayList<Integer>());
        }
        for (int i = 0; i < edges.length; i++) {
            int parent = edges[i][0];
            int child = edges[i][1];
            graph.get(parent).add(child);
        }
        Set<Integer> visited = new HashSet<Integer>();
        //taking vertex by vertex and calling DFS for its children
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < vertices; i++) {
            if(!visited.contains(i)) {
                sortDFSUtil(graph, i, visited, stack);
            }
        }
        while(!stack.isEmpty()) {
            sortedOrder.add(stack.pop());
        }
        return sortedOrder;
    }
    
    private static void sortDFSUtil(HashMap<Integer, List<Integer>> graph, Integer vertex,
                                 Set<Integer> visited, Stack<Integer> stack) {
        visited.add(vertex);
        List<Integer> children = graph.get(vertex);
        for (Integer child : children) {
            if(!visited.contains(child)) {
                sortDFSUtil(graph, child, visited, stack);
            }
        }
        //after visitings its children, add the current vertex into the stack
        stack.add(vertex);
    }

    public static void main(String[] args) {
        int[][] input = new int[][] { new int[] { 3, 2 }, new int[] { 3, 0 }, new int[] { 2, 0 }, new int[] { 2, 1 } };
        System.out.println(sortBFS(4, input));
        System.out.println(sortDFS(4, input));

        int[][] input2 =  new int[][] { new int[] { 4, 2 }, new int[] { 4, 3 }, new int[] { 2, 0 },new int[] { 2, 1 }, new int[] { 3, 1 } };
        System.out.println(sortBFS(5, input2));
        System.out.println(sortDFS(5, input2));
    
        int[][] input3 = new int[][] { new int[] { 6, 4 }, new int[] { 6, 2 }, new int[] { 5, 3 },
                                    new int[] { 5, 4 }, new int[] { 3, 0 }, new int[] { 3, 1 }, new int[] { 3, 2 }, new int[] { 4, 1 }};
        System.out.println(sortBFS(7, input3));
        System.out.println(sortDFS(7, input3));
    }


}