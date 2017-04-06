package searchers;

import main.Node;
import resources.GSException;

public class DFSSearcher extends Searcher {

    public DFSSearcher(Node root) {
        super(root);
    }

    @Override
    public boolean search() throws GSException, InterruptedException {
        recursiveSearch(root, null);
        return false;
    }

    @Override
    protected void traverseNode(Node n, Node parent) {
        n.setTraversed(true);
        System.out.println("DFS TRAVERSED: " + n.getName());
        n.setParent(parent);
    }

    private void recursiveSearch(Node root, Node parent){
        traverseNode(root, parent);
        for (Node n : root.getNeighbors() ){
            if (!n.isTraversed())
                recursiveSearch(n, root);
        }
    }
}
