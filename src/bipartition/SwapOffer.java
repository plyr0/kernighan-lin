package bipartition;

public class SwapOffer {
    public Vertex a;
    public Vertex b;
    public int cost;

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
}