package resources;

import searchers.BFSearcher;
import searchers.DFSearcher;

public class Graph {

    /**
     * The start node (initial node)
     */
    private Node parent;
    
    private BFSearcher bfs;
    private DFSearcher dfs;

    public Graph(Node parent) {
        this.parent = parent;
        bfs = new BFSearcher(parent);
        dfs = new DFSearcher(parent);
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

    public boolean BFSTraverse() throws InterruptedException, GSException {
        if (bfs == null)
            bfs = new BFSearcher(parent);
        if (bfs.getRoot() == null)
            bfs.setRoot(parent);
        return bfs.search();
    }

    public boolean DFSTraverse(int depthLimit, boolean iterative) throws InterruptedException, GSException {
        if (dfs == null)
            dfs = new DFSearcher(parent);
        if (dfs.getRoot() == null)
            dfs.setRoot(parent);
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