package bipartition;

import java.util.Random;

public class GraphFactory {
    public static Graph generate(int vertices, float densityClicque) {
        int cliqueEdges = vertices * (vertices - 1) / 2;
        int edges = Math.round(cliqueEdges * densityClicque);
        return generate(vertices, edges);
    }

    public static Graph generate(int vertices, int edgesTo) {
        Random random = new Random();
        int edges = 0;
        Graph graph = new Graph(vertices);
        while (edges < edgesTo) {
            int v1 = random.nextInt(vertices);
            int v2 = random.nextInt(vertices);
            int w = random.nextInt(10) + 1;
            //int w = 1;
            if (v1 != v2 && graph.addEdge(v1, v2, w)) {
                edges++;
            }
        }
        assumeNoIsolated(graph);
        return graph;
    }

    static void assumeNoIsolated(Graph graph) {
        for (Vertex v : graph.vertices) {
            if (!graph.vertexInEdges(v)) {
                graph.addEdge(v.getId(), (v.getId() + 1) % graph.vertices.size(), new Random().nextInt(10) + 1);
            }
        }
    }
}
