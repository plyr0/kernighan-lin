package bipartition;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private int id;
    private List<Integer> neighbours;
    private boolean lock = false;

    Vertex(int id) {
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

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    @Override
    public String toString() {
        return id + "-" + neighbours;
    }
}
