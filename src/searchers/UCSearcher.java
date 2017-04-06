package searchers;

import resources.Problem;
import resources.GSException;
import resources.Node;

import java.util.Comparator;
import java.util.PriorityQueue;

public class UCSearcher extends Searcher {

    private PriorityQueue<Node> q;

    public UCSearcher(Problem problem) {
        super(problem);
        q = new PriorityQueue<>(Comparator.comparingInt(Node::getPathCost));
    }

    @Override
    public Node search() throws GSException, InterruptedException {
        startNode.setPathCost(0);
        q.add(startNode);
        while (!q.isEmpty()){
            Node curr = q.remove();
            if (verifyNode(curr, null))
                return curr;
            // TODO: complete this.
            // TODO: get the next state from problem class instead of getting it from the root.
        }
        return null;
    }

    @Override
    protected boolean verifyNode(Node n, Node parent) {
        n.setTraversed(true);
        n.setExplored(true);
        System.out.println("UC TRAVERSED: " + n.getName());
        n.setParent(parent);
        return problem.goalTest(n);
    }
}
