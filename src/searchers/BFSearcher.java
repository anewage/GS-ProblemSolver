package searchers;

import resources.Action;
import resources.Node;
import resources.Problem;
import sun.misc.Queue;
import utilities.GSException;

import java.util.Vector;

public class BFSearcher extends Searcher {

    /**
     * Constructor method. the frontier must be set at the end of constructing this object.
     *
     * @param problem {@link Problem} to be searched.
     */
    public BFSearcher(Problem problem) {
        super(problem);
        frontier = new Vector<Node>();
    }

    @Override
    public Node search() throws GSException, InterruptedException {
        // Initializations
        if (frontier == null)
            throw new GSException("Frontier is null!");
        ((Vector) frontier).add(rootNode());
        explored = new Vector<>();

        // Looping
        while (!((Vector)frontier).isEmpty()){

            // Measuring
            if (((Vector) frontier).size() + explored.size() > maxNodeCountInMemory)
                maxNodeCountInMemory = ((Vector) frontier).size() + explored.size();

            Node leaf = (Node) ((Vector)frontier).firstElement();
            ((Vector) frontier).removeElementAt(0);

            // Measuring
            visitedNodesCount++;

            // Goal test
            if (problem.goalTest(leaf.getState()))
                return leaf;

            // Leaf is not a goal. Then it must be expanded. Making sure not to visit this node again!
            explored.add(leaf);

            // Expanding the leaf node
            Vector<Action> actions = problem.actions(leaf.getState());
            for (Action a : actions) {
                Node child = childNode(leaf, a);

                // Measuring
                expandedNodesCount++;

                if (!((Vector)frontier).contains(child) || !(explored.contains(leaf)))
                    ((Vector)frontier).add(child);
            }
        }
        return null;
    }
}
