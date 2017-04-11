package searchers;

import resources.Action;
import resources.State;
import sun.misc.Queue;
import utilities.GSException;
import resources.Node;
import resources.Problem;

import java.util.Vector;

/**
 * Bi-directional Searcher.
 */
public class BDSearcher extends Searcher {

    private Vector frontierFromGoal;
    private State goalState;

    /**
     * Constructor method. the frontier must be set at the end of constructing this object.
     *
     * @param problem {@link Problem} to be searched.
     */
    public BDSearcher(Problem problem) {
        super(problem);
        frontier = new Vector<Node>();
        frontierFromGoal = new Vector<Node>();
    }

    @Override
    public Node search() throws GSException, InterruptedException {
        // Initializations
        if (frontier == null || frontierFromGoal == null)
            throw new GSException("One of the frontiers are null!");
        if (goalState == null)
            throw new GSException("Goal state is not set in the B-D Search.");
        explored = new Vector<>();
        ((Vector) frontier).add(rootNode());
        frontierFromGoal.add(new Node(null, goalState, null,0));

        // Looping
        while (!((Vector)frontier).isEmpty() || !(frontierFromGoal.isEmpty())){

            // Looping from the initial state.
            // Measuring
            if (((Vector) frontier).size() + frontierFromGoal.size() + explored.size() > maxNodeCountInMemory)
                maxNodeCountInMemory = ((Vector) frontier).size() + frontierFromGoal.size() + explored.size();

            Node leaf = (Node) ((Vector)frontier).firstElement();
            ((Vector) frontier).removeElementAt(0);

            // Measuring
            visitedNodesCount++;

            // Goal test
            if (frontierFromGoal.contains(leaf) || problem.goalTest(leaf.getState()))
                return buildPath(leaf, frontierFromGoal);

            // Leaf is not a goal. Then it must be expanded. Making sure not to visit this node again!
            explored.add(leaf);

            // Expanding the leaf node
            Vector<Action> actions = problem.actions(leaf.getState());
            for (Action a : actions) {
                Node child = childNode(leaf, a);

                // Measuring
                expandedNodesCount++;

                if (!((Vector)frontier).contains(child) || !explored.contains(child)){
                    System.out.println("Expanded: " + child + " from initial");
                    ((Vector)frontier).add(child);
                }
            }


            // Looping from the goal state.
            // Measuring
            if (((Vector) frontier).size() + frontierFromGoal.size() + explored.size() > maxNodeCountInMemory)
                maxNodeCountInMemory = ((Vector) frontier).size() + frontierFromGoal.size() + explored.size();

            Node leafFromGoal = (Node) frontierFromGoal.firstElement();
            frontierFromGoal.removeElementAt(0);

            // Measuring
            visitedNodesCount++;

            // Goal test
            if (((Vector) frontier).contains(leafFromGoal) || problem.initialState().equals(leafFromGoal.getState()) )
                return buildPath(leafFromGoal, (Vector) frontier);

            // Leaf is not a goal. Then it must be expanded. Making sure not to visit this node again!
            explored.add(leafFromGoal);

            // Expanding the leaf node
            Vector<Action> actionsFromGoal = problem.actions(leafFromGoal.getState());
            for (Action action : actionsFromGoal) {
                Node child = childNode(leafFromGoal, action);

                // Measuring
                expandedNodesCount++;

                if (!frontierFromGoal.contains(child) || !explored.contains(child)){
                    System.out.println("Expanded: " + child + " from goal");
                    frontierFromGoal.add(child);
                }
            }
        }
        return null;
    }

    public void setGoalState(State goalState) {
        this.goalState = goalState;
    }

    /**
     * Build the path from the current common node both from the goal and initial side.
     *
     * @param node {@link Node} The middle node.
     * @param frontierContainingNode {@link Vector} to trace the route from the common node.
     * @return Node|null
     */
    private Node buildPath(Node node, Vector frontierContainingNode){
        System.out.println("BUILDING PATH FROM " + node + " AND: " + frontierContainingNode );
        if (problem.goalTest(node.getState()))
            return node;
        if (problem.initialState().equals(node.getState())){
            // The path should be reversed.
            return node;
        }
        Node toManipulate = (Node) frontierContainingNode.elementAt(frontierContainingNode.indexOf(node));
        Node p = toManipulate.getParent();
        Node res = node;
        while (p != null){
            for (Action a : problem.actions(res.getState())) {
                if (problem.result(res.getState(), a).equals(p.getState()))
                    res = childNode(res, a);
            }
            p = p.getParent();
        }
        return res;
    }
}
