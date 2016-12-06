package bipartition;

import java.util.ArrayList;
import java.util.List;

public class MyVertex {
    private int id;
    private List<Integer> neighbours;

    MyVertex(int id) {
        this.id = id;
        neighbours = new ArrayList<>();
    }

    public void addNeighbour(int other) {
        neighbours.add(other);
    }

    public boolean hasNeighbour(int other) {
        return neighbours.contains(other);
    }

    public List<Integer> getNeighbours() {
        return neighbours;
    }

    int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + "+" + neighbours;
    }
}
