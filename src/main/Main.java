package main;

import resources.*;
import searchers.BFSearcher;
import searchers.DFSearcher;

import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        Graph g1 = new Graph(20);
        Graph g2 = new Graph(8);
        try {
            g1.addEdge(0,1,75,false);
            g1.addEdge(0,3,118,false);
            g1.addEdge(0,4,140,false);
            g1.addEdge(1,2,71,false);
            g1.addEdge(2,4,151,false);
            g1.addEdge(3,5,111,false);
            g1.addEdge(4,10,99,false);
            g1.addEdge(4,9,80,false);
            g1.addEdge(5,6,70,false);
            g1.addEdge(6,7,75,false);
            g1.addEdge(7,8,120,false);
            g1.addEdge(8,9,146,false);
            g1.addEdge(8,11,138,false);
            g1.addEdge(9,11,97,false);
            g1.addEdge(10,13,211,false);
            g1.addEdge(11,13,101,false);
            g1.addEdge(12,13,90,false);
            g1.addEdge(13,14,85,false);
            g1.addEdge(14,15,98,false);
            g1.addEdge(14,17,142,false);
            g1.addEdge(15,16,86,false);
            g1.addEdge(17,18,92,false);
            g1.addEdge(18,19,87,false);

            g2.addEdge(0,1,3, false);
            g2.addEdge(0,2,2, false);
            g2.addEdge(1,3,10, false);
            g2.addEdge(1,4,7, false);
            g2.addEdge(2,5,10, false);
            g2.addEdge(2,6,5, false);
            g2.addEdge(4,7,1, false);

        } catch (GSException e) {
            e.printStackTrace();
        }

        Problem p = new Problem(g1) {
            @Override
            public Vector<Action> actions(Node n) {
                Vector<Action> res = new Vector<>();
                int index = n.getIndex();
                int [] dests = graph.getAdjMatrix()[index];
                for (int i = 0; i < dests.length; i++) {
                    int weight = dests[i];
                    if (weight != 0) {
                        res.add(new Action("Trip", weight, i));
                    }
                }
                return res;
            }

            @Override
            public Node result(Node n, Action a) {
                int j = a.getjIndex();
                Node dst = graph.getNodes()[j];
                return dst;
            }

            @Override
            public Node getInitialState() {
                Node n = graph.getNodes()[0];
                n.setParent(null);
                n.setExplored(false);
                n.setTraversed(false);
                n.setName("Arad");
                n.setPathCost(graph.getAdjMatrix()[0][0]);
                return n;
            }

            @Override
            public boolean goalTest(Node n) {
                System.out.println("CHECKING Node #" + n.getIndex());
                return n.getIndex() == 17;
            }

            @Override
            public int stepCost(Node n, Action a) {
                int i = n.getIndex();
                int j = a.getjIndex();
                return graph.getAdjMatrix()[i][j];
            }

            @Override
            public int pathCost(Node n) {
                return n.getPathCost();
            }
        };

//        BFSearcher bfs = new BFSearcher(p);
        DFSearcher dfs = new DFSearcher(p);
        dfs.setDepthLimit(8);
//        dfs.setIteratingMaxDepth(3);
//        dfs.setIterating(true);
        try {
//            Node res = bfs.search();
            Node res = dfs.search();
            if (res == null)
                System.out.println("NOT FOUND!");
            else
                System.out.println(res.getPath());
        } catch (GSException | InterruptedException e) {
            e.printStackTrace();
        }


    }
}