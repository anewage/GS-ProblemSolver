package resources;

public class State {

    private Object status;

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
