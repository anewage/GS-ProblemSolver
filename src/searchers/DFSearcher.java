package searchers;

import resources.Action;
import resources.Node;
import resources.Problem;

import utilities.GSException;


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
     * Constructor method. the frontier must be set at the end of constructing this object.
     *
     * @param problem {@link Problem} to be searched.
     */
    public DFSearcher(Problem problem) {
        super(problem);
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
                Node res = recursiveSearch(rootNode());
                if (res != null)
                    return res;
            }
        else{
            Node res = recursiveSearch(rootNode());
            if (res != null)
                return res;
        }
        return null;
    }

    private Node recursiveSearch(Node root){
        explored.add(root);
        if (depthLimit != 0 &&  root.getDepth() > depthLimit)
            return null;
        if (problem.goalTest(root.getState()))
            return root;
        for (Action a : problem.actions(root.getState())){
            Node child = childNode(root, a);
            if (!explored.contains(child)){
                Node res = recursiveSearch(child);
                if (res != null)
                    return res;
            }
        }
        // Reverting everything back to normal!
//        for (Action a : problem.actions(root.getState())){
//            Node n = childNode(root,a);
//            if (n.getParent() != null)
//                if (n.getParent().equals(root)){
//                    n.setTraversed(false);
//                    n.setExplored(false);
//            }
//        }
        return null;
    }
}
