package bipartition;

public class MyEdge {
    private MyVertex v1;
    private MyVertex v2;
    private int weight;

    public MyEdge(MyVertex v1, MyVertex v2, int weight) {
        if (v1.getId() <= v2.getId()) {
            this.v1 = v1;
            this.v2 = v2;
        } else {
            this.v1 = v2;
            this.v2 = v1;
        }
        this.weight = weight;
    }

    public MyVertex getV1() {
        return v1;
    }

    public MyVertex getV2() {
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
}
