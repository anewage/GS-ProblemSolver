package resources;

public class Action {

    private Object action;
    private double cost;

    public Action(Object action, double cost){
        this.action = action;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Object getAction() {
        return action;
    }

    public void setAction(Object action) {
        this.action = action;
    }
}
