package searchers;

import resources.Action;
import resources.Problem;
import utilities.GSException;
import resources.Node;

import java.util.Comparator;
import java.util.PriorityQueue;

public class UCSearcher extends Searcher {
    /**
     * Constructor method. the frontier must be set at the end of constructing this object.
     *
     * @param problem {@link Problem} to be searched.
     */
    public UCSearcher(Problem problem) {
        super(problem);
    }

    @Override
    public Node search() throws GSException, InterruptedException {
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
