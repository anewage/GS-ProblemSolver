package searchers;

import resources.Action;
import resources.Problem;
import utilities.GSException;
import resources.Node;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;
import java.util.function.Predicate;

/**
 * Uniform-Cost Searcher.
 */
public class UCSearcher extends Searcher {

    /**
     * Constructor method. the frontier must be set at the end of constructing this object.
     *
     * @param problem {@link Problem} to be searched.
     */
    public UCSearcher(Problem problem) {
        super(problem);
        frontier = new PriorityQueue<>(Comparator.comparingDouble(Node::getPathCost));
    }

    @Override
    public Node search() throws GSException, InterruptedException {
        // Initializations
        if (frontier == null)
            throw new GSException("Frontier is null!");
        ((PriorityQueue) frontier).add(rootNode());
        explored = new Vector<>();

        // Looping
        while (!((Vector)frontier).isEmpty()){

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
                else if (((PriorityQueue)frontier).contains(child)){
                    ((PriorityQueue) frontier).removeIf(o -> {
                        Node n = (Node) o;
                        return n.equals(child) && n.getPathCost() > child.getPathCost();
                    });
                }
            }
        }
        return null;
    }

//    private PriorityQueue<Node> q;
//
//    public UCSearcher(Problem problem) {
//        super(problem);
//        q = new PriorityQueue<>(Comparator.comparingInt(Node::getPathCost));
//    }
//
//    @Override
//    public Node search() throws GSException, InterruptedException {
//        startNode.setPathCost(0);
//        q.add(startNode);
//        while (!q.isEmpty()){
//            Node curr = q.remove();
//            if (verifyNode(curr, null))
//                return curr;
//            for (Action a : problem.actions(curr)) {
//                Node child = problem.result(curr, a);
//                if (!child.isTraversed() || !q.contains(child))
//                    q.add(child);
//                else if (q.contains(child)){
//                    q.remove(child);
//                    q.add(child);
//                }
//            }
//        }
//        return null;
//    }
//
//    @Override
//    protected boolean verifyNode(Node n, Node parent) {
//        n.setTraversed(true);
//        n.setExplored(true);
//        System.out.println("UC TRAVERSED: " + n.getState());
//        int weight = 0;
//        if (parent != null)
//            weight = problem.getPathCost(parent.getIndex(), n.getIndex());
//        n.setParent(parent, weight);
//        return problem.goalTest(n);
//    }
}
