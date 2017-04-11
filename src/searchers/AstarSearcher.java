package searchers;

import resources.Action;
import utilities.GSException;
import resources.Node;
import resources.Problem;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;

/**
 * A* Searcher.
 */
public class AstarSearcher extends Searcher {

    /**
     * Constructor method. the frontier must be set at the end of constructing this object.
     *
     * @param problem {@link Problem} to be searched.
     */
    public AstarSearcher(Problem problem) {
        super(problem);
        frontier = new PriorityQueue<>(Comparator.comparingDouble(this::fScore));
    }

    @Override
    public Node search() throws GSException, InterruptedException {
        // Initializations
        if (frontier == null)
            throw new GSException("Frontier is null!");
        ((PriorityQueue) frontier).add(rootNode());
        explored = new Vector<>();

        // Looping
        while (!((PriorityQueue)frontier).isEmpty()){

            // Measuring
            if (((PriorityQueue) frontier).size() + explored.size() > maxNodeCountInMemory)
                maxNodeCountInMemory = ((PriorityQueue) frontier).size() + explored.size();

            Node leaf = (Node) ((PriorityQueue)frontier).remove();

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

                if (!((PriorityQueue)frontier).contains(child) || !(explored.contains(leaf)))
                    ((PriorityQueue)frontier).add(child);
            }
        }
        return null;
    }

    /**
     * The old grin traditional f-cost of a state!
     * Uses problem's heuristic function.
     *
     * @param n {@link Node} node which is in state s.
     * @return the f-cost to evaluate.
     */
    private double fScore(Node n){
        return n.getPathCost() + problem.heuristic(n.getState());
    }

}
