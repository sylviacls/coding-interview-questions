import java.util.*;

/**
 * Leetcode: 133. Clone Graph 
 * https://leetcode.com/problems/clone-graph/
 * 
 * Given a reference of a node in a connected undirected graph. 
 * Return a deep copy (clone) of the graph.
 * 
 * Each node in the graph contains a val (int) and a list (List[Node]) of its
 * neighbors.
 * 
 */
public class GraphClone {

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        
        Map<Node, Node> map = new HashMap<Node, Node>(); // origin node : copied node
        Node myNode = new Node(node.val); // copy
        
        map.put(node, myNode);
        
        Queue<Node> q = new ArrayDeque<Node>(); // origin nodes
        q.add(node);
        
        // bfs traverse graph
        while (!q.isEmpty()) {
            Node cur = q.poll();
            // all neighbors of current origin node
            for (Node neighbor : cur.neighbors) {
                // if the origin node is not visited
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new Node(neighbor.val));
                    // to avoid loop, we just add the unvisited node to queue
                    q.offer(neighbor);
                }
                // add neighbors to the copied node
                // copied node: map.get(cur) -> copied node of cur
                // neighbors: map.get(neighbor) -> copied node of neighbor
                map.get(cur).neighbors.add(map.get(neighbor));
            }
        }
        return myNode;
    }
}

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}