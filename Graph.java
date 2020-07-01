import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * A implementation of Graph that uses Map from Java Collections to define the
 * adjacency list.
 * 
 * @param <T> Graph's elements type
 */
public class Graph<T> {
    private Map<Vertex<T>, List<Vertex<T>>> graph;
    private int preCount; //used for store the discover time at findArticulation() function

    public Graph() {
        this.graph = new HashMap<Vertex<T>, List<Vertex<T>>>();
    }

    /**
     * Perform DFS
     * @param start
     */
    public void depthFirstTraversal(T start) {
        Stack<Vertex<T>> stack = new Stack<Vertex<T>>();
        Set<Vertex<T>> visited = new HashSet<Vertex<T>>();

        // add start to the search
        stack.push(new Vertex<T>(start));

        while (!stack.empty()) {
            // pull a node
            Vertex<T> current = stack.pop();
            // process if not seen
            if (!visited.contains(current)) {
                visited.add(current);
                System.out.println(current.toString());
            }
            for (Vertex<T> adjVertex : getAdjVertices(current.value)) {
                if (!visited.contains(adjVertex)) {
                    stack.push(adjVertex);
                }
            }
        }
    }

    /**
     * Drive-Method: Perform DFS-Recursive
     * @param start
     */
    public void depthFirstTraversalRecursive(T start) {
        Set<Vertex<T>> visitedRecursive = new HashSet<Vertex<T>>();
        dfsRecursive(start, visitedRecursive);
    }

    /**
     * Perform DFS recursively
     * @param start
     * @param visitedRecursive
     */
    public void dfsRecursive(T start, Set<Vertex<T>> visitedRecursive) {
        Vertex<T> current = new Vertex<T>(start);

        if (visitedRecursive.contains(current))
            return;

        visitedRecursive.add(current);
        System.out.println(current.toString());

        for (Vertex<T> adVertex : getAdjVertices(start)) {
            dfsRecursive(adVertex.value, visitedRecursive);
        }
    }

    /**
     * Perform BFS
     * @param start
     */
    public void breadthFirstTraversal(T start) {
        Deque<Vertex<T>> queue = new LinkedList<Vertex<T>>();
        Set<Vertex<T>> visited = new HashSet<Vertex<T>>();

        queue.add(new Vertex<T>(start));

        while (!queue.isEmpty()) {

            Vertex<T> current = queue.poll();
            if (!visited.contains(current)) {
                visited.add(current);
                System.out.println(current.toString());
            }

            for (Vertex<T> adjVertex : getAdjVertices(current.value)) {
                if (!visited.contains(adjVertex)) {
                    queue.add(adjVertex);
                }
            }
        }
    }

    /**
     * Given an undirected graph, print all connected components line by line.
     * Drive-Method
     */
    public void connectedComponents() {
        Set<Vertex<T>> visited = new HashSet<Vertex<T>>();

        for (Vertex<T> vertex : graph.keySet()) {
            if (!visited.contains(vertex)) {
                dfsConnectedComponents(vertex, visited);
                System.out.println();
            }
        }
    }

    /**
     * It uses BFS approach
     * @param vertex
     * @param visited
     */
    private void dfsConnectedComponents(Vertex<T> vertex, Set<Vertex<T>> visited) {
        visited.add(vertex);
        System.out.print(vertex.value + " ");
        for (Vertex<T> adVertex : getAdjVertices(vertex.value)) {
            if (!visited.contains(adVertex)) {
                dfsConnectedComponents(adVertex, visited);
            }
        }
    }

    /**
     * Given a graph and a source vertex in the graph, find shortest paths from
     * source to all vertices in the given graph.
     * 
     * @param node1 source
     * @param node2 destinatation
     */
    public List<String> shortestPathBFS(T node1, T node2) {
        Deque<Vertex<T>> queue = new LinkedList<Vertex<T>>();
        Map<Vertex<T>, Vertex<T>> parents = new HashMap<Vertex<T>, Vertex<T>>();

        Vertex<T> endNode = new Vertex<T>(node2);

        Vertex<T> startNode = new Vertex<T>(node1);
        queue.add(startNode);
        parents.put(startNode, null);

        while (!queue.isEmpty()) {
            Vertex<T> current = queue.poll();
            // return if end node is reached
            if (current.equals(endNode)) {
                break;
            }
            for (Vertex<T> adjVertex : getAdjVertices(current.value)) {
                // a vertex has been visited if it has a parent in parents map.
                if (!parents.containsKey(adjVertex)) {
                    queue.add(adjVertex);
                    parents.put(adjVertex, current);
                }
            }
        }
        return getPath(parents, endNode);
    }

    /**
     * DFS-based function to find all articulation points. 
     * It uses recursive function findArticulationDFS() 
     * https://www.geeksforgeeks.org/bridge-in-a-graph/
     * https://codeforces.com/blog/entry/68138
     * https://www.sanfoundry.com/java-program-tarjan-algorithm/
     */
    public void findArticulations(){
        //keeps tract of visited vertices 
        Set<Vertex<T>> visited = new HashSet<Vertex<T>>(); 
        //Stores parent vertices in DFS tree 
        Map<Vertex<T>, Vertex<T>> parent = new HashMap<Vertex<T>, Vertex<T>>();
        //Stores discovery times of visited vertices
        Map<Vertex<T>, Integer> disc = new HashMap<Vertex<T>, Integer>();     
         //Stores the lowest vertex reachable for each vertex
         Map<Vertex<T>, Integer> low = new HashMap<Vertex<T>, Integer>();   
         
         
         for (Vertex<T> vertex : this.graph.keySet()) {
             if(!visited.contains(vertex)) {
                 findArticulationDFS(vertex, visited,disc,low, parent);
             }
         }

    }
    
    private void findArticulationDFS(Vertex<T> u, Set<Vertex<T>> visited, Map<Vertex<T>, Integer> disc,
            Map<Vertex<T>, Integer> low, Map<Vertex<T>, Vertex<T>> parent) {
            //Mark the current node as visited 
            visited.add(u);

            //Initialize discovery time and low value 
            preCount++;  
            disc.put(u, this.preCount);
            low.put(u, this.preCount);

            // Go through all vertices aadjacent to the current vertex
            for (Vertex<T> v : getAdjVertices(u.value)) {
                //if "v" is not visited then make it as child of "u" and then recur for it
                if(!visited.contains(v)) {
                    parent.put(v, u);
                    findArticulationDFS(v, visited, disc, low, parent);
                 
                // check if the subtree rooted with v (child) has a connection 
                //to one of the ancestors of u (parent)
                    low.put(u, Math.min(low.get(u), low.get(v)));

                // If the lowest vertex reachable from subtree under v(child) is below u (parent) 
                // in DFS tree, then u-v is a bridge 
                if (low.get(v) > disc.get(u)) 
                    System.out.println(u+" "+v); 

                } 
                //if v is already visited and its not a parent of u, then there is a back-edge
                // Update low value of u for parent function calls.
                else if (parent.get(u) != null && v.value != parent.get(u).value) {
                    low.put(u, Math.min(low.get(u), disc.get(v))); 
                }
            } 
    }

    /**
     * Test if graph is connected
     * 
     * @return
     */
    public boolean isConnected() {
        /**
         *  Run DFS from any vertex and then check if any vertices not visited
         */
        //TODO 
        return false;
    }

    
    private List<String> getPath(Map<Vertex<T>, Vertex<T>> parents, Vertex<T> endNode) {
        // reconstructing the path
        Vertex<T> current = endNode;
        ArrayList<String> path = new ArrayList<String>();
        while (current != null ) {
            path.add(current.toString());
            current = parents.get(current);
        }
        Collections.reverse(path);

        return path;
    }

    /**
     * Add a vertex to the graph
     * @param vertex
     */
    private void addVertex(Vertex<T> vertex) {
        graph.putIfAbsent(vertex, new ArrayList<Vertex<T>>());
    }

    /**
     * Remove a vertex from the graph
     * @param vertex
     */
    public void removeVertex(T vertex) {
        Vertex<T> v = new Vertex<T>(vertex);
        graph.values().stream().forEach(e -> e.remove(v));
        graph.remove(new Vertex<T>(vertex));
    }

    /**
     * Add an edge between two vertices 
     * @param vertex1
     * @param vertex2
     */
    public void addEdge(T vertex1, T vertex2) {
        Vertex<T> v1 = new Vertex<T>(vertex1);
        Vertex<T> v2 = new Vertex<T>(vertex2);
        addVertex(v1);
        addVertex(v2);
        graph.get(v1).add(v2);
        graph.get(v2).add(v1);
    }

    /**
     * Remove the edge that exists in the vertices
     * @param vertex1
     * @param vertex2
     */
    public void removeEdge(T vertex1, T vertex2) {
        Vertex<T> v1 = new Vertex<T>(vertex1);
        Vertex<T> v2 = new Vertex<T>(vertex2);

        List<Vertex<T>> listV1 = graph.get(v1);
        List<Vertex<T>> listV2 = graph.get(v2);
        if (listV1 != null) {
            listV1.remove(v2);
        }
        if (listV2 != null) {
            listV2.remove(v1);
        }
    }

    /**
     * Return all adjancent vertices of a givn vertex
     * @param vertex
     * @return
     */
    public List<Vertex<T>> getAdjVertices(T vertex) {
        return graph.get(new Vertex<T>(vertex));
    }

    /**
     * Auxiliary print method
     */
    public void print() {
        this.graph.forEach((v, list) -> System.out.println("Vertex " + v.value + " Adjancenties :" + list.toString()));
    }

    public static void main(String[] args) {
        Graph<Integer> g1 = new Graph<Integer>(); 
        g1.addEdge(1, 0); 
        g1.addEdge(0, 2); 
        g1.addEdge(2, 1); 
        g1.addEdge(0, 3); 
        g1.addEdge(3, 4); 
        g1.findArticulations(); 
        System.out.println(); 

        Graph<Integer> g2 = new Graph<Integer>(); 
        g2.addEdge(0, 1); 
        g2.addEdge(1, 2); 
        g2.addEdge(2, 3); 
        g2.findArticulations(); 
        System.out.println(); 

        Graph<Integer> g3 = new Graph<Integer>(); 
        g3.addEdge(0, 1); 
        g3.addEdge(1, 2); 
        g3.addEdge(2, 0); 
        g3.addEdge(1, 3); 
        g3.addEdge(1, 4); 
        g3.addEdge(1, 6); 
        g3.addEdge(3, 5); 
        g3.addEdge(4, 5); 
        g3.findArticulations(); 
    }
}

class Vertex<T> {
    T value;

    Vertex(T value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        @SuppressWarnings("unchecked")
        Vertex<T> other = (Vertex<T>) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

}
