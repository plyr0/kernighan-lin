package bipartition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {
    List<MyVertex> vertices;
    Set<MyEdge> edges;

    public Graph(int n) {
        vertices = new ArrayList<>(n);
        edges = new HashSet<>();
        for (int i = 0; i < n; i++) {
            vertices.add(new MyVertex(i));
            if (i % 2 == 0) vertices.get(i).setGroup(MyVertex.Groups.Right);
        }
    }

    public void assumeNoIsolated() {
        for (MyVertex v : vertices) {
            if (!vertexInEdges(v)) {
                addEdge(v.getId(), (v.getId() + 1) % vertices.size(), 22);
            }
        }
    }

    private boolean vertexInEdges(MyVertex v) {
        int id = v.getId();
        for (MyEdge e : edges) {
            if (e.getV1().getId() == id || e.getV2().getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "\n vertices=" + vertices +
                "\n myEdges=" + edges +
                '}';
    }

    public static void main(String[] args) {
        Graph g = GraphFactory.generate(20, .15f);
        System.out.println(g.toString());
        Visualizer.visualize(g);
    }

    public boolean addEdge(int v1, int v2, int w) {
        for (MyEdge e : edges) {
            if (edgeEqual(v1, v2, e)) {
                return false;
            }
        }
        MyEdge myEdge = new MyEdge(new MyVertex(v1), new MyVertex(v2), w);
        edges.add(myEdge);
        return true;
    }

    private boolean edgeEqual(int v1, int v2, MyEdge e) {
        return (e.getV1().getId() == v1 && e.getV2().getId() == v2) ||
                (e.getV2().getId() == v1 && e.getV1().getId() == v2);
    }
}
