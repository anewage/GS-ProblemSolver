package utilities;

public class GSException extends Exception {

    private Object param;

    public GSException(String message){
        super(message);
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }
}
