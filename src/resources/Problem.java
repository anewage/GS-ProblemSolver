package resources;

import resources.Node;

import java.util.Vector;

public abstract class Problem {

    private Node initialState;

    public Problem(Node initialState){
        this.initialState = initialState;
    }

    public Node getInitialState() {
        return initialState;
    }

    public void setInitialState(Node initialState) {
        this.initialState = initialState;
    }

    public abstract Vector<Action> actions(Node n);

    public abstract Node result(Node n, Action a);

    public abstract boolean goalTest(Node n);

    public abstract int stepCost(Node n);

    public abstract int pathCost(Node n);

}
