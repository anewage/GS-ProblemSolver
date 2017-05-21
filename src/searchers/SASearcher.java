package searchers;

import resources.Action;
import resources.Node;
import resources.Problem;
import utilities.GSException;

import java.util.Random;
import java.util.Vector;

/**
 * Simulated Annealing Searcher
 */
public class SASearcher extends Searcher {

    private double [] tempMapping;

    /**
     * Constructor method. the frontier must be set at the end of constructing this object.
     *
     * @param problem {@link Problem} to be searched.
     */
    public SASearcher(Problem problem) {
        super(problem);
    }

    public void setTempMapping(double[] tempMapping) {
        this.tempMapping = tempMapping;
    }

    @Override
    public Node search() throws GSException, InterruptedException {
        // Initializations
        Random rnd = new Random();
        Node current = rootNode();

        // Looping through time
        for (int t = 0; t < tempMapping.length; t++) {
            // The temperature or simply the "T"
            double tmp = tempMapping[t];

            // Cooling process is over now!
            if (tmp == 0)
                return current;

            // Getting a randomly selected successor of current node
            Vector actions = problem.actions(current.getState());
            Node next = childNode(current, (Action) actions.elementAt(rnd.nextInt(actions.size())));

            // Calculating Delta E and the probability to accept the generated node.
            double deltaE = problem.objectiveFunction(next.getState()) - problem.objectiveFunction(current.getState());
            double p = Math.exp(deltaE/tmp);

            // Measuring
            visitedNodesCount++;

            // Stepping forward!
            if (deltaE > 0 || rnd.nextDouble() < p){
                current = next;

                // Measuring
                expandedNodesCount++;
            }
        }
        return null;
    }
}
