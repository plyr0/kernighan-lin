package bipartition;

import bipartition.algo.GraphFactory;
import bipartition.algo.KernighanLin;
import bipartition.model.Graph;
import bipartition.visi.Visualizer;
import bipartition.visi.VisualizerStub;
import bipartition.visi.Visualizerable;

class Main {
    private static Visualizerable visualizer = getVisualizer();

    private static Visualizerable getVisualizer() {
        try {
            Class.forName("org.graphstream.ui.j2dviewer.J2DGraphRenderer");
            System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
            System.out.println("graphstream J2DGraphRenderer OK");
        } catch (ClassNotFoundException ex) {
            System.out.println("graphstream J2DGraphRenderer not present: required: gs-ui-1.3.jar");
        }
        try {
            Class.forName("org.graphstream.graph.Node");
            System.out.println("graphstream OK");
            return new Visualizer();
        } catch (ClassNotFoundException ex) {
            System.out.println("graphstream NOT present, required: gs-core-1.3.jar");
            return new VisualizerStub();
        }
    }

    public static void main(String[] args) {
        Graph g;
        if (args.length > 0 && args[0].equals("n")) {
            g = GraphFactory.generate(40, .05f, true);
        } else {
            g = GraphFactory.generate(40, .05f, false);
        }
        System.out.println(g.toString());
        KernighanLin kernighanLin = new KernighanLin(g);
        System.out.println(kernighanLin.toString());
        visualizer.visualize(kernighanLin, "pre " + kernighanLin.bipartitionCutCost());
        kernighanLin.kernighanLin();
        System.out.println(kernighanLin.toString());
        visualizer.visualize(kernighanLin, "post " + kernighanLin.bipartitionCutCost());
    }
}
