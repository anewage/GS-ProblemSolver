package resources;

public class Action {

    private String name;

    private int weight;

    private int jIndex;

    public Action(String name, int weight, int jIndex){
        this.name = name;
        this.weight = weight;
        this.jIndex = jIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getjIndex() {
        return jIndex;
    }

    public void setjIndex(int jIndex) {
        this.jIndex = jIndex;
    }
}
