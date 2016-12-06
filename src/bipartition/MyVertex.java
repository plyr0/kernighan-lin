package bipartition;

public class MyVertex {
    private int id;

    MyVertex(int id) {
        this.id = id;
    }

    int getId() {
        return id;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
