package bipartition.algo;

import bipartition.model.Graph;
import bipartition.model.Vertex;

import java.util.Random;

public class GraphFactory {
    private static final Random random = new Random();
    private static boolean isNonWeightedGraph = false;

    public static Graph generate(int vertices, float densityClicque) {
        int cliqueEdges = vertices * (vertices - 1) / 2;
        int edges = Math.round(cliqueEdges * densityClicque);
        return generate(vertices, edges);
    }

    public static Graph generate(int vertices, float densityClicque, boolean isNonWeightedGraph) {
        GraphFactory.isNonWeightedGraph = isNonWeightedGraph;
        return generate(vertices, densityClicque);
    }

    public static Graph generate(int vertices, int edgesTo) {
        int edges = 0;
        Graph graph = new Graph(vertices);
        while (edges < edgesTo) {
            int v1 = random.nextInt(vertices);
            int v2 = random.nextInt(vertices);
            int w = getNewWeight();
            if (v1 != v2 && graph.addEdge(v1, v2, w)) {
                edges++;
            }
        }
        assumeNoIsolated(graph);
        return graph;
    }

    private static void assumeNoIsolated(Graph graph) {
        for (Vertex v : graph.getVertices()) {
            if (!graph.vertexInEdges(v)) {
                graph.addEdge(v.getId(), (v.getId() + 1) % graph.getVertices().size(), getNewWeight());
            }
        }
    }

    private static int getNewWeight() {
        if (isNonWeightedGraph)
            return 1;
        return random.nextInt(10) + 1;
    }
}
