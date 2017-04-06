package searchers;

import resources.Node;
import resources.GSException;

public abstract class Searcher {

    /**
     * The node which the search is being started on it
     */
    protected Node root;

    public Searcher(Node root){
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public abstract boolean search() throws GSException, InterruptedException;

    protected abstract void traverseNode(Node n, Node parent);
}
