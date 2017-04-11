package main;

import resources.*;
import searchers.*;
import utilities.GSException;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void p1() {
        Problem p1 = new Problem() {

            @Override
            public State initialState() {
                return new State("Arad");
            }

            @Override
            public Vector<Action> actions(State s) {
                Vector<Action> res = new Vector<Action>();
                // TODO: worst implementation ever!
                switch (((String)s.getStatus()).toLowerCase()){
                    case "arad" :
                        res.add(new Action("Zerind",75.0));
                        res.add(new Action("Timisoara",118.0));
                        res.add(new Action("Sibiu",140.0));
                    break;

                    case "zerind":
                        res.add(new Action("Oradea",71.0));
                        res.add(new Action("Arad",75.0));
                        break;

                    case "oradea":
                        res.add(new Action("Sibiu",151.0));
                        res.add(new Action("Zerind",71.0));
                        break;

                    case "sibiu":
                        res.add(new Action("Fagaras",99.0));
                        res.add(new Action("Rimnicu Vilcea",80.0));
                        res.add(new Action("Oradea",151.0));
                        res.add(new Action("Arad",140.0));
                        break;

                    case "timisoara":
                        res.add(new Action("Arad",118.0));
                        res.add(new Action("Lugoj",111.0));
                        break;

                    case "lugoj":
                        res.add(new Action("Timisoara",111.0));
                        res.add(new Action("Mehadia",70.0));
                        break;

                    case "mehadia":
                        res.add(new Action("Lugoj",70.0));
                        res.add(new Action("Dobreta",75.0));
                        break;

                    case "dobreta":
                        res.add(new Action("Craiova",120.0));
                        res.add(new Action("Mehadia",120.0));
                        break;

                    case "fagaras":
                        res.add(new Action("Sibiu",99.0));
                        res.add(new Action("Bucharest",211.0));
                        break;

                    case "rimnicu vilcea":
                        res.add(new Action("Sibiu",80.0));
                        res.add(new Action("Craiova",146.0));
                        res.add(new Action("Pitesti",97.0));
                        break;

                    case "craiova":
                        res.add(new Action("Pitesti",138.0));
                        res.add(new Action("Dobreta",120.0));
                        res.add(new Action("Rimnicu Vilcea",146.0));
                        break;

                    case "pitesti":
                        res.add(new Action("Rimnicu vilcea",97.0));
                        res.add(new Action("Craiova",138.0));
                        res.add(new Action("Bucharest",101.0));
                        break;

                    case "bucharest":
                        res.add(new Action("Giurgiu",90.0));
                        res.add(new Action("Pitesti",101.0));
                        res.add(new Action("Fagaras",211.0));
                        res.add(new Action("Urziceni",85.0));
                        break;

                    case "giurgiu":
                        res.add(new Action("Bucharest",90.0));
                        break;

                    case "urziceni":
                        res.add(new Action("Bucharest",85.0));
                        res.add(new Action("Vaslui",142.0));
                        res.add(new Action("Hirsova",98.0));
                        break;

                    case "vaslui":
                        res.add(new Action("Lasi",92.0));
                        res.add(new Action("Urziceni",142.0));
                        break;

                    case "lasi":
                        res.add(new Action("Neamt",87.0));
                        res.add(new Action("Vaslui",92.0));
                        break;

                    case "neamt":
                        res.add(new Action("Lasi",87.0));
                        break;

                    case "hirsova":
                        res.add(new Action("Urziceni",98.0));
                        res.add(new Action("Eforie",86.0));
                        break;

                    case "eforie":
                        res.add(new Action("Hirsova",86.0));
                        break;
                }
                return res;
            }

            @Override
            public State result(State s, Action a) {
                return new State(a.getAction());
            }

            @Override
            public boolean goalTest(State n) {
                return ((String)n.getStatus()).equalsIgnoreCase("Vaslui");
            }

            @Override
            public double actionCost(State s, Action a) {
                return a.getCost();
            }

            @Override
            public double pathCost(Node n) {
                Node p = n.getParent();
                double cost = n.getPathCost();
                while(p != null){
                    cost += p.getPathCost();
                    p = p.getParent();
                }
                return cost;
            }

            @Override
            public double heuristic(State s) {
                int index = -1;
                double [] cities = {
                        710,
                        634,
                        576,
                        677,
                        408,
                        636,
                        720,
                        669,
                        561,
                        507,
                        330,
                        447,
                        388,
                        324,
                        267,
                        283,
                        387,
                        0,
                        66.6,
                        133,
                };
                switch (((String)s.getStatus()).toLowerCase()){
                    case "arad" :
                        index = 0;
                        break;

                    case "zerind":
                        index = 1;
                        break;

                    case "oradea":
                        index = 2;
                        break;

                    case "sibiu":
                        index = 4;
                        break;

                    case "timisoara":
                        index = 3;
                        break;

                    case "lugoj":
                        index = 5;
                        break;

                    case "mehadia":
                        index = 6;
                        break;

                    case "dobreta":
                        index = 7;
                        break;

                    case "fagaras":
                        index = 10;
                        break;

                    case "rimnicu vilcea":
                        index = 9;
                        break;

                    case "craiova":
                        index = 8;
                        break;

                    case "pitesti":
                        index = 11;
                        break;

                    case "bucharest":
                        index = 13;
                        break;

                    case "giurgiu":
                        index = 12;
                        break;

                    case "urziceni":
                        index = 14;
                        break;

                    case "vaslui":
                        index = 17;
                        break;

                    case "lasi":
                        index = 18;
                        break;

                    case "neamt":
                        index = 19;
                        break;

                    case "hirsova":
                        index = 15;
                        break;

                    case "eforie":
                        index = 16;
                        break;

                }
                return cities[index];
            }
        };
        BFSearcher bfs = new BFSearcher(p1);
        DFSearcher dfs = new DFSearcher(p1);
        dfs.setDepthLimit(8);
        AstarSearcher aStar = new AstarSearcher(p1);


        UCSearcher ucs = new UCSearcher(p1);
        BDSearcher bds = new BDSearcher(p1);
        bds.setGoalState(new State("Vaslui"));


        try {
            Node res1 = bfs.search();
            System.out.println("BFS:");
            System.out.println((res1 != null ? Problem.solution(res1)  : "NOT FOUND ") + bfs.toString() );

            Node res2 = dfs.search();
            System.out.println("DFS:");
            System.out.println((res2 != null ? Problem.solution(res2)  : "NOT FOUND ") + dfs.toString() );

            Node res3 = aStar.search();
            System.out.println("A*:");
            System.out.println((res3 != null ? Problem.solution(res3)  : "NOT FOUND ") + aStar.toString() );

            Node res4 = ucs.search();
            System.out.println("UCS:");
            System.out.println((res4 != null ? Problem.solution(res4)  : "NOT FOUND ") + ucs.toString() );

            Node res5 = bds.search();
            System.out.println("BDS:");
            System.out.println((res5 != null ? Problem.solution(res5)  : "NOT FOUND ") + bds.toString() );
        } catch (GSException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void p2() {

    }

    public static void p3(){

    }

    public static void main(String[] args) {
        p1();
        p2();
        p3();
    }

//    public static void p3(){
//        Problem p3 = new Problem(new Graph(1)) {
//            @Override
//            public int getPathCost(int from, int to) {
//                return 1;
//            }
//
//            @Override
//            public Vector<Action> actions(Node n) {
//                int [] state = (int []) n.getState();
//                Vector<Action> actions = new Vector<>();
//                if (state[0] == 0 && state[1] == 0)
//                    return actions;
//                if (state[0] >= 1 && (state[0]-1 <= state[1]) && ((4-state[0]) <= state[1])){
//                    actions.add(new Action(new int[]{1,0}, 1, -1));
//                }
//                if (state[0] >= 2 && (state[0]-2 <= state[1]) && ((5-state[0]) <= state[1])){
//                    actions.add(new Action(new int[]{2,0}, 1, -1));
//                }
//                if (state[1] >= 1 && (state[1]-1 >= state[0]) && ((4-state[1]) >= state[0])){
//                    actions.add(new Action(new int[]{0,1}, 1, -1));
//                }
//                if (state[1] >= 2 && (state[1]-2 >= state[0]) && ((5-state[1]) >= state[0])){
//                    actions.add(new Action(new int[]{0,2}, 1, -1));
//                }
//                if (state[1] >= 1 && state[0] >= 1 && (state[1]-1 >= state[0]-1) && ((4-state[1]) >= 4-state[0])){
//                    actions.add(new Action(new int[]{1,1}, 1, -1));
//                }
//                return actions;
//            }
//
//            @Override
//            public Node result(Node n, Action a) {
//                int [] state = (int []) n.getState();
//                int [] action = (int []) a.getName();
//                int [] copy = new int [2];
//                copy [0] = state[0] - action[0]; // Adamkhar
//                copy [1] = state[1] - action[1]; // Mobalegh
//                Node child = new Node(n.getIndex()+1, action, copy);
//                child.setParent(n,1);
//                return child;
//            }
//
//            @Override
//            public Node getInitialState() {
//                if (graph.getStart() == null){
//                    Node n = new Node(0, action, new int[]{3, 3});
//                    graph.setStart(n);
//                    return n;
//                }
//                return graph.getStart();
//            }
//
//            @Override
//            public boolean goalTest(Node n) {
//                int [] state = (int []) n.getState();
//                return state[0] == 0 && state[1] == 0;
//            }
//
//            @Override
//            public int stepCost(Node n, Action a) {
//                return 1;
//            }
//
//            @Override
//            public int pathCost(Node n) {
//                return n.getPathCost();
//            }
//
//            @Override
//            public int hCost(Node n) {
//                return 1;
//            }
//        };
//
//        BFSearcher bfs = new BFSearcher(p3);
//        DFSearcher dfs = new DFSearcher(p3);
//        dfs.setIterating(true);
//        dfs.setIteratingMaxDepth(Integer.MAX_VALUE);
//        try {
//            Node res = bfs.search();
//            while (res != null){
//                System.out.println("[ " + ((int [])res.getState())[0] + ", "  + ((int [])res.getState())[1] + " ]");
//                res = res.getParent();
//            }
//
//            Node res2 = dfs.search();
//            while (res2 != null){
//                System.out.println("[ " + ((int [])res2.getState())[0] + ", "  + ((int [])res2.getState())[1] + " ]");
//                res2 = res2.getParent();
//            }
//        } catch (GSException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void p1() {
//        Graph g1 = new Graph(20);
//        Graph g2 = new Graph(1);
//        try {
//            g1.addEdge(0,1,75,false);
//            g1.addEdge(0,3,118,false);
//            g1.addEdge(0,4,140,false);
//            g1.addEdge(1,2,71,false);
//            g1.addEdge(2,4,151,false);
//            g1.addEdge(3,5,111,false);
//            g1.addEdge(4,10,99,false);
//            g1.addEdge(4,9,80,false);
//            g1.addEdge(5,6,70,false);
//            g1.addEdge(6,7,75,false);
//            g1.addEdge(7,8,120,false);
//            g1.addEdge(8,9,146,false);
//            g1.addEdge(8,11,138,false);
//            g1.addEdge(9,11,97,false);
//            g1.addEdge(10,13,211,false);
//            g1.addEdge(11,13,101,false);
//            g1.addEdge(12,13,90,false);
//            g1.addEdge(13,14,85,false);
//            g1.addEdge(14,15,98,false);
//            g1.addEdge(14,17,142,false);
//            g1.addEdge(15,16,86,false);
//            g1.addEdge(17,18,92,false);
//            g1.addEdge(18,19,87,false);
//
////            g2.addEdge(0,1,3, false);
////            g2.addEdge(0,2,2, false);
////            g2.addEdge(1,3,10, false);
////            g2.addEdge(1,4,7, false);
////            g2.addEdge(2,5,10, false);
////            g2.addEdge(2,6,5, false);
////            g2.addEdge(4,7,1, false);
//
//        } catch (GSException e) {
//            e.printStackTrace();
//        }
//
//        Problem p1 = new Problem(g1) {
//            @Override
//            public int getPathCost(int from, int to) {
//                return graph.getAdjMatrix()[from][to];
//            }
//
//            @Override
//            public Vector<Action> actions(Node n) {
//                Vector<Action> res = new Vector<>();
//                int index = n.getIndex();
//                int [] dests = graph.getAdjMatrix()[index];
//                for (int i = 0; i < dests.length; i++) {
//                    int weight = dests[i];
//                    if (weight != 0) {
//                        res.add(new Action("Trip", weight, i));
//                    }
//                }
//                return res;
//            }
//
//            @Override
//            public Node result(Node n, Action a) {
//                int j = a.getjIndex();
//                Node dst = graph.getNodes()[j];
//                return dst;
//            }
//
//            @Override
//            public Node getInitialState() {
//                Node n = graph.getNodes()[0];
//                n.setParent(null,0);
//                n.setExplored(false);
//                n.setTraversed(false);
//                n.setState("Arad");
//                n.setPathCost(graph.getAdjMatrix()[0][0]);
//                return n;
//            }
//
//            @Override
//            public boolean goalTest(Node n) {
//                System.out.println("CHECKING Node #" + n.getIndex());
//                return n.getIndex() == 17;
//            }
//
//            @Override
//            public int stepCost(Node n, Action a) {
//                int i = n.getIndex();
//                int j = a.getjIndex();
//                return graph.getAdjMatrix()[i][j];
//            }
//
//            @Override
//            public int pathCost(Node n) {
//                return n.getPathCost();
//            }
//
//            @Override
//            public int hCost(Node n) {
//                int [] cities = {
//                        710,
//                        634,
//                        576,
//                        677,
//                        408,
//                        636,
//                        720,
//                        669,
//                        561,
//                        507,
//                        330,
//                        447,
//                        388,
//                        324,
//                        267,
//                        283,
//                        387,
//                        0,
//                        66,
//                        133,
//                };
//                return cities[n.getIndex()];
//            }
//        };
//        BFSearcher bfs1 = new BFSearcher(p1);
//        DFSearcher dfs1 = new DFSearcher(p1);
//        dfs1.setDepthLimit(8);
//        AstarSearcher aStar1 = new AstarSearcher(p1);
//        try {
//            Node res1 = bfs1.search();
//            p1.resetGraph();
//            Node res2 = dfs1.search();
//            p1.resetGraph();
//            Node res3 = aStar1.search();
//            System.out.println("BFS: " + (res1 != null ? res1.getPath() : ""));
//            System.out.println("Limited DFS with 8 depth: " + (res2 != null ? res2.getPath() : ""));
//            System.out.println("A* search: " + (res3 != null ? res3.getPath() : ""));
//        } catch (GSException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void p2(){
//        Graph g2 = new Graph(1);
//        Problem p2 = new Problem(g2) {
//            @Override
//            public int getPathCost(int from, int to) {
//                return 0;
//            }
//
//            @Override
//            public Vector<Action> actions(Node n) {
//                int [][] puzzle = (int [][]) n.getState();
//                int row = -1, col =  -1;
//                for (int i = 0; i < puzzle.length; i++) {
//                    for (int j = 0; j < puzzle.length; j++) {
//                        if (puzzle[i][j] == 0){
//                            row = i;
//                            col = j;
//                        }
//                    }
//                }
//                Vector<Action> res = new Vector<>();
//                if (row == 0)
//                    res.add(new Action("DOWN", 1,-1));
//                else if (row == puzzle.length-1)
//                    res.add(new Action("UP", 1,-1));
//                else if (row > 0 && row < puzzle.length-1){
//                    res.add(new Action("DOWN", 1,-1));
//                    res.add(new Action("UP", 1,-1));
//                }
//                if (col == 0)
//                    res.add(new Action("RIGHT", 1,-1));
//                else if (col == puzzle.length-1)
//                    res.add(new Action("LEFT", 1,-1));
//                else if (col > 0 && col < puzzle.length-1){
//                    res.add(new Action("RIGHT", 1,-1));
//                    res.add(new Action("LEFT", 1,-1));
//                }
//                return res;
//            }
//
//            @Override
//            public Node result(Node n, Action a) {
//                int[][] puzzle = (int [][]) n.getState();
//                int row = -1, col =  -1;
//                for (int i = 0; i < puzzle.length; i++) {
//                    for (int j = 0; j < puzzle.length; j++) {
//                        if (puzzle[i][j] == 0){
//                            row = i;
//                            col = j;
//                        }
//                    }
//                }
//                int [][] copy = puzzle.clone();
//                switch ((String)a.getName()){
//                    case "UP":
//                        copy[row][col] = copy[row-1][col];
//                        copy[row-1][col] = 0;
//                        break;
//                    case "DOWN":
//                        copy[row][col] = copy[row+1][col];
//                        copy[row+1][col] = 0;
//                        break;
//                    case "LEFT":
//                        copy[row][col] = copy[row][col+1];
//                        copy[row][col+1] = 0;
//                        break;
//                    case "RIGHT":
//                        copy[row][col] = copy[row][col-1];
//                        copy[row][col-1] = 0;
//                        break;
//                }
//                Node child = new Node(n.getIndex()+1, action, copy);
//                child.setParent(n, 1);
//                return child;
//            }
//
//            @Override
//            public Node getInitialState() {
//                Scanner sc = new Scanner(System.in);
//                System.out.println("Enter number of rows in puzzle:");
//                int n = sc.nextInt();
//                int [][] puzzle = new int [n][n];
//                System.out.println("Enter puzzle numbers row by row");
//                for (int i = 0; i < puzzle.length; i++) {
//                    for (int j = 0; j < puzzle.length; j++)
//                        puzzle[i][j] = sc.nextInt();
//                    System.out.println("");
//                }
//                Node node = new Node(0, action, puzzle);
//                return node;
//            }
//
//            @Override
//            public boolean goalTest(Node n) {
//                int[][] puzzle = (int [][]) n.getState();
//                for (int i = 0; i < puzzle.length; i++)
//                    for (int j = 0; j < puzzle.length; j++)
//                        if(puzzle[i][j] != (i*puzzle.length) + j)
//                            return false;
//                return true;
//            }
//
//            @Override
//            public int stepCost(Node n, Action a) {
//                return 1;
//            }
//
//            @Override
//            public int pathCost(Node n) {
//                return n.getPathCost();
//            }
//
//            @Override
//            public int hCost(Node n) {
//                int h = 0;
//                int[][] puzzle = (int [][]) n.getState();
//                for (int i = 0; i < puzzle.length; i++)
//                    for (int j = 0; j < puzzle.length; j++)
//                        if(puzzle[i][j] != (i*puzzle.length) + j)
//                            h++;
//                return h;
//            }
//        };
//        DFSearcher dfs2 = new DFSearcher(p2);
//        try {
//            dfs2.search();
//        } catch (GSException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}