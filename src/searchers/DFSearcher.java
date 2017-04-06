package searchers;

import resources.Graph;
import resources.Node;
import resources.GSException;

public class DFSearcher extends Searcher {

    /**
     * Used in Limited DFS.
     * If set to -1 the normal DFS will be performed
     */
    private int depthLimit;

    /**
     * Used in Iterative Deepening DFS.
     * If set to true, the depthLimit value will be ignored
     */
    private boolean iterating;

    /**
     * Used in Iterative Deepening DFS.
     * Only used when iterating flag set to true.
     */
    private int iteratingMaxDepth;

    /**
     * Constructor method. Initializes the searcher to be a normal DFSearcher.
     * @param root the initial state from which the search begins.
     */
    public DFSearcher(Node root) {
        super(root);
        iterating = false;
        depthLimit = -1;
        iteratingMaxDepth = -1;
    }

    public int getDepthLimit() {
        return depthLimit;
    }

    public void setDepthLimit(int depthLimit) {
        this.depthLimit = depthLimit;
    }

    public boolean isIterating() {
        return iterating;
    }

    public void setIterating(boolean iterating) {
        this.iterating = iterating;
    }

    public int getIteratingMaxDepth() {
        return iteratingMaxDepth;
    }

    public void setIteratingMaxDepth(int iteratingMaxDepth) {
        this.iteratingMaxDepth = iteratingMaxDepth;
    }

    @Override
    public boolean search() throws GSException, InterruptedException {
        if (iterating)
            for (depthLimit = 0; depthLimit <= iteratingMaxDepth ; depthLimit++){
                recursiveSearch(root, null);
                Graph.setTraversed(root, false);
            }
        else
            recursiveSearch(root, null);
        return false;
    }

    @Override
    protected void traverseNode(Node n, Node parent) {
        n.setTraversed(true);
        System.out.println("DFS TRAVERSED WITH D-Limit of " + depthLimit + (iterating ? " and being iterative" : "" )+ ": " + n.getName());
        n.setParent(parent);
    }

    private void recursiveSearch(Node root, Node parent){
        if (depthLimit != -1 && root.getDepth() > depthLimit)
            return;
        traverseNode(root, parent);
        for (Node n : root.getNeighbors() ){
            if (!n.isTraversed())
                recursiveSearch(n, root);
        }
    }
}
