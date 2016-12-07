package bipartition;

public class Main {
    public static void main(String[] args) {
        Graph g;
        if(args.length>0 && args[0].equals('n')){
            g = GraphFactory.generate(40, .05f, true);
        } else {
            g = GraphFactory.generate(40, .05f);
        }
        System.out.println(g.toString());
        KernighanLin kernighanLin = new KernighanLin(g);
        System.out.println(kernighanLin.toString());
        Visualizer.visualize(kernighanLin, "pre " + kernighanLin.bipartitionCutCost());
        kernighanLin.kernighanLin();
        System.out.println(kernighanLin.toString());
        Visualizer.visualize(kernighanLin, "post " + kernighanLin.bipartitionCutCost());
    }
}
