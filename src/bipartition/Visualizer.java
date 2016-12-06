package bipartition;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Visualizer {

    static void visualize(Graph data) {
        SingleGraph graph = new SingleGraph("kcolor");

        for (int i = 0; i < data.vertices.size(); i++) {
            Node node = graph.addNode(Integer.toString(i));
            node.addAttribute("ui.label", data.vertices.get(i).getId());
            //stylize(node, data.vertices.get(i).getGroup());
        }
        for (MyEdge e : data.edges) {
            Edge edge = graph.addEdge(e.getV1() + "-" + e.getV2(), e.getV1(), e.getV2());
            edge.addAttribute("ui.label", e.getWeight());
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
    }

    /*private static void stylize(Node node, MyVertex.Groups color) {
        switch (color) {
            case Left:
                node.addAttribute("ui.style", "fill-color: rgb(0,0,255);");
                break;
            case Right:
                node.addAttribute("ui.style", "fill-color: rgb(255,0,0);");
                break;
        }
    }*/
}