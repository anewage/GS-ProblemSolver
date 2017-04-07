package searchers;

import resources.Action;
import resources.GSException;
import resources.Node;
import resources.Problem;
import sun.misc.Queue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class AstarSearcher extends Searcher {

    private PriorityQueue<Node> q;

    public AstarSearcher(Problem problem) {
        super(problem);
        q = new PriorityQueue<>(Comparator.comparingInt(this::fScore));
    }

    @Override
    public Node search() throws GSException, InterruptedException {
        if (startNode == null)
            throw new GSException("Initial State is null! Please set the initial state first!");
        if (q == null)
            q = new PriorityQueue<>(Comparator.comparingInt(this::fScore));
//        if (verifyNode(startNode, null))
//            return startNode;
        q.add(startNode);
        while (!q.isEmpty()){
            Node curr = q.remove();
            if (verifyNode(curr, null))
                return curr;
            for (Action a : problem.actions(curr)) {
                Node n = problem.result(curr, a);
                if (!n.isTraversed()){
                    buildPath(n, curr);
                    q.add(n);
                }
            }
        }
        return null;
    }

    @Override
    protected boolean verifyNode(Node n, Node parent) {
        n.setTraversed(true);
        return problem.goalTest(n);
    }

    private void buildPath(Node n, Node parent){
        int weight = 0;
        if (parent != null)
            weight = problem.getPathCost(parent.getIndex(), n.getIndex());
        n.setParent(parent, weight);
    }

    private int fScore(Node n){
        int f = gScore(n) + hScore(n);
        n.setFScore(f);
        return f;
    }

    private int gScore(Node n){
        int g = n.getPathCost();
        n.setGScore(g);
        return g;
    }

    private int hScore(Node n){
        int h = problem.hCost(n);
        n.setHScore(h);
        return h;
    }
}
