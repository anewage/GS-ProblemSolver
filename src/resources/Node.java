package resources;


import java.util.List;
import java.util.Vector;

public class Node {

    private Node parent;
    private State state;
    private double costFromParent;

    public Node(Node parent, double costFromParent){
        setParent(parent, costFromParent);
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setParent(Node parent, double stepCost) {
        this.parent = parent;
        costFromParent = stepCost;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public double getCostFromParent() {
        return costFromParent;
    }

    public void setCostFromParent(double costFromParent) {
        this.costFromParent = costFromParent;
    }

    @Override
    public String toString() {
        return "State:" + state + ", Step cost from parent: " + costFromParent;
    }

    @Override
    public boolean equals(Object obj) {
        Node dst = (Node) obj;
        return dst.getState().equals(this.state) && dst.getParent().equals(this.parent);
    }
}
