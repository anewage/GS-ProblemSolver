package searchers;

import main.Node;
import resources.GSException;
import sun.misc.Queue;

public class BFSearcher extends Searcher {

    public BFSearcher(Node root) {
        super(root);
    }

    @Override
    public boolean search() throws GSException, InterruptedException {
        if (root == null)
            throw new GSException("Initial State is null! Please set the initial state first!");
        Queue<Node> q = new Queue<>();
        traverseNode(root, null);
        q.enqueue(root);
        while (!q.isEmpty()){
            Node curr = q.dequeue();
            for (Node n : curr.getNeighbors()) {
                if (!n.isTraversed()){
                    traverseNode(n, curr);
                    q.enqueue(n);
                }
            }
        }
        return true;
    }

    @Override
    protected void traverseNode(Node n, Node parent) {
        n.setTraversed(true);
        System.out.println("BFS TRAVERSED: " + n.getName());
        n.setParent(parent);
    }
}
