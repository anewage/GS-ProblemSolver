package main;

import resources.GSException;
import searchers.BFSSearcher;

public class Graph {

    /**
     * The start node (initial node)
     */
    private Node parent;
    
    private BFSSearcher bfs;

    public Graph(Node parent) {
        this.parent = parent;
        bfs = new BFSSearcher(parent);
    }

    public boolean BFSTravese() throws InterruptedException, GSException {
        if (bfs.getRoot() != null)
            bfs.setRoot(parent);
        return bfs.search();
    }

}