package bipartition.model;

public class SwapOffer {
    private final Vertex a;
    private final Vertex b;
    private final int cost;

    public SwapOffer(Vertex a, Vertex b, int cost) {
        this.a = a;
        this.b = b;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "SwapOffer{" +
                "a=" + a +
                ", b=" + b +
                ", sumCost=" + cost +
                '}';
    }

    public Vertex getA() {
        return a;
    }

    public Vertex getB() {
        return b;
    }

    public int getCost() {
        return cost;
    }
}