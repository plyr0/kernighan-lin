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

    boolean addEdge(int v1, int v2, int w)
    {
        if(hasEdge(v1,v2)){
            return  false;
        }
        MyEdge myEdge = new MyEdge(v1, v2, w);
        edges.add(myEdge);
        vertices.get(v1).addNeighbour(v2);
        vertices.get(v2).addNeighbour(v1);
        return true;
    }

    private boolean hasEdge(int v1, int v2) {
        for (MyEdge e : edges) {
            if (e.getV1() == v1 && e.getV2() == v2 || e.getV1() == v2 && e.getV2() == v1) {
                return true;
            }
        }
        return false;
    }
}
