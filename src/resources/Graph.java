package resources;

import main.Problem;
import searchers.BFSearcher;
import searchers.DFSearcher;

public class Graph {

    private Problem problem;

    /**
     * The start node (initial node)
     */
    private Node parent;
    
    private BFSearcher bfs;
    private DFSearcher dfs;

    public Graph(Problem p) {
        this.problem = p;
        this.parent = p.getInitialState();
        bfs = new BFSearcher(p);
        dfs = new DFSearcher(p);
    }

    public Graph(Node p) {
        this.parent = p;
    }

    public static void setTraversed(Node start, boolean flag){
        if (start == null)
            return;
        (new Graph(start)).setTraversed(flag);
    }

    public void setTraversed(boolean flag){
        if (parent == null)
            return;
        setNodeTraversed(parent, flag);
    }

    public int getDepth(){
        return maxDepth(parent);
    }

    public Node BFSTraverse() throws InterruptedException, GSException {
        if (bfs == null)
            bfs = new BFSearcher(problem);
        if (bfs.getStartNode() == null)
            bfs.setStartNode(parent);
        return bfs.search();
    }

    public Node DFSTraverse(int depthLimit, boolean iterative) throws InterruptedException, GSException {
        if (dfs == null)
            dfs = new DFSearcher(problem);
        if (dfs.getStartNode() == null)
            dfs.setStartNode(parent);
        dfs.setDepthLimit(depthLimit);
        dfs.setIterating(iterative);
        if (iterative){
            dfs.setIteratingMaxDepth(getDepth());
        }
        return dfs.search();
    }

    private void setNodeTraversed(Node n, boolean flag){
        for (Node child : n.getNeighbors()) {
            setNodeTraversed(child, flag);
        }
        n.setTraversed(flag);
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