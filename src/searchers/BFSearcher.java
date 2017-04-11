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
            Node leaf = (Node) ((Vector)frontier).firstElement();
            ((Vector) frontier).removeElementAt(0);

            // Goal test
            if (problem.goalTest(leaf.getState()))
                return leaf;

            // Leaf is not a goal. Then it must be expanded. Making sure not to visit this node again!
            explored.add(leaf);

            // Expanding the leaf node
            Vector<Action> actions = problem.actions(leaf.getState());
            for (Action a : actions) {
                Node child = childNode(leaf, a);
                boolean f1 = ((Vector)frontier).contains(child);
                boolean f2 = explored.contains(leaf);
                if (!f1 || !f2)
                    ((Vector)frontier).add(child);
            }
        }
        return null;
    }
}
