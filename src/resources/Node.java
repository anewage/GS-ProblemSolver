package resources;


import java.util.List;
import java.util.Vector;

public class Node {

    private Node parent;
    private Vector<Node> neighbors;
    private int depth;
//    private List<Node> path;
    private String name;
    private String state;
    private boolean traversed;
    private boolean explored;
    private int pathCost;

    public Node(Vector<Node> neighbors, int depth, List<Node> path, String name, String state, boolean traversed, boolean explored) {
        this.neighbors = neighbors;
        this.depth = depth;
//        this.path = path;
        this.name = name;
        this.state = state;
        this.traversed = traversed;
        this.explored = explored;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
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
        return pathCost;
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
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

//    public List<Node> getPath() {
//        return path;
//    }

//    public void setPath(List<Node> path) {
//        this.path = path;
//    }

    public boolean addNeighbour(Node dest){
        return neighbors.add(dest);
    }

    @Override
    public String toString() {
        return getName() + " " + getState() + " " + String.valueOf(isTraversed());
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((Node) obj).getName());
    }
}
