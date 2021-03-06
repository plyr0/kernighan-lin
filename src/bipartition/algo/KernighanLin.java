package bipartition.algo;

import bipartition.model.Edge;
import bipartition.model.Graph;
import bipartition.model.SwapOffer;
import bipartition.model.Vertex;

import java.util.*;
import java.util.stream.Collectors;

public class KernighanLin {
    private List<Vertex> A;
    private List<Vertex> B;
    private List<SwapOffer> swapOffers;
    private final Graph graph;
    private final int n;
    private final int nHalf;

    public KernighanLin(Graph g) {
        graph = g;
        n = g.getVertices().size();
        nHalf = n / 2;
        A = new ArrayList<>(n / 2);
        B = new ArrayList<>(n / 2);
        preliminaryBipartition(g);
    }

    private void preliminaryBipartition(Graph g) {
        for (int i = 0; i < n; i++) {
            Vertex v = g.getVertices().get(i);
            (i % 2 == 0 ? A : B).add(v);
        }
    }

    @Override
    public String toString() {
        A = A.stream().sorted().collect(Collectors.toList());
        B = B.stream().sorted().collect(Collectors.toList());
        return "A=" + A +
                "\nB=" + B +
                "\n * Bipartition Cut Cost=" + bipartitionCutCost() + " *";
    }

    public void kernighanLin() {
        int bestSum = 1;
        while (bestSum > 0) {
            initAndFindAllSwaps();

            bestSum = Integer.MIN_VALUE;
            int bestStep = -1;
            int sum = 0;
            for (int i = 0; i < nHalf; i++) {
                sum += swapOffers.get(i).getCost();
                if (sum > bestSum) {
                    bestSum = sum;
                    bestStep = i;
                }
            }
            if (bestSum > 0) {
                for (int i = 0; i <= bestStep; i++) {
                    SwapOffer s = swapOffers.get(i);
                    swap(s.getA(), s.getB());
                }
            }
            unlockAllVertices();
            System.out.println(" bestSum " + bestSum + " best step " + bestStep);
        }
    }

    private void unlockAllVertices() {
        for (Vertex v : graph.getVertices()) {
            v.setLock(false);
        }
    }

    private void initAndFindAllSwaps() {
        swapOffers = new ArrayList<>(nHalf);
        for (int i = 0; i < nHalf; i++) {
            swapOffers.add(findSingleSwap());
            System.out.println(swapOffers.get(swapOffers.size() - 1));
        }
    }

    private SwapOffer findSingleSwap() {
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
        assert bestEdgeVertexInA != null;
        bestEdgeVertexInA.setLock(true);
        bestEdgeVertexInB.setLock(true);
        return new SwapOffer(bestEdgeVertexInA, bestEdgeVertexInB, bestGain);
    }

    public int bipartitionCutCost() {
        int costAll = 0;
        for (Edge edge : graph.getEdges()) {
            boolean inA1 = isInSubsetA(edge.getV1());
            boolean inA2 = isInSubsetA(edge.getV2());
            if (inA1 != inA2) costAll += edge.getWeight();
        }
        return costAll;
    }

    private int vertexCost(Vertex vertex) {
        int external = 0;
        int internal = 0;
        boolean subsetA1 = A.contains(vertex);
        for (int n : vertex.getNeighbours()) {
            if (graph.getVertices().get(n).isLock())
                continue;
            boolean subsetA2 = isInSubsetA(n);
            Edge edge = graph.tryFindEdge(vertex.getId(), n);
            assert edge != null;
            if (subsetA1 == subsetA2) {
                internal += edge.getWeight();
            } else {
                external += edge.getWeight();
            }
        }
        return external - internal;
    }

    public boolean isInSubsetA(int vid) {
        for (Vertex v : A) {
            if (v.getId() == vid) return true;
        }
        return false;
    }

    private void swap(Vertex v1, Vertex v2) {
        assert isInSubsetA(v1.getId());
        assert !isInSubsetA(v2.getId());
        A.remove(v1);
        B.remove(v2);
        A.add(v2);
        B.add(v1);
    }

    public Graph getGraph() {
        return graph;
    }
}
