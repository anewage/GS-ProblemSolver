package resources;

import searchers.BFSearcher;
import searchers.DFSearcher;

import java.util.Vector;

public class Graph {

    /**
     * The start node (initial node)
     */
    private Node parent;

    private Node[] nodes;
    
    private BFSearcher bfs;
    private DFSearcher dfs;

    private int [][] adjMatrix;

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

    public Graph(Node p) {
        this.parent = p;
    }

    public void setTraversed(boolean flag){
        for (Node n : nodes) {
            n.setTraversed(flag);
        }
    }

    public int getDepth(){
        return maxDepth(parent);
    }

    private int maxDepth(Node n){
        if (n.getNeighbors().isEmpty())
            return n.getDepth();
        int d = 0;
        for (Node child : n.getNeighbors()) {
            int tmp = maxDepth(child);
            if (tmp > d)
                d = tmp;
        }
        return d;
    }

}