package bipartition.visi;

import bipartition.algo.KernighanLin;
import bipartition.model.Edge;
import bipartition.model.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Visualizer implements Visualizerable {

    @Override
    public void visualize(KernighanLin kernighanLin, String text) {
        Graph data = kernighanLin.getGraph();
        SingleGraph graph = new SingleGraph("kcolor");
        //graph.addAttribute("ui.stylesheet", "graph { fill-color: #ffe6ff; }");

        for (int i = 0; i < data.getVertices().size(); i++) {
            Node node = graph.addNode(Integer.toString(i));
            node.addAttribute("ui.label", data.getVertices().get(i).getId());
            stylizeNode(node, kernighanLin.isInSubsetA(i));
        }
        for (Edge e : data.getEdges()) {
            int v1 = e.getV1();
            int v2 = e.getV2();
            org.graphstream.graph.Edge edge = graph.addEdge(v1 + "-" + v2, v1, v2);
            edge.addAttribute("ui.label", e.getWeight());

            if (kernighanLin.isInSubsetA(v1) == kernighanLin.isInSubsetA(v2)) {
                if (kernighanLin.isInSubsetA(v1)) {
                    edge.addAttribute("ui.style", "shape: blob; fill-color: Navy;");
                } else {
                    edge.addAttribute("ui.style", "shape: blob; fill-color: #800000;");
                }
            }
        }
        Viewer viewer = graph.display();
        View view = viewer.getDefaultView();
        view.addMouseListener(new MouseAdapter() {
            double scale = 1.0;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    scale /= 2.0;
                } else {
                    scale *= 2.0;
                }
                view.getCamera().setViewPercent(scale);
            }
        });
        viewer.getDefaultView().setToolTipText(text);
    }

    private static void stylizeNode(Node node, boolean isInA) {
        if (isInA) {
            node.addAttribute("ui.style", "fill-color: Blue; text-color: Yellow; size: 15px;");
        } else {
            node.addAttribute("ui.style", "fill-color: Red; text-color: Yellow; size: 15px;");
        }
    }
}