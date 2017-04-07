package searchers;

import resources.*;

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
     * @param p the problem in which we must search for the answer
     */
    public DFSearcher(Problem p) {
        super(p);
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
    public Node search() throws GSException, InterruptedException {
        if (iterating)
            for (depthLimit = 0; depthLimit <= iteratingMaxDepth ; depthLimit++){
                recursiveSearch(startNode, null);
                Graph.setTraversed(startNode, false);
            }
        else
            recursiveSearch(startNode, null);
        return null;
    }

    @Override
    protected boolean verifyNode(Node n, Node parent) {
        n.setTraversed(true);
        n.setExplored(true);
        System.out.println("DFS TRAVERSED WITH D-Limit of " + depthLimit + (iterating ? " and being iterative" : "" )+ ": " + n.getName());
        n.setParent(parent);
        return problem.goalTest(n);
    }

    private void recursiveSearch(Node root, Node parent){
        if (depthLimit != -1 && root.getDepth() > depthLimit)
            return;
        verifyNode(root, parent);
        for (Action a : problem.actions(root)){
            Node n = problem.result(root, a);
            if (!n.isTraversed())
                recursiveSearch(n, root);
        }
    }
}
