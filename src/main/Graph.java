package main;

import resources.GSException;
import searchers.BFSSearcher;
import searchers.DFSSearcher;

public class Graph {

    /**
     * The start node (initial node)
     */
    private Node parent;
    
    private BFSSearcher bfs;
    private DFSSearcher dfs;

    public Graph(Node parent) {
        this.parent = parent;
        bfs = new BFSSearcher(parent);
        dfs = new DFSSearcher(parent);
    }

    public boolean BFSTraverse() throws InterruptedException, GSException {
        if (bfs == null)
            bfs = new BFSSearcher(parent);
        if (bfs.getRoot() == null)
            bfs.setRoot(parent);
        return bfs.search();
    }

    public boolean DFSTraverse() throws InterruptedException, GSException {
        if (dfs == null)
            dfs = new DFSSearcher(parent);
        if (dfs.getRoot() == null)
            dfs.setRoot(parent);
        return dfs.search();
    }

}