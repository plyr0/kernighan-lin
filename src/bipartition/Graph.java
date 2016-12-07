package bipartition;

import java.util.*;

public class Graph {
    List<Vertex> vertices;
    SortedSet<Edge> edges;

    public Graph(int n) {
        vertices = new ArrayList<>(n);
        edges = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            Vertex v = new Vertex(i);
            vertices.add(v);
        }
    }

    public boolean vertexInEdges(Vertex v) {
        int id = v.getId();
        for (Edge e : edges) {
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

    public boolean addEdge(int v1, int v2, int w) {
        if (tryFindEdge(v1, v2) != null) {
            return false;
        }
        Edge edge = new Edge(v1, v2, w);
        edges.add(edge);
        vertices.get(v1).addNeighbour(v2);
        vertices.get(v2).addNeighbour(v1);
        return true;
    }

    public Edge tryFindEdge(int v1, int v2) {
        for (Edge e : edges) {
            if (e.getV1() == v1 && e.getV2() == v2 || e.getV1() == v2 && e.getV2() == v1) {
                return e;
            }
        }
        return null;
    }
}
