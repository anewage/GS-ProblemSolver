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
        depthLimit = 0;
        iteratingMaxDepth = 0;
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
            for (depthLimit = 1; depthLimit <= iteratingMaxDepth ; depthLimit++){
                Node res = recursiveSearch(startNode, null);
                if (res != null)
                    return res;
                problem.resetGraph();
            }
        else{
            Node res = recursiveSearch(startNode, null);
            if (res != null)
                return res;
        }
        return null;
    }

    @Override
    protected boolean verifyNode(Node n, Node parent) {
        n.setTraversed(true);
        n.setExplored(true);
        System.out.println("DFS TRAVERSED " + (depthLimit != 0 ? "WITH D-Limit of " + depthLimit : "" ) + (iterating ? " and being iterative" : "" )+ ": #" + n.getIndex());
        buildPath(n, parent);
        return problem.goalTest(n);
    }

    private void buildPath(Node n, Node parent){
        n.setParent(parent);
    }

    private Node recursiveSearch(Node root, Node parent){
        if (root.getIndex() == 4)
            System.out.println("Hello");
        buildPath(root, parent);
        int rootDepth = root.getDepth();
        if (depthLimit != 0 &&  rootDepth > depthLimit)
            return null;
        if (verifyNode(root, parent)){
            System.out.println("HOORAY! FOUND: #" + root.getIndex() + root.getPath() );
            return root;
        }
        for (Action a : problem.actions(root)){
            Node n = problem.result(root, a);
            if (!n.isTraversed()){
                Node res = recursiveSearch(n, root);
                if (res != null)
                    return res;
            }
        }

        // Reverting everything back to normal!
        for (Action a : problem.actions(root)){
            Node n = problem.result(root, a);
            if (n.getParent().equals(root)){
                n.setTraversed(false);
                n.setExplored(false);
            }
        }
        return null;
    }
}
