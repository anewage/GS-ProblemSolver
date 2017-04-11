package resources;

import java.util.Collections;
import java.util.Vector;

public abstract class Problem {

    /**
     * Solution which lead to the node result. Containing the middle states.
     *
     * @param result {@link Node} the leaf node which is the goal.
     * @return {@link Vector<State>} containing the states traversed from root to the goal.
     */
    public static Vector<State> solution(Node result){
        if (result == null)
            return new Vector<>();
        Vector<State> res = new Vector<>();
        res.add(result.getState());
        Node p = result.getParent();
        while (p != null) {
            res.add(p.getState());
            p = p.getParent();
        }
        Collections.reverse(res);
        return res;
    }

    /**
     * Getting the problem's initial state in which we start the search.
     *
     * @return State the initial state.
     */
    public abstract State initialState();

    /**
     * The actions list which can be performed while the agent is in state s.
     *
     * @param s State to review.
     * @return Vector set of actions available in state s.
     */
    public abstract Vector<Action> actions(State s);

    /**
     * Returns the result node of an action performed on agent when it was in state s.
     *
     * @param s State the state in which the agent is.
     * @param a Action the action to perform.
     * @return State with parent n.
     */
    public abstract State result(State s, Action a);

    /**
     * Determine whether the given state is the goal of the problem or not.
     *
     * @param n State to review.
     * @return boolean the answer.
     */
    public abstract boolean goalTest(State n);

    /**
     * Returns the cost of action a in state s.
     *
     * @param s State in which the action will be performed.
     * @param a Action to be performed
     * @return double cost of the action.
     */
    public abstract double actionCost(State s, Action a);

    /**
     * Returns the path in leaded to node n.
     * @param n Node the leaf node.
     * @return double path from root to leaf node n.
     */
    public abstract double pathCost(Node n);
}
