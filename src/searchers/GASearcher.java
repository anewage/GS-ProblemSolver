package searchers;

import resources.Node;
import resources.Problem;
import resources.State;
import utilities.GSException;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Vector;

/**
 * Genetic Algorithm Searcher
 */
public class GASearcher extends Searcher{

    /**
     * Generating new population will go on until this amount is reached.
     */
    private int generationCount;

    /**
     * New generated children are mutated with this probability.
     */
    private double mutationProbability;

    /**
     * Count of population of each generation.
     */
    private int population;

    /**
     * Constructor method. the frontier must be set at the end of constructing this object.
     *
     * @param problem {@link Problem} to be searched.
     */
    public GASearcher(Problem problem) {
        super(problem);
    }

    @Override
    public Node search() throws GSException, InterruptedException {

        return null;
    }

    public int getGenerationCount() {
        return generationCount;
    }

    public void setGenerationCount(int generationCount) {
        this.generationCount = generationCount;
    }

    public double getMutationProbability() {
        return mutationProbability;
    }

    public void setMutationProbability(double mutationProbability) {
        this.mutationProbability = mutationProbability;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    private Node GeneticAction(Vector<Node> population){

        int generation = 0;
        boolean presidentIsInThePopulation = false;
        Random rnd = new Random();

        while (generation != generationCount && !presidentIsInThePopulation) {
            Vector<Node> nextGeneration = new Vector<>();
            for (int i = 0; i < population.size(); i++) {
                int p1 = rnd.nextInt(population.size()), p2 = rnd.nextInt(population.size());
                while (p2 == p1)
                    p2 = rnd.nextInt(population.size());

                Node x = population.elementAt(p1);
                Node y = population.elementAt(p2);
                Node child = reProduce(x, y);
                if (rnd.nextDouble() < mutationProbability)
                    child = mutate(child);
                if (problem.goalTest(child.getState()))
                    presidentIsInThePopulation = true;
                nextGeneration.add(child);
            }
            generation++;
            population = nextGeneration;
        }

        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> (int) (problem.objectiveFunction(o1.getState()) - problem.objectiveFunction(o2.getState())));
        q.addAll(population);
        return q.remove();
    }

    private Node reProduce(Node x, Node y){
        String s1 = (String) x.getState().getStatus();
        String s2 = (String) y.getState().getStatus();
        int c = (new Random()).nextInt(s1.length());
        Node child = new Node(x, new State(s1.substring(0, c) + s2.substring(c+1, s2.length())), null, 0);
        child.setSecondParent(y);
        return child;
    }

    private Node mutate(Node individual){
        StringBuilder status = new StringBuilder((String) individual.getState().getStatus());
        int c = (new Random()).nextInt(((String) individual.getState().getStatus()).length());
        String chars = "`1234567890-=qwertyuiop[]\\asdfghjkl;'zxcvbnm,./~!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKL:\"ZXCVBNM<>?";
        int d = (new Random()).nextInt(chars.length());
        status.setCharAt(c, chars.charAt(d));
        individual.getState().setStatus(status.toString());
        return individual;
    }
}
