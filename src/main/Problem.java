package main;

import resources.Node;

public class Problem {

    private Node initialState;

    public Node getInitialState() {
        return initialState;
    }

    public void setInitialState(Node initialState) {
        this.initialState = initialState;
    }

    public boolean goalTest(Node n){
        return false;
    }
}
