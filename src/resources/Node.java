package resources;

public class Node {

    private Node parent;
    private State state;
    private Action action;
    private double pathCost;

    public Node(Node parent, State state, Action action, double pathCost){
        this.action = action;
        this.state = state;
        setParent(parent, pathCost);
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setParent(Node parent, double stepCost) {
        this.parent = parent;
        pathCost = stepCost;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public double getPathCost() {
        return pathCost;
    }

    public void setPathCost(double pathCost) {
        this.pathCost = pathCost;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "State:" + state + ", Step cost from parent: " + pathCost;
    }

    @Override
    public boolean equals(Object obj) {
        Node dst = (Node) obj;
        return dst.getState().equals(this.state) && dst.getParent().equals(this.parent);
    }
}
