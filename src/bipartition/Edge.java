package bipartition;

public class Edge implements Comparable<Edge> {
    private int v1;
    private int v2;
    private int weight;

    public Edge(int v1, int v2, int weight) {
        assert v1 != v2;
        this.v1 = Math.min(v1, v2);
        this.v2 = Math.max(v1, v2);
        this.weight = weight;
    }

    public int getV1() {
        return v1;
    }

    public int getV2() {
        return v2;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "\n{" +
                "v1=" + v1 +
                ", v2=" + v2 +
                ", weight=" + weight +
                "}";
    }

    @Override
    public int compareTo(Edge o) {
        int comp = Integer.compare(v1, o.v1);
        int comp2 = Integer.compare(v2, o.v2);
        return comp == 0 ? comp2 : comp;
    }
}
