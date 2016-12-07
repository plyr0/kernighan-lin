package bipartition;

import java.util.*;

public class KernighanLin {
    private List<Vertex> A;
    private List<Vertex> B;
    List<Swap> swaps;
    Graph graph;
    int n, nHalf;

    KernighanLin(Graph g) {
        graph = g;
        n = g.vertices.size();
        nHalf = n / 2;
        A = new ArrayList<>(n / 2);
        B = new ArrayList<>(n / 2);
        swaps = new ArrayList<>(nHalf);
        preliminaryBipartition(g);
    }

    private void preliminaryBipartition(Graph g) {
        for (int i = 0; i < n; i++) {
            Vertex v = g.vertices.get(i);
            if (i % 2 == 0) A.add(v);
            else B.add(v);
        }
    }

    public static void main(String[] args) {
        Graph g = GraphFactory.generate(20, .10f);
        System.out.println(g.toString());
        KernighanLin kernighanLin = new KernighanLin(g);
        System.out.println(kernighanLin.toString());
        Visualizer.visualize(kernighanLin);
        kernighanLin.kernighanLin();
        System.out.println(kernighanLin.toString());
        Visualizer.visualize(kernighanLin);
    }

    @Override
    public String toString() {
        return "A=" + A +
                "\nB=" + B +
                "\nCost=" + bipartitionCutCost();
    }

    public void kernighanLin() {
        int Gk;
        do {
            computeDvAll();
            for (int i = 0; i < nHalf; i++) {
                swaps.add(singleSwap());
            }

            Gk = swaps.get(0).cost;
            int kBestStep = 0;
            for (int i = 1, tempGk; i < nHalf; i++) {
                tempGk = swaps.get(i).cost;
                if (tempGk > Gk) {
                    Gk = tempGk;
                    kBestStep = i;
                }
            }
            cancelSwapsFrom(kBestStep);
            for(Vertex v : graph.vertices){
                v.setLock(false);
            }
        } while (Gk <= 0);
    }

    private void cancelSwapsFrom(int kBestStep) {
        for (int i = kBestStep; i < nHalf; i++) {
            Swap swap = swaps.get(i);
            Vertex fromA = swap.a;
            Vertex fromB = swap.b;
            swap(fromA, fromB);
        }
    }

    private Swap singleSwap() {
        Vertex bestEdgeVertexInA = null;
        Vertex bestEdgeVertexInB = null;
        int bestGain = Integer.MIN_VALUE;
        for (Vertex a : A) {
            if (a.isLock()) continue;
            for (Vertex b : B) {
                if (b.isLock()) continue;
                Edge edge = graph.tryFindEdge(a.getId(), b.getId());
                int weight = 0;
                if (edge != null) {
                    weight = edge.getWeight();
                }
                int gain = vertexCost(a) + vertexCost(b) - 2 * weight;
                if (gain > bestGain) {
                    bestEdgeVertexInA = a;
                    bestEdgeVertexInB = b;
                    bestGain = gain;
                }
            }
        }
        bestEdgeVertexInA.setLock(true);
        bestEdgeVertexInB.setLock(true);
        swap(bestEdgeVertexInA, bestEdgeVertexInB);
        return new Swap(bestEdgeVertexInB, bestEdgeVertexInA, bipartitionCutCost());
    }

    private void computeDvAll() {
        for (Vertex v : graph.vertices) {
            if (!v.isLock())
                v.setDvCostReduction(vertexCost(v));
        }
    }

    int bipartitionCutCost() {
        int costAll = 0;
        for (Edge edge : graph.edges) {
            boolean inA1 = isInSubsetA(edge.getV1());
            boolean inA2 = isInSubsetA(edge.getV2());
            if (inA1 != inA2) costAll += edge.getWeight();
        }
        return costAll;
    }

    public int vertexCost(Vertex vertex) {
        int external = 0;
        int internal = 0;
        boolean subsetA1 = A.contains(vertex);
        for (int a : vertex.getNeighbours()) {
            boolean subsetA2 = isInSubsetA(a);
            Edge edge = graph.tryFindEdge(vertex.getId(), a);
            assert edge != null;
            if (subsetA1 == subsetA2) {
                internal += edge.getWeight();
            } else {
                external += edge.getWeight();
            }
        }
        return external - internal;
    }

    boolean isInSubsetA(int vid) {
        for (Vertex v : A) {
            if (v.getId() == vid) return true;
        }
        return false;
    }

    void swap(Vertex v1, Vertex v2) {
        assert isInSubsetA(v1.getId());
        assert !isInSubsetA(v2.getId());
        A.remove(v1);
        B.remove(v2);
        A.add(v2);
        B.add(v1);
    }
}
