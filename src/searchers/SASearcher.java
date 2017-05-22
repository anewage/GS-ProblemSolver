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

    /**
     * Maximum temperature which is decreased by one in each iteration
     */
    private int maxTemp;

    private double decreaseRatio;

    private int maxTime;

    /**
     * Constructor method. the frontier must be set at the end of constructing this object.
     *
     * @param problem {@link Problem} to be searched.
     */
    public SASearcher(Problem problem) {
        super(problem);
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void setDecreaseRatio(double decreaseRatio) {
        this.decreaseRatio = decreaseRatio;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    @Override
    public Node search() throws GSException, InterruptedException {
        // Initializations
        Random rnd = new Random();
        Node current = rootNode();

        // Looping through time
        for (int t = 0; t < maxTime; t++) {
            // The temperature or simply the "T"
            double tmp = tempMapping(t);
            System.out.println("time: " + t + "temp: " + tmp);

            // Cooling process is over now!
            if (tmp == 0)
                return current;

            // Getting a randomly selected successor of current node
            Vector actions = problem.actions(current.getState());
            int actionIndex = rnd.nextInt(actions.size());
            Node next = childNode(current, (Action) actions.elementAt(actionIndex));

            // Calculating Delta E and the probability to accept the generated node.
            double deltaE = problem.heuristic(next.getState()) - problem.heuristic(current.getState());
            double p = Math.exp(deltaE/tmp);

            // Measuring
            visitedNodesCount++;

            // Stepping forward!
            if (deltaE > 0.0){
                System.out.println("Moving from: " + current.getState() + ", To: " + next.getState() + ". deltaE:" + deltaE + ", p:" + p + ", time: " + t +  ", temp:" + tmp );
                current = next;

                // Measuring
                expandedNodesCount++;
            }
            else if(rnd.nextDouble() < p) {
                current = next;
                expandedNodesCount++;
            }
        }
        return null;
    }

    private double tempMapping(int time){
        return maxTemp - (time * decreaseRatio);
    }
}
