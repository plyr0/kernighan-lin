package bipartition;

public class MyEdge {
    private int v1;
    private int v2;
    private int weight;

    MyEdge(int v1, int v2, int weight) {
        if (v1 <= v2) {
            this.v1 = v1;
            this.v2 = v2;
        } else {
            this.v1 = v2;
            this.v2 = v1;
        }
        this.weight = weight;
    }

    int getV1() {
        return v1;
    }

    int getV2() {
        return v2;
    }

    int getWeight() {
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
}
