package bipartition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class KernighanLin {
    private List<MyVertex> inA;
    private List<MyVertex> inB;
    private List<MyVertex> A;
    private List<MyVertex> B;
    private List<Swap> swaps;
    Graph graph;

    KernighanLin(Graph g) {
        graph = g;
        int n = g.vertices.size();
        A = new ArrayList<>(n / 2);
        B = new ArrayList<>(n / 2);
        inA = new ArrayList<>(n / 2);
        inB = new ArrayList<>(n / 2);
        swaps = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            MyVertex v = g.vertices.get(i);
            if (i % 2 == 0) inA.add(v);
            else inB.add(v);
        }
        A.addAll(inA);
        B.addAll(inB);
    }

    @Override
    public String toString() {
        return "KernighanLin{" +
                ", \ninA=" + inA +
                ", \ninB=" + inB +
                ", \noutA=" + A +
                ", \noutB=" + B +
                '}';
    }

    public static void main(String[] args) {
        Graph g = GraphFactory.generate(20, .15f);
        System.out.println(g.toString());
        KernighanLin kl = new KernighanLin(g);
        Visualizer.visualize(kl);
        System.out.println(kl.toString());
        System.out.println("bipartitionCost: " + kl.bipartitionCost());
        kl.step();
        System.out.println(kl.toString());
        System.out.println("bipartitionCost: " + kl.bipartitionCost());

    }

    int vertexCost(MyVertex vertex) {
        int external = 0;
        int internal = 0;
        boolean subsetA1 = A.contains(vertex);
        for (int a : vertex.getNeighbours()) {
            boolean subsetA2 = isInSubsetA(a);
            MyEdge edge = graph.edge(vertex.getId(), a);
            if (subsetA1 == subsetA2) {
                internal += edge.getWeight();
            } else {
                external += edge.getWeight();
            }
        }
        return external - internal;
    }

    boolean isInSubsetA(int vid) {
        for (MyVertex v : A) {
            if (v.getId() == vid) return true;
        }
        return false;
    }

    void step() {
        MyVertex bestEdgeV1 = null;
        MyVertex bestEdgeV2 = null;
        int bestGain = Integer.MIN_VALUE;

        for (MyVertex a : inA) {
            for (MyVertex b : inB) {
                MyEdge edge = graph.edge(a.getId(), b.getId());
                int weight;
                if (edge == null) {
                    weight = 0;
                } else {
                    weight = edge.getWeight();
                }
                int gain = vertexCost(a) + vertexCost(b) - 2 * weight;
                if (gain > bestGain) {
                    bestEdgeV1 = a;
                    bestEdgeV2 = b;
                    bestGain = gain;
                }
            }
        }
        System.out.println(bestEdgeV1.getId() + " " + bestEdgeV2.getId() + " " + bestGain);
        swap(bestEdgeV1, bestEdgeV2);
        swaps.add(new Swap(bestEdgeV1, bestEdgeV2, bestGain));
        inA.remove(bestEdgeV1);
        inB.remove(bestEdgeV2);
    }

    int bipartitionCost() {
        int costAll = 0;
        for (MyEdge edge : graph.edges) {
            boolean inA1 = isInSubsetA(edge.getV1());
            boolean inA2 = isInSubsetA(edge.getV2());
            if (inA1 != inA2) costAll += edge.getWeight();
        }
        return costAll;
    }

    void swap(MyVertex v1, MyVertex v2) {
        assert isInSubsetA(v1.getId()) != isInSubsetA(v2.getId());
        A.remove(v1);
        B.remove(v2);
        A.add(v2);
        B.add(v1);
    }
}
















