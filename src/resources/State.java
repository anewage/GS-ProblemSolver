package resources;

public class State {

    /**
     * The main data of the this object.
     */
    private Object status;

    /**
     * Constructor method.
     *
     * @param status Gives the initial data.
     */
    public State(Object status){
        this.status = status;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        State s = (State) obj;
        return s.getStatus().equals(this.status);
    }
}
