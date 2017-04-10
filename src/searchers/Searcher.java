package searchers;

import resources.Action;
import resources.Problem;
import resources.Node;
import resources.GSException;

import java.util.PriorityQueue;
import java.util.Vector;

public abstract class Searcher {

    /**
     * The problem to be searched and solved
     */
    private  Problem problem;

    /**
     * The main queue from which the nodes will be checked.
     */
    private PriorityQueue<Node> frontier;

    /**
     * Explored set used on graphs.
     */
    private Vector<Node> explored;

    /**
     * Constructor method. the frontier must be passed along
     *
     * @param problem {@link Problem} to be searched.
     * @param frontier {@link PriorityQueue} in which the nodes are being kept.
     */
    public Searcher(Problem problem, PriorityQueue<Node> frontier){
        this.problem = problem;
        this.frontier = frontier;
    }

    /**
     * The actuator function and main responsibility of this class.
     *
     * @return Null if no solution found | {@link Node} containing a solution.
     * @throws GSException if the frontier is not initialized.
     */
    public Node search() throws GSException{
        // Initializations
        initializeFrontier();
        explored = new Vector<>();

        // Looping
        while (!frontier.isEmpty()){
            Node leaf = frontier.remove();

            // Goal test
            if (problem.goalTest(leaf.getState()))
                return leaf;

            // Leaf is not a goal. Then it must be expanded. Making sure not to visit this node again!
            explored.add(leaf);

            // Expanding the leaf node
            Vector<Action> actions = problem.actions(leaf.getState());
            for (Action a : actions) {
                Node child = childNode(leaf, a);
                if (!frontier.contains(child) || !explored.contains(leaf))
                    frontier.add(child);

            }

        }
        return null;
    }

    /**
     * Add the root node containing the initial state of the problem to the frontier.
     *
     * @throws GSException if the frontier is set to null.
     */
    private void initializeFrontier() throws GSException {
        if (this.frontier == null)
            throw new GSException("Frontier for the searcher method is null!");
        Node root = new Node(null, problem.initialState(), null,0);
        frontier.add(root);
    }

    /**
     * Building the child node and the path from parent to it.
     *
     * @param parent {@link Node} the father!
     * @param a {@link Action} the action which lead a step from father to this node.
     * @return {@link Node} the child!
     */
    private Node childNode(Node parent, Action a){
        return new Node(parent, problem.result(parent.getState(), a), a,
                parent.getPathCost() + problem.actionCost(parent.getState(), a));
    }
}
