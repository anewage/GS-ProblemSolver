package resources;

public class Node {

    /**
     * The father from which this node resulted.
     */
    private Node parent;

    /**
     * The state in which the agent is while traversing this node.
     */
    private State state;

    /**
     * The action which caused stepping to this node.
     */
    private Action action;

    /**
     * Actual cost of the path which lead to this node.
     */
    private double pathCost;

    /**
     * Constructor method.
     *
     * @param parent {@link Node} the father!
     * @param state {@link State} the actual state.
     * @param action {@link Action} which was performed on parent and resulted this.
     * @param pathCost The actual cost which lead to this node.
     */
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
