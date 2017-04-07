package searchers;

import resources.Action;
import resources.Problem;
import resources.Node;
import resources.GSException;
import sun.misc.Queue;

public class BFSearcher extends Searcher {

    private Queue<Node> q;

    public BFSearcher(Problem p) {
        super(p);
        q = new Queue<>();
    }

    @Override
    public Node search() throws GSException, InterruptedException {
        if (startNode == null)
            throw new GSException("Initial State is null! Please set the initial state first!");
        if (q == null)
            this.q = new Queue<>();
        if (verifyNode(startNode, null))
            return startNode;
        q.enqueue(startNode);
        while (!q.isEmpty()){
            Node curr = q.dequeue();
            for (Action a : problem.actions(curr)) {
                Node n = problem.result(curr, a);
                if (!n.isTraversed()){
                    if (verifyNode(n, curr)){
                        System.out.println("HOORAY! FOUND: #" + n.getIndex() + n.getPath() );
                        return n;
                    }
                    q.enqueue(n);
                }
            }
        }
        return null;
    }

    @Override
    protected boolean verifyNode(Node n, Node parent) {
        n.setTraversed(true);
        n.setExplored(true);
        int weight = 0;
        if (parent != null)
            weight = problem.getPathCost(parent.getIndex(), n.getIndex());
        n.setParent(parent, weight);
        System.out.println("BFS TRAVERSED: " + n.getState());
        return problem.goalTest(n);
    }
}
