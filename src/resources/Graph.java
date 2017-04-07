package resources;

import searchers.BFSearcher;
import searchers.DFSearcher;

public class Graph {

    /**
     * The start node (initial node)
     */
    private Node start;

    private Node[] nodes;
    
    private BFSearcher bfs;
    private DFSearcher dfs;

    private int [][] adjMatrix;

    public Graph(Node p) {
        this.start = p;
    }

    public Graph(int nodeCount) {
        adjMatrix = new int[nodeCount][nodeCount];
        nodes = new Node[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            nodes[i] = new Node(i);
            for (int j = 0; j < nodeCount; j++) {
                adjMatrix[i][j] = 0;
            }
        }
    }

    public Graph(int [][] adjMatrix){
        this.adjMatrix = adjMatrix;
    }

    public void addEdge(int src, int dst, int weight, boolean directed) throws GSException {
        if (src > adjMatrix.length || dst > adjMatrix.length)
            throw new GSException("Index out of bound!");
        adjMatrix[src][dst] = weight;
        if (!directed)
            adjMatrix[dst][src] = weight;
    }

    public int [][] getAdjMatrix(){
        return adjMatrix;
    }

    public Node[] getNodes() {
        return nodes;
    }

    public void setAdjMatrix(int[][] adjMatrix) {
        this.adjMatrix = adjMatrix;
    }

    public void setTraversed(boolean flag){
        for (Node n : nodes) {
            n.setTraversed(flag);
        }
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }
}