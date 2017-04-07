package searchers;

import resources.Action;
import resources.GSException;
import resources.Node;
import resources.Problem;

import java.util.Vector;

public class BDSearcher extends Searcher {

    private Problem inverseProblem;
    private Node goal;
    private Vector<Node> fromGoal;
    private Vector<Node> fromRoot;

    public BDSearcher(Problem problem) throws GSException, CloneNotSupportedException {
        super(problem);
        inverseProblem = problem.inverse();
        goal = inverseProblem.getInitialState();
        fromGoal = new Vector<>();
        fromRoot = new Vector<>();
    }

    @Override
    public Node search() throws GSException, InterruptedException {
        if (startNode == null)
            throw new GSException("Initial State is null! Please set the initial state first!");
        if (goal == null)
            throw new GSException("Goal State is null! Please set the goal state of the problem first!");
        if (fromRoot == null)
            fromRoot = new Vector<>();
        if (fromGoal == null)
            fromGoal = new Vector<>();
        if (verifyNode(startNode, null))
            return startNode;
        while (!fromRoot.isEmpty() && !fromGoal.isEmpty()){
            Node currFromRoot = fromRoot.firstElement();
            if (fromGoal.contains(currFromRoot))
                return currFromRoot;

            Node currFromGoal = fromGoal.firstElement();
            for (int i = 0; i < Math.max(problem.actions(currFromRoot).size(), inverseProblem.actions(currFromGoal).size()); i++) {
                Action actionFromRoot = problem.actions(currFromRoot).elementAt(i);
                Node childFromRoot = problem.result(currFromRoot, actionFromRoot);
                //TODO
            }
        }
//        fromRoot.enqueue(startNode);
//        fromGoal.enqueue(goal);
        return null;
    }

    @Override
    protected boolean verifyNode(Node n, Node parent) {
        return false;
    }

    private void buildPath(){

    }
}
