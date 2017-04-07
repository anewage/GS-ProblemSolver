package resources;

import resources.Node;

import java.util.Vector;

public abstract class Problem {

    private Node initialState;

    private Node goal;

    protected Graph graph;

    public Problem(Graph graph) {
        this.graph = graph;
    }

    public Problem(Node initialState){
        this.initialState = initialState;
    }

    public void setInitialState(Node initialState) {
        this.initialState = initialState;
    }

    public Problem inverse() throws CloneNotSupportedException, GSException {
        if (goal == null)
            throw new GSException("GOAL for this problem is undefined! Please first define the problem.");
        Problem p = (Problem) this.clone();
        p.setInitialState(this.getGoal());
        p.setGoal(this.getInitialState());
        return p;
    }

    public Node getGoal(){
        return goal;
    }

    public void setGoal(Node goal) {
        this.goal = goal;
    }

    public void resetGraph(){
        graph.setTraversed(false);
    }

    public int getPathCost(int from, int to) {
        return graph.getAdjMatrix()[from][to];
    }

    public abstract Vector<Action> actions(Node n);

    public abstract Node result(Node n, Action a);

    public abstract Node getInitialState();

    public abstract boolean goalTest(Node n);

    public abstract int stepCost(Node n, Action a);

    public abstract int pathCost(Node n);
}
