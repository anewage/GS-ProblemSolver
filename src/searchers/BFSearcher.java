package searchers;

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
            for (Node n : curr.getNeighbors()) {
                if (!n.isTraversed()){
                    if (verifyNode(n, curr))
                        return n;
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
        n.setParent(parent);
        System.out.println("BFS TRAVERSED: " + n.getName());
        return problem.goalTest(n);
    }
}
