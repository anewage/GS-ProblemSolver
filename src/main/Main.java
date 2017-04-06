package main;

import resources.GSException;
import resources.Graph;
import resources.Node;

import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        Node h = new Node(new Vector<>(), 3, new Vector<>(), "h", "404", false, false);
        Node e = new Node(new Vector<>(), 2, new Vector<>(), "e", "404", false, false);
        e.addNeighbour(h);
        Node d = new Node(new Vector<>(), 2, new Vector<>(), "d", "404", false, false);
        Node f = new Node(new Vector<>(), 2, new Vector<>(), "f", "404", false, false);
        Node g = new Node(new Vector<>(), 2, new Vector<>(), "g", "404", false, false);
        Node b = new Node(new Vector<>(), 1, new Vector<>(), "b", "404", false, false);
        b.addNeighbour(d);
        b.addNeighbour(e);
        Node c = new Node(new Vector<>(), 1, new Vector<>(), "c", "404", false, false);
        c.addNeighbour(f);
        c.addNeighbour(g);
        Node a = new Node(new Vector<>(), 0, new Vector<>(), "a", "404", false, false);
        a.addNeighbour(b);
        a.addNeighbour(c);
        Graph gr = new Graph(a);
        try {
//            gr.BFSTraverse();
//            gr.setTraversed(false);
//            gr.DFSTraverse(-1,false);
            gr.setTraversed(false);
            gr.DFSTraverse(3,true);
        } catch (GSException | InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
