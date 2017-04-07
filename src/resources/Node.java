package resources;


import java.util.List;
import java.util.Vector;

public class Node {

    private Node parent;
    private Vector<Node> path;
    private Object state;
    private boolean traversed;
    private boolean explored;
    private int pathCost;
    private int costFromParent;

    private int fScore;
    private int hScore;
    private int gScore;


    private int index;

    public Node(int index) {
        this.index = index;
    }

    public Node(int index, Object state) {
        this.index = index;
        this.state = state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent, int stepCost) {
        this.parent = parent;
        costFromParent = stepCost;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public boolean isTraversed() {
        return traversed;
    }

    public void setTraversed(boolean traversed) {
        this.traversed = traversed;
    }

    public boolean isExplored() {
        return explored;
    }

    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    public int getPathCost() {
        int cost = 0;
        Node p = this;
        while (p != null){
            cost += p.getCostFromParent();
            p = p.getParent();
        }
        return cost;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }

    public int getDepth() {
        return getPath().size();
    }

    public Vector<Node> getPath() {
        path = new Vector<>();
        path.add(this);
        Node parent = this.parent;
        while (parent != null){
            path.add(parent);
            parent = parent.getParent();
        }
        return path;
    }

    @Override
    public String toString() {
        return "Node #" + getIndex() + " STATE: " + getState() + ", g:" + gScore + " h:" + hScore + " f:" + fScore + ", " + String.valueOf(isTraversed());
    }

    @Override
    public boolean equals(Object obj) {
        return index == ((Node) obj).getIndex();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCostFromParent() {
        return costFromParent;
    }

    public void setCostFromParent(int costFromParent) {
        this.costFromParent = costFromParent;
    }

    public void setFScore(int fScore) {
        this.fScore = fScore;
    }

    public void setGScore(int GScore) {
        this.gScore = GScore;
    }

    public void setHScore(int HScore) {
        this.hScore = HScore;
    }
}
