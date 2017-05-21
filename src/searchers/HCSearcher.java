package searchers;

import resources.Action;
import resources.Node;
import resources.Problem;
import utilities.GSException;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Vector;

/**
 * Hill Climbing Searcher
 */
public class HCSearcher extends Searcher {

    /**
     * Used if the First choice mode is requested.
     */
    private boolean firstChoiceHillClimbing;

    /**
     * Used if the Stochastic mode is requested.
     */
    private boolean stochasticHillClimbing;

    /**
     * Used if the Random restart mode is requested.
     */
    private boolean randomRestartHillClimbing;


    /**
     * Constructor method. the frontier must be set at the end of constructing this object.
     *
     * @param problem {@link Problem} to be searched.
     */
    public HCSearcher(Problem problem) {
        super(problem);
    }

    @Override
    public Node search() throws GSException, InterruptedException {
        // Initialization
        Node current = rootNode();
        double objective1 = 0, objective2 = 0;

        // Looping
        while (objective1 > objective2 || objective1 == objective2){
            Node neighbor = getUphillMove(current);

            // Measuring
            visitedNodesCount++;


            objective1 = problem.objectiveFunction(neighbor.getState());
            objective2 = problem.objectiveFunction(current.getState());
            if (objective1 < objective2 ){
                expandedNodesCount++;
                if (randomRestartHillClimbing && !problem.goalTest(current.getState()))
                    // Restarting from random state
                    current = randomNode();
                else
                    return current;
            }
            current = neighbor;
        }
        return null;
    }

    public boolean isFirstChoiceHillClimbing() {
        return firstChoiceHillClimbing;
    }

    public void setFirstChoiceHillClimbing(boolean firstChoiceHillClimbing) {
        this.firstChoiceHillClimbing = firstChoiceHillClimbing;
    }

    public boolean isStochasticHillClimbing() {
        return stochasticHillClimbing;
    }

    public void setStochasticHillClimbing(boolean stochasticHillClimbing) {
        this.stochasticHillClimbing = stochasticHillClimbing;
    }

    public boolean isRandomRestartHillClimbing() {
        return randomRestartHillClimbing;
    }

    public void setRandomRestartHillClimbing(boolean randomRestartHillClimbing) {
        this.randomRestartHillClimbing = randomRestartHillClimbing;
    }

    private Node getUphillMove(Node current){
        PriorityQueue<Node> prioritizedNeighbors = new PriorityQueue<>((o1, o2) -> (int) (problem.objectiveFunction(o1.getState()) - problem.objectiveFunction(o2.getState())));
        Vector<Node> upwardMoves = new Vector<>();
        for (Action a : problem.actions(current.getState())) {
            Node next = childNode(current, a);
            if (firstChoiceHillClimbing){
                if (problem.objectiveFunction(next.getState()) > problem.objectiveFunction(current.getState()))
                    return next;
            } else if (stochasticHillClimbing) {
                if (problem.objectiveFunction(next.getState()) > problem.objectiveFunction(current.getState()))
                    upwardMoves.add(next);
            } else {
                prioritizedNeighbors.add(next);
            }
        }
        if (stochasticHillClimbing)
            return upwardMoves.elementAt((new Random()).nextInt(upwardMoves.size()));
        else
            return prioritizedNeighbors.remove();
    }
}
