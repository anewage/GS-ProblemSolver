package searchers;

import main.Problem;
import resources.Node;
import resources.GSException;

public abstract class Searcher {

    /**
     * The node which the search is being started on it
     */
    protected Node startNode;

    /**
     * The problem to be searched and solved
     */
    protected Problem problem;

    public Searcher(Problem problem){
        this.problem = problem;
        this.startNode = problem.getInitialState();
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public abstract Node search() throws GSException, InterruptedException;

    protected abstract boolean verifyNode(Node n, Node parent);
}
