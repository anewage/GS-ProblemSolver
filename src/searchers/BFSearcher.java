package searchers;

import resources.Node;
import resources.Problem;

import java.util.Comparator;
import java.util.PriorityQueue;

public class BFSearcher extends Searcher {

    /**
     * Constructor method. the frontier must be set at the end of constructing this object.
     *
     * @param problem {@link Problem} to be searched.
     */
    public BFSearcher(Problem problem) {
        super(problem);
        setFrontier(new PriorityQueue<Node>(Comparator.comparingInt(this::priority)));
    }

    private int priority(Node n){

    }
}
