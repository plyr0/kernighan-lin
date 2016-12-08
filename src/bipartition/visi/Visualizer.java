package bipartition.visi;

import bipartition.algo.KernighanLin;

public class Visualizer {
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
            return new VisualizerImpl();
        } catch (ClassNotFoundException ex) {
            System.out.println("graphstream NOT present, required: gs-core-1.3.jar");
            return new VisualizerStub();
        }
    }

    public static void visualize(KernighanLin kernighanLin, String text) {
        visualizer.visualize(kernighanLin, text);
    }
}
