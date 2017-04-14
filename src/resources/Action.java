package resources;

public class Action {

    /**
     * Object containing data
     */
    private Object data;

    /**
     * Actual cost of the action
     */
    private double cost;

    /**
     * Constructor method.
     *
     * @param data {@link Action} the action
     * @param cost The actual cost of this action.
     */
    public Action(Object data, double cost){
        this.data = data;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Object getAction() {
        return data;
    }

    public void setAction(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
