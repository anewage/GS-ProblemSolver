package searchers;

import resources.Action;
import resources.Problem;
import resources.Node;
import utilities.GSException;

import java.util.PriorityQueue;
import java.util.Vector;

public abstract class Searcher {

    /**
     * The problem to be searched and solved
     */
    protected  Problem problem;

    /**
     * The main queue from which the nodes will be checked.
     */
    protected Object frontier;

    /**
     * Explored set used on graphs.
     */
    protected Vector<Node> explored;

    /**
     * Performance measurement values of the search algorithm.
     */
    public int visitedNodesCount;
    public int expandedNodesCount;
    public int maxNodeCountInMemory;

    /**
     * Constructor method. the frontier must be set at the end of constructing this object.
     *
     * @param problem {@link Problem} to be searched.
     */
    public Searcher(Problem problem){
        this.problem = problem;
        visitedNodesCount = 0;
        expandedNodesCount = 0;
        maxNodeCountInMemory = 0;
    }

    /**
     * The actuator function and main responsibility of this class.
     *
     * @return Null if no solution found | {@link Node} containing a solution.
     * @throws GSException if the frontier is not initialized.
     */
    public abstract Node search() throws GSException, InterruptedException;

    @Override
    public String toString() {
        return "#Visited Nodes: " + visitedNodesCount + ", #Expanded Nodes: " + expandedNodesCount + ", #Maximum Memory Cells Used: " + maxNodeCountInMemory;
    }

    /**
     * Building the child node and the path from parent to it.
     *
     * @param parent {@link Node} the father!
     * @param a {@link Action} the action which lead a step from father to this node.
     * @return {@link Node} the child!
     */
    protected Node childNode(Node parent, Action a){
        return new Node(parent, problem.result(parent.getState(), a), a,
                parent.getPathCost() + problem.actionCost(parent.getState(), a));
    }

    protected Node rootNode(){
        return new Node(null, problem.initialState(), null,0);
    }
}
