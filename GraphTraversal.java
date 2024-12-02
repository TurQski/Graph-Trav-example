import java.util.*;

class Graph {
    private final Map<String, List<String>> adjacencyList = new HashMap<>();

    // Add a vertex to the graph
    public void addVertex(String vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    // Add an edge to the graph
    public void addEdge(String from, String to) {
        adjacencyList.get(from).add(to);
    }

    // Breadth-First Search (BFS)
    public List<String> breadthFirstTraversal(String startVertex) {
        List<String> visitedOrder = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(startVertex);
        visited.add(startVertex);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            visitedOrder.add(current);

            for (String neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return visitedOrder;
    }

    // Depth-First Search (DFS) using a Stack
    public List<String> depthFirstTraversal(String startVertex) {
        List<String> visitedOrder = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();

        stack.push(startVertex);
        visited.add(startVertex);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            visitedOrder.add(current);

            for (String neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return visitedOrder;
    }

    // Build BFS Tree
    public Map<String, String> buildBFSTree(String startVertex) {
        Map<String, String> parentMap = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(startVertex);
        visited.add(startVertex);
        parentMap.put(startVertex, null); // Root has no parent

        while (!queue.isEmpty()) {
            String current = queue.poll();

            for (String neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }
        return parentMap;
    }

    // Build DFS Tree
    public Map<String, String> buildDFSTree(String startVertex) {
        Map<String, String> parentMap = new HashMap<>();
        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();

        stack.push(startVertex);
        visited.add(startVertex);
        parentMap.put(startVertex, null); // Root has no parent

        while (!stack.isEmpty()) {
            String current = stack.pop();

            for (String neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }
        return parentMap;
    }

    // Print tree from parent map
    public void printTree(Map<String, String> parentMap) {
        System.out.println("Tree Structure:");
        for (Map.Entry<String, String> entry : parentMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

public class GraphTraversal {
    public static void main(String[] args) {
        Graph graph = new Graph();

        // Add vertices
        String[] vertices = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        for (String vertex : vertices) {
            graph.addVertex(vertex);
        }

        // Add edges
        graph.addEdge("A", "B");
        graph.addEdge("A", "D");
        graph.addEdge("A", "E");
        graph.addEdge("B", "E");
        graph.addEdge("D", "G");
        graph.addEdge("E", "F");
        graph.addEdge("E", "H");
        graph.addEdge("G", "H");
        graph.addEdge("F", "C");
        graph.addEdge("F", "H");
        graph.addEdge("H", "I");
        graph.addEdge("C", "B");
        graph.addEdge("I", "F");

        // Perform BFS
        System.out.println("Breadth-First Traversal:");
        System.out.println(graph.breadthFirstTraversal("A"));

        // Perform DFS
        System.out.println("Depth-First Traversal:");
        System.out.println(graph.depthFirstTraversal("A"));

        // Build and print BFS Tree
        System.out.println("\nBFS Tree:");
        Map<String, String> bfsTree = graph.buildBFSTree("A");
        graph.printTree(bfsTree);

        // Build and print DFS Tree
        System.out.println("\nDFS Tree:");
        Map<String, String> dfsTree = graph.buildDFSTree("A");
        graph.printTree(dfsTree);
    }
}
