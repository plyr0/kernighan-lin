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
            int v1 = random.nextInt(vertices - 1);
            int v2 = random.nextInt(vertices - 1);
            int w = random.nextInt(20) + 1;
            if (v1 != v2 && graph.addEdge(v1, v2, w)) {
                edges++;
            }
        }
        graph.assumeNoIsolated();
        return graph;
    }
}
