package bipartition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {
    List<MyVertex> vertices;
    Set<MyEdge> edges;

    Graph(int n) {
        vertices = new ArrayList<>(n);
        edges = new HashSet<>();
        for (int i = 0; i < n; i++) {
            MyVertex v = new MyVertex(i);
            vertices.add(v);
        }
    }

    void assumeNoIsolated() {
        for (MyVertex v : vertices) {
            if (!vertexInEdges(v)) {
                addEdge(v.getId(), (v.getId() + 1) % vertices.size(), 22);
            }
        }
    }

    private boolean vertexInEdges(MyVertex v) {
        int id = v.getId();
        for (MyEdge e : edges) {
            if (e.getV1() == id || e.getV2() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "\n vertices=" + vertices.size() +
                "\n myEdges=" + edges +
                '}';
    }

    boolean addEdge(int v1, int v2, int w) {
        for (MyEdge e : edges) {
            if (edgeEqual(v1, v2, e)) {
                return false;
            }
        }
        MyEdge myEdge = new MyEdge(v1, v2, w);
        edges.add(myEdge);
        return true;
    }

    private boolean edgeEqual(int v1, int v2, MyEdge e) {
        return (e.getV1() == v1 && e.getV2() == v2) ||
                (e.getV2() == v1 && e.getV1() == v2);
    }
}
