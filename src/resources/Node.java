package resources;


import java.util.List;
import java.util.Vector;

public class Node {

    private Node parent;
    private Vector<Node> neighbors;
//    private int depth;
    private Vector<Node> path;
    private String name;
    private String state;
    private boolean traversed;
    private boolean explored;
    private int pathCost;
    private int costFromParent;

    private int index;

    public Node(int index) {
        this.index = index;
    }

    public Node(Vector<Node> neighbors, int depth, List<Node> path, String name, String state, boolean traversed, boolean explored) {
        this.neighbors = neighbors;
//        this.depth = depth;
//        this.path = path;
        this.name = name;
        this.state = state;
        this.traversed = traversed;
        this.explored = explored;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent, int stepCost) {
        this.parent = parent;
        costFromParent = stepCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
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
            p = parent.getParent();
        }
        return cost;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }

    public Vector<Node> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Vector<Node> neighbors) {
        this.neighbors = neighbors;
    }

    public int getDepth() {
        return getPath().size();
    }

//    public void setDepth(int depth) {
//        this.depth = depth;
//    }

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

    public boolean addNeighbour(Node dest){
        return neighbors.add(dest);
    }

    @Override
    public String toString() {
        return "Node #" + getIndex() + " -> " + getName() + " " + getState() + " " + String.valueOf(isTraversed());
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
}
