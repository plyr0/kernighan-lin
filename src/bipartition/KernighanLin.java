package bipartition;

import java.util.ArrayList;
import java.util.List;

public class KernighanLin {
    private List<MyVertex> beginA;
    private List<MyVertex> beginB;
    private List<MyVertex> A;
    private List<MyVertex> B;

    KernighanLin(Graph g) {
        int n = g.vertices.size();
        A = new ArrayList<>(n/2);
        B = new ArrayList<>(n/2);
        beginA = new ArrayList<>(n/2);
        beginB = new ArrayList<>(n/2);
        for (int i = 0; i < n; i++) {
            MyVertex v = g.vertices.get(i);
            (i % 2 == 0 ? beginA : beginB).add(v);
        }
        A.addAll(beginA);
        B.addAll(beginB);
    }

    @Override
    public String toString() {
        return "KernighanLin{" +
                "\n\nbeginA=" + beginA +
                ", \n\nbeginB=" + beginB +
                ", \n\nA=" + A +
                ", \n\nB=" + B +
                '}';
    }

    public static void main(String[] args) {
        Graph g = GraphFactory.generate(20, .15f);
        System.out.println(g.toString());
        Visualizer.visualize(g);
        KernighanLin kl = new KernighanLin(g);
        System.out.println(kl.toString());
    }
}
