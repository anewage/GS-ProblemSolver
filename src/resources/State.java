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
        try {
            int [][] matrix1 = (int [][]) s.getStatus();
            int [][] matrix2 = (int [][]) getStatus();
            for (int i = 0; i < matrix1.length; i++)
                for (int j = 0; j < matrix1.length; j++)
                    if(matrix1[i][j] != matrix2[i][j])
                        return false;
            return true;
        } catch (Exception e){
            return s.getStatus().equals(this.status);
        }
    }

    @Override
    public String toString() {
        try {
            String res = "";
            int [][] puzzle = (int [][]) getStatus();
            for (int i = 0; i < puzzle.length; i++)
                for (int j = 0; j < puzzle.length; j++)
                    res += puzzle[i][j];
            return res;
        } catch (Exception e){
            try{
                String res = "";
                int [] p = (int []) getStatus();
                for (int i = 0; i < p.length; i++)
                    res += p[i];
                return res;
            } catch (Exception e2){
                return status.toString();
            }
        }
    }
}
