package main;

import resources.*;
import searchers.*;
import utilities.GSException;

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

        try {
            Node res1 = bfs.search();
            System.out.println("BFS:");
            System.out.println((res1 != null ? Problem.solution(res1) + " Path Cost: " +  res1.getPathCost() + " " : "NOT FOUND ") + bfs.toString() );

            Node res2 = dfs.search();
            System.out.println("DFS:");
            System.out.println((res2 != null ? Problem.solution(res2) + " Path Cost: " +  res2.getPathCost() + " " : "NOT FOUND ") + dfs.toString() );

            Node res3 = aStar.search();
            System.out.println("A*:");
            System.out.println((res3 != null ? Problem.solution(res3) + " Path Cost: " +  res3.getPathCost() + " " : "NOT FOUND ") + aStar.toString() );
        } catch (GSException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void p2() {
        Problem p2 = new Problem() {
            @Override
            public State initialState() {
                return new State(new int[][]{
                        {1, 4, 2},
                        {0, 3, 5},
                        {6, 7, 8}
                });
            }

            @Override
            public Vector<Action> actions(State s) {
                Vector<Action> res = new Vector<>();
                int row = -1;
                int col = -1;
                int [][] status = (int [][]) s.getStatus();
                for (int i = 0; i < status.length; i++) {
                    for (int j = 0; j < status[i].length; j++) {
                        if (status[i][j] == 0) {
                            row = i;
                            col = j;
                        }
                    }
                }
                if (col == 0)
                    res.add(new Action("RIGHT", 1));
                else if (col == status.length-1)
                    res.add(new Action("LEFT", 1));
                else if (col > 0 && col < status.length-1){
                    res.add(new Action("RIGHT", 1));
                    res.add(new Action("LEFT", 1));
                }
                if (row == 0)
                    res.add(new Action("DOWN", 1));
                else if (row == status.length-1)
                    res.add(new Action("UP", 1));
                else if (row > 0 && row < status.length-1){
                    res.add(new Action("DOWN", 1));
                    res.add(new Action("UP", 1));
                }
                return res;
            }

            @Override
            public State result(State s, Action a) {
                int[][] puzzle = (int [][]) s.getStatus();
                int row = -1, col =  -1;
                int [][] copy = new int[3][3];
                for (int i = 0; i < puzzle.length; i++) {
                    for (int j = 0; j < puzzle.length; j++) {
                        if (puzzle[i][j] == 0){
                            row = i;
                            col = j;
                        }
                        copy[i][j] = puzzle[i][j];
                    }
                }
                if (col == -1 || row == -1){
                    System.out.println("HELLO");
                }
                switch ((String)a.getAction()){
                    case "UP":
                        copy[row][col] = copy[row-1][col];
                        copy[row-1][col] = 0;
                        break;
                    case "DOWN":
                        copy[row][col] = copy[row+1][col];
                        copy[row+1][col] = 0;
                        break;
                    case "LEFT":
                        copy[row][col] = copy[row][col-1];
                        copy[row][col-1] = 0;
                        break;
                    case "RIGHT":
                        copy[row][col] = copy[row][col+1];
                        copy[row][col+1] = 0;
                        break;
                }
                return new State(copy);
            }

            @Override
            public boolean goalTest(State n) {
                int[][] puzzle = (int [][]) n.getStatus();
                for (int i = 0; i < puzzle.length; i++)
                    for (int j = 0; j < puzzle.length; j++)
                        if(puzzle[i][j] != (i*puzzle.length) + j)
                            return false;
                return true;
            }

            @Override
            public double actionCost(State s, Action a) {
                return 1;
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
                int h = 0;
                int[][] puzzle = (int [][]) s.getStatus();
                for (int i = 0; i < puzzle.length; i++)
                    for (int j = 0; j < puzzle.length; j++)
                        if(puzzle[i][j] != (i*puzzle.length) + j)
                            h++;
                return h;
            }
        };
        DFSearcher dfs = new DFSearcher(p2);
        BDSearcher bds = new BDSearcher(p2);
        bds.setGoalState(new State(new int[][]{
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8}
        }));
        AstarSearcher astar = new AstarSearcher(p2);

        try {
            Node res1 = dfs.search();
            System.out.println("DFS:");
            System.out.println((res1 != null ? Problem.solution(res1) + " Path Cost: " +  res1.getPathCost() + " " : "NOT FOUND ") + dfs.toString() );

            System.out.println("START");
            Node res2 = bds.search();
            System.out.println("BDS:");
            System.out.println((res2 != null ? Problem.solution(res2) + " Path Cost: " +  res2.getPathCost() + " " : "NOT FOUND ") + bds.toString() );

            Node res3 = astar.search();
            System.out.println("BDS:");
            System.out.println((res3 != null ? Problem.solution(res3) + " Path Cost: " +  res3.getPathCost() + " " : "NOT FOUND ") + astar.toString() );
        } catch (GSException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void p3(){
        Problem p3 = new Problem() {
            @Override
            public State initialState() {
                return new State(new int [] {3,3});
            }

            @Override
            public Vector<Action> actions(State s) {
                int [] state = (int []) s.getStatus();
                Vector<Action> actions = new Vector<>();
                if (state[0] == 0 && state[1] == 0)
                    return actions;
                if (state[0] >= 1 && (state[0]-1 <= state[1]) && ((4-state[0]) <= state[1])){
                    actions.add(new Action(new int[]{1,0}, 1));
                }
                if (state[0] >= 2 && (state[0]-2 <= state[1]) && ((5-state[0]) <= state[1])){
                    actions.add(new Action(new int[]{2,0}, 1));
                }
                if (state[1] >= 1 && (state[1]-1 >= state[0]) && ((4-state[1]) >= state[0])){
                    actions.add(new Action(new int[]{0,1}, 1));
                }
                if (state[1] >= 2 && (state[1]-2 >= state[0]) && ((5-state[1]) >= state[0])){
                    actions.add(new Action(new int[]{0,2}, 1));
                }
                if (state[1] >= 1 && state[0] >= 1 && (state[1]-1 >= state[0]-1) && ((4-state[1]) >= 4-state[0])){
                    actions.add(new Action(new int[]{1,1}, 1));
                }
                return actions;
            }

            @Override
            public State result(State s, Action a) {
                int [] state = (int []) s.getStatus();
                int [] action = (int []) a.getAction();
                int [] copy = new int [2];
                copy [0] = state[0] - action[0]; // Adamkhar
                copy [1] = state[1] - action[1]; // Mobalegh
                return new State(copy);
            }

            @Override
            public boolean goalTest(State n) {
                int [] state = (int []) n.getStatus();
                return state[0] == 0 && state[1] == 0;
            }

            @Override
            public double actionCost(State s, Action a) {
                return 1;
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
                return 0;
            }
        };
        BFSearcher bfs = new BFSearcher(p3);
        DFSearcher dfs = new DFSearcher(p3);
        dfs.setIterating(true);
        dfs.setIteratingMaxDepth(Integer.MAX_VALUE);
        try {
            Node res = bfs.search();
            System.out.println("BFS:");
            System.out.println((res != null ? Problem.solution(res) + " Path Cost: " +  res.getPathCost() + " " : "NOT FOUND ") + bfs.toString() );

            Node res2 = dfs.search();
            System.out.println("DFS:");
            System.out.println((res2 != null ? Problem.solution(res2) + " Path Cost: " +  res2.getPathCost() + " " : "NOT FOUND ") + dfs.toString() );
        } catch (GSException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        p1();
        p2();
        p3();
    }
}